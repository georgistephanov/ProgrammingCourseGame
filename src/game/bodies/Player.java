package game.bodies;

import city.cs.engine.*;
import game.buildingblocks.Coin;
import game.buildingblocks.Edge;
import org.jbox2d.common.Vec2;

public class Player extends Walker implements StepListener, CollisionListener {

	private static final Shape playerShape = new PolygonShape(1.7485f,-3.2175f,
			1.6965f,2.366f, 1.5015f,2.457f, -0.8125f,1.846f, -1.69f,0.871f,
			-1.7745f,-1.976f, -1.339f,-3.2305f);

	private PlayerMovement lastMovingDirection = PlayerMovement.NOT_MOVING;
	private enum PlayerMovement {
		LEFT, RIGHT, NOT_MOVING, JUMPING, FALLING;
	}
	private int imageIndex = 1;

	private int coins = 0;

	public Player(World world) {
		this(world, playerShape);
	}

	private Player(World world, Shape shape) {
		super(world, shape);
		addImage(new BodyImage("data/player_stand.png", 6.5f));
		setPosition(new Vec2(-45, -20));
		addCollisionListener(this);
		setGravityScale(2);
		world.addStepListener(this);
	}

	public int getCoins() {
		return coins;
	}
	public void setCoins(int coins) {
		this.coins = coins;
		System.out.println("Coins: " + coins);

		// TODO: Add an observer to notify the world to display the correct amount of coins
	}

	@Override
	public void startWalking(float speed) {
		Vec2 velocity = getLinearVelocity();
		float initialForce = 0;

		if (speed > 0) {
			initialForce = 15;
			changeImage(PlayerMovement.RIGHT);
		}
		else if (speed < 0) {
			initialForce = -15;
			changeImage(PlayerMovement.LEFT);
		}

		float velocityChange;
		if ( (speed < 0 && velocity.x > 0) || (speed > 0 && velocity.x < 0) )  {
			velocityChange = initialForce;
		} else {
			velocityChange = initialForce - velocity.x;
		}

		float force = getMass() * velocityChange / (1/60f);
		applyForce(new Vec2(force, 0));
	}

	@Override
	public void stopWalking() {
		float force = getMass() * -(getLinearVelocity().x) / (1/60f);
		applyForce(new Vec2(force, 0));
	}

	@Override
	public void jump(float speed) {
		if (getLinearVelocity().y == 0) {
			applyImpulse(new Vec2(0, getMass() * speed));
		}
	}

	@Override
	public void preStep(StepEvent stepEvent) { }

	@Override
	public void postStep(StepEvent stepEvent) {
		float y = getLinearVelocity().y;
		float x = getLinearVelocity().x;

		if (y > 0) {
			changeImage(PlayerMovement.JUMPING);
		} else if (y < 0) {
			changeImage(PlayerMovement.FALLING);
		} else if (x == 0) {
			changeImage(PlayerMovement.NOT_MOVING);
		}
	}

	private void changeImage(PlayerMovement direction) {
		BodyImage newImg = null;

		switch (direction) {
			case RIGHT:
			case LEFT:
				if (imageIndex++ % 2 == 0) {
					newImg = new BodyImage("data/player_walk2.png", 6.5f);
				} else {
					newImg = new BodyImage("data/player_walk1.png", 6.5f);
				}
				lastMovingDirection = direction;
				break;
			case NOT_MOVING:
				imageIndex = 1;
				newImg = new BodyImage("data/player_stand.png", 6.5f);
				break;
			case JUMPING:
				newImg = new BodyImage("data/player_jump.png", 6.5f);
				break;
			case FALLING:
				newImg = new BodyImage("data/player_fall.png", 6.5f);
				break;
		}

		removeAllImages();

		if (direction == PlayerMovement.LEFT || lastMovingDirection == PlayerMovement.LEFT) {
			addImage(newImg).flipHorizontal();
		} else {
			addImage(newImg);
		}
	}

	public void collide(CollisionEvent e) {
		if ( e.getOtherBody() instanceof Enemy ) {
			System.out.println("Ouch");
		} else if (e.getOtherBody() instanceof Coin) {
			e.getOtherBody().destroy();
			setCoins(++coins);
		}
	}
}
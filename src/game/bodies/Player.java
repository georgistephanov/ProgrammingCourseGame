package game.bodies;

import city.cs.engine.*;
import game.buildingblocks.Coin;
import game.buildingblocks.Door;
import game.images.WalkerImageManager;
import org.jbox2d.common.Vec2;
import static game.GameConstants.MovementDirections.*;

public class Player extends Walker implements StepListener, CollisionListener {

	private static final Shape playerShape = new PolygonShape(1.7485f,-3.2175f,
			1.6965f,2.366f, 1.5015f,2.457f, -0.8125f,1.846f, -1.69f,0.871f,
			-1.7745f,-1.976f, -1.339f,-3.2305f);

	private static BodyImage standingImage = new BodyImage("data/player_stand.png", 6.5f);
	private static BodyImage jumpingImage = new BodyImage("data/player_jump.png", 6.5f);
	private static BodyImage fallingImage = new BodyImage("data/player_fall.png", 6.5f);
	private static BodyImage [] walkingImages = {
			new BodyImage("data/player_walk1.png", 6.5f),
			new BodyImage("data/player_walk2.png", 6.5f)
	};

	private int coins = 0;
	private static WalkerImageManager imageManager;

	public Player(World world) {
		this(world, playerShape);

		imageManager = new WalkerImageManager(this, standingImage, walkingImages, jumpingImage, fallingImage);
		imageManager.display();
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
			imageManager.changeImage(RIGHT);
		}
		else if (speed < 0) {
			initialForce = -15;
			imageManager.changeImage(LEFT);
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
			imageManager.changeImage(JUMPING);
		} else if (y < 0) {
			imageManager.changeImage(FALLING);
		} else if (x == 0) {
			imageManager.changeImage(NOT_MOVING);
		}
	}

	public void collide(CollisionEvent e) {
		if ( e.getOtherBody() instanceof Enemy ) {
			System.out.println("Ouch");
		} else if (e.getOtherBody() instanceof Coin) {
			e.getOtherBody().destroy();
			setCoins(++coins);
		} else if (e.getOtherBody() instanceof Door) {
			System.out.println("You won!");
		}
	}
}
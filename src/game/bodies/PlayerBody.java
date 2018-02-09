package game.bodies;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class PlayerBody extends Walker implements StepListener {

	private static final Shape playerShape = new PolygonShape(1.7485f,-3.2175f,
			1.6965f,2.366f, 1.5015f,2.457f, -0.8125f,1.846f, -1.69f,0.871f,
			-1.7745f,-1.976f, -1.339f,-3.2305f);

	private int imageIndex = 1;

	private PlayerMovement lastMovingDirection = PlayerMovement.NOT_MOVING;
	private enum PlayerMovement {
		LEFT, RIGHT, NOT_MOVING, JUMPING, FALLING
	}

	public PlayerBody(World w) {
		this(w, playerShape);
	}

	private PlayerBody(World w, Shape s) {
		super(w, s);
		addImage(new BodyImage("data/player_stand.png", 6.5f));
		setPosition(new Vec2(-45, -20));
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

		changeImage(PlayerMovement.NOT_MOVING);
	}

	@Override
	public void jump(float speed) {
		if (getLinearVelocity().y == 0) {
			applyImpulse(new Vec2(0, getMass() * speed));
		}
		changeImage(PlayerMovement.JUMPING);
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
}

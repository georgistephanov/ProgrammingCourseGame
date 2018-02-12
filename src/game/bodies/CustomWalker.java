package game.bodies;

import city.cs.engine.BodyImage;
import city.cs.engine.Shape;
import city.cs.engine.Walker;
import city.cs.engine.World;
import game.images.WalkerImageManager;
import org.jbox2d.common.Vec2;

import static game.GameConstants.MovementDirections.LEFT;
import static game.GameConstants.MovementDirections.RIGHT;

/**
 * Todo: Implement this with the Builder pattern
 */
public class CustomWalker extends Walker {
	static WalkerImageManager imageManager;

	CustomWalker(World world, Shape shape,
				 BodyImage standingImage, BodyImage [] walkingImages,
				 BodyImage jumpingImage, BodyImage fallingImage) {
		super(world, shape);

		imageManager = new WalkerImageManager(this, standingImage, walkingImages, jumpingImage, fallingImage);
		imageManager.display();
	}

	@Override
	public void startWalking(float speed) {
		Vec2 velocity = getLinearVelocity();
		float initialForce = 0;

		if (speed > 0) {
			initialForce = 15;
			imageManager.changeImage(RIGHT);
		} else if (speed < 0) {
			initialForce = -15;
			imageManager.changeImage(LEFT);
		}

		float velocityChange;
		if ( (speed < 0 && velocity.x > 0) || (speed > 0 && velocity.x < 0) )  {
			// If the movement direction has changed to the opposite direction before the player completely stopped
			// set its velocity change to the initial force in order not to inherit the velocity from the opposite movement
			velocityChange = initialForce;
		} else {
			velocityChange = initialForce - velocity.x;
		}

		float force = getMass() * velocityChange / (1/60f);			// F = mv/t
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
}

package bodies;

import city.cs.engine.*;
import game.GameSounds;
import gui.imagemanagers.WalkerImageManager;
import org.jbox2d.common.Vec2;

/**
 * A wrapper around the {@code Walker} class.
 * Overrides the {@code startWalking()}, {@code stopWalking()} and {@code jump()} methods to provide an out-of-the-box
 * consistent movement functionality for this game.
 * This class provides a static builder which is to be used when creating an object and setting its image resources.
 */
public class CustomWalker extends Walker {
	/**
	 * {@code WalkerImageManager} instance responsible for displaying and updating this object's images based on the
	 * movement state of this object in real time.
	 */
	protected WalkerImageManager imageManager;

	/**
	 * Constructs this object and initialises the {@code imageManager} object.
	 *
	 * @param builder  the builder object with the world, shape and all necessary images set up
	 */
	public CustomWalker(Builder builder) {
		super(builder.world, builder.shape);

		imageManager = new WalkerImageManager(this, builder.standingImage,
				builder.walkingImages, builder.jumpingImage, builder.fallingImage, builder.cheerImages);
		imageManager.display();
	}

	/**
	 * Implementation of the Builder Design Pattern used to create an object with several optional fields.
	 */
	public static class Builder {
		// Required parameters
		public World world;
		private Shape shape;

		// Optional parameters
		private BodyImage standingImage = null;
		private BodyImage jumpingImage = null;
		private BodyImage fallingImage = null;
		private BodyImage [] walkingImages = null;
		private BodyImage [] cheerImages = null;

		public Builder(World world, Shape shape) {
			this.world = world;
			this.shape = shape;
		}

		public Builder standingImage(BodyImage standingImage) {
			this.standingImage = standingImage;
			return this;
		}

		public Builder walkingImages(BodyImage [] walkingImages) {
			this.walkingImages = walkingImages;
			return this;
		}

		public Builder jumpingImage(BodyImage jumpingImage) {
			this.jumpingImage = jumpingImage;
			return this;
		}

		public Builder fallingImage(BodyImage fallingImage) {
			this.fallingImage = fallingImage;
			return this;
		}

		public Builder cheerImages(BodyImage [] cheerImages) {
			this.cheerImages = cheerImages;
			return this;
		}

		public CustomWalker build() {
			return new CustomWalker(this);
		}
	}

	/**
	 * Moves this object via applying force to the centre of its body.
	 * The force is calculated by the formula F = m * v / t.
	 * The mass in the formula is this object's body mass, the velocity is based on the speed passed as a parameter
	 * and the time is 1/60.
	 * Overrides the {@code Walker}'s method to provide a unique and consistent functionality for this game.
	 *
	 * @param speed  the speed with which this object should move
	 */
	@Override
	public void startWalking(float speed) {
		Vec2 velocity = getLinearVelocity();
		float velocityChange;

		if ( (speed < 0 && velocity.x > 0) || (speed > 0 && velocity.x < 0) )  {
			// If the movement direction has changed to the opposite direction before the player completely stopped
			// set its velocity change to the initial force in order not to inherit the velocity from the opposite movement
			velocityChange = speed;
		} else {
			velocityChange = speed - velocity.x;
		}

		// Calculate and apply the force with which the object is going to move
		// Change getMass() method to 1 if it gives a compilation error, as when my code was marked for increment 1 and 2
		// on the tutor's laptop it couldn't compile for some reason. Default dynamic body's mass is 1.
		float force = getMass() * velocityChange / (1/60f);			// F = mv/t
		applyForce(new Vec2(force, 0));
	}

	/**
	 * Applies force in the negative direction of this body's current linear velocity on the X axis in order to stop it.
	 * Overrides the {@code Walker}'s method to provide a unique and consistent functionality for this game.
	 */
	@Override
	public void stopWalking() {
		// Change getMass() method to 1 if it gives a compilation error, as when my code was marked for increment 1 and 2
		// on the tutor's laptop it couldn't compile for some reason. Default dynamic body's mass is 1.
		float force = getMass() * -(getLinearVelocity().x) / (1/60f);
		applyForce(new Vec2(force, 0));
	}

	/**
	 * Applies an impulse on the Y axis to make this object jump.
	 * The power of the impulse is calculated as the product of this object's mass and the speed of the jump.
	 * Overrides the {@code Walker}'s method to provide a unique and consistent functionality for this game.
	 *
	 * @param speed  the speed with which this object to jump
	 */
	@Override
	public void jump(float speed) {
		if (getLinearVelocity().y == 0) {
			// Change getMass() method to 1 if it gives a compilation error, as when my code was marked for increment 1 and 2
			// on the tutor's laptop it couldn't compile for some reason. Default dynamic body's mass is 1.
			applyImpulse(new Vec2(0, getMass() * speed));

			// Play jump sound
			GameSounds.playJumpSound();
		}
	}

	/**
	 * Reduces the boilerplate code of creating a new {@code Vec2} object every time {@code setPosition} is called
	 * by providing a way of setting the position just by the X and Y coordinates.
	 *
	 * @param x  this object's position on the X axis
	 * @param y  this object's position on the X axis
	 */
	public void setPosition(float x, float y) {
		setPosition(new Vec2(x, y));
	}
}

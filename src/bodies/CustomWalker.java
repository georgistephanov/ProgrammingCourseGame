package bodies;

import city.cs.engine.*;
import imagemanagers.WalkerImageManager;
import org.jbox2d.common.Vec2;

public class CustomWalker extends Walker {
	protected WalkerImageManager imageManager;

	public CustomWalker(Builder builder) {
		super(builder.world, builder.shape);

		imageManager = new WalkerImageManager(this, builder.standingImage,
				builder.walkingImages, builder.jumpingImage, builder.fallingImage);
		imageManager.display();
	}

	public static class Builder {
		// Required parameters
		public World world;
		private Shape shape;

		// Optional parameters
		private BodyImage standingImage = null;
		private BodyImage jumpingImage = null;
		private BodyImage fallingImage = null;
		private BodyImage [] walkingImages = null;

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

		public CustomWalker build() {
			return new CustomWalker(this);
		}
	}

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

	public void setPosition(float x, float y) {
		setPosition(new Vec2(x, y));
	}
}

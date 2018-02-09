package game.bodies;

import city.cs.engine.*;
import game.buildingblocks.Edge;
import game.buildingblocks.Wall;
import org.jbox2d.common.Vec2;

public abstract class Enemy extends DynamicBody implements StepListener, CollisionListener {
	private float xVelocity = 10;
	private BodyImage [] images;
	private int currentStep = 0;
	private int stepsChangeImage = 1;
	private int imageIndex = 0;
	private boolean imageIndexUp = true;

	Enemy(World world, Shape shape, BodyImage image) {
		super(world, shape);
		addImage(image);
		addCollisionListener(this);
		setGravityScale(2);
		world.addStepListener(this);
	}

	public void setVelocity(float xVelocity) {
		this.xVelocity = xVelocity;
	}

	void setWalkImages(BodyImage... images) {
		if (images.length > 0) {
			this.images = images;
		}
	}

	public void setXDirection(float xDirection) {
		if ( (xDirection < 0 && xVelocity > 0) ||
				(xDirection > 0 && xVelocity < 0)) {

			xVelocity *= -1;
		}
	}

	void setStepsChangeImage(int stepsChangeImage) {
		this.stepsChangeImage = stepsChangeImage;
	}

	public void collide(CollisionEvent e) {
		if (e.getOtherFixture() instanceof Wall || e.getOtherFixture() instanceof Edge) {
			xVelocity *= -1;
		}
	}

	@Override
	public void preStep(StepEvent stepEvent) {
		setLinearVelocity(new Vec2(xVelocity, getLinearVelocity().y));
		changeImage();
	}

	@Override
	public void postStep(StepEvent stepEvent) { }

	private void changeImage() {
		// Do not change the picture if it's not the correct number of steps yet
		if (currentStep++ < stepsChangeImage || images == null || images.length == 0) {
			return;
		}

		// Reset the current step
		currentStep = 0;

		// Remove all current images, as overlapping images for characters are not needed
		removeAllImages();

		// If all images have been displayed consecutively, start displaying them backwards again
		if (imageIndex >= images.length - 1) {
			imageIndexUp = false;
		} else if (imageIndex == 0) {
			imageIndexUp = true;
		}

		// Add the image to the DynamicBody
		if (imageIndexUp) {
			addImage(images[imageIndex++]);
		} else {
			addImage(images[imageIndex--]);
		}

		// If the character is moving left -> flip the image to make it look left
		if (xVelocity < 0) {
			getImages().get(0).flipHorizontal();
		}
	}
}

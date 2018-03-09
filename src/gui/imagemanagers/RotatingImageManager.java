package gui.imagemanagers;

import city.cs.engine.Body;
import city.cs.engine.BodyImage;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;

/**
 * Concrete implementation of the {@code AbstractImageManager} for images which rotate in one position.
 * This class is used for collectibles and teleports.
 */
public final class RotatingImageManager extends AbstractImageManager implements StepListener {

	/** This is a flag variable which controls whether the images should be flipped during the rotation. Default is {@code true}. */
	private boolean flip = true;

	public RotatingImageManager(Body body, BodyImage[] images) {
		super(body, images);
	}

	/**
	 * Registers this {@code StepListener} to the world so that it starts displaying the body images.
	 */
	@Override
	public void display() {
		body.getWorld().addStepListener(this);
	}

	/**
	 * Sets the flip flag which controls whether the images should be flipped while rotating.
	 *
	 * @param flip  true if the images should be flipped on rotation
	 */
	public void setFlip(boolean flip) {
		this.flip = flip;
	}

	/**
	 * Changes the image after every several steps (set by the user or default 1).
	 *
	 * @param stepEvent  the current step's event
	 */
	@Override
	public void preStep(StepEvent stepEvent) {
		if (currentStep++ == stepCounter) {
			body.removeAllImages();

			if ((imageIndex >= images.length - 1 && imageUp) || (imageIndex == 0 && !imageUp)) {
				imageUp = !imageUp;
			}

			if (imageUp) {
				body.addImage(images[imageIndex++]);
			} else {
				if (flip) {
					body.addImage(images[imageIndex--]).flipHorizontal();
				} else {
					body.addImage(images[imageIndex--]);
				}
			}

			currentStep = 0;
		}
	}

	/**
	 * Empty implementation of the {@code StepListener} interface method.
	 *
	 * @param stepEvent  the current step's event
	 */
	@Override
	public void postStep(StepEvent stepEvent) { }
}

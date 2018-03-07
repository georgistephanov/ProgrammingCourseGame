package bodies.gui.imagemanagers;

import city.cs.engine.Body;
import city.cs.engine.BodyImage;

/**
 * This class provides an abstract implementation of the {@code ImageManager} interface.
 * It will be sub-classed by the different kinds of concrete image managers.
 */
public abstract class AbstractImageManager implements ImageManager {
	final Body body;
	BodyImage[] images;

	/** The current game step modulo the amount of steps between image changes. Range: [0, stepCounter)
	 *  This variable is used when determining when to change the image after every (stepCounter) steps.
	 */
	int currentStep = 0;

	/** The amount of game steps between image changes. */
	int stepCounter = 1;

	/** Used as an image index in the range [0, images.length) to display the images from the array consecutively. */
	int imageIndex = 0;

	/** Used as a flag variable to keep track of whether to increment or decrement the imageIndex variable. */
	boolean imageUp = true;

	AbstractImageManager(Body body, BodyImage [] images) {
		this.body = body;
		this.images = images;
	}

	/**
	 * Sets the amount of game steps between image changes.
	 *
	 * @param stepCounter  amount of game steps
	 */
	public void setStepCounter(int stepCounter) {
		this.stepCounter = stepCounter;
	}

	@Override
	public abstract void display();
}

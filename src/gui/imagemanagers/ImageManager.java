package gui.imagemanagers;

/**
 * Interface implementing the Strategy pattern for displaying body images
 */
public interface ImageManager {
	/**
	 * Displays the main image of a body (normally the main image is the one which shows the object in its standing position).
	 */
	void display();

	/**
	 * Sets the amount of game steps between image changes.
	 *
	 * @param steps  amount of game steps
	 */
	void setStepCounter(int steps);
}

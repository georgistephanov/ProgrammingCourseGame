package bodies.gui.imagemanagers;

/**
 * Interface implementing the Strategy pattern for displaying body images
 */
public interface ImageManager {
	/**
	 * Displays the main image of a body (normally the main image is the one which shows the object in its standing position).
	 */
	void display();
}

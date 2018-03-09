package game;

/**
 * This class keeps all game constants.
 */
public final class GameConstants {
	/**
	 * The width of the game window
	 */
	public static final int WINDOW_WIDTH = 1920;

	/**
	 * The height of the game window
	 */
	public static final int WINDOW_HEIGHT = 1080;

	/**
	 * The title of the game window
	 */
	static final String WINDOW_TITLE = "Basic world";

	/**
	 * The default velocity
	 */
	static final int VELOCITY = 15;

	/**
	 * The default jump speed
	 */
	static final int JUMP_SPEED = 20;

	/**
	 * The directions of movement.
	 */
	public enum MovementDirections {
		LEFT, RIGHT, NOT_MOVING, JUMPING, FALLING, CHEER
	}
}
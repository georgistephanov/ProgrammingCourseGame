package game;

public class GameConstants {
	static int WINDOW_WIDTH = 1920;
	static int WINDOW_HEIGHT = 1080;
	static String WINDOW_TITLE = "Basic world";

	static int VELOCITY = 15;
	static int JUMP_SPEED = 20;

	public enum MovementDirections {
		LEFT, RIGHT, NOT_MOVING, JUMPING, FALLING;
	}
}
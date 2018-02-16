package buildingblocks;

import bodies.Player;
import bodies.collectibles.CustomSensor;
import city.cs.engine.*;

/**
 * The door which the player aims to reach in order to win the game.
 */
public class Door extends CustomSensor {

	private static final Shape DOOR_SHAPE = new PolygonShape(-2.75f,-3.48f, -2.76f,1.41f, -2.06f,2.66f, -0.83f,3.49f,
			0.86f,3.47f, 1.9f,2.8f, 2.76f,1.54f, 2.75f,-3.48f);
	private static final BodyImage DOOR_IMAGE = new BodyImage("data/other/door.png", 7);

	/**
	 * Flag variable indicating whether the player is currently in a contact with the door.
	 */
	public static boolean userEntered = false;

	/**
	 * Constructs this object and sets its image.
	 *
	 * @param world  the world in which to be created
	 */
	public Door(World world) {
		super(new StaticBody(world), DOOR_SHAPE);
		getBody().addImage(DOOR_IMAGE);
	}

	/**
	 * Sets the {@code userEntered} variable to {@code true} when the player gets in a contact with the door.
	 *
	 * @param e  the contact event
	 */
	@Override
	public void beginContact(SensorEvent e) {
		if (e.getContactBody() instanceof Player) {
			System.out.println("Press SPACE to win...");
			userEntered = true;
		}
	}

	/**
	 * Sets the {@code userEntered} variable to {@code false} when the player gets in a contact with the door.
	 *
	 * @param e  the contact event
	 */
	@Override
	public void endContact(SensorEvent e) {
		if (e.getContactBody() instanceof Player) {
			userEntered = false;
		}
	}
}

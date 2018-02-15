package buildingblocks;

import bodies.Player;
import bodies.collectibles.CustomSensor;
import city.cs.engine.*;

public class Door extends CustomSensor {

	private static Shape doorShape = new PolygonShape(-2.75f,-3.48f, -2.76f,1.41f, -2.06f,2.66f, -0.83f,3.49f,
			0.86f,3.47f, 1.9f,2.8f, 2.76f,1.54f, 2.75f,-3.48f);
	private static BodyImage doorImage = new BodyImage("data/other/door.png", 7);

	public static boolean userEntered = false;

	public Door(World world) {
		super(new StaticBody(world), doorShape);
		getBody().addImage(doorImage);
	}

	public void beginContact(SensorEvent e) {
		if (e.getContactBody() instanceof Player) {
			System.out.println("Press SPACE to win...");
			userEntered = true;
		}
	}
	public void endContact(SensorEvent e) {
		if (e.getContactBody() instanceof Player) {
			userEntered = false;
		}
	}
}

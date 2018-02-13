package buildingblocks;

import city.cs.engine.*;

public class Door extends StaticBody {

	private static Shape doorShape = new PolygonShape(-2.75f,-3.48f, -2.76f,1.41f, -2.06f,2.66f, -0.83f,3.49f,
			0.86f,3.47f, 1.9f,2.8f, 2.76f,1.54f, 2.75f,-3.48f);
	private static BodyImage doorImage = new BodyImage("data/door.png", 7);

	public Door(World world) {
		super(world, doorShape);
		addImage(doorImage);
	}
}

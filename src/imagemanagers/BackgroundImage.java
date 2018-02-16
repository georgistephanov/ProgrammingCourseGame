package imagemanagers;

import city.cs.engine.*;

/**
 * This class is used to display the background image of the world.
 */
public class BackgroundImage extends GhostlyFixture implements ImageManager {

	private static BodyImage backgroundImage = new BodyImage("data/other/background.png", 80);

	public BackgroundImage(World world) {
		super(new StaticBody(world), new BoxShape(1, 1));
	}

	@Override
	public void display() {
		getBody().addImage(backgroundImage);
	}
}
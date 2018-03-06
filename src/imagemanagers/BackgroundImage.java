package imagemanagers;

import city.cs.engine.*;

/**
 * This class is used to display the background image of the world.
 */
public final class BackgroundImage extends GhostlyFixture {

	private static final BodyImage BACKGROUND_IMAGE = new BodyImage("data/other/background.png", 80);

	public BackgroundImage(World world) {
		super(new StaticBody(world), new BoxShape(1, 1));
	}

	public void displayLevelBackgroundImage(int level) {
		switch (level) {
			case 1:
				getBody().addImage(BACKGROUND_IMAGE);
				break;
			case 2:
				getBody().addImage(BACKGROUND_IMAGE);
				break;
		}
	}
}
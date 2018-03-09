package gui.imagemanagers;

import city.cs.engine.*;

import java.security.InvalidParameterException;

/**
 * This class is used to display the background image of the world.
 */
public final class BackgroundImage extends GhostlyFixture {

	private static final Shape BACKGROUND_IMAGE_BODY_SHAPE = new BoxShape(1, 1);
	private static final BodyImage BACKGROUND_LEVEL_1 = new BodyImage("data/other/background_1.png", 80);
	private static final BodyImage BACKGROUND_LEVEL_2 = new BodyImage("data/other/background_2.png", 80);
	private static final BodyImage BACKGROUND_LEVEL_3 = new BodyImage("data/other/background_3.png", 80);
	private static final BodyImage GAME_OVER = new BodyImage("data/other/game_over.png", 80);

	public BackgroundImage(World world) {
		super(new StaticBody(world), BACKGROUND_IMAGE_BODY_SHAPE);
	}

	/**
	 * Displays a background image based on the level.
	 *
	 * @param levelNumber  for which level to display the background image
	 * @throws InvalidParameterException if there is no such {@code levelNumber} implemented
	 */
	public void displayLevelBackgroundImage(int levelNumber) {
		switch (levelNumber) {
			case 1:
				getBody().addImage(BACKGROUND_LEVEL_1);
				break;
			case 2:
				getBody().addImage(BACKGROUND_LEVEL_2);
				break;
			case 3:
				getBody().addImage(BACKGROUND_LEVEL_3);
				break;
			default:
				throw new InvalidParameterException("This game supports only levels 1, 2 and 3");
		}
	}

	/**
	 * Displays the game over image.
	 */
	public void displayGameOverImage() {
		getBody().addImage(GAME_OVER);
	}
}
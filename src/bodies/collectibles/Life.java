package bodies.collectibles;

import bodies.Player;
import city.cs.engine.*;
import game.GameSounds;
import gui.imagemanagers.SingleImageManager;

/**
 * This class represents the collectible lives in the game.
 */
public class Life extends Collectible {

	private static final Shape LIFE_SHAPE = new CircleShape(1);
	private static final BodyImage LIFE_IMAGE = new BodyImage("data/collectibles/life.png", 2.5f);

	/**
	 * Constructs this object and creates its {@code ImageManager} as a {@code SingleImageManager} object.
	 *
	 * @param world  the world in which this object will be created
	 */
	public Life(World world) {
		super(world, LIFE_SHAPE);

		imageManager = new SingleImageManager(getBody(), LIFE_IMAGE);
		imageManager.display();
	}

	/**
	 * Destroys this life and increments the player's amount of lives by one if the contact body is a {@code Player} instance.
	 * This method is called when a solid fixture begins to overlap with this coin.
	 *
	 * @param e  the contact event
	 */
	@Override
	public void beginContact(SensorEvent e) {
		if (e.getContactBody() instanceof Player) {
			((Player) e.getContactBody()).addLife();

			// Play the life collected sound
			GameSounds.playLifeSound();

			getBody().destroy();
			destroy();
		}
	}
}

package bodies.collectibles;

import bodies.Player;
import city.cs.engine.*;
import bodies.gui.imagemanagers.RotatingImageManager;

/**
 * This class represents the collectible coins in the game.
 */
public class Coin extends Collectible {

	private static final Shape COIN_SHAPE = new CircleShape(1);

	/**
	 * Array of {@code BodyImage} objects to represent the different images of the coin.
	 * The images represent the four states of rotation of each coin.
	 */
	private static final BodyImage [] COIN_IMAGES = {
			new BodyImage("data/collectibles/gold_1.png", 2.5f),
			new BodyImage("data/collectibles/gold_2.png", 2.5f),
			new BodyImage("data/collectibles/gold_3.png", 2.5f),
			new BodyImage("data/collectibles/gold_4.png", 2.5f)
	};

	/**
	 * Constructs this coin and sets its {@code ImageManager} as an instance of {@code RotatingImageManager}.
	 * Sets the amount of game steps after which an image change will occur to 6.
	 *
	 * @param world  the world in which this coin will be created
	 */
	public Coin(World world) {
		super(world, COIN_SHAPE);

		imageManager = new RotatingImageManager(getBody(), COIN_IMAGES);
		imageManager.setStepCounter(6);
		imageManager.display();
	}

	/**
	 * Destroys this coin and increments the player's collected coins if the contact body is a {@code Player} instance.
	 * This method is called when a solid fixture begins to overlap with this coin.
	 *
	 * @param e  the contact event
	 */
	@Override
	public void beginContact(SensorEvent e) {
		if (e.getContactBody() instanceof Player) {
			((Player) e.getContactBody()).addCoin();

			getBody().destroy();
			destroy();
		}
	}
}

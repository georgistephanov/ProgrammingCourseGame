package bodies.collectibles;

import bodies.Player;
import buildingblocks.UnlockableWall;
import city.cs.engine.*;
import city.cs.engine.Shape;
import bodies.gui.imagemanagers.SingleImageManager;

/**
 * This class represents the keys which should be collected in order to open {@code UnlockableDoor}.
 */
public class Key extends Collectible {

	private static final Shape KEY_SHAPE = new CircleShape(1.5f);
	private static final BodyImage KEY_IMAGE = new BodyImage("data/collectibles/key.png", 4.5f);

	private final UnlockableWall unlockableWall;

	/**
	 * Constructs this key and sets its {@code ImageManager} as an instance of {@code SingleImageManager}.
	 * Sets the {@code UnlockableDoor} to which the key is bounded, so that this door could be unlocked when the key is collected.
	 *
	 * @param world  the world in which this coin will be created
	 * @param unlockableWall  the door which should be unlocked when the key is collected
	 */
	public Key(World world, UnlockableWall unlockableWall) {
		super(world, KEY_SHAPE);

		imageManager = new SingleImageManager(getBody(), KEY_IMAGE);
		imageManager.display();

		this.unlockableWall = unlockableWall;
	}

	/**
	 * Destroys this and calls the {@code unlock()} method on the door associated with the key if the contact body is a {@code Player} instance.
	 * This method is called when a solid fixture begins to overlap with this coin.
	 *
	 * @param e  the contact event
	 */
	@Override
	public void beginContact(SensorEvent e) {
		if (e.getContactBody() instanceof Player) {
			unlockableWall.unlock();

			getBody().destroy();
			destroy();
		}
	}
}

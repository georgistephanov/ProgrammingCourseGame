package buildingblocks;

import bodies.Player;
import bodies.collectibles.CustomSensor;
import city.cs.engine.*;
import gui.imagemanagers.ImageManager;
import gui.imagemanagers.RotatingImageManager;
import org.jbox2d.common.Vec2;

/**
 * Represents main and destination objects through which the player can teleport.
 * When the {@code Player} object begins contact with the main teleport it gets teleported to the destination teleport.
 * It works as a one way teleportation only (i.e. the player can teleport from the main object to the destination but
 * not from the destination object to the main one).
 */
public class Teleport extends CustomSensor {

	private static final Shape TELEPORT_SHAPE = new BoxShape(1.5f, 2);

	private static final BodyImage [] BLUE_TELEPORT_IMAGES = {
			new BodyImage("data/other/blue_teleport_1.png", 5),
			new BodyImage("data/other/blue_teleport_2.png", 5),
			new BodyImage("data/other/blue_teleport_3.png", 5)
	};

	private static final BodyImage [] ORANGE_TELEPORT_IMAGES = {
			new BodyImage("data/other/orange_teleport_1.png", 5),
			new BodyImage("data/other/orange_teleport_2.png", 5),
			new BodyImage("data/other/orange_teleport_3.png", 5)
	};

	/**
	 * Enumeration representing the possible colours that the teleport can be.
	 */
	public enum TeleportColor {
		BLUE, ORANGE
	}

	/**
	 * The destination object to which the {@code Player} object is being sent when it gets in contact with the main
	 * teleport object.
	 */
	private CustomSensor destinationTeleport;

	/**
	 * Constructs the main teleport object (this object) and its destination object.
	 * Creates two {@code ImageManager} objects (instances of {@code RotatingImageManager} for both objects and sets their properties.
	 *
	 * @param world  the world in which to be created
	 */
	public Teleport(World world, TeleportColor color) {
		super(new StaticBody(world), TELEPORT_SHAPE);

		// Create the second teleport bounded to this one
		destinationTeleport = new CustomSensor(new StaticBody(world), TELEPORT_SHAPE);

		BodyImage [] images;
		if (color == TeleportColor.BLUE) {
			images = BLUE_TELEPORT_IMAGES;
		} else {
			images = ORANGE_TELEPORT_IMAGES;
		}

		// Set the image managers for both teleports
		ImageManager imageManager = new RotatingImageManager(getBody(), images);
		imageManager.setStepCounter(10);
		((RotatingImageManager) imageManager).setFlip(false);
		imageManager.display();

		ImageManager destinationImageManager = new RotatingImageManager(destinationTeleport.getBody(), images);
		destinationImageManager.setStepCounter(10);
		((RotatingImageManager) destinationImageManager).setFlip(false);
		destinationImageManager.display();

	}

	/**
	 * Sets the position of the teleport which is bounded to this object.
	 *
	 * @param x  the destinationTeleport teleport's position on the X axis
	 * @param y  the destinationTeleport teleport's position on the Y axis
	 */
	public void setDestinationPosition(float x, float y) {
		destinationTeleport.setPosition(x, y);
	}

	/**
	 * Sets the {@code playerInside} flag variable to {@code true} when the player gets in contact with this object.
	 *
	 * @param e  the contact event
	 */
	@Override
	public void beginContact(SensorEvent e) {
		if (e.getContactBody() instanceof Player) {
			Vec2 destinationTeleportPosition = destinationTeleport.getBody().getPosition();
			((Player) e.getContactBody()).setPosition(destinationTeleportPosition.x, destinationTeleportPosition.y);
		}
	}
}

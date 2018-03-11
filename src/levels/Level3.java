package levels;

import bodies.Player;
import bodies.collectibles.Coin;
import bodies.enemies.FlyMan;
import bodies.enemies.SpikeMan;
import bodies.enemies.WingMan;
import bodies.enemies.Zombie;
import buildingblocks.Platform;
import buildingblocks.Teleport;
import buildingblocks.Wall;
import city.cs.engine.World;

import static game.GameConstants.MovementDirections.LEFT;
import static game.GameConstants.MovementDirections.RIGHT;

/**
 * Concrete implementation of the {@code AbstractLevel} abstract class for the third level of the game.
 */
public class Level3 extends AbstractLevel {

	/**
	 * Creates this object and positions the player and the door.
	 *
	 * @param world  the world in which to generate this level
	 * @param player  the player associated with the game
	 */
	Level3(World world, Player player) {
		super(world, 3);

		// Set the door and the player's positions
		player.setPosition(-45, -20);
		door.setPosition(14.5f, -21.5f);
	}

	@Override
	public void displayPlatforms() {
		super.displayPlatforms();

		// Orange teleport
		Teleport orangeTeleport = new Teleport(world, Teleport.TeleportColor.ORANGE);
		orangeTeleport.setPosition(-44, 23);
		orangeTeleport.setDestinationPosition(-31, -22.5f);

		// Blue teleport
		Teleport blueTeleport = new Teleport(world, Teleport.TeleportColor.BLUE);
		blueTeleport.setPosition(44.25f, 20);
		blueTeleport.setDestinationPosition(44.25f, -22.5f);
	}

	@Override
	public void displayCollectibles() {
		super.displayCollectibles();

		// Left-hand side
		for (int i = 1; i <= 8; i++) {
			new Coin(world).setPosition(-30 + (i * 3.75f), -22.5f);
		}

		// Right-hand side
		for (int i = 1; i <= 5; i++) {
			new Coin(world).setPosition(15 + (i * 4.5f), 19.5f);
		}

		for (int i = 1; i <= 8; i++) {
			new Coin(world).setPosition(9 + (i * 3.75f), 1.5f);
		}

		for (int i = 0; i < 30; i++) {
			new Coin(world).setPosition(19 + (i % 10 * 2.4f), -23.25f + (i / 10) * 2);
		}
	}
}

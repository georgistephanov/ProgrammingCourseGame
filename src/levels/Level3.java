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
		super(world);

		player.setPosition(-45, -20);
		door.setPosition(14.5f, -21.5f);

		levelLabel.firePropertyChange("amount", levelLabel.getAmount(), 3);
	}

	@Override
	public void displayPlatforms() {
		// Left-hand side
		new Platform(world, 13.5f, .5f).setPosition(-33.5f, -8.5f);
		new Platform(world, 13.5f, .5f).setPosition(-33.5f, 1);
		new Platform(world, 5, .5f).setPosition(-6, 10);
		new Platform(world, 5, .5f).setPosition(-24, 15);
		new Platform(world, 3, .5f).setPosition(-44, 20);

		// Mid
		new Wall(world, 22.5f).setPosition(0, 4.5f);
		new Platform(world, 18, .5f).setPosition(-18, -17.5f);
		new Wall(world, 4).setPosition(-35, -21);

		// Right-hand side
		new Platform(world, 18.75f, .5f).setPosition(28.25f, -17);
		new Platform(world, 4, .5f).setPosition(43, -9);
		new Platform(world, 16, .5f).setPosition(26f, -1);
		new Platform(world, 4, .5f).setPosition(5, 7);
		new Platform(world, 15, .5f).setPosition(32, 17);
		new Wall(world, 4.5f).setPosition(10.5f, -21);

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
	public void displayEnemies() {
		// Left-hand side
		new WingMan(world).setPosition(-25, 23.75f);
		new SpikeMan(world).setPosition(-33, 2);
		new SpikeMan(world).setMovementDirection(LEFT).setPosition(-31, 2);
		new SpikeMan(world).setPosition(-33, -5);
		new Zombie(world).setPosition(-15, -13);

		// Right-hand side
		new WingMan(world).setMovementDirection(RIGHT).setPosition(35, 21);
		new FlyMan(world).setMovementDirection(LEFT).setPosition(27, 14);
		new FlyMan(world).setPosition(37, 14);
		new Zombie(world).setMovementDirection(LEFT).setPosition(26, -13);
		new Zombie(world).setPosition(30, -13);
	}

	@Override
	public void displayCollectibles() {
		// Left-hand side
		for (int i = 1; i <= 8; i++) {
			new Coin(world).setPosition(-30 + (i * 3.75f), -22.5f);
		}

		new Coin(world).setPosition(-6, 12.5f);
		new Coin(world).setPosition(-24, 17.5f);

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

		new Coin(world).setPosition(5, 9.5f);
		new Coin(world).setPosition(43.5f, -6.5f);
	}

	@Override
	public void resetLevel() {

	}
}

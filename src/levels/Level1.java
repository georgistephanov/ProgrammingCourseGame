package levels;

import bodies.Player;
import bodies.collectibles.Coin;
import bodies.collectibles.Life;
import bodies.enemies.SpikeMan;
import bodies.enemies.WingMan;
import buildingblocks.Platform;
import buildingblocks.Wall;
import city.cs.engine.World;

import static game.GameConstants.MovementDirections.LEFT;

public class Level1 extends AbstractLevel {

	Level1(World world, Player player) {
		super(world, player);

		player.setPosition(-45, -20);
		door.setPosition(44.25f, -21.5f);
	}

	@Override
	public void displayPlatforms() {
		// Left-hand side platforms
		new Platform(world, 15, .5f).setPosition(-32, -12);
		new Platform(world, 9, .5f).setPosition(-38, 0);
		new Platform(world, 5, .5f).setPosition(-35, 9.5f);
		new Platform(world, 4, .5f).setPosition(-5, -18);
		new Platform(world, 4, .5f).setPosition(-5, -5);
		new Platform(world, 4, .5f).setPosition(-10, 13);

		// Mid wall
		new Wall(world, 20).setPosition(0, -5f);

		// Right-hand side platforms
		new Platform(world, 18, .5f).setPosition(29, 18);
		new Platform(world, 16, .5f).setPosition(17, 9);
		new Platform(world, 12, .5f).setPosition(35, 2);
		new Platform(world, 15, .5f).setPosition(16, -8);
		new Platform(world, 18, .5f).setPosition(29, -16);
	}

	@Override
	public void displayEnemies() {
		// Make SpikeMan enemies
		new SpikeMan(world).setPosition(-20, -23);
		new SpikeMan(world).setMovementDirection(LEFT).setPosition(-35, -11);
		new SpikeMan(world).setMovementDirection(LEFT).setPosition(20, -6);
		new SpikeMan(world).setPosition(20, -14);

		// Make WingMan enemies
		new WingMan(world).setMovementDirection(LEFT).setPosition(-10, 23);
		new WingMan(world).setPosition(10, 5.5f);
	}

	@Override
	public void displayCollectibles() {
		// Left-hand side collectibles
		new Coin(world).setPosition(-5, -2.5f);
		new Coin(world).setPosition(-44, -9.5f);
		new Coin(world).setPosition(-35, 11.5f);
		new Coin(world).setPosition(-10, 15.5f);

		// Right-hand side collectibles
		new Life(world).setPosition(45, 20);
		new Coin(world).setPosition(5, 11.5f);
		new Coin(world).setPosition(5, -5.5f);
		new Coin(world).setPosition(45, -13.5f);
		new Coin(world).setPosition(45, 4.5f);

	}
}

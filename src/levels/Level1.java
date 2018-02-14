package levels;

import bodies.Player;
import bodies.collectibles.Coin;
import bodies.collectibles.Life;
import bodies.enemies.Enemy;
import bodies.enemies.SpikeMan;
import bodies.enemies.WingMan;
import buildingblocks.Platform;
import buildingblocks.Wall;
import city.cs.engine.World;

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
		new Platform(world, 3, .5f).setPosition(-4, -18);
		new Platform(world, 3, .5f).setPosition(-4, -5);
		new Platform(world, 4, .5f).setPosition(-10, 13);

		// Mid wall
		new Wall(world, 20).setPosition(0, -5f);

		// Right-hand side platforms
		new Platform(world, 18, .5f).setPosition(29, 18);
	}

	@Override
	public void displayEnemies() {
		// Make SpikeMan enemies
		Enemy e1 = new SpikeMan(world);
		e1.setPosition(-20, -23);
		Enemy e2 = new SpikeMan(world);
		e2.setPosition(-35, -11);
		e2.setMovementDirection(-1);

		// Make WingMan enemies
		Enemy wingMan1 = new WingMan(world);
		wingMan1.setPosition(-10, 23);
		wingMan1.setMovementDirection(-1);
		Enemy wingMan2 = new WingMan(world);
		wingMan2.setPosition(10, 8);
	}

	@Override
	public void displayCollectibles() {
		new Coin(world).setPosition(-4, -16);
		new Coin(world).setPosition(-25, -23.5f);
		new Coin(world).setPosition(-35, 12);

		new Life(world).setPosition(45, 20);
	}
}

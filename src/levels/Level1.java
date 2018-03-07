package levels;

import bodies.Player;
import bodies.collectibles.Coin;
import bodies.collectibles.Life;
import bodies.enemies.SpikeMan;
import bodies.enemies.WingMan;
import buildingblocks.Platform;
import city.cs.engine.Body;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

import static game.GameConstants.MovementDirections.LEFT;

/**
 * Concrete implementation of the {@code AbstractLevel} abstract class for the first level of the game.
 */
public class Level1 extends AbstractLevel {

	/**
	 * Creates this object and positions the player and the door.
	 *
	 * @param world  the world in which to generate this level
	 * @param player  the player associated with the game
	 */
	Level1(World world, Player player) {
		super(world);

		player.setPosition(-45, -20);
		door.setPosition(44.25f, -21.5f);

		levelLabel.firePropertyChange("amount", levelLabel.getAmount(), 1);
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
//		new Wall(world, 20).setPosition(0, -5f);

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
		new Coin(world).setPosition(-5, -3f);
		new Coin(world).setPosition(-44, -10);
		new Coin(world).setPosition(-35, 11.5f);
		new Coin(world).setPosition(-10, 15);

		// Right-hand side collectibles
		new Life(world).setPosition(45, 20);
		new Coin(world).setPosition(5, 11f);
		new Coin(world).setPosition(5, -6);
		new Coin(world).setPosition(45, -14);
		new Coin(world).setPosition(45, 4);

		for (int i = 1; i <= 10; i++) {
			new Coin(world).setPosition(3.75f * i, -23.5f);
		}
	}

	@Override
	public void resetLevel() {
		for (Body body : world.getDynamicBodies()) {
			if ( !(body instanceof Player) ) {
				body.destroy();
			} else {
				((Player) body).setLinearVelocity(new Vec2(0, 0));
			}
		}

		displayEnemies();
	}
}
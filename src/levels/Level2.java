package levels;

import bodies.Player;
import bodies.collectibles.Coin;
import bodies.collectibles.Life;
import bodies.enemies.FlyMan;
import bodies.enemies.SpikeMan;
import buildingblocks.Platform;
import buildingblocks.UnlockableWall;
import buildingblocks.Wall;
import city.cs.engine.Body;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;
import java.awt.*;

import static game.GameConstants.MovementDirections.LEFT;
import static game.GameConstants.MovementDirections.RIGHT;

/**
 * Concrete implementation of the {@code AbstractLevel} abstract class for the second level of the game.
 */
public class Level2 extends AbstractLevel {

	/**
	 * Creates this object and positions the player and the door.
	 *
	 * @param world  the world in which to generate this level
	 * @param player  the player associated with the game
	 */
	Level2(World world, Player player) {
		super(world, player);

		// Update the level label on the status panel
		levelLabel.firePropertyChange("amount", levelLabel.getAmount(), 2);
	}

	@Override
	public void displayPlatforms() {
		// Left-hand side platforms
		new Platform(world, 8, .5f).setPosition(11, -15.5f);
		new Platform(world, 19, .5f).setPosition(-23.5f, -11);
		new Platform(world, 5, .5f).setPosition(0, -5);
		new Platform(world, 5, .5f).setPosition(-10, 0);
		new Platform(world, 14, .5f).setPosition(5, 10);
		new Platform(world, 5, .5f).setPosition(-30, 15);
		new Platform(world, 7, .5f).setPosition(-40, 20);

		new Wall(world, 20).setPosition(20, 4);

		// Right-hand side platforms
		new Platform(world, 4, .5f).setPosition(43, -16);
		new Platform(world, 5, .5f).setPosition(26, -9);
		new Platform(world, 10, .5f).setPosition(37, 0);
		new Platform(world, 7.5f, .5f).setPosition(39.5f, 9);
		new Platform(world, 5, .5f).setPosition(42, 18);


		UnlockableWall greenWall = new UnlockableWall(world, 6, Color.GREEN);
		greenWall.setPosition(20, -20);
		greenWall.setKeyPosition(-40, 23);

		UnlockableWall redWall = new UnlockableWall(world, .5f, 4.5f, Color.RED);
		redWall.setUnlockingType(UnlockableWall.Unlocking.HIDE);
		redWall.setPosition(39.5f, -21);
		redWall.setKeyPosition(43, 21);
	}

	@Override
	public void displayEnemies() {
		new FlyMan(world).setMovementDirection(LEFT).setPosition(-10, 23);

		new SpikeMan(world).setMovementDirection(RIGHT).setPosition(5, 12.5f);
		new SpikeMan(world).setMovementDirection(LEFT).setPosition(-25, -10);
		new SpikeMan(world).setMovementDirection(RIGHT).setPosition(-21, -10);
		new SpikeMan(world).setMovementDirection(LEFT).setPosition(-12, -23);
		new SpikeMan(world).setMovementDirection(RIGHT).setPosition(-8, -23);
		new SpikeMan(world).setPosition(38, 1);
	}

	@Override
	public void displayCollectibles() {
		new Life(world).setPosition(20, 25.5f);

		for (int i = 1; i <= 4; i++) {
			new Coin(world).setPosition(-12.5f + (7 * i), 12.5f);
		}

		for (int i = 1; i <= 8; i++) {
			new Coin(world).setPosition(-44 + (4.5f * i), -8.5f);
		}

		// Right-hand side
		for (int i = 1; i <= 3; i++) {
			new Coin(world).setPosition(30 + (5 * i), 11.5f);
		}

		for (int i = 1; i <= 2; i++) {
			new Coin(world).setPosition(18.5f + (5 * i), -6.5f);
		}

		for (int i = 1; i <= 3; i++) {
			new Coin(world).setPosition(20.25f + (5 * i), -23);
		}
	}
}

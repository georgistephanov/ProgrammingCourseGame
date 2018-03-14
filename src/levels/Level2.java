package levels;

import bodies.Player;
import bodies.collectibles.Coin;
import buildingblocks.UnlockableWall;
import city.cs.engine.World;
import java.awt.*;

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
		super(world, 2);

		// Set the door and the player's positions
		player.setPosition(-45, -20);
		door.setPosition(44.25f, -21.5f);
	}

	@Override
	public void displayPlatforms() {
		super.displayPlatforms();

		UnlockableWall greenWall = new UnlockableWall(world, 6, Color.GREEN);
		greenWall.setPosition(20, -20);
		greenWall.setKeyPosition(-40, 23);

		UnlockableWall redWall = new UnlockableWall(world, .5f, 4.5f, Color.RED);
		redWall.setUnlockingType(UnlockableWall.Unlocking.HIDE);
		redWall.setPosition(39.5f, -21);
		redWall.setKeyPosition(43, 21);
	}

	@Override
	public void displayCollectibles() {
		super.displayCollectibles();

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

		for (int i = 1; i <= 3; i++) {
			new Coin(world).setPosition(20.25f + (5 * i), -23);
		}
	}
}

package levels;

import bodies.Player;
import bodies.collectibles.Coin;
import bodies.collectibles.Life;
import bodies.enemies.SpikeMan;
import bodies.enemies.WingMan;
import city.cs.engine.World;
import gui.AmountJLabel;
import gui.StatusPanel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
		super(world, 1);

		// Set the door and the player's positions
		player.setPosition(-45, -20);
		door.setPosition(44.25f, -21.5f);
	}

	@Override
	public void displayCollectibles() {
		super.displayCollectibles();

		for (int i = 1; i <= 10; i++) {
			new Coin(world).setPosition(3.75f * i, -23.5f);
		}
	}
}
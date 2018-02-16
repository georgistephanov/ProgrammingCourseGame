package levels;

import bodies.Player;
import buildingblocks.Door;
import city.cs.engine.World;

/**
 * Abstract implementation of the Level class which keeps references to the {@code World},
 * {@code Player} and the {@code Door} objects associated with the level.
 */
abstract class AbstractLevel implements Level {
	World world;
	Player player;
	Door door;

	AbstractLevel(World world, Player player) {
		this.world = world;
		this.player = player;

		door = new Door(world);
	}

	@Override
	public abstract void displayPlatforms();

	@Override
	public abstract void displayEnemies();

	@Override
	public abstract void displayCollectibles();

	@Override
	public abstract void resetLevel();
}

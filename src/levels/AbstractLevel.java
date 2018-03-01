package levels;

import buildingblocks.Door;
import buildingblocks.Platform;
import city.cs.engine.World;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract implementation of the Level class which keeps references to the {@code World},
 * {@code Player} and the {@code Door} objects associated with the level.
 */
abstract class AbstractLevel implements Level {
	final World world;
	final Door door;

	AbstractLevel(World world) {
		this.world = world;
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

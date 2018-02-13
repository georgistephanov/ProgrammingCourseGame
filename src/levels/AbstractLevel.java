package levels;

import bodies.Player;
import buildingblocks.Door;
import city.cs.engine.World;

abstract class AbstractLevel implements Level {
	World world;
	Player player;
	Door door;

	AbstractLevel(World world, Player player) {
		this.world = world;
		this.player = player;
		door = new Door(world);
	}

	public abstract void displayPlatforms();
	public abstract void displayEnemies();
	public abstract void displayCollectibles();
}

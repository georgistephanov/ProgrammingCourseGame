package levels;

import bodies.Player;
import buildingblocks.Door;
import buildingblocks.Platform;
import buildingblocks.Wall;
import city.cs.engine.Body;
import city.cs.engine.BodyImage;
import city.cs.engine.World;
import game.StatusPanel;
import imagemanagers.BackgroundImage;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract implementation of the Level class which keeps references to the {@code World},
 * {@code Player} and the {@code Door} objects associated with the level.
 */
abstract class AbstractLevel implements Level {
	final World world;
	final Door door;
	final StatusPanel.CollectiblesJLabel levelLabel;

	AbstractLevel(World world) {
		this.world = world;

		levelLabel = StatusPanel.getInstance().getLevelLabel();

		for (Body body : world.getDynamicBodies()) {
			if (body instanceof Player)
				continue;

			body.destroy();
		}

		for (Body body : world.getStaticBodies()) {
			body.destroy();
		}

		// Ground
		new Platform(world, 50, 1).setPosition(0, -26);
		// Right and left walls
		new Wall(world, 28).setPosition(-48, 0);
		new Wall(world, 28).setPosition(48, 0);

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

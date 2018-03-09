package levels;

import bodies.Player;
import gui.AmountJLabel;
import buildingblocks.Door;
import buildingblocks.Platform;
import buildingblocks.Wall;
import city.cs.engine.Body;
import city.cs.engine.World;
import gui.StatusPanel;
import org.jbox2d.common.Vec2;

/**
 * Abstract implementation of the Level class which keeps references to the {@code World},
 * {@code Player} and the {@code Door} objects associated with the level.
 */
abstract class AbstractLevel implements Level {
	final World world;
	final Door door;
	final AmountJLabel levelLabel;

	AbstractLevel(World world, Player player) {
		this.world = world;

		levelLabel = StatusPanel.getInstance(world).getLevelLabel();

		// Destroy all present bodies except the player
		for (Body body : world.getDynamicBodies()) {
			if (body instanceof Player) {
				continue;
			}

			body.destroy();
		}
		for (Body body : world.getStaticBodies()) {
			body.destroy();
		}

		// Set the position of the ground platform and the walls
		new Platform(world, 50, 1).setPosition(0, -26);
		new Wall(world, 48).setPosition(-48, 20);
		new Wall(world, 48).setPosition(48, 20);


		// Set the player and the door at their default positions
		door = new Door(world);
		door.setPosition(44.25f, -21.5f);
		player.setPosition(-45, -20);
	}

	@Override
	public abstract void displayPlatforms();

	@Override
	public abstract void displayEnemies();

	@Override
	public abstract void displayCollectibles();

	@Override
	public void resetLevel() {
		for (Body body: world.getDynamicBodies()) {
			if ( !(body instanceof Player) ) {
				body.destroy();
			} else {
				((Player) body).setLinearVelocity(new Vec2(0, 0));
			}
		}

		displayEnemies();
	}
}

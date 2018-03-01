package levels;

import bodies.Player;
import buildingblocks.Platform;
import buildingblocks.UnlockableWall;
import buildingblocks.Wall;
import city.cs.engine.Body;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;
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
		super(world);

		player.setPosition(-45, -20);
		door.setPosition(44.25f, -21.5f);
	}

	@Override
	public void displayPlatforms() {
		new Platform(world, 7, .5f).setPosition(-40, 20);

		new Wall(world, 17.5f).setPosition(20, 2.5f);

		UnlockableWall wall = new UnlockableWall(world, 6, Color.GREEN);
		wall.setPosition(20, -19);
//		wall.setKeyPosition(-40, 23);
		wall.setKeyPosition(-30, -20);
	}

	@Override
	public void displayEnemies() {

	}

	@Override
	public void displayCollectibles() {

	}

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

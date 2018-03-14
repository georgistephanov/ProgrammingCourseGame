package bodies.factories;

import bodies.collectibles.Collectible;
import bodies.enemies.Enemy;
import buildingblocks.CustomSolidFixture;
import city.cs.engine.World;

/**
 * Static singleton factory for game object creation.
 */
public class StaticFactory {
	private static GameObjectFactory<CustomSolidFixture> platformFactory;
	private static GameObjectFactory<Enemy> enemyFactory;
	private static GameObjectFactory<Collectible> collectibleFactory;

	/**
	 * Initialises the factory.
	 *
	 * @param world  the world in which game objects will be created
	 */
	public static void initialise(World world) {
		platformFactory = new PlatformFactory(world);
		enemyFactory = new EnemyFactory(world);
		collectibleFactory = new CollectibleFactory(world);
	}

	/**
	 * Creates an enemy object based from the line of the level file.
	 *
	 * @param line  a line of a game level file (for more information see {@link GameObjectFactory}
	 * @return  the created enemy object
	 * @throws RuntimeException if this factory is not initialised
	 */
	public static Enemy createEnemy(String line) {
		if (enemyFactory == null) {
			throw new RuntimeException("StaticFactory must be initialised before being used.");
		}

		return enemyFactory.create(line);
	}

	/**
	 * Creates a {@code Platform} or a {@code Wall} object based from the line of the level file.
	 *
	 * @param line  a line of a game level file (for more information see {@link GameObjectFactory}
	 * @return  the created platform/wall
	 * @throws RuntimeException if this factory is not initialised
	 */
	public static CustomSolidFixture createPlatform(String line) {
		if (platformFactory == null) {
			throw new RuntimeException("StaticFactory must be initialised before being used.");
		}

		return platformFactory.create(line);
	}

	/**
	 * Creates a {@code Collectible} object based from the line of the level file.
	 *
	 * @param line  a line of a game level file (for more information see {@link GameObjectFactory}
	 * @return  the created collectible
	 * @throws RuntimeException if this factory is not initialised
	 */
	public static Collectible createCollectible(String line) {
		if (collectibleFactory == null) {
			throw new RuntimeException("StaticFactory must be initialised before being used.");
		}

		return collectibleFactory.create(line);
	}
}

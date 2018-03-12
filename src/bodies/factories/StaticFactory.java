package bodies.factories;

import bodies.collectibles.Collectible;
import bodies.enemies.Enemy;
import buildingblocks.CustomSolidFixture;
import city.cs.engine.World;

/**
 * Static factory for game object creation.
 */
public class StaticFactory {
	private static GameObjectFactory<CustomSolidFixture> platformFactory;
	private static GameObjectFactory<Enemy> enemyFactory;
	private static GameObjectFactory<Collectible> collectibleFactory;

	public static void initialise(World world) {
		platformFactory = new PlatformFactory(world);
		enemyFactory = new EnemyFactory(world);
		collectibleFactory = new CollectibleFactory(world);
	}

	public static Enemy createEnemy(String line) {
		if (enemyFactory == null) {
			throw new RuntimeException("StaticFactory must be initialised before being used.");
		}

		return enemyFactory.create(line);
	}

	public static CustomSolidFixture createPlatform(String line) {
		if (platformFactory == null) {
			throw new RuntimeException("StaticFactory must be initialised before being used.");
		}

		return platformFactory.create(line);
	}

	public static Collectible createCollectible(String line) {
		if (collectibleFactory == null) {
			throw new RuntimeException("StaticFactory must be initialised before being used.");
		}

		return collectibleFactory.create(line);
	}
}

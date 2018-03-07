package levels;

import bodies.Player;
import city.cs.engine.World;
import bodies.gui.imagemanagers.BackgroundImage;

public final class LevelManager {
	public static final int MIN_LEVEL = 1;
	public static final int MAX_LEVEL = 2;

	private static LevelManager instance;
	private static World world;

	private static LevelFactory levelFactory;
	private static int currentLevel;

	public static LevelManager getInstance() {
		if (instance == null) {
			throw new RuntimeException("LevelManager has to be initialised first before getting an instance of it");
		}

		return instance;
	}

	private LevelManager(World world, Player player) {
		LevelManager.world = world;
		levelFactory = LevelFactory.getInstance(world, player);
	}

	public static void initialiseLevelManager(World world, Player player) {
		instance = new LevelManager(world, player);
	}

	public static void displayLevel(int levelNumber) {
		if (levelNumber >= MIN_LEVEL && levelNumber <= MAX_LEVEL) {
			Level level = levelFactory.getLevel(levelNumber);

			level.displayPlatforms();
			level.displayEnemies();
			level.displayCollectibles();

			new BackgroundImage(world).displayLevelBackgroundImage(levelNumber);

			currentLevel = levelNumber;
		} else {
			throw new RuntimeException("The game supports only levels " + MIN_LEVEL + " through " + MAX_LEVEL);
		}
	}

	public static int getCurrentLevel() {
		return currentLevel;
	}
}

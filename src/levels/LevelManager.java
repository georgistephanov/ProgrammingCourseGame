package levels;

import bodies.Player;
import city.cs.engine.World;
import gui.imagemanagers.BackgroundImage;

public final class LevelManager {
	public static final int MIN_LEVEL = 1;
	public static final int MAX_LEVEL = 3;

	private static LevelManager instance;
	private World world;
	private Player player;

	private LevelFactory levelFactory;
	private int currentLevel;

	public static void initialiseLevelManager(World world, Player player) {
		instance = new LevelManager(world, player);
	}

	public static LevelManager getInstance() {
		if (instance == null) {
			throw new RuntimeException("LevelManager has to be initialised first before getting an instance of it");
		}

		return instance;
	}

	private LevelManager(World world, Player player) {
		levelFactory = LevelFactory.getInstance(world, player);
		this.world = world;
		this.player = player;
	}

	public void displayLevel(int levelNumber) {
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

	/**
	 * Restarts the game and sets the player's lives and coins to their default values.
	 */
	public void restartGame() {
		displayLevel(1);
		resetPlayerStats();
	}

	/**
	 * Implements the jumping between levels functionality and resets the player's lives and coins.
	 *
	 * @param level  the level to which to jump
	 */
	public void jumpToLevel(int level) {
		resetPlayerStats();
		displayLevel(level);
	}

	/**
	 * Resets teh player's stats to their initial values.
	 */
	private void resetPlayerStats() {
		player.resetLives();
		player.resetCoins();
	}

	public int getCurrentLevel() {
		return currentLevel;
	}
}

package levels;

import bodies.Player;
import city.cs.engine.Body;
import city.cs.engine.SoundClip;
import city.cs.engine.World;
import game.GameSounds;
import gui.StatusPanel;
import gui.imagemanagers.BackgroundImage;

/**
 * This is a singleton class that manages the game levels.
 */
public final class LevelManager {
	private static final int MIN_LEVEL = 1;
	public static final int MAX_LEVEL = 3;

	private static LevelManager instance;
	private final World world;
	private final Player player;

	private final LevelFactory levelFactory;
	private int currentLevel;

	/**
	 * Creates a singleton instance of this class.
	 * This method is needed to avoid passing 2 parameters to every {@code getInstance} method.
	 *
	 * @param world  the game world
	 * @param player  the game player
	 */
	public static void initialiseLevelManager(World world, Player player) {
		instance = new LevelManager(world, player);
	}

	/**
	 * Gets an instance object of this class.
	 *
	 * @return  the singleton object instance
	 * @throws RuntimeException if the instance object has not been yet initialised by the {@code initialiseLevelManager} method
	 */
	public static LevelManager getInstance() {
		if (instance == null) {
			throw new RuntimeException("LevelManager has to be initialised first before getting an instance of it");
		}

		return instance;
	}

	/**
	 * Private constructor to ensure the singleton object property.
	 *
	 * @param world  the game world
	 * @param player  the game player
	 */
	private LevelManager(World world, Player player) {
		levelFactory = LevelFactory.getInstance(world, player);
		this.world = world;
		this.player = player;
	}

	/**
	 * Generates and displays a game level.
	 *
	 * @param levelNumber  the level which to be generated and displayed
	 * @param resetPlayerStats  true if the player stats should be reset
	 * @throws RuntimeException  if there is no such {@code levelNumber} implemented
	 */
	public void displayLevel(int levelNumber, boolean resetPlayerStats) {
		if (levelNumber >= MIN_LEVEL && levelNumber <= MAX_LEVEL) {
			currentLevel = levelNumber;

			Level level = levelFactory.getLevel(levelNumber);
			level.displayPlatforms();
			level.displayEnemies();
			level.displayCollectibles();

			new BackgroundImage(world).displayLevelBackgroundImage(levelNumber);
			GameSounds.startBackgroundMusic(currentLevel);

			if (resetPlayerStats) {
				resetPlayerStats();
			}
		} else {
			throw new RuntimeException("The game supports only levels " + MIN_LEVEL + " through " + MAX_LEVEL);
		}
	}

	/**
	 * Restarts the game and sets the player's lives and coins to their default values.
	 */
	public void restartGame() {
		// Resume the game if it's paused
		StatusPanel statusPanel = StatusPanel.getInstance(world);
		if (!statusPanel.isGameRunning()) {
			statusPanel.clickPauseButton();
		}
		statusPanel.clearCentralMessage();

		// Reset the player stats and display the first level
		resetPlayerStats();
		displayLevel(1, true);
	}

	/**
	 * Removes all bodies from the world and displays the game over image.
	 */
	public void gameOver() {
		// Remove all bodies from the world except the player. Position the player outside of the visible world.
		for (Body body : world.getDynamicBodies()) {
			if (body instanceof Player) {
				// Hide the player from the screen
				((Player) body).setPosition(50, 0);
			} else {
				body.destroy();
			}
		}
		for (Body body : world.getStaticBodies()) {
			body.destroy();
		}

		// Display the game over image
		new BackgroundImage(world).displayGameOverImage();
	}

	/**
	 * Resets the player's stats to their initial values.
	 */
	private void resetPlayerStats() {
		player.resetLives();
		player.resetCoins();
	}

	/**
	 * Gets the current level of the game.
	 *
	 * @return  the current level
	 */
	public int getCurrentLevel() {
		return currentLevel;
	}
}

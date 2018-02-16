package levels;

/**
 * Interface for the game levels.
 */
public interface Level {
	/**
	 * Displays and positions the platforms of the current level.
	 */
	void displayPlatforms();

	/**
	 * Displays and positions the enemies of the current level.
	 */
	void displayEnemies();

	/**
	 * Displays and positions the collectibles of the current level.
	 */
	void displayCollectibles();

	/**
	 * Resets the level when the player dies and the level resets.
	 * Does not reset the collectibles and the user should keep what they collected.
	 */
	void resetLevel();
}

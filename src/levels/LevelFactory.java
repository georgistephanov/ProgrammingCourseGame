package levels;

import bodies.Player;
import city.cs.engine.World;

import java.security.InvalidParameterException;

/**
 * Implements the Static Factory Pattern for creating level objects.
 */
class LevelFactory {
	private static LevelFactory instance;
	private final World world;
	private final Player player;

	/**
	 * A private constructor to enforce a singleton object.
	 *
	 * @param world  the world associated with the level creation
	 * @param player  the player associated with the world
	 */
	private LevelFactory(World world, Player player) {
		this.world = world;
		this.player = player;
	}

	/**
	 * Gets the singleton instance of this class.
	 * If the instance is not yet instantiated, creates a new instance.
	 *
	 * @param world  the world associated with the level creation
	 * @param player  the player associated with the world
	 * @return  the singleton instance of this factory
	 */
	static LevelFactory getInstance(World world, Player player) {
		if (instance == null) {
			instance = new LevelFactory(world, player);
		}

		return instance;
	}

	/**
	 * Creates and returns the level by the level number passed as a parameter.
	 * Currently, the implemented levels are: 1
	 *
	 * @param level  the level number of the level which is to be created
	 * @return  the level associated with the parameter, null if such level doesn't exist
	 * @throws InvalidParameterException if there is no such {@code levelNumber} implemented
	 */
	Level getLevel(int level) {
		switch(level) {
			case 1:
				return new Level1(world, player);
			case 2:
				return new Level2(world, player);
			case 3:
				return new Level3(world, player);
			default:
				throw new InvalidParameterException("This game supports only levels 1, 2 and 3");
		}
	}

}

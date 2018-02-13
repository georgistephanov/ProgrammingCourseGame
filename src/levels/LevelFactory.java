package levels;

import bodies.Player;
import city.cs.engine.World;

public class LevelFactory {
	private static LevelFactory instance;
	private World world;
	private Player player;

	private LevelFactory(World world, Player player) {
		this.world = world;
		this.player = player;
	}

	public static LevelFactory getInstance(World world, Player player) {
		if (instance == null) {
			instance = new LevelFactory(world, player);
		}

		return instance;
	}

	public Level getLevel(int level) {
		switch(level) {
			case 1:
				return new Level1(world, player);
			default:
				return null;
		}
	}

}

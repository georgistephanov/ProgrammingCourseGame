package bodies.factories;

import bodies.enemies.*;
import city.cs.engine.World;
import game.GameConstants;
import java.security.InvalidParameterException;

/**
 * Concrete implementation of the {@link GameObjectFactory} for creating {@code Enemy} objects.
 */
class EnemyFactory extends AbstractGameObjectFactory<Enemy> {

	EnemyFactory(World world) {
		super(world);
	}

	@Override
	public Enemy create(String line) throws InvalidParameterException {
		Enemy enemy;

		if (stringsInLine(line) != 4) {
			throw new InvalidParameterException();
		}
		String enemyType = line.split("\t")[0];

		switch (enemyType) {
			case "s":
				enemy = new SpikeMan(world);
				break;
			case "w":
				enemy = new WingMan(world);
				break;
			case "f":
				enemy = new FlyMan(world);
				break;
			case "z":
				enemy = new Zombie(world);
				break;
			default:
				throw new InvalidParameterException();
		}

		// Extract the parameters from the line
		float[] parameters = getParametersFromFileLine(line);

		// Set the walking direction of the enemy
		boolean walkRight = parameters[0] == 1;
		enemy.setMovementDirection(walkRight ? GameConstants.MovementDirections.RIGHT : GameConstants.MovementDirections.LEFT);

		// Set the enemy position in the world
		float x = parameters[1];
		float y = parameters[2];
		enemy.setPosition(x, y);

		return enemy;
	}
}

package bodies.factories;

import bodies.collectibles.Coin;
import bodies.collectibles.Collectible;
import bodies.collectibles.Life;
import city.cs.engine.World;

import java.security.InvalidParameterException;

/**
 * Concrete implementation of the {@link GameObjectFactory} for creating {@code Collectible} objects.
 */
class CollectibleFactory extends AbstractGameObjectFactory<Collectible> {

	CollectibleFactory(World world) {
		super(world);
	}

	@Override
	public Collectible create(String line) throws InvalidParameterException {

		if (stringsInLine(line) != 3) {
			throw new InvalidParameterException();
		}

		Collectible collectible;

		float [] parameters = getParametersFromFileLine(line);
		String collectibleType = line.split("\t")[0];

		switch (collectibleType) {
			case "c":
				collectible = new Coin(world);
				break;
			case "l":
				collectible = new Life(world);
				break;
			default:
				throw new InvalidParameterException();
		}

		float x = parameters[0];
		float y = parameters[1];
		collectible.setPosition(x, y);

		return collectible;
	}
}

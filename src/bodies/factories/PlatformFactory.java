package bodies.factories;

import buildingblocks.CustomSolidFixture;
import buildingblocks.Platform;
import buildingblocks.Wall;
import city.cs.engine.World;

import java.security.InvalidParameterException;

/**
 * Concrete implementation of the {@link GameObjectFactory} for creating {@code Platform} and {@code Wall} objects.
 */
class PlatformFactory extends AbstractGameObjectFactory<CustomSolidFixture> {

	PlatformFactory(World world) {
		super(world);
	}

	@Override
	public CustomSolidFixture create(String line) throws InvalidParameterException {
		if (stringsInLine(line) != 4) {
			throw new InvalidParameterException();
		}

		CustomSolidFixture customSolidFixture;
		String platformType = line.split("\t")[0];

		float [] parameters = getParametersFromFileLine(line);
		float size = parameters[0];

		switch (platformType) {
			case "p":
				customSolidFixture = new Platform(world, size);
				break;
			case "w":
				customSolidFixture = new Wall(world, size);
				break;
			default:
				throw new InvalidParameterException();
		}

		// Set the position of the element
		float x = parameters[1];
		float y = parameters[2];
		customSolidFixture.setPosition(x, y);

		return customSolidFixture;
	}
}

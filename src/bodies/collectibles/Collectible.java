package bodies.collectibles;

import city.cs.engine.*;
import imagemanagers.AbstractImageManager;

abstract class Collectible extends StaticBody {

	AbstractImageManager imageManager;

	Collectible(World world, Shape shape) {
		super(world, shape);
	}
}

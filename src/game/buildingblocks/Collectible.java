package game.buildingblocks;

import city.cs.engine.*;
import game.images.AbstractImageManager;

abstract class Collectible extends StaticBody {

	AbstractImageManager imageManager;

	Collectible(World world, Shape shape) {
		super(world, shape);
	}
}

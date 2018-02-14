package bodies.collectibles;

import city.cs.engine.*;
import imagemanagers.AbstractImageManager;
import org.jbox2d.common.Vec2;

public abstract class Collectible extends StaticBody {

	AbstractImageManager imageManager;

	Collectible(World world, Shape shape) {
		super(world, shape);
	}

	public void setPosition(float x, float y) {
		setPosition(new Vec2(x, y));
	}
}

package bodies.collectibles;

import city.cs.engine.*;
import imagemanagers.AbstractImageManager;
import org.jbox2d.common.Vec2;

public abstract class Collectible extends CustomSensor {

	AbstractImageManager imageManager;

	Collectible(World world, Shape shape) {
		super(new StaticBody(world), shape);
		addSensorListener(this);
	}

	public void setPosition(float x, float y) {
		getBody().setPosition(new Vec2(x, y));
	}
}
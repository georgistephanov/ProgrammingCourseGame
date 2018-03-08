package bodies.collectibles;

import city.cs.engine.*;
import gui.imagemanagers.AbstractImageManager;
import org.jbox2d.common.Vec2;

/**
 * A base class for all objects which the player will be able to collect.
 * This class provides a package-private instance of an {@code AbstractImageManager}, registers the SensorListener
 * and provides a {@code setPosition} method to reduce the boilerplate code of creating a new {@code Vec2} object every time it is called.
 */
public abstract class Collectible extends CustomSensor {

	AbstractImageManager imageManager;

	/**
	 * Constructs this object and registers this class as a sensor listener of this object.
	 *
 	 * @param world  the world in which this object will be created
	 * @param shape  the shape of this object
	 */
	Collectible(World world, Shape shape) {
		super(new StaticBody(world), shape);
		addSensorListener(this);
	}

	/**
	 * Reduces the boilerplate code of creating a new {@code Vec2} object every time {@code setPosition} is called
	 * by providing a way of setting the position just by the X and Y coordinates.
	 *
	 * @param x  this object's position on the X axis
	 * @param y  this object's position on the X axis
	 */
	@Override
	public void setPosition(float x, float y) {
		getBody().setPosition(new Vec2(x, y));
	}
}
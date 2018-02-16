package bodies.collectibles;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

/**
 * A wrapper class around the {@code Sensor} class which implements the {@code SensorListener}.
 */
public class CustomSensor extends Sensor implements SensorListener {

	/**
	 * Constructs the {@code Sensor} object and adds the {@code SensorListener} to this class.
	 *
	 * @param body  the body of this object
	 * @param shape  the shape of this object
	 */
	public CustomSensor(Body body, Shape shape) {
		super(body, shape);

		addSensorListener(this);
	}

	/**
	 * Reduces the boilerplate code of creating a new {@code Vec2} object every time {@code setPosition} is called
	 * by providing a way of setting the position just by the X and Y coordinates.
	 *
	 * @param x  this object's position on the X axis
	 * @param y  this object's position on the X axis
	 */
	public void setPosition(float x, float y) {
		getBody().setPosition(new Vec2(x, y));
	}

	/**
	 * Respond to a solid fixture beginning to overlap with a sensor.
	 * This is an empty implementation of this method in case that a child class doesn't want to override it.
	 *
	 * @param e  the contact event
	 */
	public void beginContact(SensorEvent e) {}

	/**
	 * Respond to a solid fixture ceasing to overlap with a sensor.
	 * This is an empty implementation of this method in case that a child class doesn't want to override it.
	 *
	 * @param e  the contact event
	 */
	public void endContact(SensorEvent e) {}
}

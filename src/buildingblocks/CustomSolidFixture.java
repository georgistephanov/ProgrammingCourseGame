package buildingblocks;

import city.cs.engine.Body;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;
import org.jbox2d.common.Vec2;

/**
 * A wrapper around the {@code SolidFixture} class which provides a less boilerplate way of setting the position of its
 * instances in the world.
 */
class CustomSolidFixture extends SolidFixture {

	/**
	 * Creates the solid fixture.
	 *
	 * @param body  the body of the solid fixture
	 * @param shape  the shape of the solid fixture
	 */
	CustomSolidFixture(Body body, Shape shape) {
		super(body, shape);
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
}

package buildingblocks;

import city.cs.engine.BoxShape;
import city.cs.engine.StaticBody;
import city.cs.engine.World;

import java.awt.*;

/**
 * Vertical solid platform with a friction of 0 so that no object could stick to it.
 */
public class Wall extends CustomSolidFixture {
	/**
	 * Constructs the wall with a half width of 1 meter, sets its friction to 0 and its colour to {@code Color.DARK_GRAY}
	 *
	 * @param world  the world in which to be created
	 * @param height  the height of the wall
	 */
	public Wall(World world, float height) {
		super (new StaticBody(world), new BoxShape(1, height));
		getBody().setFillColor(Color.DARK_GRAY);
		setFriction(0);
	}

	/**
	 * Creates this object with a custom half width, sets its friction to 0 and its colour to {@code Color.DARK_GRAY}
	 * This method is package private as it is to be used by {@code UnlockableDoor}.
	 *
	 * @param world  the world in which to be created
	 * @param width  the width of the wall
	 * @param height  the height of the wall
	 */
	Wall(World world, float width, float height) {
		super (new StaticBody(world), new BoxShape(width, height));
		getBody().setFillColor(Color.DARK_GRAY);
		setFriction(0);
	}

	/**
	 * Sets the fill colour of the body
	 *
	 * @param color  the fill colour of the body
	 */
	void setFillColor(Color color) {
		getBody().setFillColor(color);
	}
}

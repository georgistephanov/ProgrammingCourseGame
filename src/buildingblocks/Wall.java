package buildingblocks;

import city.cs.engine.BoxShape;
import city.cs.engine.SolidFixture;
import city.cs.engine.StaticBody;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

import java.awt.*;

/**
 * Vertical solid platform with a friction of 0 so that no object could stick to it.
 */
public class Wall extends CustomSolidFixture {
	/**
	 * Creates this object with a half width of 1 meter, sets its friction to 0 and its colour to {@code Color.DARK_GRAY}
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
	 * Sets the fill colour of the body
	 *
	 * @param color  the fill colour of the body
	 */
	void setFillColor(Color color) {
		getBody().setFillColor(color);
	}
}

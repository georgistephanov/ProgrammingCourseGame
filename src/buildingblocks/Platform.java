package buildingblocks;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.awt.*;

/**
 * A solid platform which is used as the main building block of the levels.
 */
public class Platform extends CustomSolidFixture {
	private static final float DEFAULT_HEIGHT = .5f;

	private final Edge leftEdge;
	private final Edge rightEdge;
	private final float totalWidth;

	/**
	 * Creates this platform using the default height (0.5f).
	 *
	 * @param world  the world in which to be created
	 * @param width  the width of the platform
	 */
	public Platform(World world, float width) {
		this(world, width, DEFAULT_HEIGHT);
	}

	/**
	 * Creates this platform and its edges.
	 *
	 * @param world  the world in which to be created
	 * @param width  the width of the platform
	 * @param height  the height of the platform
	 */
	public Platform(World world, float width, float height) {
		super(new StaticBody(world), new BoxShape(width - .4f, height));
		getBody().setFillColor(Color.DARK_GRAY);

		leftEdge = new Edge(world, height);
		leftEdge.getBody().setFillColor(Color.DARK_GRAY);

		rightEdge = new Edge(world, height);
		rightEdge.getBody().setFillColor(Color.DARK_GRAY);

		totalWidth = width;
	}

	@Override
	public void setPosition(float x, float y) {
		getBody().setPosition(new Vec2(x, y));
		leftEdge.setPosition(x - (totalWidth - .2f), y);
		rightEdge.setPosition(x + (totalWidth - .2f), y);
	}

	/**
	 * Represents an edge of this platform which has its friction set to 0 in order to prevent the user from sticking to it.
	 */
	private static class Edge extends CustomSolidFixture {
		Edge(World world, float height) {
			super (new StaticBody(world), new BoxShape(.18f, height));
			setFriction(0);
		}
	}
}

package buildingblocks;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.awt.*;

public class Platform extends SolidFixture {
	private Edge leftEdge;
	private Edge rightEdge;
	private float totalWidth;

	public Platform (World world, float width, float height) {
		super(new StaticBody(world), new BoxShape(width - .4f, height));
		getBody().setFillColor(Color.DARK_GRAY);

		leftEdge = new Edge(world, height);
		rightEdge = new Edge(world, height);

		totalWidth = width;
	}

	public Platform setPosition(float x, float y) {
		getBody().setPosition(new Vec2(x, y));
		leftEdge.setPosition(x - (totalWidth - .2f), y);
		rightEdge.setPosition(x + (totalWidth - .2f), y);

		return this;
	}
}

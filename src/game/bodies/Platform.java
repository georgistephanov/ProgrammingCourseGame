package game.bodies;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.awt.*;

public class Platform extends SolidFixture {
	private SolidFixture leftEdge;
	private SolidFixture rightEdge;
	private float totalWidth;

	public Platform (World w, float width, float height) {
		super(new StaticBody(w), new BoxShape(width - .02f, height));
		getBody().setFillColor(Color.DARK_GRAY);

		leftEdge = new SolidFixture(new StaticBody(w), new BoxShape(.001f, height));
		rightEdge = new SolidFixture(new StaticBody(w), new BoxShape(.001f, height));
		leftEdge.setFriction(0);
		rightEdge.setFriction(0);

		totalWidth = width;
	}

	public Platform setPosition(float x, float y) {
		getBody().setPosition(new Vec2(x, y));
		leftEdge.getBody().setPosition(new Vec2(x - totalWidth - .001f, y));
		rightEdge.getBody().setPosition(new Vec2(x + (totalWidth - .001f), y));
		return this;
	}
}

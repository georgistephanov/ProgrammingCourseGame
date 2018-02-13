package buildingblocks;

import city.cs.engine.BoxShape;
import city.cs.engine.SolidFixture;
import city.cs.engine.StaticBody;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

public class Edge extends SolidFixture {
	private static float HALF_WIDTH = .18f;

	Edge(World world, float height) {
		super (new StaticBody(world), new BoxShape(HALF_WIDTH, height));
		setFriction(0);
	}

	void setPosition(float x, float y) {
		getBody().setPosition(new Vec2(x, y));
	}
}
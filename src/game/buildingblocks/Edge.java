package game.buildingblocks;

import city.cs.engine.BoxShape;
import city.cs.engine.SolidFixture;
import city.cs.engine.StaticBody;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

public class Edge extends SolidFixture {
	Edge(World world, float height) {
		super (new StaticBody(world), new BoxShape(.2f, height));
		setFriction(0);
	}

	Edge setPosition(float x, float y) {
		getBody().setPosition(new Vec2(x, y));
		return this;
	}
}

package buildingblocks;

import city.cs.engine.Body;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;
import org.jbox2d.common.Vec2;

class CustomSolidFixture extends SolidFixture {

	CustomSolidFixture(Body body, Shape shape) {
		super(body, shape);
	}

	public void setPosition(float x, float y) {
		getBody().setPosition(new Vec2(x, y));
	}
}

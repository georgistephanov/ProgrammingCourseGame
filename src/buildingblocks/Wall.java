package buildingblocks;

import city.cs.engine.BoxShape;
import city.cs.engine.SolidFixture;
import city.cs.engine.StaticBody;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

import java.awt.*;

public class Wall extends CustomSolidFixture {
	public Wall(World world, float height) {
		super (new StaticBody(world), new BoxShape(1, height));
		getBody().setFillColor(Color.DARK_GRAY);
		setFriction(0);
	}
}

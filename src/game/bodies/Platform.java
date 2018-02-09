package game.bodies;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class Platform extends StaticBody {
	
	public Platform (World w, float width, float height) {
		super(w, new BoxShape(width, height));
	}

	public Platform setPosition(float x, float y) {
		setPosition(new Vec2(x, y));
		return this;
	}
}

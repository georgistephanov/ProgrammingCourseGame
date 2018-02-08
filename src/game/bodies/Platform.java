package game.bodies;

import city.cs.engine.*;

public class Platform extends StaticBody {
	
	public Platform (World w, float width, float height) {
		super(w, new BoxShape(width, height));
	}
}

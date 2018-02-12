package game.buildingblocks;

import city.cs.engine.*;
import game.images.SingleImageManager;

public class Life extends Collectible {

	private static final Shape lifeShape = new CircleShape(1);
	private static final BodyImage lifeImage = new BodyImage("data/life.png", 2.5f);

	public Life(World world) {
		super(world, lifeShape);

		imageManager = new SingleImageManager(this, lifeImage);
		imageManager.display();
	}
}

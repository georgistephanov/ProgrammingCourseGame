package bodies.collectibles;

import city.cs.engine.*;
import imagemanagers.RotatingImageManager;

public class Coin extends Collectible {

	private static final Shape coinShape = new CircleShape(1);
	private static final BodyImage [] coinImages = {
			new BodyImage("data/gold_1.png", 2.5f),
			new BodyImage("data/gold_2.png", 2.5f),
			new BodyImage("data/gold_3.png", 2.5f),
			new BodyImage("data/gold_4.png", 2.5f)
	};

	public Coin(World world) {
		super(world, coinShape);

		imageManager = new RotatingImageManager(this, coinImages);
		imageManager.setStepCounter(6);
		imageManager.display();
	}
}

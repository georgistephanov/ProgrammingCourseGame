package bodies.collectibles;

import bodies.Player;
import city.cs.engine.*;
import imagemanagers.RotatingImageManager;

public class Coin extends Collectible {

	private static final Shape coinShape = new CircleShape(1);
	private static final BodyImage [] coinImages = {
			new BodyImage("data/collectibles/gold_1.png", 2.5f),
			new BodyImage("data/collectibles/gold_2.png", 2.5f),
			new BodyImage("data/collectibles/gold_3.png", 2.5f),
			new BodyImage("data/collectibles/gold_4.png", 2.5f)
	};

	public Coin(World world) {
		super(world, coinShape);

		imageManager = new RotatingImageManager(getBody(), coinImages);
		imageManager.setStepCounter(6);
		imageManager.display();
	}

	public void beginContact(SensorEvent e) {
		if (e.getContactBody() instanceof Player) {
			((Player) e.getContactBody()).addCoin();

			getBody().destroy();
			destroy();
		}
	}
	public void endContact(SensorEvent e) {}
}

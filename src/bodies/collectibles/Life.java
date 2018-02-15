package bodies.collectibles;

import bodies.Player;
import city.cs.engine.*;
import imagemanagers.SingleImageManager;

public class Life extends Collectible {

	private static final Shape lifeShape = new CircleShape(1);
	private static final BodyImage lifeImage = new BodyImage("data/collectibles/life.png", 2.5f);

	public Life(World world) {
		super(world, lifeShape);

		imageManager = new SingleImageManager(getBody(), lifeImage);
		imageManager.display();
	}

	public void beginContact(SensorEvent e) {
		if (e.getContactBody() instanceof Player) {
			((Player) e.getContactBody()).addLife();

			getBody().destroy();
			destroy();
		}
	}
	public void endContact(SensorEvent e) {}
}

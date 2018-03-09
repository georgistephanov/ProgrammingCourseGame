package bodies.enemies;

import city.cs.engine.*;

public class Zombie extends Enemy {
	private static final Shape ZOMBIE_SHAPE = new PolygonShape(1.7485f,-3.2175f,
			1.6965f,1.366f, 1.5015f,1.457f, -0.8125f,1.446f, -1.69f,0.871f,
			-1.7745f,-1.976f, -1.339f,-3.2305f);
	private static final BodyImage[] ZOMBIE_IMAGES = {
			new BodyImage("data/enemies/zombie1.png", 6.5f),
			new BodyImage("data/enemies/zombie2.png", 6.5f)
	};

	public Zombie(World world) {
		super ( new Builder(world, ZOMBIE_SHAPE)
				.standingImage(ZOMBIE_IMAGES[0])
				.walkingImages(ZOMBIE_IMAGES));

		setXVelocity(2.5f);

		setChangeImagePerSteps(20);
		imageManager.setFlipImage(true);
	}
}

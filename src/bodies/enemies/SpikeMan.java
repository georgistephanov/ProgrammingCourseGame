package bodies.enemies;

import city.cs.engine.*;
import bodies.CustomWalker;

public class SpikeMan extends Enemy {
	private static final Shape spikeManShape = new PolygonShape(-1.16f,-1.99f, -1.15f,-0.22f, -0.03f,1.98f,
			1.14f,-0.2f, 1.15f,-1.98f);

	private static final BodyImage standingImage = new BodyImage("data/enemies/spikeMan_stand.png", 4);
	private static final BodyImage [] walkingImages = {
			new BodyImage("data/enemies/spikeMan_walk1.png", 4),
			new BodyImage("data/enemies/spikeMan_walk2.png", 4)
	};

	public SpikeMan(World world) {
		super( new CustomWalker.Builder(world, spikeManShape)
				.standingImage(standingImage)
				.walkingImages(walkingImages)
		);

		setXVelocity(8);
		setChangeImagePerSteps(5);
	}
}

package bodies.enemies;

import city.cs.engine.*;
import bodies.CustomWalker;

/**
 * This class represents the spike enemies in the game.
 */
public class SpikeMan extends Enemy {
	private static final Shape SPIKE_MAN_SHAPE = new PolygonShape(-1.16f,-1.99f, -1.15f,-0.22f, -0.03f,1.98f,
			1.14f,-0.2f, 1.15f,-1.98f);

	private static final BodyImage STANDING_IMAGE = new BodyImage("data/enemies/spikeMan_stand.png", 4);
	private static final BodyImage [] WALKING_IMAGES = {
			new BodyImage("data/enemies/spikeMan_walk1.png", 4),
			new BodyImage("data/enemies/spikeMan_walk2.png", 4)
	};

	/**
	 * Constructs this spike man and sets its velocity to 8 and the steps between image changes to 5.
	 *
	 * @param world  the world in which to be created
	 */
	public SpikeMan(World world) {
		super( new CustomWalker.Builder(world, SPIKE_MAN_SHAPE)
				.standingImage(STANDING_IMAGE)
				.walkingImages(WALKING_IMAGES)
		);

		setXVelocity(8);
		setChangeImagePerSteps(5);
	}
}

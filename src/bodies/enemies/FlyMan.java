package bodies.enemies;

import city.cs.engine.*;

/**
 * Represents the flying spring enemies in the game.
 */
public class FlyMan extends Enemy {
	private static final Shape FLY_MAN_SHAPE = new BoxShape(2, 2);
	private static final BodyImage FLY_MAN_IMAGE = new BodyImage("data/enemies/flyMan_fly.png", 4);

	/**
	 * Constructs this flying man and sets its gravity to 0.
	 *
	 * @param world  the world in which to be created
	 */
	public FlyMan(World world) {
		super(new Builder(world, FLY_MAN_SHAPE).standingImage(FLY_MAN_IMAGE));
		setGravityScale(0);
	}
}

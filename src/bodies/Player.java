package bodies;

import city.cs.engine.*;
import bodies.enemies.Enemy;
import org.jbox2d.common.Vec2;
import static game.GameConstants.MovementDirections.*;

/**
 * This class represents the main player character of the game, which the user controls.
 */
public class Player extends CustomWalker implements StepListener, CollisionListener {

	private static final Shape playerShape = new PolygonShape(1.7485f,-3.2175f,
			1.6965f,2.366f, 1.5015f,2.457f, -0.8125f,1.846f, -1.69f,0.871f,
			-1.7745f,-1.976f, -1.339f,-3.2305f);

	private static BodyImage standingImage = new BodyImage("data/player/player_stand.png", 6.5f);
	private static BodyImage jumpingImage = new BodyImage("data/player/player_jump.png", 6.5f);
	private static BodyImage fallingImage = new BodyImage("data/player/player_fall.png", 6.5f);
	private static BodyImage [] walkingImages = {
			new BodyImage("data/player/player_walk1.png", 6.5f),
			new BodyImage("data/player/player_walk2.png", 6.5f)
	};

	private int coins = 0;
	private int lives = 3;

	/**
	 * Creates the superclass instance object, sets the gravity scale of this object to 2 and registers the
	 * step and collision listeners.
	 *
	 * @param world  the world in which this object will be created
	 */
	public Player(World world) {
		super( new CustomWalker.Builder(world, playerShape)
				.standingImage(standingImage)
				.walkingImages(walkingImages)
				.jumpingImage(jumpingImage)
				.fallingImage(fallingImage) );

		setGravityScale(2);

		world.addStepListener(this);
		addCollisionListener(this);
	}

	/**
	 * Gets the amount of coins this player has collected.
	 *
	 * @return  the amount of collected coins
	 */
	public int getCoins() {
		return coins;
	}

	/**
	 * Increment the amount of coins this player has collected by one.
	 */
	public void addCoin() {
		setCoins(coins + 1);
	}

	/**
	 * Set and print on the console the amount of coins this player has collected.
	 *
	 * @param coins  the amount of coins which are to be set
	 */
	private void setCoins(int coins) {
		// TODO: Add an observer to notify the world to display the correct amount of coins

		this.coins = coins;
		System.out.println("Lives: " + lives + "\tCoins: " + coins);
	}

	/**
	 * Gets the amount of lives this player has left.
	 *
	 * @return  the amount of lives left
	 */
	public int getLives() {
		return lives;
	}

	/**
	 * Increment the amount of lives this player has left.
	 */
	public void addLife() {
		setLives(lives + 1);
	}

	/**
	 * Decrease the amount of lives this player has left.
	 */
	private void decreaseLives() {
		setLives(lives - 1);
	}

	/**
	 * Set and print on the console the amount of lives this player has left.
	 *
	 * @param lives  the amount of lives which are to be set
	 */
	private void setLives(int lives) {
		// TODO: Add an observer to notify the world to display the correct amount of lives

		this.lives = lives;
		System.out.println("Lives: " + lives + "\tCoins: " + coins);
	}

	@Override
	public void startWalking(float speed) {
		super.startWalking(speed);

		// Display the correct image based on which direction on the X axis the walker is moving
		if (speed > 0) {
			imageManager.changeImage(RIGHT);
		} else if (speed < 0) {
			imageManager.changeImage(LEFT);
		}
	}

	/**
	 * Empty implementation of the {@code StepListener} interface method.
	 *
	 * @param stepEvent  the current step's event
	 */
	@Override
	public void preStep(StepEvent stepEvent) { }

	/**
	 * Displays the correct image if this player is not walking based on whether it is jumping, falling or not moving.
	 *
	 * @param stepEvent  the current step's event
	 */
	@Override
	public void postStep(StepEvent stepEvent) {
		Vec2 velocity = getLinearVelocity();

		if (velocity.y > 0) {
			imageManager.changeImage(JUMPING);
		} else if (velocity.y < 0) {
			imageManager.changeImage(FALLING);
		} else if (velocity.x == 0) {
			imageManager.changeImage(NOT_MOVING);
		}
	}

	/**
	 * Stops the world if this player collides with an enemy.
	 *
	 * @param e  the event of the collision
	 */
	public void collide(CollisionEvent e) {
		if ( e.getOtherBody() instanceof Enemy) {
			decreaseLives();
			getWorld().stop();

			if (lives > 0) {
				System.out.println("Ouch...");
				System.out.println("Press space to continue");
			} else {
				System.out.println("GAME OVER!");
			}

			System.out.println();
		}
	}
}
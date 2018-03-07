package bodies;

import bodies.gui.CollectiblesJLabel;
import city.cs.engine.*;
import bodies.enemies.Enemy;
import bodies.gui.StatusPanel;
import bodies.gui.imagemanagers.BackgroundImage;
import org.jbox2d.common.Vec2;
import static game.GameConstants.MovementDirections.*;

/**
 * This class represents the main player character of the game, which the user controls.
 */
public class Player extends CustomWalker implements StepListener, CollisionListener {

	private static final Shape PLAYER_SHAPE = new PolygonShape(1.7485f,-3.2175f,
			1.6965f,2.366f, 1.5015f,2.457f, -0.8125f,1.846f, -1.69f,0.871f,
			-1.7745f,-1.976f, -1.339f,-3.2305f);

	private static final BodyImage STANDING_IMAGE = new BodyImage("data/player/player_stand.png", 6.5f);
	private static final BodyImage JUMPING_IMAGE = new BodyImage("data/player/player_jump.png", 6.5f);
	private static final BodyImage FALLING_IMAGE = new BodyImage("data/player/player_fall.png", 6.5f);
	private static final BodyImage [] WALKING_IMAGES = {
			new BodyImage("data/player/player_walk1.png", 6.5f),
			new BodyImage("data/player/player_walk2.png", 6.5f)
	};

	private int coins = 0;
	private int lives = 3;

	private final String propertyName = CollectiblesJLabel.PROPERTY_NAME;
	private final CollectiblesJLabel livesLabel = StatusPanel.getInstance().getLivesLabel();
	private final CollectiblesJLabel coinsLabel = StatusPanel.getInstance().getCoinsLabel();

	/**
	 * Creates the superclass instance object, sets the gravity scale of this object to 2 and registers the
	 * step and collision listeners.
	 *
	 * @param world  the world in which this object will be created
	 */
	public Player(World world) {
		super( new CustomWalker.Builder(world, PLAYER_SHAPE)
				.standingImage(STANDING_IMAGE)
				.walkingImages(WALKING_IMAGES)
				.jumpingImage(JUMPING_IMAGE)
				.fallingImage(FALLING_IMAGE) );

		setGravityScale(2);

		world.addStepListener(this);
		addCollisionListener(this);

		livesLabel.firePropertyChange(propertyName, livesLabel.getAmount(), lives);
		coinsLabel.firePropertyChange(propertyName, coinsLabel.getAmount(), coins);
	}

	/**
	 * Increment the amount of coins this player has collected by one.
	 */
	public void addCoin() {
		coinsLabel.firePropertyChange(propertyName, coinsLabel.getAmount(), ++coins);
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
		setLives(++lives);
	}

	/**
	 * Decrease the amount of lives this player has left.
	 */
	private void decreaseLives() {
		setLives(--lives);
	}

	/**
	 * Set and print on the console the amount of lives this player has left.
	 *
	 * @param lives  the amount of lives which are to be set
	 */
	private void setLives(int lives) {
		livesLabel.firePropertyChange(propertyName, livesLabel.getAmount(), lives);
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
	 * Handles collisions with enemies.
	 * Decreases the player lives and if the player has no more lives shows the game over image and stops the world.
	 *
	 * @param e  the event of the collision
	 */
	@Override
	public void collide(CollisionEvent e) {
		if ( e.getOtherBody() instanceof Enemy) {
			decreaseLives();

			if (lives > 0) {
				System.out.println("Ouch...");
				System.out.println("Press space to continue");
			} else {
				// Remove all bodies from the world
				for (Body body : getWorld().getDynamicBodies())
					body.destroy();
				for (Body body : getWorld().getStaticBodies())
					body.destroy();

				// Display the game over image
				new BackgroundImage(getWorld()).displayGameOverImage();
				System.out.println("Game over!");
			}
			System.out.println();

			StatusPanel.getInstance().displayStartButton();
			getWorld().stop();
		}
	}
}
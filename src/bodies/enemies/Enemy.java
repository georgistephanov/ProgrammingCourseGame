package bodies.enemies;

import buildingblocks.Platform;
import city.cs.engine.*;
import bodies.CustomWalker;
import game.GameConstants.MovementDirections;
import static game.GameConstants.MovementDirections.LEFT;
import static game.GameConstants.MovementDirections.RIGHT;

/**
 * Base class for all enemy objects.
 */
public abstract class Enemy extends CustomWalker implements StepListener, CollisionListener {
	private float xVelocity = 10;
	private int currentStep = 0;
	private int stepsChangeImage = 10;

	/**
	 * Constructs the enemy object, sets its scales its gravity by 2 and registers the collision and step listeners.
	 *
	 * @param builder  the builder object which is used to construct the parent object
	 */
	Enemy(Builder builder) {
		super(builder);

		setGravityScale(2);

		addCollisionListener(this);
		builder.world.addStepListener(this);
	}

	final void setXVelocity(float xVelocity) {
		this.xVelocity = xVelocity;
	}

	/**
	 * Sets the movement direction of this object.
	 *
	 * @param movementDirection  the direction in which this object will be moving
	 * @return  this object
	 */
	public final Enemy setMovementDirection(MovementDirections movementDirection) {
		if ( (movementDirection == LEFT && xVelocity > 0) ||
				(movementDirection == RIGHT && xVelocity < 0) ) {
			xVelocity = -xVelocity;
		}

		return this;
	}

	/**
	 * Sets the amount of game steps needed before changing image's next state.
	 *
	 * @param stepsChangeImage  the amount of steps before changing image's state
	 */
	final void setChangeImagePerSteps(int stepsChangeImage) {
		this.stepsChangeImage = stepsChangeImage;
	}

	/**
	 * Changes the position in which this object moves on the X axis when a collision with an object different than a {@code Platform} occurs.
	 * Implementation of the {@code CollisionListener}'s interface.
	 *
	 * @param e  the collision event
	 */
	@Override
	public void collide(CollisionEvent e) {
		if (e.getOtherFixture() instanceof Platform) {
			return;
		}

		xVelocity = -xVelocity;
	}

	/**
	 * Calls the {@code startWalking()} method of this object on every game step and deals with the image changes based on
	 * on how many game steps should it happen.
	 *
	 * @param stepEvent  the step event of the current step
	 */
	@Override
	public void preStep(StepEvent stepEvent) {
		startWalking(xVelocity);

		if (currentStep++ == stepsChangeImage) {
			// Reset the current step
			currentStep = 0;

			if (xVelocity > 0) {
				imageManager.changeImage(RIGHT);
			} else {
				imageManager.changeImage(LEFT);
			}
		}
	}

	/**
	 * Empty implementation of this method, as it is part of the {@code StepListener} interface.
	 *
	 * @param stepEvent  the step event of the current step
	 */
	@Override
	public void postStep(StepEvent stepEvent) { }
}

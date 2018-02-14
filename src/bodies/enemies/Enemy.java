package bodies.enemies;

import bodies.collectibles.Collectible;
import buildingblocks.Platform;
import city.cs.engine.*;
import bodies.CustomWalker;
import buildingblocks.Edge;
import buildingblocks.Wall;
import game.GameConstants.MovementDirections;
import static game.GameConstants.MovementDirections.LEFT;
import static game.GameConstants.MovementDirections.RIGHT;

public abstract class Enemy extends CustomWalker implements StepListener, CollisionListener {
	private float xVelocity = 10;
	private int currentStep = 0;
	private int stepsChangeImage = 10;

	Enemy(Builder builder) {
		super(builder);

		setGravityScale(2);

		addCollisionListener(this);
		builder.world.addStepListener(this);
	}

	void setXVelocity(float xVelocity) {
		this.xVelocity = xVelocity;
	}

	public Enemy setMovementDirection(MovementDirections movementDirection) {
		if ( (movementDirection == LEFT && xVelocity > 0) ||
				(movementDirection == RIGHT && xVelocity < 0) ) {
			xVelocity = -xVelocity;
		}

		return this;
	}

	void setChangeImagePerSteps(int stepsChangeImage) {
		this.stepsChangeImage = stepsChangeImage;
	}

	public void collide(CollisionEvent e) {
		if (e.getOtherFixture() instanceof Platform) {
			return;
		}

		xVelocity = -xVelocity;
	}

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

	@Override
	public void postStep(StepEvent stepEvent) { }
}

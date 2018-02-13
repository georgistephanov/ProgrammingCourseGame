package bodies.enemies;

import city.cs.engine.*;
import bodies.CustomWalker;
import buildingblocks.Edge;
import buildingblocks.Wall;
import game.GameConstants;

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

	public void setMovementDirection(float movementDirection) {
		if ( (movementDirection < 0 && xVelocity > 0) || (movementDirection > 0 && xVelocity < 0) ) {
			xVelocity = -xVelocity;
		}
	}

	void setChangeImagePerSteps(int stepsChangeImage) {
		this.stepsChangeImage = stepsChangeImage;
	}

	public void collide(CollisionEvent e) {
		if (e.getOtherFixture() instanceof Wall || e.getOtherFixture() instanceof Edge) {
			xVelocity = -xVelocity;
		}
	}

	@Override
	public void preStep(StepEvent stepEvent) {
		startWalking(xVelocity);

		if (currentStep++ == stepsChangeImage) {
			// Reset the current step
			currentStep = 0;

			if (xVelocity > 0) {
				imageManager.changeImage(GameConstants.MovementDirections.RIGHT);
			} else {
				imageManager.changeImage(GameConstants.MovementDirections.LEFT);
			}
		}
	}

	@Override
	public void postStep(StepEvent stepEvent) { }
}

package game.bodies;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public abstract class Enemy extends DynamicBody implements StepListener, CollisionListener {
	private float xVelocity = 10;
	private BodyImage walk1;
	private BodyImage walk2;
	private byte imageIndex = 1;

	Enemy(World world, Shape shape, BodyImage image) {
		super(world, shape);
		addImage(image);
		addCollisionListener(this);
		setGravityScale(2);
		world.addStepListener(this);
	}

	public void setVelocity(float xVelocity) {
		this.xVelocity = xVelocity;
	}

	void setWalkImages(BodyImage... images) {
		if (images.length >= 2) {
			walk1 = images[0];
			walk2 = images[1];
		}
	}

	public void collide(CollisionEvent e) {
		if (e.getOtherFixture() instanceof Wall) {
			xVelocity *= -1;
		}
	}

	@Override
	public void preStep(StepEvent stepEvent) {
		setLinearVelocity(new Vec2(xVelocity, 0));

		if (walk1 != null && walk2 != null) {
			removeAllImages();

			switch (imageIndex) {
				case 1:
					addImage(walk1);
					imageIndex = 2;
					break;
				case 2:
					addImage(walk2);
					imageIndex = 1;
					break;
			}

			if (xVelocity < 0) {
				getImages().get(0).flipHorizontal();
			}
		}
	}

	@Override
	public void postStep(StepEvent stepEvent) { }
}

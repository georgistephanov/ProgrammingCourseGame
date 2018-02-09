package game.bodies;

import city.cs.engine.BodyImage;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import city.cs.engine.World;

public class WingMan extends Enemy {
	private static final Shape wingManShape = new PolygonShape(-2.95f,-1.97f, -2.94f,1.98f, 2.93f,1.99f, 2.93f,-1.99f);

	private static final BodyImage[] images = {
			new BodyImage("data/wingMan1.png", 4),
			new BodyImage("data/wingMan2.png", 4),
			new BodyImage("data/wingMan3.png", 4),
			new BodyImage("data/wingMan4.png", 4),
			new BodyImage("data/wingMan5.png", 4),
	};

	public WingMan(World world) {
		super(world, wingManShape, images[0]);
		setGravityScale(0);

		setWalkImages(images);
		setStepsChangeImage(6);
	}
}

package bodies.enemies;

import city.cs.engine.BodyImage;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import city.cs.engine.World;

public class WingMan extends Enemy {
	private static final Shape wingManShape = new PolygonShape(-2.95f,-1.97f, -2.94f,1.98f, 2.93f,1.99f, 2.93f,-1.99f);

	private static final BodyImage[] walkingImages = {
			new BodyImage("data/enemies/wingMan1.png", 4),
			new BodyImage("data/enemies/wingMan2.png", 4),
			new BodyImage("data/enemies/wingMan3.png", 4),
			new BodyImage("data/enemies/wingMan4.png", 4),
			new BodyImage("data/enemies/wingMan5.png", 4),
	};

	public WingMan(World world) {
		super( new Builder(world, wingManShape)
				.standingImage(walkingImages[3])
				.walkingImages(walkingImages)
		);

		setGravityScale(0);

		setChangeImagePerSteps(6);
		imageManager.setFlipImage(false);
	}
}

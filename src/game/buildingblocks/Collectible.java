package game.buildingblocks;

import city.cs.engine.*;

public abstract class Collectible extends StaticBody implements StepListener {

	private int stepCounter = 0;
	private int imageIndex = 0;
	private boolean imageUp = true;
	private BodyImage [] images;

	Collectible(World world, Shape shape, BodyImage [] images) {
		super(world, shape);

		this.images = images;
		addImage(images[0]);

		// Register the step listener if there are multiple images that have to be rendered
		if (images.length > 1) {
			world.addStepListener(this);
		}
	}

	@Override
	public void preStep(StepEvent stepEvent) {
		if (stepCounter++ == 6) {
			removeAllImages();

			if ((imageIndex >= images.length - 1 && imageUp) || (imageIndex == 0 && !imageUp)) {
				imageUp = !imageUp;
			}

			if (imageUp) {
				addImage(images[imageIndex++]);
			} else {
				addImage(images[imageIndex--]).flipHorizontal();
			}

			stepCounter = 0;
		}
	}

	@Override
	public void postStep(StepEvent stepEvent) {	}
}

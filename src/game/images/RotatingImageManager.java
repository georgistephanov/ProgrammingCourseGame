package game.images;

import city.cs.engine.Body;
import city.cs.engine.BodyImage;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;

public class RotatingImageManager extends AbstractImageManager implements StepListener {

	public RotatingImageManager(Body body, BodyImage[] images) {
		super(body, images);
	}

	public void display() {
		body.getWorld().addStepListener(this);
	}

	@Override
	public void preStep(StepEvent stepEvent) {
		if (currentStep++ == stepCounter) {
			body.removeAllImages();

			if ((imageIndex >= images.length - 1 && imageUp) || (imageIndex == 0 && !imageUp)) {
				imageUp = !imageUp;
			}

			if (imageUp) {
				body.addImage(images[imageIndex++]);
			} else {
				body.addImage(images[imageIndex--]).flipHorizontal();
			}

			currentStep = 0;
		}
	}

	@Override
	public void postStep(StepEvent stepEvent) { }
}

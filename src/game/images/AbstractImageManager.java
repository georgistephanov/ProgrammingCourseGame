package game.images;

import city.cs.engine.Body;
import city.cs.engine.BodyImage;

public abstract class AbstractImageManager implements ImageManager {
	Body body;
	BodyImage[] images;
	int currentStep = 0;
	int stepCounter = 1;
	int imageIndex = 0;
	boolean imageUp = true;

	AbstractImageManager(Body body, BodyImage [] images) {
		this.body = body;
		this.images = images;
	}

	public void setStepCounter(int stepCounter) {
		this.stepCounter = stepCounter;
	}

	public abstract void display();
}

package imagemanagers;

import city.cs.engine.Body;
import city.cs.engine.BodyImage;

public class SingleImageManager extends AbstractImageManager {

	public SingleImageManager(Body body, BodyImage image)  {
		super( body, new BodyImage[] {image} );
	}

	public void display() {
		body.addImage(images[0]);
	}
}

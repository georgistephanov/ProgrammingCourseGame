package bodies.gui.imagemanagers;

import city.cs.engine.Body;
import city.cs.engine.BodyImage;

/**
 * A concrete implementation of the {@code AbstractImageManager} for bodies with a single image resource.
 */
public final class SingleImageManager extends AbstractImageManager {

	public SingleImageManager(Body body, BodyImage image)  {
		super( body, new BodyImage[] {image} );
	}

	@Override
	public void display() {
		body.addImage(images[0]);
	}
}

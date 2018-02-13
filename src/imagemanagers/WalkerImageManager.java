package imagemanagers;

import city.cs.engine.Body;
import city.cs.engine.BodyImage;
import static game.GameConstants.MovementDirections;
import static game.GameConstants.MovementDirections.*;

public class WalkerImageManager implements ImageManager {

	private final Body body;
	private final BodyImage standingImage;
	private final BodyImage [] walkingImages;
	private final BodyImage jumpingImage;
	private final BodyImage fallingImage;

	private MovementDirections lastMovingDirection = NOT_MOVING;
	private int walkingImagesIndex = 0;
	private boolean flipImage = true;

	public WalkerImageManager(Body body,
							  BodyImage standingImage,
							  BodyImage [] walkingImages,
							  BodyImage jumpingImage,
							  BodyImage fallingImage) {

		this.body 			= body;
		this.standingImage 	= standingImage;
		this.walkingImages 	= walkingImages;
		this.jumpingImage 	= jumpingImage;
		this.fallingImage 	= fallingImage;
	}

	public void display() {
		if (standingImage != null) {
			body.addImage(standingImage);
		}
	}

	public void setFlipImage(boolean flipImage) {
		this.flipImage = flipImage;
	}

	public void changeImage(MovementDirections direction) {
		body.removeAllImages();

		BodyImage newImg;

		if ( (direction == RIGHT || direction == LEFT) &&
				(walkingImages != null && walkingImages.length > 0) ) {

			if (walkingImagesIndex == walkingImages.length) {
				walkingImagesIndex = 0;
			}

			newImg = walkingImages[walkingImagesIndex++];
			lastMovingDirection = direction;

		} else if ( direction == JUMPING && jumpingImage != null ) {
			newImg = jumpingImage;
		} else if ( direction == FALLING && fallingImage != null ) {
			newImg = fallingImage;
		} else {
			newImg = standingImage;
		}

		if ( flipImage && (direction == LEFT || lastMovingDirection == LEFT)) {
			body.addImage(newImg).flipHorizontal();
		} else {
			body.addImage(newImg);
		}
	}
}

package game.images;

import city.cs.engine.Body;
import city.cs.engine.BodyImage;
import static game.GameConstants.MovementDirections;
import static game.GameConstants.MovementDirections.*;

public class WalkerImageManager extends AbstractImageManager {

	private MovementDirections lastMovingDirection = NOT_MOVING;

	private BodyImage standingImage;
	private BodyImage jumpingImage;
	private BodyImage fallingImage;
	private BodyImage [] walkingImages;
	private int walkingImagesIndex = 0;

	public WalkerImageManager(Body body, BodyImage standingImage, BodyImage [] walkingImages,
							  BodyImage jumpingImage, BodyImage fallingImage) {

		// Pass null as a BodyImage [] parameter to the superclass constructor because all different scenario images
		// will be kept as data fields
		super(body, null);

		this.standingImage = standingImage;
		this.walkingImages = walkingImages;
		this.jumpingImage = jumpingImage;
		this.fallingImage = fallingImage;
	}

	public void display() {
		body.addImage(standingImage);
	}

	/* ============== Setters ============== */
	public void setStandingImage(BodyImage standingImage) {
		this.standingImage = standingImage;
	}

	public void setWalkingImages(BodyImage [] walkingImages) {
		this.walkingImages = walkingImages;
	}

	public void setJumpingImage(BodyImage jumpingImage) {
		this.jumpingImage = jumpingImage;
	}

	public void setFallingImage(BodyImage fallingImage) {
		this.fallingImage = fallingImage;
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

		if (direction == LEFT || lastMovingDirection == LEFT) {
			body.addImage(newImg).flipHorizontal();
		} else {
			body.addImage(newImg);
		}
	}

}

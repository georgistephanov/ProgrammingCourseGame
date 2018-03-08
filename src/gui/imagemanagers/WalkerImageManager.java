package gui.imagemanagers;

import city.cs.engine.Body;
import city.cs.engine.BodyImage;
import static game.GameConstants.MovementDirections;
import static game.GameConstants.MovementDirections.*;

/**
 * A concrete implementation of the {@code ImageManager} interface.
 * This class is used as an {@code ImageManager} for {@code CustomWalker} objects.
 */
public final class WalkerImageManager implements ImageManager {

	private final Body body;
	private final BodyImage standingImage;
	private final BodyImage [] walkingImages;
	private final BodyImage jumpingImage;
	private final BodyImage fallingImage;

	private MovementDirections lastMovingDirection = NOT_MOVING;

	/** Used as an image index in the range [0, walkingImages.length) to display the images from the array consecutively. */
	private int walkingImagesIndex = 0;
	/** Flag variable to indicate whether the image should be flipped when the object is moving in the left direction. */
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

	@Override
	public void display() {
		if (standingImage != null) {
			body.addImage(standingImage);
		}
	}

	/**
	 * Set whether the images should be flipped when the user is walking in the left direction.
	 *
	 * @param flipImage  true if the images should be flipped, false otherwise
	 */
	public void setFlipImage(boolean flipImage) {
		this.flipImage = flipImage;
	}

	/**
	 * Displays the correct image of the user based on the direction of the movement.
	 *
	 * @param direction  the direction in which the user moves
	 */
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

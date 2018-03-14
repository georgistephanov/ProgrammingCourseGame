package buildingblocks;

import bodies.collectibles.Key;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

import java.awt.*;

/**
 * Coloured instance of {@code Wall} which is unlocked when the associated with it {@code Key} gets collected.
 */
public class UnlockableWall extends Wall {
	private final Key key;
	private final float halfHeight;

	/**
	 * Enumeration of all possible ways that the door can be unlocked.
	 * <ul>
	 *     <li><b>MOVE_UP</b>  moves the wall upwards by 3/4 of its halfHeight</li>
	 *     <li><b>HIDE</b>  destroys the wall from the world</li>
	 * </ul>
	 */
	public enum Unlocking {
		MOVE_UP, HIDE
	}

	private Unlocking unlockingType = Unlocking.MOVE_UP;

	/**
	 * Constructs a wall with half width of 1 meter, sets its fill colour and creates a {@code Key} object which will be associated with this wall.
	 *
	 * @param world  the world in which to be created
	 * @param height  the halfHeight of the wall
	 * @param color  the fill colour of the wall
	 */
	public UnlockableWall(World world, float height, Color color) {
		super(world, height);
		setFillColor(color);

		halfHeight = height;
		key = new Key(world, this);
	}

	/**
	 * Creates a wall with custom specified width, sets its fill colour and creates a {@code Key} object which will be associated with this wall.
	 *
	 * @param world  the world in which to be created
	 * @param width  the width of the wall
	 * @param height  the halfHeight of the wall
	 * @param color  the fill colour of the wall
	 */
	public UnlockableWall(World world, float width, float height, Color color) {
		super(world, width, height);
		setFillColor(color);

		halfHeight = height;
		key = new Key(world, this);
	}

	/**
	 * Sets the position of the associated key.
	 *
	 * @param x  position on the X axis
	 * @param y  position on the Y axis
	 */
	public void setKeyPosition(float x, float y) { key.getBody().setPosition(new Vec2(x, y)); }

	/**
	 * Sets the unlocking type of this object.
	 *
	 * @param unlockingType  the unlocking type
	 */
	public void setUnlockingType(Unlocking unlockingType) {
		this.unlockingType = unlockingType;
	}

	/**
	 * Unlocks the door so that it is no longer an obstacle for the player.
	 */
	public void unlock() {
		switch (unlockingType) {
			case MOVE_UP:
				getBody().move(new Vec2(0, 6 * halfHeight / 4));
				break;
			case HIDE:
				getBody().destroy();
				destroy();
				break;
		}
	}
}

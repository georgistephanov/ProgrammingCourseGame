package buildingblocks;

import bodies.collectibles.Key;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

import java.awt.*;

/**
 * Coloured instance of {@code Wall} which is unlocked when the associated with it {@code Key} gets collected.
 */
public class UnlockableWall extends Wall {
	private Key key;

	/**
	 * Creates this wall, sets its fill colour and creates a {@code Key} object which will be associated with this wall.
	 *
	 * @param world  the world in which to be created
	 * @param height  the height of the wall
	 * @param color  the fill colour of the wall
	 */
	public UnlockableWall(World world, float height, Color color) {
		super(world, height);
		setFillColor(color);

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
	 * Unlocks the door so that it is no longer an obstacle for the player.
	 */
	public void unlock() {
		getBody().move(new Vec2(0, 9));
	}
}

package game;

import bodies.Player;
import city.cs.engine.UserView;
import gui.StatusPanel;
import org.jbox2d.common.Vec2;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * This class is used to detect mouse presses on the screen in order to move the player with the mouse.
 */
public class MouseHandler extends MouseAdapter {

	private final Player player;
	private final UserView view;

	MouseHandler(UserView view, Player player) {
		this.view = view;
		this.player	= player;
	}

	/**
	 * Calculates the angle between the positive horizontal X axis of the player and the mouse position and then
	 * moves the player in the respective direction.
	 * If the angle is in the range [30, 60] degrees in either direction (left or right) the player starts walking
	 * and jumps simultaneously.
	 * If the mouse click is under the player, he will start walking but not jump even if the
	 * mouse click is in the above range.
	 *
	 * @param e  the event of the mouse click
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// Calculate the degrees of the angle from the cosine
		int degrees = (int) Math.toDegrees(Math.acos(findAngle(e)));

		view.requestFocus();

		// If any of the buttons on the status panel has focus return so that no player movement is invoked
		if (StatusPanel.getInstance(player.getWorld()).buttonHasFocus()) {
			return;
		}

		// If the position of the mouse on the Y axis is higher than the player's position on the axis
		if ( player.getPosition().y < view.viewToWorld(e.getPoint()).y ) {

			if (degrees < 30) {
				player.startWalking(GameConstants.VELOCITY);
			}
			else if (degrees <= 60) {
				player.startWalking(GameConstants.VELOCITY);
				player.jump(GameConstants.JUMP_SPEED);
			}
			else if (degrees < 120) {
				player.jump(GameConstants.JUMP_SPEED);
			}
			else if (degrees <= 150) {
				player.startWalking( -(GameConstants.VELOCITY) );
				player.jump(GameConstants.JUMP_SPEED);
			}
			else {
				player.startWalking( -(GameConstants.VELOCITY) );
			}

		} else {
			// The position of the mouse is below the player, therefore do not jump
			if (degrees <= 90) {
				player.startWalking(GameConstants.VELOCITY);
			} else {
				player.startWalking( -(GameConstants.VELOCITY) );
			}
		}
	}

	/**
	 * Finds the angle between the position of the mouse click and the player based on the player's positive X axis.
	 *
	 * @param e  the event of the mouse click
	 * @return  the cosine of the angle between the player and the mouse position based on the player's positive X axis
	 */
	private float findAngle(MouseEvent e) {
		Vec2 origin = new Vec2(player.getPosition().x, player.getPosition().y);

		Vec2 playerUnitVector = new Vec2(1, 0);
		Vec2 mouseUnitVector = new Vec2(view.viewToWorld(e.getPoint())).sub(origin);

		return Vec2.dot(playerUnitVector, mouseUnitVector) / mouseUnitVector.normalize();
	}
}

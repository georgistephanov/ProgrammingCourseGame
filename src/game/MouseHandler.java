package game;

import bodies.Player;
import city.cs.engine.UserView;
import org.jbox2d.common.Vec2;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {

	private Player player;
	private UserView view;

	public MouseHandler(UserView view, Player player) {
		this.view = view;
		this.player	= player;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		int degrees = (int) Math.toDegrees(Math.acos(findAngle(e)));

		if ( player.getPosition().y < view.viewToWorld(e.getPoint()).y ) {
			if (degrees < 30) {
				player.startWalking(GameConstants.VELOCITY);
			} else if (degrees <= 60) {
				player.startWalking(GameConstants.VELOCITY);
				player.jump(GameConstants.JUMP_SPEED);
			} else if (degrees < 120) {
				player.jump(GameConstants.JUMP_SPEED);
			} else if (degrees <= 150) {
				player.startWalking( -(GameConstants.VELOCITY) );
				player.jump(GameConstants.JUMP_SPEED);
			} else {
				player.startWalking( -(GameConstants.VELOCITY) );
			}
		} else {
			if (degrees <= 90) {
				player.startWalking(GameConstants.VELOCITY);
			} else {
				player.startWalking( -(GameConstants.VELOCITY) );
			}
		}
	}

	private float findAngle(MouseEvent e) {
		Vec2 origin = new Vec2(player.getPosition().x, player.getPosition().y);

		Vec2 playerUnitVector = new Vec2(1, 0);
		Vec2 mouseUnitVector = new Vec2(view.viewToWorld(e.getPoint())).sub(origin);

		return Vec2.dot(playerUnitVector, mouseUnitVector) / mouseUnitVector.normalize();
	}
}

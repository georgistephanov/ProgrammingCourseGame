package game;

import city.cs.engine.*;
import game.bodies.Player;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static game.GameConstants.JUMP_SPEED;
import static game.GameConstants.VELOCITY;


public class KeyboardHandler extends KeyAdapter {

	private WorldView view;
	private Player player;

	KeyboardHandler(WorldView view, Player player) {
		this.view = view;
		this.player = player;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_RIGHT:
				player.startWalking(VELOCITY);
				break;
			case KeyEvent.VK_LEFT:
				player.startWalking(-VELOCITY);
				break;
			case KeyEvent.VK_UP:
				player.jump(JUMP_SPEED);
				break;
			default:
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_LEFT:
				player.stopWalking();
				break;
			default:
				break;
		}
	}
}

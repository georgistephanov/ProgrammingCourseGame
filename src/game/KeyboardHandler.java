package game;

import city.cs.engine.*;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyboardHandler extends KeyAdapter {

	private WorldView view;
	private Walker player;

	KeyboardHandler(WorldView view, Walker player) {
		this.view = view;
		this.player = player;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_RIGHT:
				player.startWalking(.5f);
				break;
			case KeyEvent.VK_LEFT:
				player.startWalking(-.5f);
				break;
			default:
				break;
		}
	}
}

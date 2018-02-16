package game;

import bodies.Player;
import buildingblocks.Door;
import city.cs.engine.World;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import static game.GameConstants.JUMP_SPEED;
import static game.GameConstants.VELOCITY;


public class KeyboardHandler extends KeyAdapter {

	private World world;
	private Player player;

	KeyboardHandler(World world, Player player) {
		this.world = world;
		this.player = player;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_D:
				player.startWalking(VELOCITY);
				break;
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_A:
				player.startWalking(-VELOCITY);
				break;
			case KeyEvent.VK_UP:
			case KeyEvent.VK_W:
				player.jump(JUMP_SPEED);
				break;
			case KeyEvent.VK_SPACE:
				if ( !world.isRunning() ) {
					world.start();
				} else if (Door.userEntered) {
					System.out.println("You win!");
					world.stop();
				}
			default:
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
			case KeyEvent.VK_RIGHT:
			case KeyEvent.VK_LEFT:
			case KeyEvent.VK_A:
			case KeyEvent.VK_D:
				player.stopWalking();
				break;
			default:
				break;
		}
	}
}

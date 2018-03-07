package game;

import bodies.Player;
import bodies.gui.StatusPanel;
import buildingblocks.Door;
import city.cs.engine.World;
import levels.LevelManager;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import static game.GameConstants.JUMP_SPEED;
import static game.GameConstants.VELOCITY;

/**
 * Handles the keyboard inputs.
 */
public class KeyboardHandler extends KeyAdapter {

	private World world;
	private Player player;

	KeyboardHandler(World world, Player player) {
		this.world = world;
		this.player = player;
	}

	/**
	 * Invoked when a key has been pressed.
	 * <p>
	 * Current supported key operations are:
	 * <ul>
	 *     <li>Arrows - moving the player</li>
	 *     <li>WASD - moving the player</li>
	 *     <li>Space - restarting the game when the player died or winning the game (when inside the door)</li>
	 * </ul>
	 * </p>
	 *
	 * @param e  the key event
	 */
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
				if ( !world.isRunning() && player.getLives() > 0 ) {
					world.start();
					StatusPanel.getInstance().displayPauseButton();
				} else if (Door.userEntered) {
					int currentLevel = LevelManager.getCurrentLevel();

					if (currentLevel < LevelManager.MAX_LEVEL) {
						LevelManager.displayLevel(currentLevel + 1);
						Door.userEntered = false;
					} else {
						StatusPanel.getInstance().displayWinningMessage();
						System.out.println("You win!");
						world.stop();
					}
				}

			default:
		}
	}

	/**
	 * Invoked when a key has been released.
	 * <p>
	 * Current supported key operations are:
	 * <ul>
	 *     <li>Left/right arrow - stop the player from walking</li>
	 *     <li>A, D - stop the player from walking</li>
	 * </ul>
	 * </p>
	 *
	 * @param e  the key event
	 */
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

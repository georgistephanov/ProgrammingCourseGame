package game;

import bodies.Player;
import gui.StatusPanel;
import buildingblocks.Door;
import city.cs.engine.World;
import levels.LevelManager;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

import static game.GameConstants.JUMP_SPEED;
import static game.GameConstants.VELOCITY;

// TODO: Refactor this method as it should only call methods, not contain logic

/**
 * Handles the keyboard inputs.
 */
public class KeyboardHandler extends KeyAdapter {

	private World world;
	private Player player;

	private StringBuilder password = new StringBuilder();

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
					StatusPanel.getInstance(world).displayPauseButton();
				} else if (Door.hasPlayerEntered()) {
					int currentLevel = LevelManager.getInstance().getCurrentLevel();

					if (currentLevel < LevelManager.MAX_LEVEL) {
						LevelManager.getInstance().displayLevel(currentLevel + 1);
						Door.setPlayerEntered(false);
					} else {
						player.setHasWon(true);
						System.out.println("You win!");
						StatusPanel.getInstance(world).displayWinningMessage();

						new Timer().schedule(new TimerTask() {
							@Override
							public void run() {
								world.stop();
							}
						}, 2000);
					}
				}
				break;
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

			case KeyEvent.VK_P:
				StatusPanel.getInstance(world).clickPauseButton();
				break;

			case KeyEvent.VK_R:
				StatusPanel.getInstance(world).clickRestartButton();
				break;

			case KeyEvent.VK_L:
				password.append('l');
				if ( password.length() != 1 && !password.toString().equals("level")) {
					password = new StringBuilder();
				}
				break;

			case KeyEvent.VK_E:
				if (password.length() > 0) {
					password.append('e');

					if ( !password.toString().equals("le") && !password.toString().equals("leve") ) {
						password = new StringBuilder();
					}
				}
				break;

			case KeyEvent.VK_V:
				if (password.length() > 0) {
					password.append('v');

					if ( !password.toString().equals("lev") ) {
						password = new StringBuilder();
					}
				}
				break;

			case KeyEvent.VK_1:
				if (password.toString().equals("level")) {
					LevelManager.getInstance().jumpToLevel(1);
				}
				if (password.length() > 0) {
					password = new StringBuilder();
				}
				break;

			case KeyEvent.VK_2:
				if (password.toString().equals("level")) {
					LevelManager.getInstance().jumpToLevel(2);
				}
				if (password.length() > 0) {
					password = new StringBuilder();
				}
				break;

			case KeyEvent.VK_3:
				if (password.toString().equals("level")) {
					LevelManager.getInstance().jumpToLevel(3);
				}
				if (password.length() > 0) {
					password = new StringBuilder();
				}
				break;

			default:
				if (password.length() > 0) {
					password = new StringBuilder();
				}
				break;
		}
	}
}

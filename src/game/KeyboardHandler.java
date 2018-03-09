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

/**
 * Handles the keyboard inputs.
 */
public class KeyboardHandler extends KeyAdapter {

	private World world;
	private Player player;

	private StatusPanel statusPanel;
	private LevelManager levelManager;

	private StringBuilder password = new StringBuilder();
	private static final String [] PASSWORDS = {
			"level1", "level2", "level3"
	};

	KeyboardHandler(World world, Player player) {
		this.world = world;
		this.player = player;

		statusPanel = StatusPanel.getInstance(world);
		levelManager = LevelManager.getInstance();
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
					statusPanel.displayPauseButton();
				} else if (Door.hasPlayerEntered()) {

					int currentLevel = levelManager.getCurrentLevel();

					if (currentLevel < LevelManager.MAX_LEVEL) {
						// There is a next level -> display it
						levelManager.displayLevel(currentLevel + 1);
						Door.setPlayerEntered(false);
					} else {
						// The player has won
						player.setHasWon(true);
						statusPanel.displayWinningMessage();
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
				statusPanel.clickPauseButton();
				break;

			case KeyEvent.VK_R:
				statusPanel.clickRestartButton();
				break;

			default:
				password.append(e.getKeyChar());
				checkPassword();
		}
	}

	/**
	 * Checks whether the current value of the entered password matches any of the valid game passwords.
	 * If not, checks whether it is a prefix of any valid password (i.e. password currently being typed).
	 * If none of these is the case, delete the content of the password.
	 */
	private void checkPassword() {

		boolean isPrefix = false;

		for (String p : PASSWORDS) {
			// Check for match with any of the valid game passwords
			if (p.equals(password.toString())) {
				switch (p) {
					case "level1":
						levelManager.displayLevel(1);
						break;
					case "level2":
						levelManager.displayLevel(2);
						break;
					case "level3":
						levelManager.displayLevel(3);
						break;
					default:
				}
			} else if (p.startsWith(password.toString())) {
				// The password could be currently typed
				isPrefix = true;
			}
		}

		// If the password is not a prefix of any existing game password -> delete the content of the password variable
		if ( !isPrefix ) {
			password = new StringBuilder();
		}
	}
}

package game;

import gui.StatusPanel;
import city.cs.engine.*;
import bodies.Player;
import levels.LevelManager;

import javax.swing.*;
import java.awt.*;

import static game.GameConstants.WINDOW_HEIGHT;
import static game.GameConstants.WINDOW_TITLE;
import static game.GameConstants.WINDOW_WIDTH;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Game {

	/** The World in which the bodies move and interact. */
	private final World world = new World();

	/** A graphical display of the world (a specialised JPanel). */
	private final UserView view = new UserView(world, WINDOW_WIDTH, WINDOW_HEIGHT);

	/** The window in which the world will be displayed */
	private final JFrame window = new JFrame(WINDOW_TITLE);

	/** The player object which the user controls to play the game */
	private final Player player;

	/**
	 * Constructs a new instance of the Game and sets up the window and the world.
	 */
	private Game() {
		// Set the window and its settings
		setWindowProperties();

		// make the player
		player = new Player(world);

		// Initialise the LevelManager singleton object and generate and display the first level
		LevelManager.initialiseLevelManager(world, player);
		LevelManager.getInstance().displayLevel(1, false);

		// Register the keyboard and mouse handlers
		view.addKeyListener(new KeyboardHandler(world, player));
		view.addMouseListener(new MouseHandler(view, player));

		// Request focus to allow the keyboard listener to detect input
		view.requestFocus();

		// Start the world
		world.start();
	}

	/**
	 * The main method which starts the game by creating a new instance of this class.
	 */
	public static void main(String[] args) {
		new Game();
	}

	/**
	 * Sets the window and its properties
	 */
	private void setWindowProperties() {
		// Set the default closing option of the window
		window.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Set this window to appear at its default location
		window.setLocationByPlatform(true);

		// Set the status panel at the top of the window
		window.add(StatusPanel.getInstance(world), BorderLayout.NORTH);

		// Add the view
		window.add(view);

		// Prevent the window from being resizable
		window.setResizable(false);

		// Enlarge the window to fit the preferred size and layouts of its sub-components
		window.pack();

		// Set the window as visible
		window.setVisible(true);

		// Show the grid
//		view.setGridResolution(1);
		// Show the debug viewer
//		new DebugViewer(world, 1920, 1080);
	}
}

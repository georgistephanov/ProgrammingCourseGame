package game;

import city.cs.engine.*;
import buildingblocks.*;
import bodies.Player;
import imagemanagers.BackgroundImage;
import levels.Level;
import levels.LevelFactory;
import javax.swing.*;
import java.awt.geom.Point2D;

import static game.GameConstants.WINDOW_HEIGHT;
import static game.GameConstants.WINDOW_TITLE;
import static game.GameConstants.WINDOW_WIDTH;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Game {

	/** The World in which the bodies move and interact. */
	private final World world = new World();

	/** A graphical display of the world (a specialised JPanel). */
	private final UserView view = new UserView(world, WINDOW_WIDTH, WINDOW_HEIGHT);

	/** The frame in which the world will be displayed */
	private final JFrame frame = new JFrame(WINDOW_TITLE);

	/** A LevelFactory instance which is used to generate the levels */
	private final LevelFactory levelFactory;

	/** The player object which the user controls to play the game */
	private final Player player;

	/**
	 * Constructs a new instance of the Game and sets up the window and the world.
	 */
	private Game() {
		// Set the frame and its settings
		setFrame();

		// make the player
		player = new Player(world);

		// Get an instance of the LevelFactory
		levelFactory = LevelFactory.getInstance(world, player);

		// Set the ground, walls and generate the first level
		populateWorld();

		// Register the keyboard and mouse handlers
		view.addKeyListener(new KeyboardHandler(world, player));
		view.addMouseListener(new MouseHandler(view, player));

		// Request focus to allow the keyboard listener to detect input
		view.requestFocus();

		// Set the background image of the world
		new BackgroundImage(world).display();

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
	 * Sets the frame and its properties
	 */
	private void setFrame() {
		// Set the default closing option of the frame
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Set this window to appear at its default location
		frame.setLocationByPlatform(true);

		// Add the view
		frame.add(view);

		// Prevent the frame from being resizable
		frame.setResizable(true);

		// Enlarge the frame to fit the preferred size and layouts of its subcomponents
		frame.pack();

		// Set the frame as visible
		frame.setVisible(true);

		// Show the grid
//		view.setGridResolution(1);
		// Show the debug viewer
		//new DebugViewer(world, 1920, 1080);
	}

	/**
	 * Renders the walls and loads the first level of the game.
	 */
	private void populateWorld() {
		// Render the floor and the walls
		renderWalls();

		// Generate and display level 1
		Level levelOne = levelFactory.getLevel(1);
		levelOne.displayPlatforms();
		levelOne.displayEnemies();
		levelOne.displayCollectibles();
	}

	/**
	 * Creates the ground and the left and right wall of the window.
	 */
	private void renderWalls() {
		// Ground
		new Platform(world, 50, 1).setPosition(0, -26);

		// Left wall
		new Wall(world, 28).setPosition(-48, 0);

		// Right wall
		new Wall(world, 28).setPosition(48, 0);
	}
}

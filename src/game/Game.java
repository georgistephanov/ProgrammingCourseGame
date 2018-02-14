package game;

import city.cs.engine.*;
import buildingblocks.*;
import bodies.Player;
import levels.Level;
import levels.LevelFactory;

import javax.swing.*;

import static game.GameConstants.WINDOW_HEIGHT;
import static game.GameConstants.WINDOW_TITLE;
import static game.GameConstants.WINDOW_WIDTH;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Game {

	/** The World in which the bodies move and interact. */
	private final World world = new World();

	/** A graphical display of the world (a specialised JPanel). */
	private final UserView view = new UserView(world, WINDOW_WIDTH, WINDOW_HEIGHT);

	// display the view in a frame
	private final JFrame frame = new JFrame(WINDOW_TITLE);

	private final LevelFactory levelFactory;

	private Player player;

	/** Initialise a new Game. */
	private Game() {
		// Set the frame and its settings
		setFrame();

		// make a character
		player = new Player(world);

		// Get an instance of the LevelFactory
		levelFactory = LevelFactory.getInstance(world, player);

		// Set the ground, walls and generate the first level
		populateWorld();

		// Register the KeyboardHandler
		view.addKeyListener(new KeyboardHandler(player));
		// Request focus to allow the keyboard listener to detect input
		view.requestFocus();

		world.start();
	}

	/** Run the game. */
	public static void main(String[] args) {
		new Game();
	}

	private void setFrame() {
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setLocationByPlatform(true);
		frame.add(view);
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);

		view.setGridResolution(1);
		new DebugViewer(world, 1920, 1080);
	}

	private void populateWorld() {
		// Render the floor and the walls
		renderWalls();

		// Generate level 1
		Level levelOne = levelFactory.getLevel(1);
		levelOne.displayPlatforms();
		levelOne.displayEnemies();
		levelOne.displayCollectibles();
	}

	private void renderWalls() {
		// Ground
		new Platform(world, 50, 1).setPosition(0, -26);

		// Left wall
		new Wall(world, 28).setPosition(-48, 0);

		// Right wall
		new Wall(world, 28).setPosition(48, 0);
	}
}

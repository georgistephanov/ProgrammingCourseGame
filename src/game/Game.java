package game;

import city.cs.engine.*;
import game.bodies.Platform;
import game.bodies.Player;
import game.bodies.SpikeMan;
import game.bodies.Wall;
import javax.swing.JFrame;
import static game.GameConstants.WINDOW_HEIGHT;
import static game.GameConstants.WINDOW_TITLE;
import static game.GameConstants.WINDOW_WIDTH;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Game {

	/** The World in which the game.bodies move and interact. */
	private final World world = new World();

	/** A graphical display of the world (a specialised JPanel). */
	private final UserView view = new UserView(world, WINDOW_WIDTH, WINDOW_HEIGHT);

	// display the view in a frame
	private final JFrame frame = new JFrame(WINDOW_TITLE);

	private Player player;

	/** Initialise a new Game. */
	private Game() {
		setFrame();
		populateWorld();

		// add this to the view, so coordinates are relative to the view
		view.addKeyListener(new KeyboardHandler(view, player));
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

//		view.setGridResolution(1);
//		JFrame debugView = new DebugViewer(world, 1920, 1080);
	}

	private void populateWorld() {
		renderWalls();
		renderLevel1();

		// make a character
		player = new Player(world);
		new SpikeMan(world);
	}

	private void renderLevel1() {
		// TODO: If these are not used remove the setPosition method and just do it with the regular one
		SolidFixture leftPlatform1 = new Platform(world, 15, .5f).setPosition(-33, -12);
		SolidFixture leftPlatform2 = new Platform(world, 9, .5f).setPosition(-39, 0);
		SolidFixture leftPlatform3 = new Platform(world, 5, .5f).setPosition(-22.5f, 10);
		SolidFixture leftPlatform4 = new Platform(world, 3, .5f).setPosition(-4f, -18);

		SolidFixture midPlatform = new Wall(world, 20).setPosition(0, -5);

		SolidFixture rightPlatform1 = new Platform(world, 19, .5f).setPosition(34, 18);
	}

	private void renderWalls() {
		// make the ground
		SolidFixture ground = new Platform(world, 50, 1).setPosition(0, -26);
		SolidFixture ceiling = new Platform(world, 50, .1f).setPosition(0, 27);
		SolidFixture leftWall = new Wall(world, 28).setPosition(-48, 0);
		SolidFixture rightWall = new Wall(world, 28).setPosition(48, 0);
	}
}

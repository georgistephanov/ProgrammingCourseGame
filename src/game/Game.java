package game;

import city.cs.engine.*;
import game.bodies.Platform;
import game.bodies.PlayerBody;
import org.jbox2d.common.Vec2;

import javax.swing.JFrame;

public class Game {

	/** The World in which the game.bodies move and interact. */
	private final World world = new World();

	/** A graphical display of the world (a specialised JPanel). */
	private final UserView view = new UserView(world, 1920, 1080);

	// display the view in a frame
	private final JFrame frame = new JFrame("Basic world");

	private PlayerBody player;

	/** Initialise a new Game. */
	Game() {
		setFrame();
		populateWorld();

		// add this to the view, so coordinates are relative to the view
		view.addKeyListener(new KeyboardHandler(view, player));
		// Request focus to allow the keyboard listener to detect input
		view.requestFocus();

		// start!
		world.start();
	}

	/** Run the game. */
	public static void main(String[] args) {
		new Game();
	}

	private void setFrame() {
		// quit the application when the game window is closed
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationByPlatform(true);
		// display the world in the window
		frame.add(view);
		// don't let the game window be resized
		frame.setResizable(true);
		// size the game window to fit the world view
		frame.pack();
		// make the window visible
		frame.setVisible(true);

		view.setGridResolution(1);

		renderWalls();

//		JFrame debugView = new DebugViewer(world, 1920, 1080);
	}

	private void populateWorld() {
		renderLevel1();

		// make a character
		player = new PlayerBody(world);
	}

	private void renderLevel1() {
		Body leftPlatform1 = new Platform(world, 15, .5f);
		leftPlatform1.setPosition(new Vec2(-33, -12));

		Body leftPlatform2 = new Platform(world, 9, .5f);
		leftPlatform2.setPosition(new Vec2(-39, 0));

		Body leftPlatform3 = new Platform(world, 5, .5f);
		leftPlatform3.setPosition(new Vec2(-22.5f, 10));

		Body midPlatform = new Platform(world, 1f, 20);
		midPlatform.setPosition(new Vec2(0, -5));

		Body rightPlatform1 = new Platform(world, 19, .5f);
		rightPlatform1.setPosition(new Vec2(34, 18));
	}

	private void renderWalls() {
		// make the ground
		Body ground = new Platform(world, 50, 1);
		ground.setPosition(new Vec2(0, -26));

		Body leftWall = new Platform(world, .5f, 28);
		leftWall.setPosition(new Vec2(-47.5f, 0));

		Body rightWall = new Platform(world, .5f, 28);
		rightWall.setPosition(new Vec2(47.5f, 0));

		Body ceiling = new Platform(world, 50, .1f);
		ceiling.setPosition(new Vec2(0, 27));
	}
}

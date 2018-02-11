package game;

import city.cs.engine.*;
import game.bodies.Enemy;
import game.bodies.WingMan;
import game.buildingblocks.Coin;
import game.buildingblocks.Life;
import game.buildingblocks.Platform;
import game.bodies.Player;
import game.bodies.SpikeMan;
import game.buildingblocks.Wall;
import org.jbox2d.common.Vec2;
import javax.swing.*;

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
//		new DebugViewer(world, 1920, 1080);
	}

	private void populateWorld() {
		renderWalls();
		renderLevel1();

		// make a character
		player = new Player(world);

		// Make SpikeMan characters
		Enemy e1 = new SpikeMan(world);
		e1.setPosition(new Vec2(-20, -23));
		Enemy e2 = new SpikeMan(world);
		e2.setPosition(new Vec2(-35, -11));
		e2.setXDirection(-1);

		// Make WingMan characters
		Enemy wingMan1 = new WingMan(world);
		wingMan1.setPosition(new Vec2(-10, 23));
		wingMan1.setXDirection(-1);
		Enemy wingMan2 = new WingMan(world);
		wingMan2.setPosition(new Vec2(10, 5));
	}

	private void renderLevel1() {
		// TODO: If these are not used remove the setPosition method and just do it with the regular one
		new Platform(world, 15, .5f).setPosition(-32, -12);
		new Platform(world, 9, .5f).setPosition(-38, 0);
		new Platform(world, 5, .5f).setPosition(-35, 10);
		new Platform(world, 3, .5f).setPosition(-4f, -18);
		new Platform(world, 3, .5f).setPosition(-4f, -5);
		new Platform(world, 4, .5f).setPosition(-10f, 13);

		new Wall(world, 20).setPosition(0, -5);

		new Platform(world, 18, .5f).setPosition(29, 18);

		new Coin(world).setPosition(new Vec2(-4, -16));
		new Coin(world).setPosition(new Vec2(-25, -23.5f));
		new Coin(world).setPosition(new Vec2(-35, 12));

		new Life(world).setPosition(new Vec2(45, 20));
	}

	private void renderWalls() {
		// Ground
		new Platform(world, 50, 1).setPosition(0, -26);

		// Ceiling
		new Platform(world, 50, .1f).setPosition(0, 27);

		// Left wall
		new Wall(world, 28).setPosition(-48, 0);

		// Right wall
		new Wall(world, 28).setPosition(48, 0);
	}
}

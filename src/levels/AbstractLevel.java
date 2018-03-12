package levels;

import bodies.Player;
import bodies.factories.StaticFactory;
import gui.AmountJLabel;
import buildingblocks.Door;
import buildingblocks.Platform;
import buildingblocks.Wall;
import city.cs.engine.Body;
import city.cs.engine.World;
import gui.StatusPanel;
import org.jbox2d.common.Vec2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidParameterException;
import java.util.stream.Stream;

/**
 * Abstract implementation of the Level class which keeps references to the {@code World},
 * {@code Player} and the {@code Door} objects associated with the level.
 */
abstract class AbstractLevel implements Level {
	final World world;
	final Door door;
	private String fileDirectory = "data/levels/level?/";

	AbstractLevel(World world, int levelNumber) {
		this.world = world;

		// Update the level number on the status panel
		AmountJLabel levelLabel = StatusPanel.getInstance(world).getLevelLabel();
		levelLabel.firePropertyChange(AmountJLabel.PROPERTY_NAME, levelLabel.getAmount(), levelNumber);

		// Destroy all present bodies except the player
		for (Body body : world.getDynamicBodies()) {
			if (body instanceof Player) {
				continue;
			}

			body.destroy();
		}
		for (Body body : world.getStaticBodies()) {
			body.destroy();
		}

		// Set the player and the door at their default positions
		door = new Door(world);

		// Set the correct file directory
		fileDirectory = fileDirectory.replace('?', Character.forDigit(levelNumber, 10));
		// Initialise the static factory
		StaticFactory.initialise(world);
	}

	@Override
	public void displayPlatforms() {
		// Set the position of the ground platform and the walls
		new Platform(world, 50, 1).setPosition(0, -26);
		new Wall(world, 48).setPosition(-48, 20);
		new Wall(world, 48).setPosition(48, 20);

		// Read the platforms from the file and create them
		String filePath = fileDirectory + "platforms.txt";

		try (Stream<String> file = Files.lines(Paths.get(filePath))) {
			file.forEach(StaticFactory::createPlatform);
		} catch (IOException ioe) {
			System.out.println("There was a problem with the file located at " + filePath);
			ioe.printStackTrace();
		} catch (InvalidParameterException ipe) {
			System.out.println("The file located at " + filePath + " contains line(s) with invalid format.");
			ipe.printStackTrace();
		}
	}

	@Override
	public void displayEnemies() {
		String filePath = fileDirectory + "enemies.txt";

		try (Stream<String> file = Files.lines(Paths.get(filePath))) {
			file.forEach(StaticFactory::createEnemy);
		} catch (IOException ioe) {
			System.out.println("There was a problem with the file located at " + filePath);
			ioe.printStackTrace();
		} catch (InvalidParameterException ipe) {
			System.out.println("The file located at " + filePath + " contains line(s) with invalid format.");
			ipe.printStackTrace();
		}
	}

	@Override
	public void displayCollectibles() {
		String filePath = fileDirectory + "collectibles.txt";

		try (Stream<String> file = Files.lines(Paths.get(filePath))) {
			file.forEach(StaticFactory::createCollectible);
		} catch (IOException ioe) {
			System.out.println("There was a problem with the file located at " + filePath);
			ioe.printStackTrace();
		} catch (InvalidParameterException ipe) {
			System.out.println("The file located at " + filePath + " contains line(s) with invalid format.");
			ipe.printStackTrace();
		}
	}

	@Override
	public void resetLevel() {
		for (Body body: world.getDynamicBodies()) {
			if ( !(body instanceof Player) ) {
				body.destroy();
			} else {
				((Player) body).setLinearVelocity(new Vec2(0, 0));
			}
		}

		displayEnemies();
	}
}

package levels;

import bodies.Player;
import bodies.collectibles.Coin;
import bodies.collectibles.Life;
import bodies.enemies.FlyMan;
import bodies.enemies.SpikeMan;
import bodies.enemies.WingMan;
import bodies.enemies.Zombie;
import game.GameConstants;
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
		// Set the world of the LevelBuilder
		LevelBuilder.setWorld(world);
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
			file.forEach(LevelBuilder::createPlatform);
		} catch (IOException ioe) {
			System.out.println("There was a problem with the file located at " + filePath);
			ioe.printStackTrace();
		}
	}

	@Override
	public void displayEnemies() {
		String filePath = fileDirectory + "enemies.txt";

		try (Stream<String> file = Files.lines(Paths.get(filePath))) {
			file.forEach(LevelBuilder::createEnemy);
		} catch (IOException ioe) {
			System.out.println("There was a problem with the file located at " + filePath);
			ioe.printStackTrace();
		}
	}

	@Override
	public void displayCollectibles() {
		String filePath = fileDirectory + "collectibles.txt";

		try (Stream<String> file = Files.lines(Paths.get(filePath))) {
			file.forEach(LevelBuilder::createCollectible);
		} catch (IOException ioe) {
			System.out.println("There was a problem with the file located at " + filePath);
			ioe.printStackTrace();
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

	private static class LevelBuilder {
		private static World world;

		static void setWorld(World world) {
			LevelBuilder.world = world;
		}

		static void createPlatform(String line) {
			if (stringsInLine(line) == 4) {
				float [] parameters = getParametersFromFileLine(line);

				String platformType = line.split("\t")[0];
				float size = parameters[0];
				float x = parameters[1];
				float y = parameters[2];

				switch (platformType) {
					case "p":
						new Platform(world, size).setPosition(x, y);
						break;
					case "w":
						new Wall(world, size).setPosition(x, y);
						break;
				}
			}
		}

		static void createEnemy(String line) {
			if (stringsInLine(line) == 4) {
				float [] parameters = getParametersFromFileLine(line);

				String enemyType = line.split("\t")[0];
				boolean walkRight = parameters[0] == 1;
				float x = parameters[1];
				float y = parameters[2];

				switch (enemyType) {
					case "s":
						if (walkRight) {
							new SpikeMan(world).setPosition(x, y);
						} else {
							new SpikeMan(world).setMovementDirection(GameConstants.MovementDirections.LEFT).setPosition(x, y);
						}
						break;
					case "w":
						if (walkRight) {
							new WingMan(world).setPosition(x, y);
						} else {
							new WingMan(world).setMovementDirection(GameConstants.MovementDirections.LEFT).setPosition(x, y);
						}
						break;
					case "f":
						if (walkRight) {
							new FlyMan(world).setPosition(x, y);
						} else {
							new FlyMan(world).setMovementDirection(GameConstants.MovementDirections.LEFT).setPosition(x, y);
						}
						break;
					case "z":
						if (walkRight) {
							new Zombie(world).setPosition(x, y);
						} else {
							new Zombie(world).setMovementDirection(GameConstants.MovementDirections.LEFT).setPosition(x, y);
						}
				}
			}
		}

		static void createCollectible(String line) {
			if (stringsInLine(line) == 3) {
				float [] parameters = getParametersFromFileLine(line);

				String collectibleType = line.split("\t")[0];
				float x = parameters[0];
				float y = parameters[1];

				switch (collectibleType) {
					case "c":
						new Coin(world).setPosition(x, y);
						break;
					case "l":
						new Life(world).setPosition(x, y);
						break;
				}
			}
		}

		static private int stringsInLine(String line) {
			return line.split("\t").length;
		}

		static private float [] getParametersFromFileLine(String line) {
			String [] stringParameters = line.split("\t");
			float [] parameters = new float[stringParameters.length - 1];

			try {
				// Start from 1 to avoid the object which is specified to be created
				for (int i = 1; i < stringParameters.length; i++) {
					parameters[i - 1] = Float.valueOf(stringParameters[i]);
				}
			} catch (NumberFormatException nfe) {
				nfe.printStackTrace();
				System.out.println("Incorrect parameter in the level file.");
			}

			return parameters;
		}
	}
}

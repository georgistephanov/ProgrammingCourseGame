package game;

import city.cs.engine.SoundClip;

/**
 * This class is responsible for all music/sound manipulations that should be done in the game.
 */
public class GameSounds {
	private static SoundClip backgroundMusic;
	private static SoundClip jump;
	private static SoundClip coin;
	private static SoundClip life;
	private static SoundClip unlockDoor;
	private static SoundClip teleport;
	private static SoundClip kill;

	/**
	 * Plays the background music for the current level on a loop.
	 *
	 * @param levelNumber  the number of the current level that is being player
	 */
	public static void startBackgroundMusic(int levelNumber) {
		if (backgroundMusic != null) {
			backgroundMusic.stop();
			backgroundMusic.close();
		}

		try {
			switch (levelNumber) {
				case 1:
					backgroundMusic = new SoundClip("data/sounds/level1.wav");
					break;
				case 2:
					backgroundMusic = new SoundClip("data/sounds/level2.wav");
					break;
				case 3:
					backgroundMusic = new SoundClip("data/sounds/level3.wav");
					break;
				default:
					throw new RuntimeException("This game supports only levels 1, 2 and 3.");
			}

			backgroundMusic.setVolume(.75);
			backgroundMusic.loop();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Pauses the level's background music.
	 */
	public static void pauseBackgroundMusic() {
		if (backgroundMusic != null) {
			backgroundMusic.pause();
		}
	}

	/**
	 * Resumes the level's background music.
	 */
	public static void resumeBackgroundMusic() {
		if (backgroundMusic != null) {
			backgroundMusic.resume();
		}
	}

	/**
	 * Plays the jump sound.
	 */
	public static void playJumpSound() {
		playSound(jump, "data/sounds/jump.wav");
	}

	/**
	 * Plays the coin sound.
	 * This method is intended to be called when a coin is collected by the player.
	 */
	public static void playCoinSound() {
		playSound(coin, "data/sounds/coin.wav");
	}

	/**
	 * Plays the life sound.
	 * This method is intended to be called when a life is collected by the player.
	 */
	public static void playLifeSound() {
		playSound(life, "data/sounds/life.wav");
	}

	/**
	 * Plays the unlock door sound.
	 * This method is intended to be called when a key is collected.
	 */
	public static void playUnlockDoorSound() {
		playSound(unlockDoor, "data/sounds/unlock_door.wav");
	}

	/**
	 * Plays the teleportation sound.
	 * This method is intended to be called when the player teleports from one teleport to its child location.
	 */
	public static void playTeleportSound() {
		playSound(teleport, "data/sounds/teleport.wav");
	}

	/**
	 * Plays the kill sound.
	 * This method is intended to be called when the player collides with an enemy.
	 */
	public static void playKillSound() {
		playSound(kill, "data/sounds/kill.wav");
	}

	/**
	 * Plays the sound of the {@code SoundClip} variable which is passed as a parameter.
	 * If the variable is null this method tries to load the sound from the path to the variable.
	 *
	 * @param variable  the sound which should be played (possibly null)
	 * @param path  the path from which the sound should be loaded to the variable if it is null
	 */
	private static void playSound(SoundClip variable, String path) {
		if (variable == null) {
			try {
				variable = new SoundClip(path);
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		}

		variable.play();
	}
}

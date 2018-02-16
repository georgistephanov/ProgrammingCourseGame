package bodies;

import city.cs.engine.*;
import bodies.enemies.Enemy;
import org.jbox2d.common.Vec2;

import static game.GameConstants.MovementDirections.*;

public class Player extends CustomWalker implements StepListener, CollisionListener {

	private static final Shape playerShape = new PolygonShape(1.7485f,-3.2175f,
			1.6965f,2.366f, 1.5015f,2.457f, -0.8125f,1.846f, -1.69f,0.871f,
			-1.7745f,-1.976f, -1.339f,-3.2305f);

	private static BodyImage standingImage = new BodyImage("data/player/player_stand.png", 6.5f);
	private static BodyImage jumpingImage = new BodyImage("data/player/player_jump.png", 6.5f);
	private static BodyImage fallingImage = new BodyImage("data/player/player_fall.png", 6.5f);
	private static BodyImage [] walkingImages = {
			new BodyImage("data/player/player_walk1.png", 6.5f),
			new BodyImage("data/player/player_walk2.png", 6.5f)
	};

	private int coins = 0;
	private int lives = 3;

	public Player(World world) {
		super( new CustomWalker.Builder(world, playerShape)
				.standingImage(standingImage)
				.walkingImages(walkingImages)
				.jumpingImage(jumpingImage)
				.fallingImage(fallingImage) );

		setGravityScale(2);

		world.addStepListener(this);
		addCollisionListener(this);
	}

	public int getCoins() {
		return coins;
	}

	public void addCoin() {
		setCoins(coins + 1);
	}

	private void setCoins(int coins) {
		// TODO: Add an observer to notify the world to display the correct amount of coins

		this.coins = coins;
		System.out.println("Coins: " + coins);
	}

	public int getLives() {
		return lives;
	}

	public void addLife() {
		setLives(lives + 1);
	}

	private void setLives(int lives) {
		// TODO: Add an observer to notify the world to display the correct amount of lives

		this.lives = lives;
		System.out.println("Lives: " + lives);
	}

	@Override
	public void startWalking(float speed) {
		super.startWalking(speed);

		// Display the correct image based on which direction on the X axis the walker is moving
		if (speed > 0) {
			imageManager.changeImage(RIGHT);
		} else if (speed < 0) {
			imageManager.changeImage(LEFT);
		}
	}

	@Override
	public void preStep(StepEvent stepEvent) { }

	@Override
	public void postStep(StepEvent stepEvent) {
		Vec2 velocity = getLinearVelocity();

		if (velocity.y > 0) {
			imageManager.changeImage(JUMPING);
		} else if (velocity.y < 0) {
			imageManager.changeImage(FALLING);
		} else if (velocity.x == 0) {
			imageManager.changeImage(NOT_MOVING);
		}
	}

	public void collide(CollisionEvent e) {
		if ( e.getOtherBody() instanceof Enemy) {
			System.out.println("Ouch...");
			System.out.println("Press space to start over");

			getWorld().stop();
		}
	}
}
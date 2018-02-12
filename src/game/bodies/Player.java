package game.bodies;

import city.cs.engine.*;
import game.buildingblocks.Coin;
import game.buildingblocks.Door;
import game.images.WalkerImageManager;
import org.jbox2d.common.Vec2;
import static game.GameConstants.MovementDirections.*;

public class Player extends CustomWalker implements StepListener, CollisionListener {

	private static final Shape playerShape = new PolygonShape(1.7485f,-3.2175f,
			1.6965f,2.366f, 1.5015f,2.457f, -0.8125f,1.846f, -1.69f,0.871f,
			-1.7745f,-1.976f, -1.339f,-3.2305f);

	private static BodyImage standingImage = new BodyImage("data/player_stand.png", 6.5f);
	private static BodyImage jumpingImage = new BodyImage("data/player_jump.png", 6.5f);
	private static BodyImage fallingImage = new BodyImage("data/player_fall.png", 6.5f);
	private static BodyImage [] walkingImages = {
			new BodyImage("data/player_walk1.png", 6.5f),
			new BodyImage("data/player_walk2.png", 6.5f)
	};

	private int coins = 0;

	public Player(World world) {

		super(world, playerShape, standingImage, walkingImages, jumpingImage, fallingImage);

		setGravityScale(2);

		addCollisionListener(this);
		world.addStepListener(this);
	}

	public int getCoins() {
		return coins;
	}

	private void setCoins(int coins) {
		// TODO: Add an observer to notify the world to display the correct amount of coins

		this.coins = coins;
		System.out.println("Coins: " + coins);
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
		if ( e.getOtherBody() instanceof Enemy ) {
			System.out.println("Ouch");
		} else if (e.getOtherBody() instanceof Coin) {
			e.getOtherBody().destroy();
			setCoins(++coins);
		} else if (e.getOtherBody() instanceof Door) {
			System.out.println("You won!");
		}
	}
}
package game.bodies;

import city.cs.engine.*;

public class SpikeMan extends Enemy {
	private static final Shape spikeManShape = new PolygonShape(-1.16f,-1.99f, -1.15f,-0.22f, -0.03f,1.98f,
			1.14f,-0.2f, 1.15f,-1.98f);

	private static final BodyImage stand = new BodyImage("data/spikeMan_stand.png", 4);
	private static final BodyImage walk1 = new BodyImage("data/spikeMan_walk1.png", 4);
	private static final BodyImage walk2 = new BodyImage("data/spikeMan_walk2.png", 4);

	public SpikeMan(World world) {
		super(world, spikeManShape, stand);
		setWalkImages(walk1, walk2);
		setStepsChangeImage(5);
	}
}

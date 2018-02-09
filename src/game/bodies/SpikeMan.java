package game.bodies;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class SpikeMan extends Enemy implements StepListener {
	private static final Shape spikeManShape = new PolygonShape(-1.16f,-1.99f, -1.15f,-0.22f, -0.03f,1.98f,
			1.14f,-0.2f, 1.15f,-1.98f);

	public SpikeMan(World world) {
		super(world, spikeManShape, new BodyImage("data/spikeMan_stand.png", 4));
		setPosition(new Vec2(-20, -23));
		setWalkImages(new BodyImage("data/spikeMan_walk1.png", 4), new BodyImage("data/spikeMan_walk2.png", 4));
	}
}

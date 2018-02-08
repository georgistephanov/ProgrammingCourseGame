/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.bodies;

import city.cs.engine.BodyImage;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import city.cs.engine.Walker;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

public class PlayerBody extends Walker {

	private static final Shape shape = new PolygonShape(1.7485f,-3.2175f,
			1.6965f,2.366f, 1.5015f,2.457f, -0.8125f,1.846f, -1.69f,0.871f,
			-1.7745f,-1.976f, -1.339f,-3.2305f);

	public PlayerBody(World w) {
		this(w, shape);
	}

	@Override
	public void startWalking(float speed) {
		move(new Vec2(.1f, 0));
	}

	private PlayerBody(World w, Shape s) {
		super(w, s);
		addImage(new BodyImage("data/player_stand.png", 6.5f));
		setPosition(new Vec2(-45, -20));
	}
}

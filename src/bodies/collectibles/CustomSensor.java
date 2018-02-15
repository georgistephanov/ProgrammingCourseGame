package bodies.collectibles;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

public class CustomSensor extends Sensor implements SensorListener {

	public CustomSensor(Body body, Shape shape) {
		super(body, shape);

		addSensorListener(this);
	}

	public void setPosition(float x, float y) {
		getBody().setPosition(new Vec2(x, y));
	}

	public void beginContact(SensorEvent e) {}
	public void endContact(SensorEvent e) {}
}

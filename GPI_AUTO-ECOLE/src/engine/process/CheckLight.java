package engine.process;

import engine.map.Block;
import engine.mobile.Car;
import engine.mobile.Light;

public class CheckLight {

	private Car car;
	private Light light;

	public CheckLight(Car car, Light light) {
		this.car = car;
		this.light = light;
	}

	public boolean verifposition() {
		int linePositionLight = light.getPosition().getLine();

		int columnPositionLight = light.getPosition().getColumn();
		Block lightPositionPossible1 = new Block(linePositionLight, columnPositionLight - 1);
		Block lightPositionPossible2 = new Block(linePositionLight, columnPositionLight + 1);

		if (car.getPosition().compareBlock(lightPositionPossible1)
				|| car.getPosition().compareBlock(lightPositionPossible2)) {
			return true;
		} else {
			return false;
		}
	}

}

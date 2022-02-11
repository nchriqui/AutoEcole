
package engine.process;

import java.util.Timer;

import engine.map.Block;
import engine.map.Map;
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
		System.out.println(linePositionLight+"|"+columnPositionLight + " : " + car.getPosition());
		Block lightPositionPossible1 = new Block(linePositionLight, columnPositionLight-1);
		Block lightPositionPossible2 = new Block(linePositionLight, columnPositionLight+1);
		System.out.println(lightPositionPossible1 + " : " + car.getPosition());
		
		System.out.println(lightPositionPossible2 + " : " + car.getPosition());
		
		if (car.getPosition().compareBlock(car.getPosition(), lightPositionPossible1) || car.getPosition().compareBlock(car.getPosition(), lightPositionPossible2)) {
			return true;
		}else {
			return false;
		}
	}
	

	
	
	
	
	
}

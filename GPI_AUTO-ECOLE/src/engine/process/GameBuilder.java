package engine.process;

import engine.Map.Map;

import engine.mobile.Car;
import engine.Map.Block;
import Config.GameConfiguration;

public class GameBuilder {

	public static Map buildMap() {
		return new Map(GameConfiguration.LINE_COUNT, GameConfiguration.COLUMN_COUNT);

	}

	public static MobileElementManager buildInitMobile(Map map) {
		MobileElementManager manager = new MobileElementManager(map);

		intializeCar(map, manager);
		return manager;
	}

	public static void intializeCar(Map map, MobileElementManager manager) {
		Block block = map.getBlock(GameConfiguration.LINE_COUNT - 2, GameConfiguration.COLUMN_COUNT - 2);
		Car car = new Car(block);
		manager.setCar(car);
	}

}

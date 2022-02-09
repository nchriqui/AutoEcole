package engine.process;

import engine.map.Block;
import engine.map.Map;
import engine.mobile.Car;
import config.GameConfiguration;

public class GameBuilder {

	public static Map buildMap() {
		return new Map(GameConfiguration.WINDOW_WIDTH/GameConfiguration.BLOCK_SIZE, GameConfiguration.COLUMN_COUNT);

	}

	public static MobileElementManager buildInitMobile(Map map) {
		MobileElementManager manager = new MobileElementManager(map);

		intializeCar(map, manager);
		
		return manager;
	}

	public static void intializeCar(Map map, MobileElementManager manager) {
		Block block = map.getBlock(GameConfiguration.LINE_COUNT - 2, (GameConfiguration.COLUMN_COUNT - 1) /2);
		Car car = new Car(block);
		manager.setCar(car);
	}

}

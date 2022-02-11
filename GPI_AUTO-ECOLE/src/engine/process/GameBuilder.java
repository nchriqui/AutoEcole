package engine.process;

import engine.map.Block;
import engine.map.Map;
import engine.mobile.Car;
import engine.mobile.Light;
import config.GameConfiguration;

public class GameBuilder {

	public static Map buildMap() {
		return new Map(GameConfiguration.WINDOW_WIDTH/GameConfiguration.BLOCK_SIZE, GameConfiguration.COLUMN_COUNT);

	}

	public static MobileElementManager buildInitMobile(Map map) {
		MobileElementManager manager = new MobileElementManager(map);
		manager.setLastMoveRight(true);
		
		intializeCar(map, manager);
		initializeLight(map,manager);
		
		return manager;
	}

	public static void intializeCar(Map map, MobileElementManager manager) {
		Block block = map.getBlock(GameConfiguration.LINE_COUNT - 2, (GameConfiguration.COLUMN_COUNT - 1) /2);
		Car car = new Car(block);
		manager.setCar(car);
	}
	
	public static void initializeLight(Map map, MobileElementManager manager) {

		Block blockRightLight = map.getBlock(5, 21);
		Block blockLeftLight = map.getBlock(5, 3);
		
		Light LeftLight = new Light(blockLeftLight,false);
		manager.setLeftLight(LeftLight);
		
		
		Light rightLight = new Light(blockRightLight,false);
		manager.setRightLight(rightLight);
	}


}

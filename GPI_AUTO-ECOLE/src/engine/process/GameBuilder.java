package engine.process;

import engine.map.Block;
import engine.map.Map;
import engine.mobile.Car;
import config.GameConfiguration;
/**
 * The essential class to the good initialization of the game and the objects.
 * 
 * @author Auto-Ecole
 */
public class GameBuilder {

	public static Map buildMap() {
		return new Map(GameConfiguration.WINDOW_WIDTH / GameConfiguration.BLOCK_SIZE, GameConfiguration.COLUMN_COUNT);

	}

	public static MobileElementManager buildInitMobile(Map map) {
		MobileElementManager manager = new MobileElementManager(map);
		manager.setLastMoveUp(true);
		manager.setLastMoveDown(false);
		manager.setLastMoveLeft(false);
		manager.setLastMoveRight(false);

		intializeCar(map, manager);

		return manager;
	}

	public static void intializeCar(Map map, MobileElementManager manager) {
		Block block = map.getBlock(GameConfiguration.LINE_COUNT - 2, 1);
		Car car = new Car(block);
		car.setLastPosition(new Block(block.getLine() + 1, block.getColumn()));
		car.setVitesse(0);
		manager.setCar(car);
	}

}

package config;

/**
 * Copyright SEDAMOP - Software Engineering
 * 
 * @author tianxiao.liu@cyu.fr
 *
 */
public class GameConfiguration {
	public static final int WINDOW_WIDTH = 400;
	public static final int WINDOW_HEIGHT = 800;
	
	public static final int BLOCK_SIZE = 40;
	
	public static final int LINE_COUNT = WINDOW_HEIGHT / BLOCK_SIZE;
	public static final int COLUMN_COUNT = WINDOW_WIDTH / BLOCK_SIZE;
	
	public static final int GAME_SPEED = 1000;
	
	public static final int MAX_BOMB_COUNT = 3;
	
	public static final int BOMB_EXPLOSION_DELAY = 3;
	

}

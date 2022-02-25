package config;

public class GameConfiguration {

	public static final int WINDOW_WIDTH = 1000;
	public static final int WINDOW_HEIGHT = 480;

	public static final int DIFF_ROAD_POSITION = 2;

	public static final int DIFF_LIGHT_POSITION = 4;

	public static final int BLOCK_SIZE = 40;

	public static final int LINE_COUNT = WINDOW_HEIGHT / BLOCK_SIZE;// 12
	public static final int COLUMN_COUNT = WINDOW_WIDTH / BLOCK_SIZE;// 25

	public static final int GAME_SPEED = 1;
//	public static final int CHRONO_SPEED = 500;
	public static final int GAME_MINUTE_DURATION = 1;
	public static final int GAME_SECONDE_DURATION = 59;
	public static int SCORE = 40;

	public static boolean GAME_RUN = true;

}

package TestGame;

import gui.Main;

public class Test {
	public static void main(String[] args) {

		Main gameMain = new Main("driving school");

		Thread gameThread = new Thread(gameMain);
		gameThread.start();
	}
}

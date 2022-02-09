package testGame;

import gui.MainGUI;

public class Test {
	public static void main(String[] args) {

		MainGUI gameMain = new MainGUI("driving school");

		Thread gameThread = new Thread(gameMain);
		gameThread.start();
	}
}

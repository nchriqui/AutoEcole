package testGame;

import gui.MainGUI;

public class Test {
	public static void main(String[] args) {

		MainGUI gameMain = new MainGUI("Driving school");

		Thread gameThread = new Thread(gameMain);
		gameThread.start();
	}
}

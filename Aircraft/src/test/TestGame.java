package test;

import gui.MainGUI;

/**
 * Copyright SEDAMOP - Software Engineering
 * 
 * @author tianxiao.liu@cyu.fr
 *
 */
public class TestGame {
	public static void main(String[] args) {

		MainGUI gameMainGUI = new MainGUI("Aircraft game");

		Thread gameThread = new Thread(gameMainGUI);
		gameThread.start();
	}
}

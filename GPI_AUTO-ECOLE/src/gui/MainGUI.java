package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

import config.GameConfiguration;
import engine.map.Map;
import engine.process.CarMoveUtility;
import engine.process.CheckLight;
import engine.process.GameBuilder;
import engine.process.GameUtility;
import engine.process.MobileElementManager;

import java.awt.Image;

public class MainGUI extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;

	private Map map;

	private final static Dimension preferredSize = new Dimension(GameConfiguration.WINDOW_WIDTH,
			GameConfiguration.WINDOW_HEIGHT);

	private MobileElementManager manager;

	private PaintStrategy paintStrategy = new PaintStrategy();

	private GameDisplay dashboard;

	private Image imageCar;
	private Image imageLeftLight;
	private Image imageRightLight;

	private CheckLight checkLeftLight;
	private CheckLight checkRightLight;

	private CarMoveUtility carMoveUtility = new CarMoveUtility();

	public MainGUI(String title) {
		super(title);
		init();
	}

	private void init() {

		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		KeyControls keyControls = new KeyControls();
		this.addKeyListener(keyControls);

		map = GameBuilder.buildMap();
		manager = GameBuilder.buildInitMobile(map);

		dashboard = new GameDisplay(map, manager);

		dashboard.setPreferredSize(preferredSize);
		contentPane.add(dashboard, BorderLayout.CENTER);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		setPreferredSize(preferredSize);
		setResizable(false);
		setLocationRelativeTo(null);
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(GameConfiguration.GAME_SPEED);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}

			dashboard.repaint();
		}
	}

	private class KeyControls implements KeyListener {

		@Override
		public void keyPressed(KeyEvent event) {
			int keyCode = event.getKeyCode();

			switch (keyCode) {
			case 37: // FLECHE GAUCHE
				// si le dernier deplacement était en haut , en bas ou à gauche
				if (carMoveUtility.checkMoveLeft(manager)) {
					System.out.println("DEPLACEMENT A GAUCHE");
					manager.moveLeftCar();

					imageCar = paintStrategy.getImageLeft();
					paintStrategy.setImage(imageCar);
					dashboard.setPaintStrategy(paintStrategy);

					// Sauvegarde du dernier deplacement efffectue
					carMoveUtility.saveLastMove(keyCode, manager);
				} else {
					System.out.println("DEPLACEMENT IMPOSSIBLE");
				}
				break;
			case 38: // FLECHE DU HAUT
				// si le dernier deplacement était a gauche , a droite ou en haut
				if (carMoveUtility.checkMoveUp(manager)) {
					System.out.println("DEPLACEMENT EN HAUT");
					manager.moveUpCar();

					imageCar = paintStrategy.getImageUp();
					paintStrategy.setImage(imageCar);
					dashboard.setPaintStrategy(paintStrategy);

					// Sauvegarde du dernier deplacement efffectue
					carMoveUtility.saveLastMove(keyCode, manager);
				} else {
					System.out.println("DEPLACEMENT IMPOSSIBLE");
				}
				break;

			case 39: // FLECHE DROITE
				// si le dernier deplacement était en haut , en bas ou a droite
				if (carMoveUtility.checkMoveRight(manager)) {
					System.out.println("DEPLACEMENT A DROITE");
					manager.moveRightCar();

					imageCar = paintStrategy.getImageRight();
					paintStrategy.setImage(imageCar);
					dashboard.setPaintStrategy(paintStrategy);

					// Sauvegarde du dernier deplacement efffectue
					carMoveUtility.saveLastMove(keyCode, manager);
				} else {
					System.out.println("DEPLACEMENT IMPOSSIBLE");
				}
				break;
			case 40: // DEPLACEMENT EN BAS
				// si le dernier deplacement était a gauche , a droite ou en bas
				if (carMoveUtility.checkMoveBottom(manager)) {
					System.out.println("DEPLACEMENT EN BAS ");
					manager.moveDownCar();

					imageCar = paintStrategy.getImageDown();
					paintStrategy.setImage(imageCar);
					dashboard.setPaintStrategy(paintStrategy);

					// Sauvegarde du dernier deplacement efffectue
					carMoveUtility.saveLastMove(keyCode, manager);
				} else {
					System.out.println("DEPLACEMENT IMPOSSIBLE");
				}
				break;
			default:
				break;
			}

			checkLeftLight = new CheckLight(manager.getCar(), manager.getLeftLight());
			checkRightLight = new CheckLight(manager.getCar(), manager.getRightLight());
			if ((manager.getRightLight().getGo() == false && checkRightLight.verifposition() == true)) {
				imageRightLight = paintStrategy.getGreenRightLight();
				paintStrategy.setRedRightLight(imageRightLight);
				dashboard.setPaintStrategy(paintStrategy);
				System.out.println("LE FEU DE DROITE EST PASSE AU VERT");

				Timer timer = new Timer();
				timer.schedule(new TimerTask() {

					@Override
					public void run() {
						System.out.println("FIN DU TIMER");
						System.out.println("LE FEU DE DROITE REPASSE AU ROUGE");
						paintStrategy.setRedRightLight(GameUtility.readImage("src/Images/redLight.png"));
						dashboard.setPaintStrategy(paintStrategy);
						manager.getRightLight().setGo(false);
						System.out.println(manager.getRightLight().getGo());
					}
				}, 5000);
			}

			if ((manager.getLeftLight().getGo() == false && checkLeftLight.verifposition() == true)) {
				manager.getLeftLight().setGo(true);
				imageLeftLight = paintStrategy.getGreenLeftLight();
				paintStrategy.setRedLeftLight(imageLeftLight);
				dashboard.setPaintStrategy(paintStrategy);
				System.out.println("LE FEU DE GAUCHE EST PASSE AU VERT");

				Timer timer = new Timer();
				timer.schedule(new TimerTask() {

					@Override
					public void run() {
						System.out.println("FIN DU TIMER");
						System.out.println("LE FEU DE GAUCHE REPASSE AU ROUGE");
						paintStrategy.setRedLeftLight(GameUtility.readImage("src/Images/redLight.png"));
						dashboard.setPaintStrategy(paintStrategy);
						manager.getLeftLight().setGo(false);
						System.out.println(manager.getLeftLight().getGo());
					}
				}, 5000);

			}
		}

		@Override
		public void keyTyped(KeyEvent e) {

		}

		@Override
		public void keyReleased(KeyEvent e) {

		}
	}

}

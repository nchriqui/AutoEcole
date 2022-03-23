package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import chrono.Chronometer;
import config.GameConfiguration;
import engine.map.Map;
import engine.process.CarMoveUtility;
import engine.process.GameBuilder;
import engine.process.MobileElementManager;

import java.awt.Image;

public class MainGUI extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;

	private Map map;

	private static Font font = new Font(Font.MONOSPACED, Font.BOLD, 20);
	private final static Dimension preferredSize = new Dimension(GameConfiguration.WINDOW_WIDTH,
			GameConfiguration.WINDOW_HEIGHT);

	private MobileElementManager manager;

	private Chronometer chronometer = new Chronometer();

	private JLabel timeLabel = new JLabel("Temps restant:");
	private JLabel timeValue = new JLabel("");
	private JLabel scoreLabel = new JLabel("Score");
	private JLabel scoreValue = new JLabel("");

	private JPanel control = new JPanel();

	private PaintStrategy paintStrategy = new PaintStrategy();

	private GameDisplay dashboard;

	private Image imageCar;

	private CarMoveUtility carMoveUtility = new CarMoveUtility();

	private int turnNumber = 0;

	public MainGUI(String title) {
		super(title);
		init();
	}

	private void init() {
		chronometer.init();
		GameConfiguration.SCORE = 40;
		GameConfiguration.GAME_RUN = true;

		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		control.setLayout(new GridLayout(4, 1));

		timeLabel.setFont(font);
		control.add(timeLabel);
		timeValue.setFont(font);
		control.add(timeValue);

		scoreLabel.setFont(font);
		control.add(scoreLabel);
		scoreValue.setFont(font);
		control.add(scoreValue);

		KeyControls keyControls = new KeyControls();
		this.addKeyListener(keyControls);

		map = GameBuilder.buildMap();
		manager = GameBuilder.buildInitMobile(map);

		dashboard = new GameDisplay(map, manager);

		dashboard.setPreferredSize(preferredSize);
		contentPane.add(dashboard, BorderLayout.CENTER);
		contentPane.add(control, BorderLayout.EAST);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		setPreferredSize(preferredSize);
		setResizable(false);
		setLocationRelativeTo(null);
	}

	@Override
	public void run() {
		while (GameConfiguration.GAME_RUN == true) {
			try {
				Thread.sleep(GameConfiguration.GAME_SPEED);
				dashboard.repaint();
				dashboard.checkLight(manager.getCar());
				scoreValue.setText("" + GameConfiguration.SCORE);
				if (GameConfiguration.SCORE < 35) {
					GameConfiguration.GAME_RUN = false;
					this.dispose();
					new EndFrame(0);
				}

				if (turnNumber % 1000 == 0) {// <=> Thread.sleep(1000);
					if (chronometer.isRun() == false) {
						GameConfiguration.GAME_RUN = false;
						this.dispose();
						new EndFrame(1);
					} else {
						chronometer.decrement();
						timeValue.setText(chronometer.toString() + "");
						if (chronometer.endChrono()) {
							chronometer.setRun(false);
						}
					}

				}

				dashboard.nextRound(turnNumber);

				turnNumber++;

			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
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
					System.out.println("DEPLACEMENT IMPOSSIBLE, SCORE-1");
					GameConfiguration.SCORE--;
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
					System.out.println("DEPLACEMENT IMPOSSIBLE, SCORE-1");
					GameConfiguration.SCORE--;
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
					System.out.println("DEPLACEMENT IMPOSSIBLE, SCORE-1");
					GameConfiguration.SCORE--;
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
					System.out.println("DEPLACEMENT IMPOSSIBLE, SCORE-1");
					GameConfiguration.SCORE--;
				}
				break;
			default:
				break;
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

package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JTextField;

import Config.GameConfiguration;
import engine.Map.Map;
import engine.process.GameBuilder;
import engine.process.MobileElementManager;

public class Main extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;

	private Map map;

	private final static Dimension preferredSize = new Dimension(GameConfiguration.WINDOW_WIDTH,
			GameConfiguration.WINDOW_HEIGHT);

	private MobileElementManager manager;

	private PaintStrategy paintStrategy = new PaintStrategy();

	private GameDisplay dashboard;

	public Main(String title) {
		super(title);
		init();
	}

	private void init() {

		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());

		KeyControls keyControls = new KeyControls();
		JTextField textField = new JTextField();
		textField.addKeyListener(keyControls);
		contentPane.add(textField, BorderLayout.SOUTH);

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
			char keyChar = event.getKeyChar();
			switch (keyChar) {

			case 'q':
				manager.moveLeftCar();
				Image image = paintStrategy.getImageLeft();
				paintStrategy.setImage(image);
				dashboard.setPaintStrategy(paintStrategy);
				break;
			case 'd':
				manager.moveRighttCar();
				image = paintStrategy.getImageRight();
				paintStrategy.setImage(image);
				dashboard.setPaintStrategy(paintStrategy);
				;
				break;
			case 'z':
				manager.moveUpCar();
				image = paintStrategy.getImageUp();
				paintStrategy.setImage(image);
				dashboard.setPaintStrategy(paintStrategy);
				break;
			case 's':
				manager.moveDownCar();
				image = paintStrategy.getImageDown();
				paintStrategy.setImage(image);
				dashboard.setPaintStrategy(paintStrategy);
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

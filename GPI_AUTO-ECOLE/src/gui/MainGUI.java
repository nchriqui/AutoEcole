package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import config.GameConfiguration;
import engine.map.Map;
import engine.process.GameBuilder;
import engine.process.MobileElementManager;

public class MainGUI extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;

	private Map map;

	private final static Dimension preferredSize = new Dimension(GameConfiguration.WINDOW_WIDTH,
			GameConfiguration.WINDOW_HEIGHT);

	private MobileElementManager manager;

	private PaintStrategy paintStrategy = new PaintStrategy();

	private GameDisplay dashboard;

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
			
			
			System.out.println(keyCode);
			
			System.out.println( "Old position :"+ manager.getCar().getPosition());
			
			switch (keyCode) {
			case 37:
				manager.moveLeftCar();
				System.out.println("DEPLACEMENT GAUCHE");
				System.out.println( "New position :"+ manager.getCar().getPosition());
				
				
				BufferedImage image = paintStrategy.getImageLeft();
				paintStrategy.setImage(image);
				dashboard.setPaintStrategy(paintStrategy);
				break;
			case 38:
				manager.moveUpCar();
				System.out.println("DEPLACEMENT GAUCHE");
				System.out.println( "New position :"+ manager.getCar().getPosition());
				
				
				image = paintStrategy.getImageUp();
				paintStrategy.setImage(image);
				dashboard.setPaintStrategy(paintStrategy);
				
				break;
				
			case 39:
				manager.moveRightCar();
				System.out.println("DEPLACEMENT GAUCHE");
				System.out.println( "New position :"+ manager.getCar().getPosition());
				
				
				image = paintStrategy.getImageRight();
				paintStrategy.setImage(image);
				dashboard.setPaintStrategy(paintStrategy);
				break;
			case 40:
				manager.moveDownCar();
				System.out.println("DEPLACEMENT GAUCHE");
				System.out.println( "New position :"+ manager.getCar().getPosition());
				
				
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

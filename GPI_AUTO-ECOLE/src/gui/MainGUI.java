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

	private BufferedImage image;

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

			switch (keyCode) {
			case 37: // FLECHE GAUCHE
				//si le dernier deplacement était en haut , en bas ou à gauche 
				if (manager.isLastMoveUp() == true || manager.isLastMoveDown() == true || manager.isLastMoveLeft() == true ) {
					System.out.println("DEPLACEMENT A GAUCHE");
					System.out.println("Old position :" + manager.getCar().getPosition());
					manager.moveLeftCar();
					System.out.println("New position :" + manager.getCar().getPosition());
	
					image = paintStrategy.getImageLeft();
					paintStrategy.setImage(image);
					dashboard.setPaintStrategy(paintStrategy);
					
					//Sauvegarde du dernier deplacement efffectue
					manager.setLastMoveLeft(true);
					manager.setLastMoveDown(false);
					manager.setLastMoveRight(false);
					manager.setLastMoveUp(false);
				}else {
					System.out.println("DEPLACEMENT IMPOSSIBLE");
				}
				break;
			case 38: // FLECHE DU HAUT
				//si le dernier deplacement était a gauche , a droite ou en haut 
				if (manager.isLastMoveLeft() == true || manager.isLastMoveRight() == true || manager.isLastMoveUp() == true) {
					System.out.println("DEPLACEMENT EN HAUT");
					System.out.println("Old position :" + manager.getCar().getPosition());
					manager.moveUpCar();
					System.out.println("New position :" + manager.getCar().getPosition());
	
					image = paintStrategy.getImageUp();
					paintStrategy.setImage(image);
					dashboard.setPaintStrategy(paintStrategy);
					
					//Sauvegarde du dernier deplacement efffectue
					manager.setLastMoveLeft(false);
					manager.setLastMoveDown(false);
					manager.setLastMoveRight(false);
					manager.setLastMoveUp(true);
				}else {
					System.out.println("DEPLACEMENT IMPOSSIBLE");
				}
				break;

			case 39: // FLECHE DROITE
				//si le dernier deplacement était en haut , en bas ou a droite 
				if (manager.isLastMoveUp()==true || manager.isLastMoveDown()==true ||  manager.isLastMoveRight() == true){
					System.out.println("DEPLACEMENT A DROITE");
					System.out.println("Old position :" + manager.getCar().getPosition());
					manager.moveRightCar();
					System.out.println("New position :" + manager.getCar().getPosition());
	
					image = paintStrategy.getImageRight();
					paintStrategy.setImage(image);
					dashboard.setPaintStrategy(paintStrategy);
					
					//Sauvegarde du dernier deplacement efffectue
					manager.setLastMoveLeft(false);
					manager.setLastMoveDown(false);
					manager.setLastMoveRight(true);
					manager.setLastMoveUp(false);
				}else {
					System.out.println("DEPLACEMENT IMPOSSIBLE");
				}
				break;
			case 40: // DEPLACEMENT EN BAS
				//si le dernier deplacement était a gauche , a droite ou en bas 
				if (manager.isLastMoveLeft()==true || manager.isLastMoveRight()==true ||  manager.isLastMoveDown() == true){
					System.out.println("DEPLACEMENT EN BAS ");
					System.out.println("Old position :" + manager.getCar().getPosition());
					manager.moveDownCar();
					System.out.println("New position :" + manager.getCar().getPosition());
	
					image = paintStrategy.getImageDown();
					paintStrategy.setImage(image);
					dashboard.setPaintStrategy(paintStrategy);
					
					//Sauvegarde du dernier deplacement efffectue
					manager.setLastMoveLeft(false);
					manager.setLastMoveDown(true);
					manager.setLastMoveRight(false);
					manager.setLastMoveUp(false);
				}else {
					System.out.println("DEPLACEMENT IMPOSSIBLE");
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

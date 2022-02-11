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
	private Image imageLeftLight ;
	private Image imageRightLight ;
		
		
	private CheckLight checkLeftLight;
	private CheckLight checkRightLight;

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
	
					imageCar = paintStrategy.getImageLeft();
					paintStrategy.setImage(imageCar);
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
	
					imageCar = paintStrategy.getImageUp();
					paintStrategy.setImage(imageCar);
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
	
					imageCar = paintStrategy.getImageRight();
					paintStrategy.setImage(imageCar);
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
	
					imageCar = paintStrategy.getImageDown();
					paintStrategy.setImage(imageCar);
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
			
			checkLeftLight= new CheckLight(manager.getCar(), manager.getLeftLight());
			checkRightLight= new CheckLight(manager.getCar(), manager.getRightLight());
			if ( (manager.getRightLight().getGo()==false &&  checkRightLight.verifposition()==true)){
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
			
			
			if ((manager.getLeftLight().getGo()==false && checkLeftLight.verifposition()==true)){
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

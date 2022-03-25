package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import chrono.Chronometer;
import chrono.Counter;
import config.GameConfiguration;
import engine.map.Map;
import engine.process.CarMoveUtility;
import engine.process.GameBuilder;
import engine.process.MobileElementManager;

import java.awt.Image;

public class MainGUI extends JFrame implements Runnable {

    private static final long serialVersionUID = 1L;

    private Map map;

    private static Font font = new Font(Font.MONOSPACED, Font.BOLD, 22);
    private static Font fontPause = new Font(Font.MONOSPACED, Font.BOLD, 40);
    private static Font buttonFont = new Font(Font.DIALOG, Font.BOLD, 28);
    private final static Dimension preferredSize = new Dimension(GameConfiguration.WINDOW_WIDTH, GameConfiguration.WINDOW_HEIGHT);

    private MobileElementManager manager;

    private Chronometer chronometer = new Chronometer();

    private JLabel timeValue = new JLabel("");
    private JLabel scoreLabel = new JLabel("Score :");
    private JLabel scoreValue = new JLabel("");

    private JPanel scorePanel = new JPanel();

    private JPanel pausePanel = new JPanel();

    private JButton pauseButton = new JButton();
    
    private JButton turnLeft = new JButton();

	private JButton turnRight = new JButton();

	private JLabel clignotantLabel = new JLabel("Clignotants :");
	private JPanel Clignotant = new JPanel();

    private JPanel control = new JPanel();

    private PaintStrategy paintStrategy;

    private GameDisplay dashboard;

    private Image imageCar;

    private CarMoveUtility carMoveUtility = new CarMoveUtility();

    private int turnNumber = 0;

    private boolean pause = false;
    
    private Counter stopCounter = new Counter(GameConfiguration.STOP_DURATION);
	private JLabel stopLabel = new JLabel("STOP :");
	private JLabel stopValue = new JLabel("");
	private JPanel stopPanel = new JPanel();
	
	private JLabel speedBoxLabel = new JLabel("Vitesse:");
	private JLabel speedBoxValue = new JLabel("0");
	private JLabel speedLabel = new JLabel("Compteur:");
	private JLabel speedValue = new JLabel("0  Km/h");

    public MainGUI(PaintStrategy paintStrategy) {
        super("Auto-�cole - Examen");
        this.paintStrategy = paintStrategy;
        init();
    }

    private void init() {
        chronometer.init();
        GameConfiguration.SCORE = 40;
        GameConfiguration.GAME_RUN = true;
        timeValue.setText(chronometer.toString());
		paintStrategy.setLightRed(true);
		paintStrategy.setLightOrange(false);
		paintStrategy.setLightGreen(false);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        pauseButton.setIcon(new ImageIcon("src/images/pause.png"));
        pauseButton.setFocusable(false);
        pauseButton.setOpaque(false);
        pauseButton.setContentAreaFilled(false);
        pauseButton.setBorderPainted(false);

        final JDialog modelDialog = createDialog(this);
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pause = true;
                modelDialog.setVisible(true);
            }
        });
        
        turnRight.setIcon(new ImageIcon("src/images/flecheDroite.png"));
		turnRight.setFocusable(false);
		turnRight.setOpaque(false);
		turnRight.setContentAreaFilled(false);
		turnRight.setBorderPainted(false);

		turnRight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!manager.getCar().isClignotantGauche()) {
					if (manager.getCar().isClignotantDroit()) {
						manager.getCar().setClignotantDroit(false);

						turnRight.setIcon(paintStrategy.getClignotantDroit());
					} else {
						manager.getCar().setClignotantDroit(true);

						turnRight.setIcon(paintStrategy.getClignotantDroitOn());
					}
				}
			}
		});

		turnLeft.setIcon(new ImageIcon("src/images/flecheGauche.png"));
		turnLeft.setFocusable(false);
		turnLeft.setOpaque(false);
		turnLeft.setContentAreaFilled(false);
		turnLeft.setBorderPainted(false);

		turnLeft.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// setClignotantGauche(true);
				if (!manager.getCar().isClignotantDroit()) {
					if (manager.getCar().isClignotantGauche()) {
						manager.getCar().setClignotantGauche(false);

						turnLeft.setIcon(paintStrategy.getClignotantGauche());
					} else {
						manager.getCar().setClignotantGauche(true);

						turnLeft.setIcon(paintStrategy.getClignotantGaucheOn());
					}
				}
			}
		});

        control.setLayout(new GridLayout(9, 1));

        pausePanel.add(pauseButton);
        control.add(pauseButton);
        timeValue.setFont(font);
        control.add(timeValue);
        
        clignotantLabel.setFont(font);
        Clignotant.add(clignotantLabel);
        Clignotant.add(turnLeft);
		Clignotant.add(turnRight);
		control.add(Clignotant);
		
		speedBoxLabel.setFont(font);
		speedBoxValue.setFont(font);
		speedLabel.setFont(font);
		speedValue.setFont(font);
		control.add(speedBoxLabel);
		control.add(speedBoxValue);
		control.add(speedLabel);
		control.add(speedValue);

		stopLabel.setFont(font);
		stopValue.setFont(font);
		stopPanel.setLayout(new GridLayout(1, 1));
		stopPanel.add(stopLabel);
		stopPanel.add(stopValue);
		stopPanel.setVisible(false);
		control.add(stopPanel);
		
        scoreLabel.setFont(font);
        scoreValue.setFont(font);
        scorePanel.setLayout(new GridLayout(1, 1));
        scorePanel.add(scoreLabel);
        scorePanel.add(scoreValue);
        control.add(scorePanel);
        
        KeyControls keyControls = new KeyControls();
        this.addKeyListener(keyControls);

        map = GameBuilder.buildMap();
        manager = GameBuilder.buildInitMobile(map);

        dashboard = new GameDisplay(map, manager, paintStrategy);

        dashboard.setPreferredSize(preferredSize);
        contentPane.add(dashboard, BorderLayout.CENTER);
        contentPane.add(control, BorderLayout.EAST);

        
        pack();
        setVisible(true);
        setPreferredSize(preferredSize);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private JDialog createDialog(final JFrame frame) {
        final JDialog modelDialog = new JDialog(frame, "Pause", Dialog.ModalityType.DOCUMENT_MODAL);
        modelDialog.setBounds(500, 250, 400, 340);
        modelDialog.getContentPane().setBackground(Color.black);
        // modelDialog.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        modelDialog.setUndecorated(true);
        Container dialogContainer = modelDialog.getContentPane();
        dialogContainer.setLayout(new BorderLayout());
        JPanel pausePan = new JPanel();
        pausePan.setLayout(new FlowLayout(FlowLayout.CENTER));
        JPanel panTitle = new JPanel();
        JLabel pauseTitle = new JLabel("Jeu en pause");
        pauseTitle.setFont(fontPause);
        panTitle.add(pauseTitle);
        pausePan.add(panTitle);
        dialogContainer.add(pausePan, BorderLayout.NORTH);
        JButton continueButton = new JButton("Reprendre");
        continueButton.setUI(new StyledButtonUI());
        continueButton.setFont(buttonFont);
        JButton exitButton = new JButton("Quitter");
        exitButton.setUI(new StyledButtonUI());
        exitButton.setFont(buttonFont);
        JButton reglesButton = new JButton("R�gles");
        reglesButton.setUI(new StyledButtonUI());
        reglesButton.setFont(buttonFont);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        continueButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(Box.createVerticalStrut(30));
        buttonPanel.add(continueButton);
        buttonPanel.add(Box.createVerticalStrut(30));
        reglesButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(reglesButton);
        buttonPanel.add(Box.createVerticalStrut(30));
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(exitButton);

        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pause = false;
                modelDialog.setVisible(false);
            }
        });

        reglesButton.addActionListener(new ReglesAction());
        exitButton.addActionListener(new ExitAction(this));

        dialogContainer.add(buttonPanel, BorderLayout.CENTER);

        return modelDialog;
    }
    
    class ReglesAction implements ActionListener {

        public ReglesAction() {
        }

        public void actionPerformed(ActionEvent e) {
            new ReglesFrame(true);
        }

    }

    private class ExitAction implements ActionListener {
        // Window to be closed.
        private JFrame window;

        public ExitAction(JFrame window) {
            this.window = window;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            window.dispose();
        }

    }

    @Override
    public void run() {
        while (GameConfiguration.GAME_RUN == true) {
            if (!pause) {
                try {
                    Thread.sleep(GameConfiguration.GAME_SPEED);
                    dashboard.repaint();
                    dashboard.checkLight(manager.getCar());
                    dashboard.nextRound(turnNumber);
                    
                    scoreValue.setText("" + GameConfiguration.SCORE + "/40");
                    if (GameConfiguration.SCORE < 35) {
                        GameConfiguration.GAME_RUN = false;
                        this.dispose();
                        new EndFrame(0, paintStrategy);
                    }

                    if (manager.checkWaitStop()) {
						stopCounter.setRun(true);
						stopPanel.setVisible(true);
						if (turnNumber % 1000 == 0) {
							if (stopCounter.getValue() == 1) {
								stopCounter.init();
								stopPanel.setVisible(false);
								manager.moveAuto();
							} else {
								stopCounter.decrement();
							}
						}
						stopValue.setText(stopCounter.getValue() + " s");
					}

					if (stopCounter.isRun()) {
						if (!manager.checkWaitStop()) {
							stopCounter.init();
							stopPanel.setVisible(false);
							System.out.println("\nVOUS AVEZ GRILLE LE STOP");
							System.out.println("SCORE -1 \n ");
							GameConfiguration.SCORE--;
						}
					}
                    
                    if (turnNumber % 1000 == 0) {// <=> Thread.sleep(1000);
                        if (chronometer.isRun() == false) {
                            GameConfiguration.GAME_RUN = false;
                            this.dispose();
                            new EndFrame(1, paintStrategy);
                        } else {
                            chronometer.decrement();
                            timeValue.setText(chronometer.toString() + "");
                            if (chronometer.endChrono()) {
                                chronometer.setRun(false);
                            }
                        }

                    }

                    turnNumber++;

                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }
    }

    private class KeyControls implements KeyListener {

        @Override
        public void keyPressed(KeyEvent event) {
            int keyCode = event.getKeyCode();

            switch (keyCode) {
            case 37: // FLECHE GAUCHE
                // si le dernier deplacement �tait en haut , en bas ou � gauche
                if (carMoveUtility.checkMoveLeft(manager)) {
                	if (manager.moveLeftCar()) {
	                    System.out.println("DEPLACEMENT A GAUCHE");
	                    imageCar = paintStrategy.getImageLeft();
	                    paintStrategy.setImage(imageCar);
	                    dashboard.setPaintStrategy(paintStrategy);
	                    // Sauvegarde du dernier deplacement efffectue
	                    carMoveUtility.saveLastMove(keyCode, manager);
                	}
                } else {
                    System.out.println("DEPLACEMENT IMPOSSIBLE, SCORE-1");
                    GameConfiguration.SCORE--;
                }
                break;
            case 38: // FLECHE DU HAUT
                // si le dernier deplacement �tait a gauche , a droite ou en haut
                if (carMoveUtility.checkMoveUp(manager)) {
                	if (manager.moveUpCar()) {
	                    System.out.println("DEPLACEMENT EN HAUT");
	                    imageCar = paintStrategy.getImageUp();
	                    paintStrategy.setImage(imageCar);
	                    dashboard.setPaintStrategy(paintStrategy);
	                    // Sauvegarde du dernier deplacement efffectue
	                    carMoveUtility.saveLastMove(keyCode, manager);
                	}
                } else {
                    System.out.println("DEPLACEMENT IMPOSSIBLE, SCORE-1");
                    GameConfiguration.SCORE--;
                }
                break;

            case 39: // FLECHE DROITE
                // si le dernier deplacement �tait en haut , en bas ou a droite
                if (carMoveUtility.checkMoveRight(manager)) {
                	if (manager.moveRightCar()) {
	                    System.out.println("DEPLACEMENT A DROITE");
	                    imageCar = paintStrategy.getImageRight();
	                    paintStrategy.setImage(imageCar);
	                    dashboard.setPaintStrategy(paintStrategy);
	                    // Sauvegarde du dernier deplacement efffectue
	                    carMoveUtility.saveLastMove(keyCode, manager);
                	}
                } else {
                    System.out.println("DEPLACEMENT IMPOSSIBLE, SCORE-1");
                    GameConfiguration.SCORE--;
                }
                break;
            case 40: // DEPLACEMENT EN BAS
                // si le dernier deplacement �tait a gauche , a droite ou en bas
                if (carMoveUtility.checkMoveBottom(manager)) {
                	if (manager.moveDownCar()) {
	                    System.out.println("DEPLACEMENT EN BAS ");
	                    imageCar = paintStrategy.getImageDown();
	                    paintStrategy.setImage(imageCar);
	                    dashboard.setPaintStrategy(paintStrategy);
	                    // Sauvegarde du dernier deplacement efffectue
	                    carMoveUtility.saveLastMove(keyCode, manager);
                	}
                } else {
                    System.out.println("DEPLACEMENT IMPOSSIBLE, SCORE-1");
                    GameConfiguration.SCORE--;
                }
                break;
            case 49:
			case 97:
				manager.changeSpeed(1);
				break;
			case 50:
			case 98:
				manager.changeSpeed(2);
				break;
			case 51:
			case 99:
				manager.changeSpeed(3);
				break;
			case 52:
			case 100:
				manager.changeSpeed(4);
				break;
			case 53:
			case 101:
				manager.changeSpeed(5);
				break;
            default:
                break;
            }
            speedBoxValue.setText("" + manager.getCar().getVitesse() + " e vitesse");
			speedValue.setText("" + manager.getCar().getVitesse()*20 + " Km/h");
        }

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }

}

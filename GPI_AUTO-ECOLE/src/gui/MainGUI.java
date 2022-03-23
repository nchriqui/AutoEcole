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
    private JLabel scoreLabel = new JLabel("Score");
    private JLabel scoreValue = new JLabel("");

    private JPanel scorePanel = new JPanel();

    private JPanel stopPanel = new JPanel();

    private JButton stopButton = new JButton();

    private JPanel control = new JPanel();

    private PaintStrategy paintStrategy;

    private GameDisplay dashboard;

    private Image imageCar;

    private CarMoveUtility carMoveUtility = new CarMoveUtility();

    private int turnNumber = 0;

    private boolean stop = false;

    public MainGUI(String title, PaintStrategy paintStrategy) {
        super(title);
        this.paintStrategy = paintStrategy;
        init();
    }

    private void init() {
        chronometer.init();
        GameConfiguration.SCORE = 40;
        GameConfiguration.GAME_RUN = true;

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        stopButton.setIcon(new ImageIcon("src/images/pause.png"));
        stopButton.setFocusable(false);
        stopButton.setOpaque(false);
        stopButton.setContentAreaFilled(false);
        stopButton.setBorderPainted(false);

        final JDialog modelDialog = createDialog(this);
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stop = true;
                modelDialog.setVisible(true);
            }
        });

        control.setLayout(new GridLayout(4, 1));

        stopPanel.add(stopButton);
        control.add(stopPanel);
        timeValue.setFont(font);
        control.add(timeValue);

        scoreLabel.setFont(font);
        scoreValue.setFont(font);
        scorePanel.setLayout(new GridLayout(2, 1));
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

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
        setPreferredSize(preferredSize);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private JDialog createDialog(final JFrame frame) {
        final JDialog modelDialog = new JDialog(frame, "Pause", Dialog.ModalityType.DOCUMENT_MODAL);
        modelDialog.setBounds(500, 250, 400, 300);
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
        JButton exitButton = new JButton("Quitter");
        exitButton.setUI(new StyledButtonUI());
        exitButton.setFont(buttonFont);
        JButton continueButton = new JButton("Reprendre");
        continueButton.setUI(new StyledButtonUI());
        continueButton.setFont(buttonFont);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        continueButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(Box.createVerticalStrut(30));
        buttonPanel.add(continueButton);
        buttonPanel.add(Box.createVerticalStrut(30));
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonPanel.add(exitButton);

        continueButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stop = false;
                modelDialog.setVisible(false);
            }
        });

        exitButton.addActionListener(new ExitAction(this));

        dialogContainer.add(buttonPanel, BorderLayout.CENTER);

        return modelDialog;
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
            if (!stop) {
                try {
                    Thread.sleep(GameConfiguration.GAME_SPEED);
                    dashboard.repaint();
                    dashboard.checkLight(manager.getCar());
                    scoreValue.setText("" + GameConfiguration.SCORE + "/40");
                    if (GameConfiguration.SCORE < 35) {
                        GameConfiguration.GAME_RUN = false;
                        this.dispose();
                        new EndFrame(0, paintStrategy);
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

                    dashboard.nextRound(turnNumber);

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

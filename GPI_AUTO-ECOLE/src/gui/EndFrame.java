package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class EndFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private static final Font MENU_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 70);
    private static final Font BUTTON_FONT = new Font("Calibri", Font.PLAIN, 28);

    protected JButton exitButton = new JButton("Quitter");
    protected JButton replayButton = new JButton("Changer de véhicule");
    protected JButton replaySameButton = new JButton("Rejouer");
    protected JButton menuButton = new JButton("Menu");

    protected JLabel endLabel = new JLabel("Fin de Partie");

    JTextArea affichage = new JTextArea();

    private int end;

    private String valider = "";

    private PaintStrategy paintStrategy;

    public EndFrame(int end, PaintStrategy paintStrategy) {
        super("Auto-École - Fin de partie");
        this.end = end;
        this.paintStrategy = paintStrategy;

        initStyle();

        initLayout();
    }

    protected void initStyle() {
        endLabel.setFont(MENU_FONT);
        endLabel.setForeground(Color.WHITE);

        affichage.setEditable(false);
        affichage.setOpaque(false);
        affichage.setFont(new Font(Font.MONOSPACED, Font.BOLD, 28));
        affichage.setForeground(Color.WHITE);

        replaySameButton.setFont(BUTTON_FONT);

        replayButton.setFont(BUTTON_FONT);

        exitButton.setFont(BUTTON_FONT);

        menuButton.setFont(BUTTON_FONT);
    }

    protected void initLayout() {
        Image img = null;

        try {
            if (end == 0) {
                img = ImageIO.read(new File("src/images/echec.jpg"));
                valider = "PERMIS ECHOUE ! Vous avez fait plus\nde 5 erreurs. ";
                valider += "Veuillez réessayer !\n";
            } else if (end == 1) {
                img = ImageIO.read(new File("src/images/reussir.jpg"));
                valider = "Le temps est écoulé, vous avez tenu\njusqu'au bout. ";
                valider += "PERMIS VALIDE !\n";
                valider += "TOUTES MES FELICITATIONS !\n";
            }
            ImageGUI imgGui = new ImageGUI(img);

            imgGui.setLayout(null);

            endLabel.setBounds(190, -380, 450, 850);
            imgGui.add(endLabel);

            affichage.setText(valider);
            affichage.setBounds(115, 115, 720, 120);
            imgGui.add(affichage);

            replaySameButton.setUI(new StyledButtonUI());
            replaySameButton.setBounds(320, 250, 160, 50);
            imgGui.add(replaySameButton);
            replaySameButton.addActionListener(new ReplaySameAction(this, paintStrategy));

            replayButton.setUI(new StyledButtonUI());
            replayButton.setBounds(250, 330, 300, 50);
            imgGui.add(replayButton);
            replayButton.addActionListener(new ReplayAction(this));

            menuButton.setUI(new StyledButtonUI());
            menuButton.setBounds(320, 410, 160, 50);
            imgGui.add(menuButton);
            menuButton.addActionListener(new MenuAction(this));

            exitButton.setUI(new StyledButtonUI());
            exitButton.setBounds(320, 490, 160, 50);
            imgGui.add(exitButton);
            exitButton.addActionListener(new ExitAction(this));

            this.add(imgGui);
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.setBounds(500, 100, 800, 630);
            this.setLocationRelativeTo(null);
            this.setResizable(false);
            this.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private class ReplayAction implements ActionListener {
        // Window to be closed.
        private JFrame window;

        public ReplayAction(JFrame window) {
            this.window = window;

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            window.dispose();
            new ChoiceFrame();
        }
    }

    private class ReplaySameAction implements ActionListener {
        // Window to be closed.
        private JFrame window;
        private PaintStrategy paintStrategy;

        public ReplaySameAction(JFrame window, PaintStrategy paintStrategy) {
            this.window = window;
            this.paintStrategy = paintStrategy;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            window.dispose();
            Thread gameThread = new Thread(new MainGUI(paintStrategy));
            gameThread.start();
        }
    }

    private class MenuAction implements ActionListener {
        // Window to be closed.
        private JFrame window;

        public MenuAction(JFrame window) {
            this.window = window;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            window.dispose();
            new MenuFrame();
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

}

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
        super("End Game");
        this.end = end;
        this.paintStrategy = paintStrategy;
        endgame();
    }

    private void endgame() {
        Image img = null;

        try {
            if (end == 0) {
                img = ImageIO.read(new File("src/images/echec.jpg"));
                valider = "PERMIS ECHOUE ! vous avez fait plus\nde 5 erreurs.";
                valider += "Veuillez réessayer !\n";
            } else if (end == 1) {
                img = ImageIO.read(new File("src/images/reussir.jpg"));
                valider = "Le temps est écoulé, vous avez tenu\njusqu'au bout.";
                valider += "PERMIS VALIDE !\n";
                valider += "TOUTES MES FELICITATIONS ! \n";
            }
            ImageGUI imgGui = new ImageGUI(img);

            imgGui.setLayout(null);

            endLabel.setBounds(190, -380, 450, 850);
            endLabel.setFont(MENU_FONT);
            endLabel.setForeground(Color.WHITE);
            imgGui.add(endLabel);

            affichage.setText(valider);
            affichage.setEditable(false);
            affichage.setOpaque(false);
            affichage.setFont(new Font(Font.MONOSPACED, Font.BOLD, 28));
            affichage.setForeground(Color.WHITE);
            affichage.setBounds(115, 115, 720, 120);
            imgGui.add(affichage);

            replayButton.setUI(new StyledButtonUI());
            replayButton.setFont(BUTTON_FONT);
            replaySameButton.setUI(new StyledButtonUI());
            replaySameButton.setFont(BUTTON_FONT);
            exitButton.setUI(new StyledButtonUI());
            exitButton.setFont(BUTTON_FONT);
            menuButton.setUI(new StyledButtonUI());
            menuButton.setFont(BUTTON_FONT);

            replaySameButton.setBounds(320, 250, 160, 50);
            imgGui.add(replaySameButton);

            replayButton.setBounds(250, 330, 300, 50);
            imgGui.add(replayButton);

            menuButton.setBounds(320, 410, 160, 50);
            imgGui.add(menuButton);

            exitButton.setBounds(320, 490, 160, 50);
            imgGui.add(exitButton);

            replayButton.addActionListener(new ReplayAction(this));
            replaySameButton.addActionListener(new ReplaySameAction(this, "Driving school", paintStrategy));
            menuButton.addActionListener(new MenuAction(this));
            exitButton.addActionListener(new ExitAction(this));

            this.add(imgGui);
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.setBounds(500, 100, 800, 630);
            this.setLocationRelativeTo(null);
            this.setResizable(false);
            this.setVisible(true);
        } catch (IOException e) {
            // TODO Auto-generated catch block
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
        private String title;
        private PaintStrategy paintStrategy;

        public ReplaySameAction(JFrame window, String title, PaintStrategy paintStrategy) {
            this.window = window;
            this.title = title;
            this.paintStrategy = paintStrategy;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            window.dispose();
            Thread gameThread = new Thread(new MainGUI(title, paintStrategy));
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

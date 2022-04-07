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

/*
 * This class serves to define and initialize the begin Frame 
 */

public class MenuFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private static final Font BUTTON_FONT = new Font("Calibri", Font.PLAIN, 35);

    private JLabel jeu = new JLabel(" Auto-École ");

    protected JButton start = new JButton("Start");

    protected JButton exit = new JButton("Quitter");

    protected JButton regles = new JButton("Règles");

    public MenuFrame() {
        super("Auto-École - Menu");

        initStyle();

        initLayout();
    }

    protected void initStyle() {
        Font font = new Font(Font.MONOSPACED, Font.CENTER_BASELINE, 110);
        jeu.setFont(font);
        jeu.setForeground(Color.decode("#ffffff"));

        start.setBackground(new Color(0xffffff));
        start.setForeground(Color.BLACK);
        start.setFont(BUTTON_FONT);

        regles.setBackground(new Color(0xffffff));
        regles.setForeground(Color.BLACK);
        regles.setFont(BUTTON_FONT);

        exit.setBackground(new Color(0xffffff));
        exit.setForeground(Color.BLACK);
        exit.setFont(BUTTON_FONT);
    }

    protected void initLayout() {
        try {
            Image img = ImageIO.read(new File("src/images/background.jpg"));
            ImageGUI imgGui = new ImageGUI(img);

            imgGui.setLayout(null);

            jeu.setBounds(23, -350, 800, 850);
            imgGui.add(jeu);

            start.setBounds(315, 190, 220, 78);
            start.setUI(new StyledButtonUI());
            imgGui.add(start);
            start.addActionListener(new ActionStart(this));

            regles.setBounds(315, 300, 220, 78);
            regles.setUI(new StyledButtonUI());
            imgGui.add(regles);
            regles.addActionListener(new ActionRegles(this));

            exit.setBounds(315, 410, 220, 78);
            exit.setUI(new StyledButtonUI());
            imgGui.add(exit);
            exit.addActionListener(new ActionExit(this));

            this.add(imgGui);
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.setBounds(500, 100, 850, 600);
            this.setLocationRelativeTo(null);
            this.setResizable(false);
            this.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class ActionStart implements ActionListener {
        private JFrame frame;

        public ActionStart(JFrame frame) {
            this.frame = frame;
        }

        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            new ChoiceFrame();
        }

    }

    class ActionRegles implements ActionListener {
        private JFrame frame;

        public ActionRegles(JFrame frame) {
            this.frame = frame;
        }

        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            new ReglesFrame(false);
        }

    }

    class ActionExit implements ActionListener {
        private JFrame frame;

        public ActionExit(JFrame frame) {
            this.frame = frame;
        }

        public void actionPerformed(ActionEvent e) {
            frame.dispose();
        }

    }
}
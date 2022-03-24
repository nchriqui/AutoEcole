package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class ReglesFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private static final Font BUTTON_FONT = new Font("Calibri", Font.PLAIN, 35);

    private JLabel regles = new JLabel("Règles du jeu");
    private JTextArea regle1 = new JTextArea();
    private JTextArea regle2 = new JTextArea();
    private JTextArea regle3 = new JTextArea();

    protected JButton menu = new JButton("Menu");

    private boolean inGame;

    public ReglesFrame(boolean inGame) {
        super("Auto-École - Règles");
        this.inGame = inGame;

        initStyle();

        initLayout();
    }

    protected void initStyle() {
        Font font = new Font(Font.MONOSPACED, Font.CENTER_BASELINE, 70);
        regles.setFont(font);
        regles.setForeground(Color.decode("#eeeeee"));

        Font fontList = new Font(Font.DIALOG, Font.CENTER_BASELINE, 22);
        regle1.setFont(fontList);
        regle1.setForeground(Color.decode("#eeeeee"));
        regle2.setFont(fontList);
        regle2.setForeground(Color.decode("#eeeeee"));
        regle3.setFont(fontList);
        regle3.setForeground(Color.decode("#eeeeee"));

        menu.setFont(BUTTON_FONT);
    }

    protected void initLayout() {
        try {
            Image img = ImageIO.read(new File("src/images/background_regles.jpg"));
            ImageGUI imgGui = new ImageGUI(img);

            regles.setBounds(112, -350, 650, 850);
            imgGui.add(regles);

            imgGui.setLayout(null);

            regle1.setText(
                    "L'objectif de ce jeu est d'obtenir son permis pour cela,\nl'utilisateur doit réussir son examen en restant au dessus\nde 35 points jusqu'à la fin du temps imparti (2 minutes).");
            regle1.setBounds(95, 140, 1000, 120);
            regle1.setEditable(false);
            regle1.setOpaque(false);
            imgGui.add(regle1);
            JLabel puce1 = new JLabel(new ImageIcon("src/images/puce.png"));
            puce1.setBounds(50, 145, 20, 20);
            imgGui.add(puce1);

            regle2.setText("Déplacement du véhicule à l'aide des flèches\ndu clavier.");
            regle2.setBounds(95, 265, 1000, 100);
            regle2.setEditable(false);
            regle2.setOpaque(false);
            imgGui.add(regle2);
            JLabel key = new JLabel(new ImageIcon("src/images/key.png"));
            key.setBounds(598, 240, 60, 60);
            imgGui.add(key);
            JLabel puce2 = new JLabel(new ImageIcon("src/images/puce.png"));
            puce2.setBounds(50, 270, 20, 20);
            imgGui.add(puce2);

            regle3.setText("Perte de points :\nFeu orange : -1\t\tFeu rouge : -2");
            regle3.setBounds(95, 360, 1000, 80);
            regle3.setEditable(false);
            regle3.setOpaque(false);
            imgGui.add(regle3);
            JLabel puce3 = new JLabel(new ImageIcon("src/images/puce.png"));
            puce3.setBounds(50, 365, 20, 20);
            imgGui.add(puce3);

            menu.setUI(new StyledButtonUI());
            menu.setBounds(290, 445, 220, 78);
            if (inGame) {
                menu.setText("Compris");
                menu.addActionListener(new ActionContinue(this));
                this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            } else {
                menu.addActionListener(new ActionMenu(this));
                this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            }
            imgGui.add(menu);

            this.add(imgGui);            
            this.setBounds(500, 100, 800, 600);
            this.setLocationRelativeTo(null);
            this.setResizable(false);
            this.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class ActionMenu implements ActionListener {
        private JFrame frame;

        public ActionMenu(JFrame frame) {
            this.frame = frame;
        }

        public void actionPerformed(ActionEvent e) {
            frame.dispose();
            new MenuFrame();
        }

    }

    class ActionContinue implements ActionListener {
        private JFrame frame;

        public ActionContinue(JFrame frame) {
            this.frame = frame;
        }

        public void actionPerformed(ActionEvent e) {
            frame.dispose();
        }

    }
}
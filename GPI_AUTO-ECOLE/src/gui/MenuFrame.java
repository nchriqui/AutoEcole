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

public class MenuFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private static final Font BUTTON_FONT = new Font("Calibri", Font.PLAIN, 35);

    private JLabel jeu = new JLabel(" Auto-École ");

    protected JButton start = new JButton("Start");

    protected JButton exit = new JButton("Quitter");

    public MenuFrame() {

        super("Auto-École - Menu");

        initStyle();

        initLayout();

    }

    protected void initStyle() {
        Font font = new Font("Courier", Font.CENTER_BASELINE, 110);
        jeu.setFont(font);
        jeu.setForeground(Color.decode("#2832c2"));

        start.setFont(BUTTON_FONT);
        exit.setFont(BUTTON_FONT);
    }

    protected void initLayout() {

        try {
            Image img = ImageIO.read(new File("src/images/background.jpg"));
            ImageGUI imgGui = new ImageGUI(img);

            imgGui.setLayout(null);

            jeu.setBounds(23, -350, 3000, 850);
            imgGui.add(jeu);

            start.setBackground(new Color(0xffffff));
            start.setForeground(Color.BLACK);
            start.setBounds(315, 220, 220, 78);

            start.setUI(new StyledButtonUI());
            imgGui.add(start);
            start.addActionListener(new ActionStart(this));

            exit.setBackground(new Color(0xffffff));
            exit.setForeground(Color.BLACK);
            exit.setBounds(315, 420, 220, 78);

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
            // TODO Auto-generated catch block
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
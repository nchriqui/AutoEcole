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

public class ChoiceFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private static final Font BUTTON_FONT = new Font("Calibri", Font.PLAIN, 35);

    private JLabel jeu = new JLabel("Choissisez un véhicule");

    protected JButton car = new JButton(new ImageIcon("src/images/mycarrChoice.png"));
    protected JButton motorbike = new JButton(new ImageIcon("src/images/motoChoice.png"));
    protected JButton truck = new JButton(new ImageIcon("src/images/truckChoice.png"));

    protected JButton menu = new JButton("Menu");

    public ChoiceFrame() {

        super("Auto-École - Choix du véhicule");

        initStyle();

        initLayout();

    }

    protected void initStyle() {
        Font font = new Font("Courier", Font.BOLD, 70);
        jeu.setFont(font);
        jeu.setForeground(Color.decode("#cccccc"));

        menu.setFont(BUTTON_FONT);
    }

    protected void initLayout() {

        try {
            Image img = ImageIO.read(new File("src/images/choiceBg.png"));
            ImageGUI imgGui = new ImageGUI(img);

            imgGui.setLayout(null);

            jeu.setBounds(118, -350, 3000, 850);
            imgGui.add(jeu);

            car.setBounds(90, 190, 215, 101);
            car.setOpaque(true);
            car.setContentAreaFilled(false);
            car.setBorderPainted(false);
            imgGui.add(car);

            motorbike.setBounds(490, 190, 214, 101);
            motorbike.setOpaque(false);
            motorbike.setContentAreaFilled(false);
            motorbike.setBorderPainted(false);
            imgGui.add(motorbike);

            truck.setBounds(860, 190, 215, 101);
            truck.setOpaque(false);
            truck.setContentAreaFilled(false);
            truck.setBorderPainted(false);
            imgGui.add(truck);

            car.addActionListener(new ActionStart(this, 1));
            motorbike.addActionListener(new ActionStart(this, 2));
            truck.addActionListener(new ActionStart(this, 3));

            menu.setBackground(new Color(0xffffff));
            menu.setForeground(Color.BLACK);
            menu.setBounds(490, 420, 220, 78);

            menu.setUI(new StyledButtonUI());
            imgGui.add(menu);
            menu.addActionListener(new ActionMenu(this));

            this.add(imgGui);
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.setBounds(500, 100, 1200, 600);
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
        private int choice;

        public ActionStart(JFrame frame, int choice) {
            this.frame = frame;
            this.choice = choice;
        }

        public void actionPerformed(ActionEvent e) {
            frame.dispose();

            MainGUI gameMain = new MainGUI("Driving school", new PaintStrategy(choice));

            Thread gameThread = new Thread(gameMain);
            gameThread.start();
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
}
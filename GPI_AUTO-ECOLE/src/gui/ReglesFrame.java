package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import config.GameConfiguration;

/*
 * This class serve to define the rule's Frame
 */

public class ReglesFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private static final Font BUTTON_FONT = new Font("Calibri", Font.PLAIN, 35);

    private JLabel regles = new JLabel("Règles du jeu");
    private JLabel restrictions = new JLabel("Restrictions");
    private JLabel feu = new JLabel("Feux de circulation");
    private JLabel stop = new JLabel("Panneaux Stop");
    private JLabel sensInterdit = new JLabel("Panneaux Sens Interdit");
    private JLabel limiteVitesse = new JLabel("Panneaux Limite de Vitesse");
    private JTextArea regle1 = new JTextArea();
    private JTextArea regle2 = new JTextArea();
    private JTextArea regle3 = new JTextArea();
    private JTextArea regle4 = new JTextArea();
    private JTextArea regle5 = new JTextArea();
    private JTextArea regle6 = new JTextArea();
    private JTextArea regle7 = new JTextArea();

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
        regles.setForeground(Color.decode("#ffffff"));

        Font fontPartie = new Font(Font.SANS_SERIF, Font.CENTER_BASELINE, 40);
        restrictions.setFont(fontPartie);
        restrictions.setForeground(Color.decode("#cccccc"));
        
        Font fontRestrictions = new Font(Font.MONOSPACED, Font.CENTER_BASELINE, 30);
        feu.setFont(fontRestrictions);
        feu.setForeground(Color.decode("#888888"));
        stop.setFont(fontRestrictions);
        stop.setForeground(Color.decode("#888888"));
        sensInterdit.setFont(fontRestrictions);
        sensInterdit.setForeground(Color.decode("#888888"));
        limiteVitesse.setFont(fontRestrictions);
        limiteVitesse.setForeground(Color.decode("#888888"));

        Font fontList = new Font(Font.DIALOG, Font.CENTER_BASELINE, 22);
        regle1.setFont(fontList);
        regle1.setForeground(Color.decode("#ffffff"));
        regle2.setFont(fontList);
        regle2.setForeground(Color.decode("#ffffff"));
        regle3.setFont(fontList);
        regle3.setForeground(Color.decode("#ffffff"));
        regle4.setFont(fontList);
        regle4.setForeground(Color.decode("#ffffff"));
        regle5.setFont(fontList);
        regle5.setForeground(Color.decode("#ffffff"));
        regle6.setFont(fontList);
        regle6.setForeground(Color.decode("#ffffff"));
        regle7.setFont(fontList);
        regle7.setForeground(Color.decode("#ffffff"));


        menu.setFont(BUTTON_FONT);
    }

    protected void initLayout() {

        Image img = null;
        try {
            img = ImageIO.read(new File("src/images/background_regles.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageGUI contentPane = new ImageGUI(img);
        JPanel buttonPanel = new JPanel();

        contentPane.setLayout(new GridBagLayout());
        regles.setHorizontalAlignment(JLabel.CENTER);
        contentPane.add(regles,
                new GridBagConstraints(0, 0, 1, 1, 0, 0, GridBagConstraints.BASELINE, GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));

        JPanel myPanel = new LinePanel();
        myPanel.setOpaque(false);
        JPanel myPanel2 = new LinePanel();
        myPanel2.setOpaque(false);
        JPanel myPanel3 = new LinePanel();
        myPanel3.setOpaque(false);
        JPanel myPanel4 = new LinePanel();
        myPanel4.setOpaque(false);
        JPanel myPanel5 = new LinePanel();
        myPanel5.setOpaque(false);
        JPanel myPanel6 = new LinePanel();
        myPanel6.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 0;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.ipadx = 400;
        gbc.ipady = 0;

        regle1.setText(
                "L'objectif de ce jeu est d'obtenir son permis pour cela,\nl'utilisateur doit réussir son examen en restant au dessus\nde 35 points jusqu'à la fin du temps imparti (" + GameConfiguration.GAME_MINUTE_DURATION +" minutes)");

        regle1.setEditable(false);
        regle1.setOpaque(false);

        contentPane.add(regle1,
                new GridBagConstraints(0, 2, 0, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));

        gbc.gridy = 3;
        contentPane.add(myPanel, gbc);

        regle2.setText("Déplacement du véhicule à l'aide des flèches\ndu clavier.");
        regle2.setEditable(false);
        regle2.setOpaque(false);

        contentPane.add(regle2,
                new GridBagConstraints(0, 4, 0, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));

        gbc.gridy = 5;
        contentPane.add(myPanel2, gbc);

        regle3.setText("Changement des vitesses avec le pavé numerique");
        regle3.setEditable(false);
        regle3.setOpaque(false);
        contentPane.add(regle3,
                new GridBagConstraints(0, 6, 0, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));



        contentPane.add(restrictions,
                new GridBagConstraints(0, 8, 1, 1, 0, 0, GridBagConstraints.BASELINE, GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));

        contentPane.add(feu,
                new GridBagConstraints(0, 10, 1, 1, 0, 0, GridBagConstraints.BASELINE, GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));

        regle4.setText("Feu orange : -1 point\tFeu rouge : -2 points");
        regle4.setEditable(false);
        regle4.setOpaque(false);
        contentPane.add(regle4,
                new GridBagConstraints(0, 11, 0, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));

        gbc.gridy = 12;
        contentPane.add(myPanel4, gbc);

        contentPane.add(stop,
                new GridBagConstraints(0, 13, 1, 1, 0, 0, GridBagConstraints.BASELINE, GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));

        regle5.setText("Non respect du temps d'attente : -1 point");
        regle5.setEditable(false);
        regle5.setOpaque(false);
        contentPane.add(regle5,
                new GridBagConstraints(0, 14, 0, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));

        gbc.gridy = 15;
        contentPane.add(myPanel5, gbc);

        contentPane.add(sensInterdit,
                new GridBagConstraints(0, 16, 1, 1, 0, 0, GridBagConstraints.BASELINE, GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));

        regle6.setText("Non respect du sens de circulation : -1 point");
        regle6.setEditable(false);
        regle6.setOpaque(false);
        contentPane.add(regle6,
                new GridBagConstraints(0, 17, 0, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));

        gbc.gridy = 18;
        contentPane.add(myPanel6, gbc);

        contentPane.add(limiteVitesse,
                new GridBagConstraints(0, 19, 1, 1, 0, 0, GridBagConstraints.BASELINE, GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));

        regle7.setText("Non respect de la vitesse autorisée : -1 point");
        regle7.setEditable(false);
        regle7.setOpaque(false);
        contentPane.add(regle7,
                new GridBagConstraints(0, 20, 0, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));

        menu.setUI(new StyledButtonUI());

        if (inGame) {
            menu.setText("Compris");
            menu.addActionListener(new ActionContinue(this));
            this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        } else {
            menu.addActionListener(new ActionMenu(this));
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        }
        menu.setPreferredSize(new Dimension(220, 78));
        menu.setMargin(new Insets(0, 0, 0, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.add(menu);
        contentPane.add(buttonPanel,
                new GridBagConstraints(0, 21, 0, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(10, 10, 10, 10), 0, 0));

        final JScrollPane jsp = new JScrollPane(contentPane);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
           public void run() { 
               jsp.getVerticalScrollBar().setValue(0);
           }
        });
        jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jsp.getVerticalScrollBar().setUnitIncrement(14);
        this.add(jsp);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(500, 100, 800, 600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    class LinePanel extends JPanel {
        private static final long serialVersionUID = 1L;

        public LinePanel() {
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.white);
            g.drawLine(0, getHeight() / 2, getWidth(), getHeight() / 2);
        };
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

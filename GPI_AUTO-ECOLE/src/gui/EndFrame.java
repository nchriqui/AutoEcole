package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class EndFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private static final Font MENU_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 50);

	protected JButton exitButton = new JButton("Exit");;
	protected JButton replayButton = new JButton("Replay");

	protected JLabel endLabel = new JLabel("Fin de Partie");

	private final static Dimension preferredSize = new Dimension(500, 300);

	private JPanel endtextPanel;
	private JPanel endbuttonPanel;
	JTextArea affichage = new JTextArea("");
	private int end;
	private String valider = "";

	public EndFrame(int end) {
		this.end = end;
		endgame();
	}

	private void endgame() {

		setTitle("End Game");
		Container contentPaneEnd = getContentPane();
		contentPaneEnd.setLayout(new BorderLayout());

		endtextPanel = new JPanel();
		endtextPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		endLabel.setFont(MENU_FONT);
		endtextPanel.add(endLabel);

		contentPaneEnd.add(endtextPanel, BorderLayout.NORTH);

		affichage.setEditable(false);
		affichage.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
		contentPaneEnd.add(affichage, BorderLayout.CENTER);

		endbuttonPanel = new JPanel();
		endbuttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		endbuttonPanel.add(replayButton);
		endbuttonPanel.add(exitButton);

		contentPaneEnd.add(endbuttonPanel, BorderLayout.SOUTH);

		replayButton.addActionListener(new ReplayAction(this, "Driving school"));
		exitButton.addActionListener(new ExitAction(this));

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		this.setSize(preferredSize);
		setResizable(true);
		setLocationRelativeTo(null);

		if (end == 0) {
			valider = "PERMIS ECHOUE ,vous avez fait plus de 5 erreurs. \n";
			valider += "Veuillez réessayer !\n";
			affichage.setText(valider);
		} else if (end == 1) {
			valider = "Le temps est écoulé, vous avez tenu jusqu'au bout. \n";
			valider += "PERMIS VALIDE \n";
			valider += "TOUTES MES FELICITATIONS\n";
			affichage.setText(valider);
		}

	}

	private class ReplayAction implements ActionListener {
		// Window to be closed.
		private JFrame window;
		private String title;

		public ReplayAction(JFrame window, String title) {
			this.window = window;
			this.title = title;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			window.dispose();
			Thread gameThread = new Thread(new MainGUI(title));
			gameThread.start();
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

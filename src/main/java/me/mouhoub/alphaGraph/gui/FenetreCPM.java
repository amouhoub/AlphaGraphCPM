package me.mouhoub.alphaGraph.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class FenetreCPM extends JFrame {

	public static CPM graphe = new CPM();
	private JScrollPane scroll = new JScrollPane(graphe);

	// private JPanel pan = new JPanel();

	public FenetreCPM() {

		this.setTitle("Le graphe PERT ");
		this.setSize(900, 700);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// pan.add(scroll);
		// setContentPane(pan);
		this.getContentPane().add(scroll, BorderLayout.CENTER);
		this.setVisible(true);

	}
}

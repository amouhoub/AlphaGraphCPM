package me.mouhoub.alphaGraph.gui;

/*************************************************************************************
 * Classe du JDialogue qui demande a l'utilisateur de definir le type de graphes
 * qu'il en veut : Oriente ou non-oriente
 ************************************************************************************/

@SuppressWarnings("serial")
public class TypeGraphe extends javax.swing.JDialog {

	/** Creates new form TypeGraphe */
	public TypeGraphe(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	private void initComponents() {

		jLabelQuestion = new javax.swing.JLabel();
		jBtnNonOriente = new javax.swing.JButton();
		jBtnOriente = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

		jLabelQuestion
				.setText("Quel est le type de graphe que vous voulez dessiner ?");

		jBtnNonOriente.setText("Graphe non oriente");
		jBtnNonOriente.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jBtnNonOrienteActionPerformed(evt);
			}
		});

		jBtnOriente.setText("Graphe oriente");
		jBtnOriente.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jBtnOrienteActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 381, Short.MAX_VALUE)
				.addGroup(
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addContainerGap()
																.addComponent(
																		jLabelQuestion,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		361,
																		Short.MAX_VALUE))
												.addGroup(
														layout.createSequentialGroup()
																.addGap(56, 56,
																		56)
																.addComponent(
																		jBtnOriente)
																.addGap(18, 18,
																		18)
																.addComponent(
																		jBtnNonOriente)))
								.addContainerGap()));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 102, Short.MAX_VALUE)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(jLabelQuestion,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										28,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jBtnOriente)
												.addComponent(jBtnNonOriente))
								.addContainerGap(22, Short.MAX_VALUE)));
		// setLocationRelativeTo(null);
		pack();
		setTitle("Orientation");
	}

	private void jBtnOrienteActionPerformed(java.awt.event.ActionEvent evt) {
		Fenetre.leGraphe.orientation = true;
		this.setVisible(false);
		dispose();
	}

	private void jBtnNonOrienteActionPerformed(java.awt.event.ActionEvent evt) {
		Fenetre.leGraphe.orientation = false;
		dispose();
	}

	// public static void main(String args[]) {
	// java.awt.EventQueue.invokeLater(new Runnable() {
	// public void run() {
	// TypeGraphe dialog = new TypeGraphe(new javax.swing.JFrame(), true);
	// dialog.addWindowListener(new java.awt.event.WindowAdapter() {
	// public void windowClosing(java.awt.event.WindowEvent e) {
	// System.exit(0);
	// }
	// });
	// dialog.setVisible(true);
	// }
	// });
	// }

	// Declaration des variables
	private javax.swing.JButton jBtnNonOriente;
	private javax.swing.JButton jBtnOriente;
	private javax.swing.JLabel jLabelQuestion;

}

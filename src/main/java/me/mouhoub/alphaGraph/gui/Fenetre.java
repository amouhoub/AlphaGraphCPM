package me.mouhoub.alphaGraph.gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.imageio.stream.FileImageOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import me.mouhoub.alphaGraph.core.AlphaGraph;
import me.mouhoub.alphaGraph.core.ConstructionMPM;
import me.mouhoub.alphaGraph.core.DetectionZ;
import me.mouhoub.alphaGraph.graph.Graphe;

import com.sun.imageio.plugins.png.PNGImageWriter;

@SuppressWarnings({ "serial", "restriction" })
public class Fenetre extends javax.swing.JFrame {

	/** Creates new form NewJFrame */
	public Fenetre() {
		initComponents();
	}

	private void initComponents() {

		leGraphe = new Graphe();
		lePert = new Graphe();

		// ////////////////////////////////////////////////////////////////

		jPnCanvas = new GraphEditor();
		jDialogueTypeGraphe = new TypeGraphe(this, rootPaneCheckingEnabled);
		jLayeredPaneTop = new javax.swing.JLayeredPane();
		jLayeredPaneLeft = new javax.swing.JLayeredPane();
		jBtnRun = new javax.swing.JButton();
		jBtnCapture = new javax.swing.JButton();
		jBtnNew = new javax.swing.JButton();
		jBtnLoad = new javax.swing.JButton();
		jBtnSave = new javax.swing.JButton();
		// jBtnHelp = new javax.swing.JButton();
		jTgBtnSommet = new javax.swing.JToggleButton();
		jTgBtnSelect = new javax.swing.JToggleButton();
		jTgBtnArc = new javax.swing.JToggleButton();
		jTgBtnDelete = new javax.swing.JToggleButton();
		jLblPalette = new javax.swing.JLabel();
		jTgBtnProprieteSommet = new javax.swing.JToggleButton();
		jPnSommet = new javax.swing.JPanel();
		jLabelNomSommet = new javax.swing.JLabel();
		jLabelDdbt1 = new javax.swing.JLabel();
		jLabelDdbt2 = new javax.swing.JLabel();
		jLabelDFin1 = new javax.swing.JLabel();
		jLabelFin2 = new javax.swing.JLabel();
		jTxtFieldDFin2 = new javax.swing.JTextField();
		jTxtFieldDFin1 = new javax.swing.JTextField();
		jTxtFieldDdbt2 = new javax.swing.JTextField();
		jTxtFieldDdbt1 = new javax.swing.JTextField();
		jTxtFieldNom = new javax.swing.JTextField();
		jPnArc = new javax.swing.JPanel();
		jTxtFieldValeur = new javax.swing.JTextField();
		jTxtFieldNomArc = new javax.swing.JTextField();
		jLabelNomArc = new javax.swing.JLabel();
		jLabelValeur = new javax.swing.JLabel();
		jTxtFieldExtremiteInitiale = new javax.swing.JTextField();
		jTxtFieldExtremiteTerminale = new javax.swing.JTextField();
		jLabExtremiteTerminale = new javax.swing.JLabel();
		jLabExtremiteinitiale = new javax.swing.JLabel();
		jTgBtnProprietesArc = new javax.swing.JToggleButton();
		// jLlbSousBanniere = new javax.swing.JLabel();
		// jToggleButton2 = new javax.swing.JToggleButton();
		jLblBanniere = new javax.swing.JLabel();
		jScrollPaneCanvas = new javax.swing.JScrollPane(jPnCanvas);

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jBtnCapture.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"camera.png"))); // NOI18N
		jBtnCapture.setBorderPainted(false);
		jBtnCapture.setContentAreaFilled(false);
		jBtnCapture.setFocusPainted(false);
		jBtnCapture.setToolTipText("Capturer une image");
		jBtnCapture.setRolloverIcon(new javax.swing.ImageIcon(getClass()
				.getResource("camera1.png"))); // NOI18N
		jBtnCapture.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jBtnCaptureActionPerformed(evt);
			}
		});
		jBtnCapture.setBounds(950, 30, 50, 50);// setBounds(960, 86, 50, 50);
		jLayeredPaneTop
				.add(jBtnCapture, javax.swing.JLayeredPane.DEFAULT_LAYER);

		jBtnRun.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"run.png"))); // NOI18N
		jBtnRun.setBorderPainted(false);
		jBtnRun.setContentAreaFilled(false);
		jBtnRun.setFocusPainted(false);
		jBtnRun.setRolloverIcon(new javax.swing.ImageIcon(getClass()
				.getResource("run1.png"))); // NOI18N
		jBtnRun.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jBtnRunActionPerformed(evt);
			}
		});

		jBtnRun.setBounds(890, 80, 63, 62);
		jLayeredPaneTop.add(jBtnRun, javax.swing.JLayeredPane.DEFAULT_LAYER);

		jBtnNew.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"new1.png"))); // NOI18N
		jBtnNew.setBorder(null);
		jBtnNew.setBorderPainted(false);
		jBtnNew.setContentAreaFilled(false);
		jBtnNew.setFocusPainted(false);
		jBtnNew.setToolTipText("Nouveau graphe");
		jBtnNew.setRolloverIcon(new javax.swing.ImageIcon(getClass()
				.getResource("new2.png"))); // NOI18N
		jBtnNew.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jBtnNewActionPerformed(evt);
			}
		});
		jBtnNew.setBounds(780, 70, 50, 50);
		jLayeredPaneTop.add(jBtnNew, javax.swing.JLayeredPane.DEFAULT_LAYER);

		jBtnLoad.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"load1.png"))); // NOI18N
		jBtnLoad.setBorder(null);
		jBtnLoad.setBorderPainted(false);
		jBtnLoad.setContentAreaFilled(false);
		jBtnLoad.setFocusPainted(false);
		jBtnLoad.setToolTipText("Ouvrir un graphe");
		jBtnLoad.setRolloverIcon(new javax.swing.ImageIcon(getClass()
				.getResource("load2.png"))); // NOI18N
		jBtnLoad.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jBtnLoadActionPerformed(evt);
			}
		});
		jBtnLoad.setBounds(830, 40, 50, 60);
		jLayeredPaneTop.add(jBtnLoad, javax.swing.JLayeredPane.DEFAULT_LAYER);

		jBtnSave.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"save1.png"))); // NOI18N
		jBtnSave.setBorder(null);
		jBtnSave.setBorderPainted(false);
		jBtnSave.setContentAreaFilled(false);
		jBtnSave.setFocusPainted(false);
		jBtnSave.setToolTipText("Enregistrer le graphe");
		jBtnSave.setRolloverIcon(new javax.swing.ImageIcon(getClass()
				.getResource("save2.png"))); // NOI18N
		jBtnSave.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jBtnSaveActionPerformed(evt);
			}
		});
		jBtnSave.setBounds(890, 30, 50, 50);
		jLayeredPaneTop.add(jBtnSave, javax.swing.JLayeredPane.DEFAULT_LAYER);

		// jBtnHelp.setIcon(new
		// javax.swing.ImageIcon(getClass().getResource("/help1.png"))); //
		// NOI18N
		// jBtnHelp.setBorder(null);
		// jBtnHelp.setBorderPainted(false);
		// jBtnHelp.setContentAreaFilled(false);
		// jBtnHelp.setFocusPainted(false);
		// jBtnHelp.setToolTipText("Aide");
		// jBtnHelp.setRolloverIcon(new
		// javax.swing.ImageIcon(getClass().getResource("/help2.png"))); //
		// NOI18N
		// jBtnHelp.addActionListener(new java.awt.event.ActionListener() {
		// public void actionPerformed(java.awt.event.ActionEvent evt) {
		// //jBtnHelpActionPerformed(evt);
		// }
		// });
		// jBtnHelp.setBounds(950, 30, 50, 50);
		// jLayeredPaneTop.add(jBtnHelp,
		// javax.swing.JLayeredPane.DEFAULT_LAYER);

		jTgBtnProprieteSommet.setIcon(new javax.swing.ImageIcon(getClass()
				.getResource("proprieteS1.png"))); // NOI18N
		jTgBtnProprieteSommet.setBorderPainted(false);
		jTgBtnProprieteSommet.setContentAreaFilled(false);
		jTgBtnProprieteSommet.setFocusPainted(false);
		jTgBtnProprieteSommet.setToolTipText("Proprietes Sommet");
		jTgBtnProprieteSommet.setRolloverIcon(new javax.swing.ImageIcon(
				getClass().getResource("proprieteS3.png"))); // NOI18N
		jTgBtnProprieteSommet.setSelectedIcon(new javax.swing.ImageIcon(
				getClass().getResource("proprieteS2.png"))); // NOI18N
		jTgBtnProprieteSommet
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jTgBtnProprieteSommetActionPerformed(evt);
					}
				});
		jTgBtnProprieteSommet.setSelected(true);
		jTgBtnProprieteSommet.setBounds(30, 0, 190, 40);
		jLayeredPaneTop.add(jTgBtnProprieteSommet,
				javax.swing.JLayeredPane.DEFAULT_LAYER);

		jPnSommet.setOpaque(false);
		jPnArc.setOpaque(false);

		jLabelNomSommet.setForeground(new java.awt.Color(255, 255, 255));
		jLabelNomSommet.setText("Teche");

		jLabelDdbt1.setForeground(new java.awt.Color(255, 255, 255));
		jLabelDdbt1.setText("Date Debut Tet");

		jLabelDdbt2.setForeground(new java.awt.Color(255, 255, 255));
		jLabelDdbt2.setText("Date Debut Tard :");

		jLabelDFin1.setForeground(new java.awt.Color(255, 255, 255));
		jLabelDFin1.setText("Date Fin Tet :");

		jLabelFin2.setForeground(new java.awt.Color(255, 255, 255));
		jLabelFin2.setText("Date Fin Tard :");

		jPnArc.setVisible(false);

		jLabelNomArc.setForeground(new java.awt.Color(255, 255, 255));
		jLabelNomArc.setText("Libelle :");

		jLabelValeur.setForeground(new java.awt.Color(255, 255, 255));
		jLabelValeur.setText("Coet :");

		jTxtFieldExtremiteInitiale.setEditable(false);

		jTxtFieldExtremiteTerminale.setEditable(false);

		jLabExtremiteTerminale.setForeground(new java.awt.Color(255, 255, 255));
		jLabExtremiteTerminale.setText("Extremite :");

		jLabExtremiteinitiale.setForeground(new java.awt.Color(255, 255, 255));
		jLabExtremiteinitiale.setText("Origine :");

		jTxtFieldValeur.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTxtFieldValeurActionPerformed(evt);
			}
		});

		jTxtFieldNomArc.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTxtFieldNomArcActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout jPnSommetLayout = new javax.swing.GroupLayout(
				jPnSommet);
		jPnSommet.setLayout(jPnSommetLayout);
		jPnSommetLayout
				.setHorizontalGroup(jPnSommetLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								jPnSommetLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(
												jLabelNomSommet,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												42,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												jTxtFieldNom,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												183, Short.MAX_VALUE)
										.addGap(18, 18, 18)
										.addGroup(
												jPnSommetLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(
																jLabelDdbt2,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																jLabelDdbt1))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPnSommetLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jTxtFieldDdbt2,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																84,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jTxtFieldDdbt1,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																84,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(18, 18, 18)
										.addGroup(
												jPnSommetLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addComponent(
																jLabelDFin1,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																jLabelFin2))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPnSommetLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jTxtFieldDFin2,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																84,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(
																jTxtFieldDFin1,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																84,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap()));
		jPnSommetLayout
				.setVerticalGroup(jPnSommetLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPnSommetLayout
										.createSequentialGroup()
										.addGroup(
												jPnSommetLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPnSommetLayout
																		.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																		.addComponent(
																				jLabelNomSommet)
																		.addComponent(
																				jTxtFieldNom,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(
																jPnSommetLayout
																		.createSequentialGroup()
																		.addComponent(
																				jLabelDFin1)
																		.addGap(12,
																				12,
																				12)
																		.addComponent(
																				jLabelFin2))
														.addGroup(
																jPnSommetLayout
																		.createSequentialGroup()
																		.addGroup(
																				jPnSommetLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jLabelDdbt1)
																						.addComponent(
																								jTxtFieldDdbt1,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPnSommetLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jLabelDdbt2)
																						.addComponent(
																								jTxtFieldDdbt2,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)))
														.addComponent(
																jTxtFieldDFin1,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGroup(
																jPnSommetLayout
																		.createSequentialGroup()
																		.addGap(26,
																				26,
																				26)
																		.addComponent(
																				jTxtFieldDFin2,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				javax.swing.GroupLayout.PREFERRED_SIZE)))
										.addContainerGap(24, Short.MAX_VALUE)));

		jPnSommet.setBounds(20, 50, 620, 70);
		jLayeredPaneTop.add(jPnSommet, javax.swing.JLayeredPane.DEFAULT_LAYER);

		javax.swing.GroupLayout jPnArcLayout = new javax.swing.GroupLayout(
				jPnArc);
		jPnArc.setLayout(jPnArcLayout);
		jPnArcLayout
				.setHorizontalGroup(jPnArcLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPnArcLayout
										.createSequentialGroup()
										.addContainerGap()

										.addGroup(
												jPnArcLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING,
																false)
														.addGroup(
																jPnArcLayout
																		.createSequentialGroup()
																		.addComponent(
																				jLabelNomArc)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				jTxtFieldNomArc,
																				javax.swing.GroupLayout.PREFERRED_SIZE,
																				136,
																				javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(
																jPnArcLayout
																		.createSequentialGroup()
																		.addComponent(
																				jLabelValeur)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
																		.addComponent(
																				jTxtFieldValeur)))
										.addGap(18, 18, 18)
										.addGroup(
												jPnArcLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jLabExtremiteTerminale)
														.addComponent(
																jLabExtremiteinitiale,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																53,
																Short.MAX_VALUE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPnArcLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																jTxtFieldExtremiteInitiale,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																174,
																Short.MAX_VALUE)
														.addComponent(
																jTxtFieldExtremiteTerminale,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																174,
																Short.MAX_VALUE))
										.addGap(222, 222, 222)));
		jPnArcLayout
				.setVerticalGroup(jPnArcLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPnArcLayout
										.createSequentialGroup()
										.addGroup(
												jPnArcLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPnArcLayout
																		.createSequentialGroup()
																		.addGroup(
																				jPnArcLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jTxtFieldNomArc,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)
																						.addComponent(
																								jLabelNomArc))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPnArcLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jLabelValeur)
																						.addComponent(
																								jTxtFieldValeur,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE)))
														.addGroup(
																jPnArcLayout
																		.createSequentialGroup()
																		.addGroup(
																				jPnArcLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jLabExtremiteinitiale)
																						.addComponent(
																								jTxtFieldExtremiteInitiale,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE))
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addGroup(
																				jPnArcLayout
																						.createParallelGroup(
																								javax.swing.GroupLayout.Alignment.BASELINE)
																						.addComponent(
																								jLabExtremiteTerminale)
																						.addComponent(
																								jTxtFieldExtremiteTerminale,
																								javax.swing.GroupLayout.PREFERRED_SIZE,
																								javax.swing.GroupLayout.DEFAULT_SIZE,
																								javax.swing.GroupLayout.PREFERRED_SIZE))))
										.addContainerGap(24, Short.MAX_VALUE)));

		jPnArc.setBounds(20, 50, 657, 70);
		jLayeredPaneTop.add(jPnArc, javax.swing.JLayeredPane.DEFAULT_LAYER);

		jTxtFieldDFin2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTxtFieldDFin2ActionPerformed(evt);
			}
		});

		jTxtFieldDFin1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTxtFieldDFin1ActionPerformed(evt);
			}
		});

		jTxtFieldDdbt2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTxtFieldDdbt2ActionPerformed(evt);
			}
		});

		jTxtFieldDdbt1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTxtFieldDdbt1ActionPerformed(evt);
			}
		});

		jTxtFieldNom.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTxtFieldNomActionPerformed(evt);
			}
		});

		jTgBtnProprietesArc.setIcon(new javax.swing.ImageIcon(getClass()
				.getResource("proprieteA1.png"))); // NOI18N
		jTgBtnProprietesArc.setBorderPainted(false);
		jTgBtnProprietesArc.setContentAreaFilled(false);
		jTgBtnProprietesArc.setFocusPainted(false);
		jTgBtnProprietesArc.setToolTipText("Proprietes Arc");
		jTgBtnProprietesArc.setRolloverIcon(new javax.swing.ImageIcon(
				getClass().getResource("proprieteA3.png"))); // NOI18N
		jTgBtnProprietesArc.setSelectedIcon(new javax.swing.ImageIcon(
				getClass().getResource("proprieteA2.png"))); // NOI18N
		jTgBtnProprietesArc
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						jTgBtnProprietesArcActionPerformed(evt);
					}
				});
		jTgBtnProprietesArc.setBounds(220, 0, 190, 40);
		jLayeredPaneTop.add(jTgBtnProprietesArc,
				javax.swing.JLayeredPane.DEFAULT_LAYER);

		jLblBanniere.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"banner.png"))); // NOI18N
		jLblBanniere.setAlignmentY(0.0F);
		jLblBanniere.setBounds(0, 0, 1024, 158);
		jLayeredPaneTop.add(jLblBanniere,
				javax.swing.JLayeredPane.DEFAULT_LAYER);

		jScrollPaneCanvas.setBorder(null);
		// jScrollPaneCanvas.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		// jScrollPaneCanvas.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		// jScrollPaneCanvas.setEnabled(true);
		// jScrollPaneCanvas.setFocusable(true);

		// jScrollPaneCanvas.setBounds(60, 155, 959, 586);
		// jScrollPaneCanvas.setLocation(60, 155);

		jTgBtnSelect.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"select1.png"))); // NOI18N
		jTgBtnSelect.setBorder(null);
		jTgBtnSelect.setBorderPainted(false);
		jTgBtnSelect.setContentAreaFilled(false);
		jTgBtnSelect.setFocusPainted(false);
		jTgBtnSelect.setToolTipText("Selection");
		jTgBtnSelect.setRolloverIcon(new javax.swing.ImageIcon(getClass()
				.getResource("select2.png"))); // NOI18N
		jTgBtnSelect.setSelectedIcon(new javax.swing.ImageIcon(getClass()
				.getResource("select3.png"))); // NOI18N
		jTgBtnSelect.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTgBtnSelectActionPerformed(evt);
			}
		});
		jTgBtnSelect.setSelected(true);
		GraphEditor.curseur = 1;
		jTgBtnSelect.setBounds(0, 0, 50, 60);
		jLayeredPaneLeft.add(jTgBtnSelect,
				javax.swing.JLayeredPane.DEFAULT_LAYER);

		jTgBtnSommet.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"sommet1.png"))); // NOI18N
		jTgBtnSommet.setBorder(null);
		jTgBtnSommet.setBorderPainted(false);
		jTgBtnSommet.setContentAreaFilled(false);
		jTgBtnSommet.setFocusPainted(false);
		jTgBtnSommet.setToolTipText("Dessiner un sommet");
		jTgBtnSommet.setRolloverIcon(new javax.swing.ImageIcon(getClass()
				.getResource("sommet2.png"))); // NOI18N
		jTgBtnSommet.setSelectedIcon(new javax.swing.ImageIcon(getClass()
				.getResource("sommet3.png"))); // NOI18N
		jTgBtnSommet.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTgBtnSommetActionPerformed(evt);
			}
		});
		jTgBtnSommet.setBounds(0, 70, 50, 60);
		jLayeredPaneLeft.add(jTgBtnSommet,
				javax.swing.JLayeredPane.DEFAULT_LAYER);

		jTgBtnArc.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"arc1.png"))); // NOI18N
		jTgBtnArc.setBorder(null);
		jTgBtnArc.setBorderPainted(false);
		jTgBtnArc.setContentAreaFilled(false);
		jTgBtnArc.setFocusPainted(false);
		jTgBtnArc.setToolTipText("Dessiner un arc");
		jTgBtnArc.setRolloverIcon(new javax.swing.ImageIcon(getClass()
				.getResource("arc2.png"))); // NOI18N
		jTgBtnArc.setSelectedIcon(new javax.swing.ImageIcon(getClass()
				.getResource("arc3.png"))); // NOI18N
		jTgBtnArc.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTgBtnArcActionPerformed(evt);
			}
		});
		jTgBtnArc.setBounds(0, 130, 50, 57);
		jLayeredPaneLeft.add(jTgBtnArc, javax.swing.JLayeredPane.DEFAULT_LAYER);

		jTgBtnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"delete1.png"))); // NOI18N
		jTgBtnDelete.setBorder(null);
		jTgBtnDelete.setBorderPainted(false);
		jTgBtnDelete.setContentAreaFilled(false);
		jTgBtnDelete.setFocusPainted(false);
		jTgBtnDelete.setToolTipText("Supprimer");
		jTgBtnDelete.setRolloverIcon(new javax.swing.ImageIcon(getClass()
				.getResource("delete2.png"))); // NOI18N
		jTgBtnDelete.setSelectedIcon(new javax.swing.ImageIcon(getClass()
				.getResource("delete3.png"))); // NOI18N
		jTgBtnDelete.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTgBtnDeleteActionPerformed(evt);
			}
		});
		jTgBtnDelete.setBounds(0, 190, 50, 70);
		jLayeredPaneLeft.add(jTgBtnDelete,
				javax.swing.JLayeredPane.DEFAULT_LAYER);

		jLblPalette.setIcon(new javax.swing.ImageIcon(getClass().getResource(
				"palet.png"))); // NOI18N
		jLblPalette.setBounds(0, 0, 56, 350);
		jLayeredPaneLeft.add(jLblPalette,
				javax.swing.JLayeredPane.DEFAULT_LAYER);

		// this.getContentPane().add(jLayeredPaneTop, BorderLayout.CENTER);
		// this.getContentPane().add(jLayeredPaneLeft, BorderLayout.WEST);
		// this.getContentPane().add(jScrollPaneCanvas, BorderLayout.SOUTH);

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(jLayeredPaneLeft,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										60,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jScrollPaneCanvas,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										958, Short.MAX_VALUE))
				.addComponent(jLayeredPaneTop,
						javax.swing.GroupLayout.PREFERRED_SIZE, 1024,
						javax.swing.GroupLayout.PREFERRED_SIZE));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(jLayeredPaneTop,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										158,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(
														jScrollPaneCanvas,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														520, Short.MAX_VALUE)
												.addComponent(
														jLayeredPaneLeft,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														503, Short.MAX_VALUE))));

		jTxtFieldDFin2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTxtFieldDFin2ActionPerformed(evt);
			}
		});

		jTxtFieldDFin1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTxtFieldDFin1ActionPerformed(evt);
			}
		});

		jTxtFieldDdbt2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTxtFieldDdbt2ActionPerformed(evt);
			}
		});

		jTxtFieldDdbt1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTxtFieldDdbt1ActionPerformed(evt);
			}
		});

		jTxtFieldNom.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jTxtFieldNomActionPerformed(evt);
			}
		});

		// this.setResizable(false);
		this.setTitle("AlphaGraph");
		pack();
	}

	private void jBtnRunActionPerformed(java.awt.event.ActionEvent evt) {
		AlphaGraph alpha = new AlphaGraph();
		DetectionZ detecter = new DetectionZ();
		ConstructionMPM mpm = new ConstructionMPM();
		// Scanner sc = new Scanner(System.in);
		detecter.run();
		// sc.nextLine();
		// for (int i=0; i<10000000; i++){
		// //double x = Math.pow(2, 16);
		// }
		mpm.run();
		// sc.nextLine();
		// for (int i=0; i<10000000; i++){
		// //double x = Math.pow(2, 16);
		// }
		alpha.run();
		if (alpha.ok) {
			FenetreCPM leCPM = new FenetreCPM();
			leCPM.setLocationRelativeTo(this);
			leCPM.setVisible(true);
		}
	}

	/*
	 * private void jBtnHelpActionPerformed(java.awt.event.ActionEvent evt) {
	 * AlphaGraph alpha = new AlphaGraph(); alpha.run();
	 * 
	 * }
	 */

	private void jBtnCaptureActionPerformed(java.awt.event.ActionEvent evt) {
		fileChooser.showSaveDialog(null);
		if (fileChooser.getSelectedFile() != null) {
			String fileName = fileChooser.getSelectedFile().getAbsolutePath()
					+ ".jpg";

			try {
				File imageFile;
				FileImageOutputStream outputStream;
				BufferedImage img;

				imageFile = new File(fileName);

				img = (BufferedImage) ((GraphEditor) jPnCanvas).getImage();

				PNGImageWriter writer = new PNGImageWriter(null);

				writer.setOutput(outputStream = new FileImageOutputStream(
						imageFile));
				writer.write(img);

				outputStream.close();

				writer.dispose();

			} catch (IOException err) {
				JOptionPane.showMessageDialog(null,
						"Impossible de sauvegarder le fichier " + fileName);
			}
		}

	}

	private void jTgBtnProprietesArcActionPerformed(
			java.awt.event.ActionEvent evt) {
		jTgBtnProprieteSommet.setSelected(false);
		jTgBtnProprietesArc.setSelected(true);
		jPnSommet.setVisible(false);
		jPnArc.setVisible(true);

	}

	private void jTgBtnProprieteSommetActionPerformed(
			java.awt.event.ActionEvent evt) {
		jTgBtnProprieteSommet.setSelected(true);
		jTgBtnProprietesArc.setSelected(false);
		jPnArc.setVisible(false);
		jPnSommet.setVisible(true);
	}

	private void jTgBtnSelectActionPerformed(java.awt.event.ActionEvent evt) {
		if (this.jTgBtnSelect.isSelected()) {
			this.jTgBtnArc.setSelected(false);
			this.jTgBtnSommet.setSelected(false);
			this.jTgBtnDelete.setSelected(false);
			GraphEditor.curseur = 1;
		} else {
			this.jTgBtnSelect.setSelected(true);
			GraphEditor.curseur = 1;
		}
	}

	private void jTgBtnSommetActionPerformed(java.awt.event.ActionEvent evt) {
		if (this.jTgBtnSommet.isSelected()) {
			this.jTgBtnSelect.setSelected(false);
			this.jTgBtnArc.setSelected(false);
			this.jTgBtnDelete.setSelected(false);
			GraphEditor.curseur = 3;
			// jPnCanvas.modifierCurseur(jPnCanvas,"interdit.png",new
			// Point(15,15));
		} else {
			this.jTgBtnSommet.setSelected(true);
			GraphEditor.curseur = 3;
		}
	}

	private void jTgBtnArcActionPerformed(java.awt.event.ActionEvent evt) {
		if (this.jTgBtnArc.isSelected()) {
			this.jTgBtnSelect.setSelected(false);
			this.jTgBtnSommet.setSelected(false);
			this.jTgBtnDelete.setSelected(false);
			GraphEditor.curseur = 4;
		} else {
			this.jTgBtnArc.setSelected(true);
			GraphEditor.curseur = 4;
		}

	}

	private void jTgBtnDeleteActionPerformed(java.awt.event.ActionEvent evt) {
		if (this.jTgBtnDelete.isSelected()) {
			this.jTgBtnSelect.setSelected(false);
			this.jTgBtnSommet.setSelected(false);
			this.jTgBtnArc.setSelected(false);
			GraphEditor.curseur = 2;
		} else {
			this.jTgBtnDelete.setSelected(true);
			GraphEditor.curseur = 2;
		}
	}

	private void jBtnNewActionPerformed(java.awt.event.ActionEvent evt) {

		if (Fenetre.leGraphe.modifie) {
			switch (JOptionPane.showConfirmDialog(null,
					"Voulez-vous sauvgarder le Graphe en cours ?",
					"Enregistrement", 1)) {
			case 0:
				save();
				leGraphe = new Graphe();
				Fenetre.jPnCanvas = null;
				Fenetre.jPnCanvas = new GraphEditor();
				repaint();
				nomSommet = 0;
				nomArc = 0;
				GraphEditor.curseur = 0;
				this.jTgBtnSelect.setSelected(false);
				this.jTgBtnSommet.setSelected(false);
				this.jTgBtnDelete.setSelected(false);
				this.jTgBtnArc.setSelected(false);
				this.jDialogueTypeGraphe.setVisible(true);

				break;
			case 1:
				leGraphe = new Graphe();
				Fenetre.jPnCanvas = null;
				Fenetre.jPnCanvas = new GraphEditor();
				repaint();
				nomSommet = 0;
				nomArc = 0;
				GraphEditor.curseur = 0;
				this.jTgBtnSelect.setSelected(false);
				this.jTgBtnSommet.setSelected(false);
				this.jTgBtnDelete.setSelected(false);
				this.jTgBtnArc.setSelected(false);
				this.jDialogueTypeGraphe.setVisible(true);

			}
			fichier = null;
		} else {
			leGraphe = new Graphe();
			Fenetre.jPnCanvas = null;
			Fenetre.jPnCanvas = new GraphEditor();
			repaint();
			nomSommet = 0;
			nomArc = 0;
			GraphEditor.curseur = 0;
			this.jTgBtnSelect.setSelected(false);
			this.jTgBtnSommet.setSelected(false);
			this.jTgBtnDelete.setSelected(false);
			this.jTgBtnArc.setSelected(false);
			this.jDialogueTypeGraphe.setVisible(true);
			fichier = null;
		}

	}

	private void jBtnLoadActionPerformed(java.awt.event.ActionEvent evt) {
		if (Fenetre.leGraphe.modifie) {
			switch (JOptionPane.showConfirmDialog(null,
					"Voulez-vous sauvgarder le Graphe en cours ?",
					"Enregistrement", 1)) {
			case 0:
				save();
				open();
			case 1:
				open();
			}
		} else {
			open();
		}
	}

	private void jBtnSaveActionPerformed(java.awt.event.ActionEvent evt) {
		save();
	}

	private void jTxtFieldNomArcActionPerformed(java.awt.event.ActionEvent evt) {
		GraphEditor.mrArc.setNom(jTxtFieldNomArc.getText());
	}

	// private void
	// jTxtFieldExtremiteInitialeActionPerformed(java.awt.event.ActionEvent evt)
	// {
	// }

	private void jTxtFieldValeurActionPerformed(java.awt.event.ActionEvent evt) {
		GraphEditor.mrArc
				.setValeur(Integer.parseInt((jTxtFieldValeur.getText())));
	}

	// private void
	// jTxtFieldExtremiteTerminaleActionPerformed(java.awt.event.ActionEvent
	// evt) {
	// }

	private void jTxtFieldNomActionPerformed(java.awt.event.ActionEvent evt) {
		if (GraphEditor.mrSommet != null)
			GraphEditor.mrSommet.setNom(jTxtFieldNom.getText());
	}

	private void jTxtFieldDdbt1ActionPerformed(java.awt.event.ActionEvent evt) {
		GraphEditor.mrSommet.setDateDebutTot(Integer.parseInt((jTxtFieldDdbt1
				.getText())));
	}

	private void jTxtFieldDdbt2ActionPerformed(java.awt.event.ActionEvent evt) {
		GraphEditor.mrSommet.setDateDebutTard(Integer.parseInt((jTxtFieldDdbt2
				.getText())));
	}

	private void jTxtFieldDFin1ActionPerformed(java.awt.event.ActionEvent evt) {
		GraphEditor.mrSommet.setDateFinTot(Integer.parseInt((jTxtFieldDFin1
				.getText())));
	}

	private void jTxtFieldDFin2ActionPerformed(java.awt.event.ActionEvent evt) {
		GraphEditor.mrSommet.setDateFinTard(Integer.parseInt((jTxtFieldDFin2
				.getText())));
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				Fenetre maFenetre = new Fenetre();
				maFenetre.setLocation(0, 0);
				maFenetre.setVisible(true);
				maFenetre.setSize(1024, 768);
				maFenetre.addWindowListener(new java.awt.event.WindowAdapter() {
					public void windowClosing(java.awt.event.WindowEvent e) {
						System.exit(0);
					}
				});
				maFenetre.jDialogueTypeGraphe.setLocationRelativeTo(maFenetre);
				maFenetre.jDialogueTypeGraphe.setVisible(true);
			}
		});
	}

	// Variables declaration - do not modify
	private javax.swing.JButton jBtnRun;
	private javax.swing.JButton jBtnCapture;
	// private javax.swing.JButton jBtnHelp;
	private javax.swing.JButton jBtnLoad;
	private javax.swing.JButton jBtnNew;
	private javax.swing.JButton jBtnSave;
	private javax.swing.JLabel jLabExtremiteTerminale;
	private javax.swing.JLabel jLabExtremiteinitiale;
	private javax.swing.JLabel jLblPalette;
	private javax.swing.JLabel jLabelDFin1;
	private javax.swing.JLabel jLabelDdbt1;
	private javax.swing.JLabel jLabelDdbt2;
	private javax.swing.JLabel jLabelFin2;
	private javax.swing.JLabel jLabelNomArc;
	private javax.swing.JLabel jLabelNomSommet;
	private javax.swing.JLabel jLabelValeur;
	private javax.swing.JLayeredPane jLayeredPaneTop;
	private javax.swing.JLayeredPane jLayeredPaneLeft;
	private javax.swing.JLabel jLblBanniere;
	// private javax.swing.JLabel jLlbSousBanniere;
	private javax.swing.JPanel jPnArc;
	public static javax.swing.JPanel jPnCanvas;
	private javax.swing.JPanel jPnSommet;
	private javax.swing.JToggleButton jTgBtnArc;
	private javax.swing.JToggleButton jTgBtnDelete;
	private javax.swing.JToggleButton jTgBtnProprieteSommet;
	private javax.swing.JToggleButton jTgBtnProprietesArc;
	private javax.swing.JToggleButton jTgBtnSelect;
	private javax.swing.JToggleButton jTgBtnSommet;
	// private javax.swing.JToggleButton jToggleButton2;
	static javax.swing.JTextField jTxtFieldDFin1;
	static javax.swing.JTextField jTxtFieldDFin2;
	static javax.swing.JTextField jTxtFieldDdbt1;
	static javax.swing.JTextField jTxtFieldDdbt2;
	static javax.swing.JTextField jTxtFieldExtremiteInitiale;
	static javax.swing.JTextField jTxtFieldExtremiteTerminale;
	static javax.swing.JTextField jTxtFieldNom;
	static javax.swing.JTextField jTxtFieldNomArc;
	static javax.swing.JTextField jTxtFieldValeur;
	private javax.swing.JScrollPane jScrollPaneCanvas;
	// private javax.swing.JPanel jPnMain;

	// End of variables declaration
	// /////////////////////////////////////////////////
	public static int nomSommet = 0;
	public static int nomArc = 0;
	public static Graphe leGraphe;
	public static boolean oriente;
	private TypeGraphe jDialogueTypeGraphe;
	File fichier = null;
	JFileChooser fileChooser = new JFileChooser();
	// public static Vector lesGraphes;
	// public static Vector lesFichiers;

	public static Graphe lePert;

	void save() {
		if (fichier != null) {
			try {
				FileOutputStream fis = new FileOutputStream(fichier);
				ObjectOutputStream ois = new ObjectOutputStream(fis);
				ois.writeObject(leGraphe);
				ois.close();
			} catch (Exception err) {
				System.out.println("Erreur" + err);
			}
		} else {
			saveAs();
		}
	}

	void saveAs() {
		fileChooser.showSaveDialog(null);
		if (fileChooser.getSelectedFile() != null) {
			fichier = fileChooser.getSelectedFile();
			try {
				FileOutputStream fis = new FileOutputStream(fichier);
				ObjectOutputStream ois = new ObjectOutputStream(fis);
				ois.writeObject(leGraphe);
				ois.close();
			} catch (Exception err) {
				System.out.println("Erreur" + err);
			}
		}
	}

	void saveTempo() {
		fileChooser.showSaveDialog(null);
		if (fileChooser.getSelectedFile() != null) {
			fichier = fileChooser.getSelectedFile();
			try {
				FileOutputStream fis = new FileOutputStream(fichier);
				ObjectOutputStream ois = new ObjectOutputStream(fis);
				ois.writeObject(leGraphe);
				ois.close();
			} catch (Exception err) {
				System.out.println("Erreur" + err);
			}
		}
	}

	void open() {
		fileChooser.showOpenDialog(null);
		if (fileChooser.getSelectedFile() != null) {
			fichier = fileChooser.getSelectedFile();
			try {
				FileInputStream fis = new FileInputStream(fichier);
				ObjectInputStream ois = new ObjectInputStream(fis);
				leGraphe = null;
				leGraphe = (Graphe) ois.readObject();
				ois.close();
				repaint();

			} catch (Exception err) {
				System.out.println("Erreur : " + err);
			}
		}
	}

	public void crtlZ() {

	}

	public void crtlY() {

	}

}

package me.mouhoub.alphaGraph.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.geom.QuadCurve2D;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.lang.Math;

import javax.swing.JPanel;

import me.mouhoub.alphaGraph.graph.Arc;
import me.mouhoub.alphaGraph.graph.Graphe;
import me.mouhoub.alphaGraph.graph.ListeSommets;
import me.mouhoub.alphaGraph.graph.Sommet;

@SuppressWarnings("serial")
public class GraphEditor extends JPanel implements MouseListener,
		MouseMotionListener {

	/** LES ATTRIBUTS ***********************************************************************/

	public final static int RAYON_SOMMET = 10;
	public final static int CONTOUR_SOMMET = 10;
	public final static Color leBackground = new java.awt.Color(239, 239, 239);
	private static final int BARB = 10;
	final double lePHI = Math.toRadians(20);
	// private Point2D[] points;
	// private int current;
	ShapeMaker shapeMaker = new QuadCurveMaker();
	Rectangle rctSelection;
	int l, h, lp, hp;// pour garder la distance entre le rect et le Cursor
	Point point = new Point();
	int xRct, yRct;
	// Graphics g;
	// Cet attribut " Curseur " designe l'etat de fonctionnement du curseur :
	// 0 : sans fonctionnement
	// 1 : Selection
	// 2 : Suppression
	// 3 : Dessin de sommets
	// 4 : Dessin d'arcs
	public static int curseur = 0;
	private Sommet sommetChoisi;
	private Arc arcChoisi, leArcChoisi = null;
	private Sommet sommetDebut;
	private Sommet sommetFin;
	private ListeSommets sommetsSelectionnes = new ListeSommets();
	// private ListeArcs arcsSelectionnes = new ListeArcs();
	// private ListeSommets lesSommetsSelectionnes;
	private boolean selectionMultiple = false;
	// private Sommet sommetProvisoire;
	private Arc arcProvisoire;
	private int x;
	private int y;

	public static Sommet mrSommet;
	public static Arc mrArc;

	public Color bleuGris = new Color(59, 59, 104);
	public Color bleuCiel = new Color(27, 134, 255);
	public Color Crevette = new Color(237, 28, 36);

	/** LES METHODES ************************************************************************/

	public GraphEditor() {
		addMouseListener(this);
		addMouseMotionListener(this);
		setSize(getPreferredSize());
		setLocation(60, 140);
		curseur = 0;
		sommetChoisi = null;
		arcChoisi = null;
		leArcChoisi = null;
		sommetDebut = null;
		sommetFin = null;
		sommetsSelectionnes = new ListeSommets();
		selectionMultiple = false;
	}

	public void finalize() {
		System.out.println("objet supprime");
	}

	// La methode permettant de selectionner un ensemble d'arcs avec un clic ou
	// une zone de selection avec la souris
	// public void selectionnerArcs() {
	// arcsSelectionnes.removeAllElements();
	// }

	// La methode permettant de selectionner un ensemble de sommets avec un clic
	// ou une zone de selection avec la souris
	// public void selectionnerSommets() {
	// sommetsSelectionnes.removeAllElements();
	// }

	// La methode permettant de supprimer un ensemble de sommets deja
	// selectionne
	public void supprimerSommet(Sommet leSommet) {
		Fenetre.leGraphe.sommets.supprimerSommet(leSommet);
		// leSommet.finalize();
	}

	// La methode permettant de supprimer un ensemble d'arcs deja selectionne
	public void supprimerArc(Arc leArc) {
		Fenetre.leGraphe.arcs.supprimerArc(arcChoisi);
		// leArc.finalize();
	}

	// La methode permettant de selectionner un ensemble d'arcs et de sommets
	// cad un sous ensemble du graphe
	public Graphe selection() {

		return null;
	}

	private Sommet sommetProche(int leX, int leY) {
		Iterator<Sommet> it = Fenetre.leGraphe.sommets.iterator();
		while (it.hasNext()) {
			Sommet leSommet = it.next();
			if (leSommet.dansIntervale(leX, leY)) {
				return leSommet;
			}
		}
		return null;
	}

	private boolean sommetDansContour(int leX, int leY) {
		Iterator<Sommet> it = Fenetre.leGraphe.sommets.iterator();
		while (it.hasNext()) {
			Sommet leSommet = it.next();
			if (leSommet.dansContour(leX, leY)) {
				return true;
			}
		}
		return false;
	}

	private Arc arcProche(int x, int y) {
		Iterator<Arc> it = Fenetre.leGraphe.arcs.iterator();
		while (it.hasNext()) {
			Arc leArc = (Arc) it.next();
			if (leArc.estSelectionne(x, y))
				return leArc;
		}
		return null;
	}

	// La methode permettant de dessiner un sommet
	public void dessinerSommet(Sommet leSommet, Color laCouleur, Graphics g2d) {
		// Graphics g2d = getGraphics();
		int xTemp, yTemp;
		g2d.setFont(new Font("Arial", Font.BOLD, 12));

		// On dessine d'abord le sommet

		xTemp = (int) leSommet.getCoordonnees().getX();
		yTemp = (int) leSommet.getCoordonnees().getY();
		g2d.setColor(Color.white);
		g2d.fillOval(xTemp - (RAYON_SOMMET), yTemp - (RAYON_SOMMET), 20, 20);
		g2d.setColor(laCouleur);
		g2d.drawOval(xTemp - (RAYON_SOMMET), yTemp - (RAYON_SOMMET), 20, 20);

		// Puis on ecrit en sur le sommet son libelle
		g2d.setColor(laCouleur);
		g2d.drawString("" + leSommet.getLibelle(), xTemp - 6, yTemp + 5);

		// On reprend les couleurs par defaut
		g2d.setColor(Color.black);
	}

	public void dessinerTachesFictives(Sommet leSommet, Color laCouleur,
			Graphics g2d) {
		// Graphics g2d = getGraphics();
		int xTemp, yTemp;
		g2d.setFont(new Font("Arial", Font.BOLD, 12));

		// On dessine d'abord le sommet
		g2d.setColor(laCouleur);
		xTemp = (int) leSommet.getCoordonnees().getX();
		yTemp = (int) leSommet.getCoordonnees().getY();
		g2d.fillOval(xTemp - (RAYON_SOMMET / 2), yTemp - (RAYON_SOMMET / 2),
				10, 10);

		// Puis on ecrit en sur le sommet son libelle
		g2d.setColor(laCouleur);
		g2d.drawString("" + leSommet.getLibelle(), xTemp, yTemp - 10);

		// On reprend les couleurs par defaut
		g2d.setColor(Color.black);
	}

	// La methode permettant de dessiner un arc
	public void dessinerArc(Arc leArc, Color laCouleur, Graphics g2d) {
		// Graphics g2d = getGraphics();
		// double alpha1 = 0, alpha2 =0, alpha=0;
		int x1Temp, y1Temp, x2Temp, y2Temp;
		int[] px = new int[3];
		int[] py = new int[3];
		x1Temp = (int) leArc.getExtremiteInitiale().getCoordonnees().getX();
		y1Temp = (int) leArc.getExtremiteInitiale().getCoordonnees().getY();
		x2Temp = (int) leArc.getExtremiteTerminale().getCoordonnees().getX();
		y2Temp = (int) leArc.getExtremiteTerminale().getCoordonnees().getY();

		// int laDistance = (int)
		// leArc.getExtremiteInitiale().getCoordonnees().distance(leArc.getExtremiteTerminale().getCoordonnees());

		double dy = y2Temp - y1Temp;
		double dx = x2Temp - x1Temp;
		String s = leArc.getValeur() + "";
		int a = (int) (x1Temp + (dx / 2) - ((s.length() / 2) * 7));
		int b = (int) (y1Temp + dy / 2);

		// /////////////////////////////////////
		g2d.setFont(new Font("Arial", Font.BOLD, 12));

		// Point2D[] points;
		// int SIZE = 10;

		leArc.laTouche();
		// Point touche1 = new Point();
		// touche1 = (Point) leArc.getDebut();
		// Point touche2 = new Point();
		// touche2 = leArc.getFin();

		// On dessine d'abord l'arc
		g2d.setColor(laCouleur);
		// points = new Point2D[3];

		if (leArc.getExtremiteInitiale() == leArc.getExtremiteTerminale())
			g2d.drawArc(x1Temp - 36, y1Temp - 9, 38, 33, 22, 318);
		else {

			// points[0]=leArc.getExtremiteInitiale().getCoordonnees();
			// points[2]=leArc.getExtremiteTerminale().getCoordonnees();
			// points[1]=touverPointControlArc(x1Temp, x2Temp, y1Temp, y2Temp);
			// //System.out.println(points[1].getX()+" / "+points[1].getY());
			//
			//
			// if (points == null)
			// return;
			// Graphics2D g2 = (Graphics2D) g2d;
			// for (int i = 0; i < points.length; i++) {
			// double x = points[i].getX() - SIZE / 2;
			// double y = points[i].getY() - SIZE / 2;
			// g2.fill(new Rectangle2D.Double(x, y, SIZE, SIZE));

			// g2.draw(shapeMaker.makeShape(points));

			// g2d.drawLine((int)touche1.getX(),(int)touche1.getY(),(int)touche2.getX(),(int)touche2.getY());
			g2d.drawLine(x1Temp, y1Temp, x2Temp, y2Temp);
			// g2d.drawRect(50, 50,200,100);
			// g2d.drawArc(50, 50, 200, 100, 45, 180);

			if (Fenetre.leGraphe.estOriente() == true) {
				double theta = Math.atan2(dy, dx);
				theta += Math.PI;
				px[0] = (x1Temp + x2Temp) / 2;// -RAYON_SOMMET; //(int)
												// x2Temp.getX();
				py[0] = (y1Temp + y2Temp) / 2;// -RAYON_SOMMET; //(int)
												// leArc.fin.getY();
				px[1] = (x1Temp + x2Temp) / 2
						+ (int) (BARB * Math.cos(theta - lePHI));
				py[1] = (y1Temp + y2Temp) / 2
						+ (int) (BARB * Math.sin(theta - lePHI));
				px[2] = (x1Temp + x2Temp) / 2
						+ (int) (BARB * Math.cos(theta + lePHI));
				py[2] = (y1Temp + y2Temp) / 2
						+ (int) (BARB * Math.sin(theta + lePHI));

				g2d.drawPolygon(px, py, 3);
				g2d.fillPolygon(px, py, 3);
			}

			// On reprend les couleurs par defaut
			g2d.setColor(Color.black);
			g2d.setFont(new Font("Arial", Font.BOLD, 12));
			g2d.drawString(s, a + 3, b);
		}
	}

	// //////////////////////////////////////////////////////////////////////////////////////////

	public void mousePressed(MouseEvent e) {

		// switch (curseur) {
		// case 4 : {
		// Point p = e.getPoint();
		// for (int i = 0; i < points.length; i++) {
		// double x = points[i].getX() - 10 / 2;
		// double y = points[i].getY() - 10 / 2;
		// Rectangle2D r = new Rectangle2D.Double(x, y, 10, 10);
		// if (r.contains(p)) {
		// current = i;
		// return;
		// }
		// }
		// }
		//
		// }

		if (e.getButton() == 1) {
			switch (curseur) {

			// ////////////////////////////////////////////////////////////////////////////

			case 1: { // // en mode Selection

				// Les sommets
				// ///////////////////////////////////////////////////
				leArcChoisi = null;
				sommetChoisi = null;
				// on recup�re le sommet choisi
				sommetChoisi = sommetProche(e.getX(), e.getY());
				mrSommet = sommetChoisi;
				// vider
				if (!sommetsSelectionnes.contains(sommetChoisi)) {
					sommetsSelectionnes.clear();
					selectionMultiple = false;
				}

				if (sommetChoisi == null) {
					// selection=new LinkedList();

					rctSelection = new Rectangle(e.getX(), e.getY(), 0, 0);
					selectionMultiple = true;
					// Confirmation

				}

				if (sommetChoisi != null) {
					if (!sommetsSelectionnes.contientSommet(sommetChoisi)) {
						sommetsSelectionnes.ajouterSommet(sommetChoisi);
						// mrSommet=sommetChoisi;
						Fenetre.jTxtFieldNom.setText(sommetChoisi.getNom());
						Fenetre.jTxtFieldDdbt1.setText(""
								+ sommetChoisi.getDateDebutTot());
						Fenetre.jTxtFieldDdbt2.setText(""
								+ sommetChoisi.getDateDebutTard());
						Fenetre.jTxtFieldDFin1.setText(""
								+ sommetChoisi.getDateFinTot());
						Fenetre.jTxtFieldDFin2.setText(""
								+ sommetChoisi.getDateFinTard());
						// repaint();
					} else {
						if (!selectionMultiple)
							sommetsSelectionnes.supprimerSommet(sommetChoisi);
					}
					l = (int) Math.abs((e.getX() - sommetChoisi
							.getCoordonnees().getX()));
					h = (int) Math.abs((e.getY() - sommetChoisi
							.getCoordonnees().getY()));
					if (!sommetsSelectionnes.isEmpty()) {
						point = new Point((int) sommetChoisi.getCoordonnees()
								.getX(), (int) sommetChoisi.getCoordonnees()
								.getY());
					}
				}
				x = e.getX();
				y = e.getY();

				// // Les arcs
				// ////////////////////////////////////////////////////

				// On recup�re l'arc selectionne
				if (sommetChoisi == null) {
					arcChoisi = arcProche(e.getX(), e.getY());
					leArcChoisi = arcChoisi;
					mrArc = leArcChoisi;
					if (arcChoisi != null) {
						Fenetre.jTxtFieldNomArc.setText(arcChoisi.getNom());
						Fenetre.jTxtFieldValeur.setText(""
								+ arcChoisi.getValeur());
						Fenetre.jTxtFieldExtremiteInitiale.setText(arcChoisi
								.getExtremiteInitiale().getNom());
						Fenetre.jTxtFieldExtremiteTerminale.setText(arcChoisi
								.getExtremiteTerminale().getNom());
					}
				}

				// Point p = e.getPoint();
				// for (int i = 0; i < points.length; i++) {
				// double x = points[i].getX() - 10 / 2;
				// double y = points[i].getY() - 10 / 2;
				// Rectangle2D r = new Rectangle2D.Double(x, y, 10, 10);
				// if (r.contains(p)) {
				// current = 1;
				// return;
				// }
				// }

				repaint();
				break;
			}

			// /////////////////////////////////////////////////////////////////////////

			case 2: { // // en mode Supression

				sommetChoisi = null;
				// sommetsSelectionnes.removeAllElements();
				selectionMultiple = false;

				if (sommetChoisi == null) {
					sommetChoisi = sommetProche(e.getX(), e.getY());
					if (sommetChoisi != null) {
						for (int i = 0; i < Fenetre.leGraphe.arcs.size(); i++) {
							Arc leArc = (Arc) Fenetre.leGraphe.arcs
									.elementAt(i);
							if (leArc.getExtremiteInitiale() == sommetChoisi
									|| leArc.getExtremiteTerminale() == sommetChoisi) {
								Fenetre.leGraphe.arcs.removeElement(leArc);
								if (Fenetre.leGraphe.lesZ != null) {
									if (Fenetre.leGraphe.lesZ.contains(leArc))
										Fenetre.leGraphe.lesZ
												.removeElement(leArc);
								}
								i--;
								repaint();
							}
						}
						Fenetre.leGraphe.sommets.supprimerSommet(sommetChoisi);
						Fenetre.leGraphe.modifie = true;
						// Fenetre.lesGraphes.addElement(Fenetre.leGraphe.cloner());
					}
					sommetChoisi = null;
					// repaint();
				}

				if (arcChoisi == null) {
					arcChoisi = arcProche(e.getX(), e.getY());
					Fenetre.leGraphe.arcs.supprimerArc(arcChoisi);
					if (arcChoisi != null) {
						if (Fenetre.leGraphe.lesZ != null) {
							if (Fenetre.leGraphe.lesZ.contains(arcChoisi))
								Fenetre.leGraphe.lesZ.removeElement(arcChoisi);
						}
						// if (arcChoisi.getExtremiteInitiale()!=null)
						arcChoisi.getExtremiteInitiale().supprimerSuccesseur(
								arcChoisi.getExtremiteTerminale());
						// if (arcChoisi.getExtremiteTerminale()!=null)
						arcChoisi.getExtremiteTerminale()
								.supprimerPredecesseur(
										arcChoisi.getExtremiteInitiale());
						Fenetre.leGraphe.modifie = true;
						// Fenetre.lesGraphes.addElement(Fenetre.leGraphe.cloner());
						arcChoisi = null;
					}
					// repaint();
				}

				if (!sommetsSelectionnes.isEmpty()) {
					for (Iterator<Sommet> it = sommetsSelectionnes.iterator(); it
							.hasNext();) {
						Sommet leSommet = it.next();

						for (int i = 0; i < Fenetre.leGraphe.arcs.size(); i++) {
							Arc leArc = (Arc) Fenetre.leGraphe.arcs
									.elementAt(i);
							if (leArc.getExtremiteInitiale() == leSommet
									|| leArc.getExtremiteTerminale() == leSommet) {
								Fenetre.leGraphe.arcs.removeElement(leArc);
								if (Fenetre.leGraphe.lesZ != null) {
									if (Fenetre.leGraphe.lesZ.contains(leArc))
										Fenetre.leGraphe.lesZ
												.removeElement(leArc);
								}
								i--;
								;
							}
						}
						Fenetre.leGraphe.sommets.removeElement(leSommet);
					}
					sommetsSelectionnes.removeAllElements();
				}
				repaint();
				break;
			}

			// /////////////////////////////////////////////////////////////////////////

			case 3: { // // en mode dessin Sommet

				if (!sommetDansContour(e.getX(), e.getY())) {
					Point lePoint = new Point(e.getX(), e.getY());
					Sommet leSommet = new Sommet(lePoint);
					// modifierCurseur(this,"interdit.png",new Point(15,15));
					Fenetre.leGraphe.sommets.ajouterSommet(leSommet);
					Fenetre.leGraphe.modifie = true;
					// Fenetre.lesGraphes.addElement(Fenetre.leGraphe.cloner());
					repaint();
				} else {
					// modifierCurseur(this,"interdit.png",new Point(15,15));
				}
				break;
			}

			// /////////////////////////////////////////////////////////////////////////

			case 4: { // // en mode dessin Arc

				if (sommetChoisi == null) {
					sommetChoisi = sommetProche(e.getX(), e.getY());
				} else {
					sommetDebut = sommetChoisi;
					sommetFin = sommetProche(e.getX(), e.getY());
					if (sommetFin != null) {
						if (!sommetFin.estPredecesseur(sommetChoisi)) {
							sommetFin.ajouterPredecesseur(sommetChoisi);
							sommetChoisi.ajouterSuccesseur(sommetFin);
							Arc leArc = new Arc(sommetDebut, sommetFin);
							sommetFin.entrees.addElement(leArc);
							sommetDebut.sorties.addElement(leArc);
							Fenetre.leGraphe.arcs.ajouterArc(leArc);
							Fenetre.leGraphe.modifie = true;
							// Fenetre.lesGraphes.addElement(Fenetre.leGraphe.cloner());
							// sommetChoisi = null;
							// sommetProvisoire = null;
							// sommetDebut = null;
							// sommetFin = null;
							sommetChoisi = null;
							arcProvisoire = null;
						}
					}
				}
				repaint();
				break;
			}

			}
		}
	}

	public void mouseClicked(MouseEvent e) {

		if (e.getButton() == 1) {
			switch (curseur) {

			}
		} else {
			if (e.getButton() == 2) {

			}
		}
	}

	public void mouseEntered(MouseEvent arg0) {

	}

	public void mouseExited(MouseEvent arg0) {

	}

	public void mouseReleased(MouseEvent e) {

		if (e.getButton() == 1) {
			switch (curseur) {

			// ////////////////////////////////////////////////////////////////////////////

			case 1: { // // en mode Selection
				sommetChoisi = null;
				arcChoisi = null;
				// lesSommetsSelectionnes=sommetsSelectionnes;
				rctSelection = null;
				repaint();
				// selectionMultiple = false;
				break;
			}

			// case 2 : {
			// sommetProvisoire = sommetProche(e.getX(), e.getY());
			// //
			// System.out.println("noeudProv est :"+noeudProvisoire.getNom());
			// if (sommetProvisoire == null || sommetChoisi == null) {
			// arcProvisoire = null;
			// sommetChoisi = null;
			// repaint();
			// }
			//
			// break;
			// }

			case 4: { // // en mode dessin d'arcs
				// sommetProvisoire = sommetProche(e.getX(), e.getY());
				// System.out.println("noeudProv est :"+noeudProvisoire.getNom());
				// if (sommetProvisoire == null || sommetChoisi == null) {
				// arcProvisoire = null;
				// sommetChoisi = null;
				// repaint();
				// }
				// if (sommetChoisi != null){
				// sommetDebut = sommetChoisi;
				// sommetFin = sommetProche(e.getX(), e.getY());
				// if (sommetFin != null) {
				// if (!sommetFin.estPredecesseur(sommetDebut)) {
				// sommetFin.ajouterPredecesseur(sommetDebut);
				// sommetDebut.ajouterSuccesseur(sommetFin);
				// Arc leArc = new Arc(sommetDebut , sommetFin);
				// sommetFin.entrees.addElement(leArc);
				// sommetDebut.sorties.addElement(leArc);
				// Fenetre.leGraphe.arcs.ajouterArc(leArc);
				// Fenetre.leGraphe.modifie = true;
				// //Fenetre.lesGraphes.addElement(Fenetre.leGraphe.cloner());
				// //sommetChoisi = null;
				// //sommetProvisoire = null;
				// // sommetDebut = null;
				// // sommetFin = null;
				// sommetChoisi = null;
				// arcProvisoire = null;
				// sommetProvisoire = null;
				// }
				// }
				// else {
				// sommetChoisi=null;
				// sommetDebut = null;
				// sommetFin = null;
				// }
				// }
				repaint();
				break;
			}

			}
		}
	}

	public void mouseDragged(MouseEvent e) {

		// if (e.getButton()==1) {
		// switch (curseur) {
		//
		// //////////////////////////////////////////////////////////////////////////////
		//
		// case 1 : { //// en mode Selection
		if (curseur == 1) {
			if (rctSelection != null) {
				getSelect(e);
				// repaint();
			}

			if (sommetChoisi != null) {
				sommetChoisi.setCoordonnees(new Point(e.getX() - l, e.getY()
						- h));
				Fenetre.leGraphe.modifie = true;
			}

			if (!sommetsSelectionnes.isEmpty() && sommetChoisi != null) {
				for (Iterator<Sommet> itr = sommetsSelectionnes.iterator(); itr
						.hasNext();) {
					Sommet leSommet = itr.next();

					if (leSommet != sommetChoisi) {
						// System.out.println(sommetChoisi.getCoordonnees().getX()
						// - point.x);
						leSommet.setCoordonnees(new Point(
								(int) (leSommet.getCoordonnees().getX() + (sommetChoisi
										.getCoordonnees().getX() - point.x)),
								(int) (leSommet.getCoordonnees().getY() + (sommetChoisi
										.getCoordonnees().getY() - point.y))));
					}

				}
				Fenetre.leGraphe.modifie = true;
				point = new Point((int) sommetChoisi.getCoordonnees().getX(),
						(int) sommetChoisi.getCoordonnees().getY());
				repaint();
			}
			// Fenetre.lesGraphes.addElement(Fenetre.leGraphe.clone());
			// if (current == -1)
			// return;
			// points[1] = e.getPoint();

			repaint();
			// break;
			// }
			// }
		}
	}

	public void mouseMoved(MouseEvent e) {
		// if (e.getButton()==1) {
		switch (curseur) {

		// ////////////////////////////////////////////////////////////////////////////

		// case 4 : {
		// //System.out.println("Provisoire");
		// if (sommetDebut!=null ){//&& sommetFin ==null){
		// Point lePoint = new Point(e.getX(), e.getY());
		// sommetProvisoire = new Sommet(lePoint);
		// //if (sommetDebut != null) {
		// arcProvisoire = new Arc(sommetDebut, sommetProvisoire);
		// //sommetProvisoire=null;
		// //System.out.println("Sommet Provisoire");
		// //}
		//
		// }
		// // else {
		// // //arcProvisoire = null;
		// // //sommetProvisoire=null;
		// // }
		// repaint();
		// break;
		// }
		// default : {
		// System.out.println("Sommet Provisoire");
		// repaint();
		// break;
		// }
		}
	}

	// }

	public void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		super.paintComponent(g2d);

		for (Iterator<Arc> it = Fenetre.leGraphe.arcs.iterator(); it.hasNext();) {
			Arc leArc = it.next();
			dessinerArc(leArc, bleuGris, g2d);
		}

		if (arcProvisoire != null) {
			dessinerArc(arcProvisoire, bleuGris, g2d);
		}

		if (Fenetre.leGraphe.lesZ != null) {
			for (Iterator<Arc> it = Fenetre.leGraphe.lesZ.iterator(); it
					.hasNext();) {
				Arc leArc = it.next();
				dessinerArc(leArc, Color.magenta, g2d);
			}
		}

		for (Iterator<Sommet> it = Fenetre.leGraphe.sommets.iterator(); it
				.hasNext();) {
			Sommet leSommet = it.next();
			if (Fenetre.leGraphe.tachesFictives.contains(leSommet))
				dessinerTachesFictives(leSommet, Crevette, g2d);
			else
				dessinerSommet(leSommet, Crevette, g2d);
		}

		for (Iterator<Sommet> it = sommetsSelectionnes.iterator(); it.hasNext();) {
			Sommet leSommet = it.next();

			for (Iterator<Arc> it2 = Fenetre.leGraphe.arcs.iterator(); it2
					.hasNext();) {
				Arc leArc = it2.next();
				if (sommetsSelectionnes.contains(leArc.getExtremiteInitiale())
						&& sommetsSelectionnes.contains(leArc
								.getExtremiteTerminale()))
					dessinerArc(leArc, bleuCiel, g2d);
			}

			if (Fenetre.leGraphe.tachesFictives.contains(leSommet))
				dessinerTachesFictives(leSommet, bleuCiel, g2d);
			else
				dessinerSommet(leSommet, bleuCiel, g2d);

		}
		// for (Iterator it = arcsSelectionnes.iterator(); it.hasNext();){
		// Arc leArc = (Arc) it.next();
		if (!(leArcChoisi == null))
			dessinerArc(leArcChoisi, bleuCiel, g2d);

		if (rctSelection != null) {
			g2d.draw(rctSelection);
			if (sommetsSelectionnes != null) {
				for (Iterator<Sommet> itr = sommetsSelectionnes.iterator(); itr
						.hasNext();) {
					Sommet leSommet = itr.next();
					if (Fenetre.leGraphe.tachesFictives.contains(leSommet))
						dessinerTachesFictives(leSommet, Color.blue, g2d);
					else
						dessinerSommet(leSommet, bleuCiel, g);
				}
			}
		}

	}

	public void modifierCurseur(Component parent, String img, Point hotSpot) {
		Image image = null;
		// recupere le Toolkit
		Toolkit tk = Toolkit.getDefaultToolkit();
		// sur ce dernier lire le fichier avec "getClass().getRessource" pour
		// pouvoir l'ajouter a un .jar
		image = tk.getImage(getClass().getResource(img));
		// modifi le curseur avec la nouvelle image,en le posissionant grace
		// hotSpot
		// et en lui donnant le nom "X"
		Cursor c = tk.createCustomCursor(image, hotSpot, "X");
		// puis on l'associe au Panel
		parent.setCursor(c);
	}

	abstract class ShapeMaker {
		public ShapeMaker(int aPointCount) {
			pointCount = aPointCount;
		}

		public int getPointCount() {
			return pointCount;
		}

		public abstract Shape makeShape(Point2D[] p);

		// public String toString() {
		// return getClass().getName();
		// }

		private int pointCount;
	}

	class QuadCurveMaker extends ShapeMaker {
		public QuadCurveMaker() {
			super(3);
		}

		public Shape makeShape(Point2D[] p) {
			return new QuadCurve2D.Double(p[0].getX(), p[0].getY(),
					p[1].getX(), p[1].getY(), p[2].getX(), p[2].getY());
		}
	}

	public Point touverPointControlArc(double x1, double x2, double y1,
			double y2) {
		// //////////////////////////////////////////////////////////////////
		// resolution de l'equation de MERDE

		// double a,b,c,delta,leY1 = 0,leY2 = 0;
		double X, Y;
		// a=(Math.pow((y2-y1),(double)2)/Math.pow(Math.abs(x2-x1),(double)2))+1;
		// b=-((2/Math.pow(Math.abs(x2-x1),(double)2))*((Math.pow((x2),(double)2)-Math.pow((x1),(double)2)/2)+(Math.pow((y2),(double)2)-Math.pow((y1),(double)2)/2))*Math.abs(y2-y1))
		// -(((x1+x2)/Math.abs(x2-x1))*Math.abs(y2-y1))+(y1+y2);
		// c=((Math.pow(((Math.pow((x2),(double)2)-Math.pow((x1),(double)2))/2)
		// + ((Math.pow((y2),(double)2)-Math.pow((y1),(double)2))/2),(double)2))
		// / Math.pow(Math.abs(x2-x1),(double)2))
		// +((x1+x2)/Math.abs(x2-x1))*(((Math.pow((x2),(double)2)-Math.pow((x1),(double)2))/2)
		// + ((Math.pow((y2),(double)2)-Math.pow((y1),(double)2))/2))
		// -Math.pow(m,(double)2) + Math.pow((x1+x2)/2,(double)2) -
		// Math.pow((y1+y2)/2,(double)2);
		//
		//
		// // le delta et le reste de la merde
		// delta = (b*b) - 4*a*c;
		// if (delta<0)
		// {
		// System.out.println("\n\nIl n'y a pas de racines reelle a l'equation.");
		// }
		// else if (delta==0){
		// leY1=-b/(2*a);
		// }
		// else
		// {
		// leY1 = (-b-Math.sqrt(delta))/(2*a);
		// leY2 = (-b+Math.sqrt(delta))/(2*a);
		// System.out.println("\n\nLes racines sont x1 = " + x1 + " et x2 = "
		// +x2);
		// }
		// //
		// Y=Math.abs(leY2);
		// X=(1/(x2-x1))*((Math.pow((x2),(double)2)-Math.pow((x1),(double)2)/2)
		// + (Math.pow((y2),(double)2)-Math.pow((y1),(double)2)/2) - Y*(y2-y1));

		X = ((x1 + x2) / 2) - 15;
		Y = ((y1 + y2) / 2) - 15;

		Point lePoint = new Point((int) X, (int) Y);

		return lePoint;
	}

	public void selectionMultiple() {

	}

	public void getSelect(MouseEvent e) {
		if (rctSelection != null) {
			Sommet leSommet = null;

			if ((e.getX() < x) && (e.getY() < y)) {
				rctSelection.x = e.getX();
				rctSelection.y = e.getY();
				rctSelection.width = (int) Math.abs((rctSelection.getX() - x));
				rctSelection.height = (int) Math.abs((rctSelection.getY() - y));
			} else if ((e.getX() > x) && (e.getY() < y)) {
				rctSelection.x = x;
				rctSelection.y = e.getY();
				rctSelection.width = (int) Math.abs((e.getX() - rctSelection
						.getX()));
				rctSelection.height = (int) Math.abs((rctSelection.getY() - y));
			} else if ((e.getX() < x) && (e.getY() > y)) {
				rctSelection.x = e.getX();
				rctSelection.y = y;
				rctSelection.width = (int) Math.abs((rctSelection.getX() - x));
				rctSelection.height = (int) Math.abs((e.getY() - rctSelection
						.getY()));
			} else {
				rctSelection.width = (int) Math.abs((e.getX() - rctSelection
						.getX()));
				rctSelection.height = (int) Math.abs((e.getY() - rctSelection
						.getY()));
			}

			for (Iterator<Sommet> itr = Fenetre.leGraphe.sommets.iterator(); itr
					.hasNext();) {
				leSommet = (itr.next());
				// System.out.println(isRectangleInRectangle(new
				// Rectangle(vnode.getX(),vnode.getY(),vnode.getLargeur(),vnode.getHauteur()),
				// rctSelection));
				if (isRectangleInRectangle(new Rectangle((int) leSommet
						.getCoordonnees().getX() - RAYON_SOMMET, (int) leSommet
						.getCoordonnees().getY() - RAYON_SOMMET, 20, 20),
						rctSelection)) {

					if (!sommetsSelectionnes.contains(leSommet))
						sommetsSelectionnes.add(leSommet);
					// rctSelect = rctSelection.union(vnode.getRectangle());
				} else {
					if (sommetsSelectionnes.contains(leSommet))
						sommetsSelectionnes.remove(leSommet);
				}
			}
		}
	}

	static boolean isRectangleInRectangle(Rectangle rct1, Rectangle rct2) {
		Point pt1_TLft = new Point(rct1.x, rct1.y);
		Point pt1_BRt = new Point(rct1.x + rct1.width, rct1.y + rct1.height);
		boolean res = (rct2.contains(pt1_TLft.x, pt1_TLft.y) && rct2.contains(
				pt1_BRt.x, pt1_BRt.y));
		pt1_TLft = pt1_BRt = null;
		return res;
	}

	public Dimension getPreferredSize() {
		int x = 0, y = 0;

		if (Fenetre.leGraphe.sommets != null) {
			for (Iterator<Sommet> itr = Fenetre.leGraphe.sommets.iterator(); itr
					.hasNext();) {
				Sommet leSommet = (Sommet) itr.next();
				x = (int) (leSommet.getCoordonnees().getX() + 50 > x ? leSommet
						.getCoordonnees().getX() + 50 : x);
				y = (int) (leSommet.getCoordonnees().getY() + 50 > y ? leSommet
						.getCoordonnees().getY() + 50 : y);
			}
			return (new Dimension(x + 50, y + 50));
		}
		return (new Dimension(960, 600));

	}

	public Image getImage() {
		int width = this.getWidth();
		int height = this.getHeight();
		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();
		this.paintAll(g);
		g.dispose();
		return image;
	}

}
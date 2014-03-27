package me.mouhoub.alphaGraph.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Iterator;

import me.mouhoub.alphaGraph.graph.Arc;
import me.mouhoub.alphaGraph.graph.Sommet;

@SuppressWarnings("serial")
public class CPM extends GraphEditor {

	public final static int RAYON_SOMMET = 5;
	public final static int CONTOUR_SOMMET = 10;
	public final static Color leBackground = new java.awt.Color(239, 239, 239);
	private static final int BARB = 8;
	ShapeMaker shapeMaker = new QuadCurveMaker();

	// Le Constructeur
	public CPM() {
		setSize(getPreferredSize());
	}

	// La methode permettant de dessiner un sommet
	public void dessinerSommet(Sommet leSommet, Color laCouleur, Graphics g2d) {
		// Graphics g2d = getGraphics();
		int xTemp, yTemp;
		g2d.setFont(new Font("Arial", Font.BOLD, 12));

		// On dessine d'abord le sommet
		g2d.setColor(laCouleur);
		xTemp = (int) leSommet.getCoordonnees().getX();
		yTemp = (int) leSommet.getCoordonnees().getY();
		g2d.fillOval(xTemp - (RAYON_SOMMET), yTemp - (RAYON_SOMMET), 10, 10);

		// Puis on ecrit en sur le sommet son libelle
		g2d.setColor(laCouleur);
		// g2d.drawString(""+leSommet.getLibelle() , xTemp -6 , yTemp + 5);

		// On reprend les couleurs par defaut
		g2d.setColor(Color.black);
	}

	// La methode permettant de dessiner un arc
	public void dessinerArc(Arc leArc, Color laCouleur, Graphics g2d, int ordre) {
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

		Point2D[] points;
		// int SIZE = 10;
		points = new Point2D[3];

		double dy = y2Temp - y1Temp;
		double dx = x2Temp - x1Temp;
		String s = leArc.getNom() + ":" + leArc.getValeur();
		// int a = (int) (x1Temp+(dx/2) -((s.length() / 2) * 7));
		// int b = (int) (y1Temp + dy / 2);

		// /////////////////////////////////////
		g2d.setFont(new Font("Arial", Font.BOLD, 10));

		// Point2D[] points;
		// int SIZE = 10;

		leArc.laTouche();
		// Point touche1 = new Point();
		// touche1 = (Point) leArc.getDebut();
		// Point touche2 = new Point();
		// touche2 = leArc.getFin();

		Point laMerde = new Point();
		laMerde.setLocation((x1Temp + x2Temp) / 2 + (13), (y1Temp + y2Temp) / 2
				+ (13 * ordre));

		points[0] = leArc.getExtremiteInitiale().getCoordonnees();
		points[2] = leArc.getExtremiteTerminale().getCoordonnees();
		points[1] = laMerde;

		// if (points == null)
		// return;
		// Graphics2D g2 = (Graphics2D) g2d;
		// for (int i = 0; i < points.length; i++) {
		// double x = points[i].getX() - SIZE / 2;
		// double y = points[i].getY() - SIZE / 2;
		// g2.fill(new Rectangle2D.Double(x, y, SIZE, SIZE));
		// }
		g2d.setColor(laCouleur);
		((Graphics2D) g2d).draw(shapeMaker.makeShape(points));

		// On dessine d'abord l'arc

		// points = new Point2D[3];

		// if (leArc.getExtremiteInitiale()==leArc.getExtremiteTerminale())
		// g2d.drawArc(x1Temp-36,y1Temp-9,38,33,22,318);
		// else {

		// g2d.drawLine((int)touche1.getX(),(int)touche1.getY(),(int)touche2.getX(),(int)touche2.getY());
		// g2d.drawLine(x1Temp,y1Temp,x2Temp,y2Temp);

		if (Fenetre.leGraphe.estOriente() == true) {
			double theta = Math.atan2(dy, dx);
			theta += Math.PI;
			px[0] = x2Temp; // (int) x2Temp.getX();
			py[0] = y2Temp; // (int) leArc.fin.getY();
			px[1] = x2Temp + (int) (BARB * Math.cos(theta - lePHI));
			py[1] = y2Temp + (int) (BARB * Math.sin(theta - lePHI));
			px[2] = x2Temp + (int) (BARB * Math.cos(theta + lePHI));
			py[2] = y2Temp + (int) (BARB * Math.sin(theta + lePHI));

			g2d.drawPolygon(px, py, 3);
			g2d.fillPolygon(px, py, 3);
		}

		// On reprend les couleurs par defaut
		g2d.setColor(Color.black);
		g2d.setFont(new Font("Arial", Font.BOLD, 12));
		// g2d.drawString(s, a+3, b);
		g2d.drawString(s, laMerde.x - 15, laMerde.y);

	}

	public Dimension getPreferredSize() {
		int x = 0, y = 0;

		if (Fenetre.lePert.sommets != null) {
			for (Iterator<Sommet> itr = Fenetre.lePert.sommets.iterator(); itr
					.hasNext();) {
				Sommet leSommet = (Sommet) itr.next();
				x = (int) (leSommet.getCoordonnees().getX() + 100 > x ? leSommet
						.getCoordonnees().getX() + 100 : x);
				y = (int) (leSommet.getCoordonnees().getY() + 100 > y ? leSommet
						.getCoordonnees().getY() + 100 : y);

			}
			return (new Dimension(x + 50, y + 50));
		}
		return (new Dimension(100, 100));

	}

	public void paintComponent(Graphics g) {

		Graphics2D g2d2 = (Graphics2D) g;
		// super.paintComponent(g2d2);

		for (Iterator<Sommet> it = Fenetre.lePert.sommets.iterator(); it
				.hasNext();) {
			Sommet leSommet = it.next();
			if (leSommet != Fenetre.lePert.puit) {
				int ordre = 0;
				for (int i = 0; i < leSommet.sorties.size(); i++) {
					Arc leArc = (Arc) leSommet.sorties.elementAt(i);
					ordre = ordre > 0 ? Math.abs(ordre) + 1
							: (Math.abs(ordre) + 1) * -1;
					if (leArc.getExtremiteTerminale().niveau - leSommet.niveau > 1) {
						dessinerArc(
								leArc,
								bleuGris,
								g2d2,
								ordre
										* (leArc.getExtremiteTerminale().niveau - leSommet.niveau));
					} else
						dessinerArc(leArc, bleuGris, g2d2, ordre);
					ordre = ordre * -1;
				}
			}
		}
		dessinerArc((Arc) Fenetre.lePert.source.entrees.firstElement(),
				Color.black, g2d2, 0);
		dessinerArc((Arc) Fenetre.lePert.puit.sorties.firstElement(),
				Color.black, g2d2, 0);

		for (Iterator<Sommet> it = Fenetre.lePert.sommets.iterator(); it
				.hasNext();) {
			Sommet leSommet = it.next();
			dessinerSommet(leSommet, Crevette, g2d2);
		}

		// for (Iterator<Arc> it = Fenetre.lePert.arcs.iterator();
		// it.hasNext();){
		// Arc leArc = it.next();
		// int ordre=1;
		// boolean tracer = false;
		// for (Iterator<Arc> it2 = Fenetre.lePert.arcs.iterator();
		// it2.hasNext();){
		// Arc unArc = it2.next();
		// if (leArc!=unArc){
		// if (unArc.getExtremiteInitiale() == leArc.getExtremiteInitiale()){
		// //&& unArc.getExtremiteTerminale() == leArc.getExtremiteTerminale()){
		// //if(){
		// dessinerArc(leArc, Color.red, g2d2,ordre);
		// ordre= ordre>0 ? Math.abs(ordre)+1 : (Math.abs(ordre)+1)*-1;
		// ordre=ordre*-1;
		// tracer=true;
		// // }
		// }
		// // if (unArc.getExtremiteInitiale() == leArc.getExtremiteInitiale()
		// && unArc.getExtremiteTerminale() != leArc.getExtremiteTerminale()){
		// //// if(){
		// // dessinerArc(leArc, Color.red, g2d2,-ordre*2);
		// // ordre= ordre>0 ? Math.abs(ordre)+1 : (Math.abs(ordre)+1)*-1;
		// // ordre=ordre*-1;
		// // tracer=true;
		// //// }
		// // }
		// }
		// }
		// if (!tracer){
		// dessinerArc(leArc, Color.red, g2d2);
		// //System.out.println(tracer);
		// }
		// }

	}

}

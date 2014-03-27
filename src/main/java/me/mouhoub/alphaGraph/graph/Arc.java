package me.mouhoub.alphaGraph.graph;

import java.awt.Point;
import java.io.Serializable;

import me.mouhoub.alphaGraph.gui.Fenetre;

/********************************************************************************************
 * La classe Arc represente l'arc et l'ensemble des methodes agissant sur ce
 * dernier *
 ********************************************************************************************/

@SuppressWarnings("serial")
public class Arc implements Serializable {

	/** LES ATTRIBUTS ***********************************************************************/

	// Le nom "libele" de l'arc
	private String nom;

	// La valeur de l'arc ou le cout qu'il represente
	private int valeur;

	// L'extremite initiale de l'arc, qui est un sommet
	private Sommet extremiteInitiale;

	// L'extremite terminale de l'arc, qui est un sommet
	private Sommet extremiteTerminale;

	// la liste des points qui relies par des lignes qui represente le trajet
	// dont l'arc passe par au cas o�
	// ce dernier n'est pas une ligne directe reliant ses deux extremites
	// private ListePoints points;

	// Les points dont l'arc est trace avec

	public Point debut, fin;

	// Le constructeur de l'arc
	public Arc(Sommet laExtremiteInitiale, Sommet laExtremiteTerminale) {
		extremiteInitiale = laExtremiteInitiale;
		extremiteTerminale = laExtremiteTerminale;
		// laTouche();
		nom = "A" + Fenetre.nomArc;
		valeur = 0;
		Fenetre.nomArc++;
	}

	// Les accesseurs de l'attribut Nom
	public String getNom() {
		return nom;
	}

	public void setNom(String newNom) {
		nom = newNom;
	}

	// Les accesseurs de l'attribut Valeur
	public int getValeur() {
		return valeur;
	}

	public void setValeur(int newValeur) {
		valeur = newValeur;
	}

	// Les accesseurs de l'attribut ExtremiteInitial
	public Sommet getExtremiteInitiale() {
		return extremiteInitiale;
	}

	public void setExtremiteInitiale(Sommet newExtremiteInitiale) {
		extremiteInitiale = newExtremiteInitiale;
	}

	// Les accesseurs de l'attribut ExtremiteTerminale
	public Sommet getExtremiteTerminale() {
		return extremiteTerminale;
	}

	public void setExtremiteTerminale(Sommet newExtremiteTerminale) {
		extremiteTerminale = newExtremiteTerminale;
	}

	// Indique si l'arc est selectionnee lors d'un clic ou non
	public boolean estSelectionne(int leX, int leYy) {
		double a, b, c, h, g;
		int x1 = (int) getExtremiteInitiale().getCoordonnees().getX();// this.debut.getX();
		int y1 = (int) getExtremiteInitiale().getCoordonnees().getY();// this.debut.getY();
		int x2 = (int) getExtremiteTerminale().getCoordonnees().getX();// this.fin.getX();
		int y2 = (int) getExtremiteTerminale().getCoordonnees().getY();// this.fin.getY();

		a = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
		b = Math.sqrt((x1 - leX) * (x1 - leX) + (y1 - leYy) * (y1 - leYy));
		c = Math.sqrt((leX - x2) * (leX - x2) + (leYy - y2) * (leYy - y2));

		h = a / 2 - (b * b) / (2 * a) + c * c / (2 * a);

		g = Math.sqrt(c * c - h * h);
		if (g < 10
				&& (leX > x1 - 4 && leX < x2 + 4 || leX < x1 + 4
						&& leX > x2 - 4)
				&& (leYy > y1 - 4 && leYy < y2 + 4 || leYy < y1 + 4
						&& leYy > y2 - 4))
			return true;
		return false;
	}

	// Calcul a partir des coordonnees des extremites de l'arc, les deux point
	// d'intersection entre
	// le seguement passant par le sommet et le cercle du dernier

	public void laTouche() {
		double phi;
		double x1, y1, x4, y4, x2 = 0, y2 = 0, x3 = 0, y3 = 0;
		x1 = extremiteInitiale.getCoordonnees().getX();
		y1 = extremiteInitiale.getCoordonnees().getY();

		x4 = extremiteTerminale.getCoordonnees().getX();
		y4 = extremiteTerminale.getCoordonnees().getY();

		// Reconnaissance a Sid Ahmed et Oussama Igroufa pour nous avoir trouve
		// la solution geometrique
		phi = Math.atan(Math.abs(y4 - y1) / Math.abs(x4 - x1));

		if ((x1 < x4) && (y1 > y4)) {
			// phi = phi;
			// Sommet1
			x2 = x1 + 10 * Math.cos(Math.PI + Math.PI / 2 + phi);
			y2 = y1 + 10 * Math.sin(Math.PI + Math.PI / 2 + phi);
			// Sommet2
			x3 = x4 - 10 * Math.cos(Math.PI + Math.PI / 2 + phi);
			y3 = y4 - 10 * Math.sin(Math.PI + Math.PI / 2 + phi);
		}
		if ((x1 < x4) && (y1 < y4)) {
			// phi = -phi;
			// Sommet1
			x2 = x1 - 10 * Math.cos(Math.PI + phi);
			y2 = y1 - 10 * Math.sin(Math.PI + phi);
			// Sommet2
			x3 = x4 + 10 * Math.cos(Math.PI / 2 + phi);
			y3 = y4 - 10 * Math.sin(Math.PI / 2 + phi);
		}
		if ((x1 > x4) && (y1 < y4)) {
			// phi = phi + (Math.PI/2);
			// Sommet1
			x2 = x1 - 10 * Math.cos(Math.PI / 2 + Math.PI + phi);
			y2 = y1 - 10 * Math.sin(Math.PI / 2 + Math.PI + phi);
			// Sommet2
			x3 = x4 + 10 * Math.cos(phi);
			y3 = y4 - 10 * Math.sin(phi);
		}
		if ((x1 > x4) && (y1 > y4)) {
			// phi = phi + Math.PI;
			// Sommet1
			x2 = x1 - 10 * Math.cos(phi);
			y2 = y1 - 10 * Math.sin(phi);
			// Sommet2
			x3 = x4 + 10 * Math.cos(phi);
			y3 = y4 + 10 * Math.sin(phi);
		}

		Point lePoint1 = new Point((int) x2, (int) y2);
		Point lePoint2 = new Point((int) x3, (int) y3);

		debut = lePoint1;
		fin = lePoint2;

	}

	public void setDebut(Point debut) {
		this.debut = debut;
	}

	public Point getDebut() {
		return debut;
	}

	public void setFin(Point fin) {
		this.fin = fin;
	}

	public Point getFin() {
		return fin;
	}

}
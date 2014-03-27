package me.mouhoub.alphaGraph.graph;

import java.awt.Point;
import java.io.Serializable;

import me.mouhoub.alphaGraph.gui.Fenetre;

/****************************************************************************************
 * La classe Sommet represente le sommet et les methodes agissant sur ce dernier
 * 
 ****************************************************************************************/

@SuppressWarnings("serial")
public class Sommet implements Serializable {

	// Le nom (libelle) du sommet
	private String nom;
	private int libelle;

	// le point qui represente les coordonnees graphiques du sommet
	private Point coordonnees;

	// La duree de la t�che que represente le sommet
	private float duree;
	// La date de debut au plus t�t de la t�che que represente le sommet
	private int dateDebutTot;

	// La date de debut au plus tard de la t�che que represente le sommet
	private int dateDebutTard;

	// La date de fin au plus t�t de la t�che que represente le sommet
	private int dateFinTot;

	// La date de fin au plus tard de la t�che que represente le sommet
	private int dateFinTard;

	// La liste des predecesseurs du sommet
	private ListeSommets predecesseurs;

	// La liste des successeurs du sommet
	private ListeSommets successeurs;

	// Le niveau de la t�che
	public int niveau;

	public int ordre;

	public ListeArcs entrees;
	public ListeArcs sorties;

	public ListeSommets input;
	public ListeSommets output;

	// Le Constructeur du sommet
	public Sommet(Point newPoint) {
		coordonnees = newPoint;
		predecesseurs = new ListeSommets();
		successeurs = new ListeSommets();
		setNom("S" + Fenetre.nomSommet);
		libelle = Fenetre.nomSommet;
		Fenetre.nomSommet++;
		niveau = -1;
		ordre = 1;
		entrees = new ListeArcs();
		sorties = new ListeArcs();
	}

	public Sommet() {
		coordonnees = new Point();
		predecesseurs = new ListeSommets();
		successeurs = new ListeSommets();
		setNom("" + Fenetre.nomSommet);
		libelle = Fenetre.nomSommet;
		Fenetre.nomSommet++;
		niveau = -1;
		ordre = 1;
		entrees = new ListeArcs();
		sorties = new ListeArcs();
		input = new ListeSommets();
		output = new ListeSommets();
	}

	// public Sommet(ListeSommets leInput, ListeSommets leOutput) {
	// input = new ListeSommets();
	// output = new ListeSommets();
	// input.addAll(leInput);
	// output.addAll(leOutput);
	// }

	// Renvoie la liste des predecesseurs du sommet
	public ListeSommets getPredecesseurs() {
		return predecesseurs;
	}

	// Renvoie la liste des successeurs du sommet
	public ListeSommets getSuccesseurs() {
		return successeurs;
	}

	// Permet d'ajouter un predecesseur au sommet
	public void ajouterPredecesseur(Sommet leSommet) {
		predecesseurs.ajouterSommet(leSommet);
	}

	// Permet d'ajouter un successeur au sommet
	public void ajouterSuccesseur(Sommet leSommet) {
		successeurs.ajouterSommet(leSommet);
	}

	// Permet de supprimer un sommet de la liste des predecesseurs du sommet
	// actuel
	public void supprimerPredecesseur(Sommet leSommet) {
		predecesseurs.supprimerSommet(leSommet);
	}

	// Permet de supprimer un sommet de la liste des successeurs du sommet
	// actuel
	public void supprimerSuccesseur(Sommet leSommet) {
		successeurs.supprimerSommet(leSommet);
	}

	// Verifie si un sommet est un predecesseur du sommet actuel
	public boolean estPredecesseur(Sommet leSommet) {
		return predecesseurs.contains(leSommet);
	}

	// Verifie si un sommet est un successeur du sommet actuel
	public boolean estSuccesseur(Sommet leSommet) {
		return successeurs.contains(leSommet);
	}

	// Les accesseurs de l'attribut nom
	public String getNom() {
		return nom;
	}

	public void setNom(String newNom) {
		nom = newNom;
	}

	// Les accesseurs de l'attribut coordonnees
	public Point getCoordonnees() {
		return coordonnees;
	}

	public void setCoordonnees(Point newCoordonnees) {
		coordonnees = newCoordonnees;
	}

	// Les accesseurs de l'attribut dateDebutTot
	public int getDateDebutTot() {
		return dateDebutTot;
	}

	public void setDateDebutTot(int newDateDebutTot) {
		dateDebutTot = newDateDebutTot;
	}

	// Les accesseurs de l'attribut dateDebutTard
	public int getDateDebutTard() {
		return dateDebutTard;
	}

	public void setDateDebutTard(int newDateDebutTard) {
		dateDebutTard = newDateDebutTard;
	}

	// Les accesseurs de l'attribut duree
	public float getDuree() {
		return duree;
	}

	public void setDuree(float newDuree) {
		duree = newDuree;
	}

	// Les accesseurs de l'attribut dateFinTot
	public int getDateFinTot() {
		return dateFinTot;
	}

	public void setDateFinTot(int newDateFinTot) {
		dateFinTot = newDateFinTot;
	}

	// Les accesseurs de l'attribut dateFinTard
	public int getDateFinTard() {
		return dateFinTard;
	}

	public void setDateFinTard(int newDateFinTard) {
		dateFinTard = newDateFinTard;
	}

	public boolean dansIntervale(int leX, int leY) {
		return (boolean) (getCoordonnees().getX() + 10 >= leX)
				&& (getCoordonnees().getX() - 10 <= leX)
				&& (getCoordonnees().getY() + 10 >= leY)
				&& (getCoordonnees().getY() - 10 <= leY);
		// return (boolean) ((getCoordonnees().getX() - 5 <= leX) && (leX <=
		// getCoordonnees().getX() + 5) && (getCoordonnees().getY() - 5 <= leY)
		// && (leY <= getCoordonnees().getY() + 5));
	}

	public boolean dansContour(int leX, int leY) {
		return (boolean) (getCoordonnees().getX() + 40 >= leX)
				&& (getCoordonnees().getX() - 40 <= leX)
				&& (getCoordonnees().getY() + 40 >= leY)
				&& (getCoordonnees().getY() - 40 <= leY);
	}

	public void setLibelle(int libelle) {
		this.libelle = libelle;
	}

	public int getLibelle() {
		return libelle;
	}

}
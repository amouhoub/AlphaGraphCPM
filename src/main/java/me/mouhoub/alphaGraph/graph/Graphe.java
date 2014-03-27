package me.mouhoub.alphaGraph.graph;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Graphe implements Serializable, Cloneable {

	// Definie le type du graphe : Oriente ou non Oriente, et prend la valeur
	// True si c'est vrai
	public Boolean orientation;

	// La liste complete des sommets du graphe
	public ListeSommets sommets;

	// La liste complete des arcs du graphe
	public ListeArcs arcs;

	// La matrice d'adjacense
	public int matriceAdjacense[][];

	// La matrice d'incidence
	public int matriceIncidence[][];

	// Les sources du graphe
	public ListeSommets sources;

	// Les puits du graphe
	public ListeSommets puits;

	public int composantesConnexes[];

	// Indique si la version du graphe a ete modifiee ou non
	public boolean modifie = false;

	public Sommet puit;
	public Sommet source;

	public ListeArcs lesZ;
	public int nbreTachesF = 0;
	public ListeSommets tachesFictives;
	public int nbreBiparti = 0;

	// Le Constructeur du graphe
	public Graphe() {
		sommets = new ListeSommets();
		arcs = new ListeArcs();
		// puits = new ListeSommets();
		// sources = new ListeSommets();
		// matriceAdjacense = new int
		// [sommets.nombreSommets()][sommets.nombreSommets()];
		// matriceIncidence = new int
		// [sommets.nombreSommets()][arcs.nombreArcs()];
		orientation = false;
		composantesConnexes = new int[100];
		// lesZ = new ListeArcs();
		tachesFictives = new ListeSommets();
	}

	// Renvoie TRUE si le graphe est oriente, sinon, elle renvoie FALSE
	public boolean estOriente() {
		return orientation;
	}

	// public Graphe cloner() {
	// Graphe unGraphe = new Graphe();
	// unGraphe.arcs= this.arcs;
	// unGraphe.sommets = this.sommets;
	// unGraphe.orientation = this.orientation;
	// unGraphe.puits = this.puits;
	// unGraphe.sources = this.sources;
	// System.out.println("�a y est");
	// return unGraphe;
	// }
}
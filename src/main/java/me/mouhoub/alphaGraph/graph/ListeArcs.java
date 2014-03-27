package me.mouhoub.alphaGraph.graph;

import java.util.*;

/******************************************************************************************
 * La classe ListeArcs represente un ensemble d'arcs representes dans un vecteur
 * * Cette liste peut etre utilisee pour representer l'ensemble des arcs du
 * graphe comme elle peut representer un sous ensemble d'arcs
 ******************************************************************************************/

@SuppressWarnings("serial")
public class ListeArcs extends Vector<Arc> {

	// Renvoie le nombre d'arcs dans la liste des arcs
	public int nombreArcs() {
		return size();
	}

	// Permet d'ajouter un arc dans la liste des arcs
	public void ajouterArc(Arc leArc) {
		addElement(leArc);
	}

	// Permet de supprimer un arc depuis la liste des arcs
	public void supprimerArc(Arc leArc) {
		removeElement(leArc);
	}

	// Verifie si un arc est contenu dans la liste des arcs ou pas
	public boolean contientArc(Arc leArc) {
		return contains(leArc);
	}

}
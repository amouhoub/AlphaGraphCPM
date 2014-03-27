package me.mouhoub.alphaGraph.graph;

import java.util.*;

/************************************************************************************************
 * La classe ListeSommets represente un ensemble de sommets representes dans un
 * vecteur * Cette liste peut etre utilisee pour representer l'ensemble des
 * sommets du graphe * comme elle peut representer un sous ensemble de sommets
 * comme les successeurs d'un sommet *
 ************************************************************************************************/

@SuppressWarnings("serial")
public class ListeSommets extends Vector<Sommet> {

	// Renvoie le nombre de sommets dans la liste des sommets
	public int nombreSommets() {
		return size();
	}

	// Permet d'ajouter un sommet dans la liste des sommets
	public void ajouterSommet(Sommet leSommet) {
		addElement(leSommet);
	}

	// Permet de supprimer un sommet depuis la liste des sommets
	public void supprimerSommet(Sommet leSommet) {
		removeElement(leSommet);
	}

	// Verifie si un sommet est contenu dans la liste des sommets ou pas
	public boolean contientSommet(Sommet leSommet) {
		return contains(leSommet);
	}

	public void ajouterNonRedandante(ListeSommets lesSommets) {
		Iterator<Sommet> it = lesSommets.iterator();
		while (it.hasNext()) {
			Sommet leSommet = it.next();
			if (!this.contains(leSommet)) {
				this.add(leSommet);
			}
		}
	}

}
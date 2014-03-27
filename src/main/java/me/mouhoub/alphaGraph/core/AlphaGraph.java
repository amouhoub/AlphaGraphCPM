package me.mouhoub.alphaGraph.core;

import java.awt.Point;
import java.util.Iterator;

import me.mouhoub.alphaGraph.graph.Arc;
import me.mouhoub.alphaGraph.graph.ListeArcs;
import me.mouhoub.alphaGraph.graph.ListeSommets;
import me.mouhoub.alphaGraph.graph.Sommet;
import me.mouhoub.alphaGraph.gui.Fenetre;
import me.mouhoub.alphaGraph.gui.FenetreCPM;

public class AlphaGraph implements Runnable {

	int nbreCircuits;
	public boolean ok;

	/** LES METHODES ************************************************************************/

	public int nbreSommets() {
		return Fenetre.leGraphe.sommets.nombreSommets();
	}

	public int nbreArcs() {
		return Fenetre.leGraphe.arcs.nombreArcs();
	}

	public ListeSommets sommets() {
		return Fenetre.leGraphe.sommets;
	}

	public ListeArcs arcs() {
		return Fenetre.leGraphe.arcs;
	}

	public ListeSommets sources() {
		return Fenetre.leGraphe.sources;
	}

	public ListeSommets puits() {
		return Fenetre.leGraphe.puits;
	}

	public void trouverSources() {
		Fenetre.leGraphe.sources = new ListeSommets();
		Iterator<Sommet> it = sommets().iterator();
		while (it.hasNext()) {
			Sommet leSommet = it.next();
			if ((leSommet.getPredecesseurs()).size() == 0) {
				Fenetre.leGraphe.sources.addElement(leSommet);
			}
		}
		if (sources().size() == 1)
			Fenetre.leGraphe.source = sources().firstElement();
	}

	public int nbreSources() {
		return sources().nombreSommets();
	}

	public void trouverPuits() {
		Fenetre.leGraphe.puits = new ListeSommets();
		Iterator<Sommet> it = sommets().iterator();
		while (it.hasNext()) {
			Sommet leSommet = it.next();
			if ((leSommet.getSuccesseurs()).size() == 0) {
				Fenetre.leGraphe.puits.addElement(leSommet);
			}
		}
		if (puits().size() == 1)
			Fenetre.leGraphe.puit = puits().firstElement();
	}

	public int nbrePuits() {
		return puits().nombreSommets();
	}

	public void afficher(int mat[][], int max_ligne, int max_collone,
			String titre) {
		System.out.println("\nMatrice d'" + titre);
		int i;
		for (i = 0; i < max_ligne; i++) {
			for (int j = 0; j < max_collone; j++) {
				// System.out.print(i);
				System.out.print(mat[i][j] + "\t");
			}
			System.out.print("\n");
		}
	}

	public void remplire_zero(int mat[][], int max_ligne, int max_colone) {
		for (int i = 0; i < max_ligne; i++) {
			for (int j = 0; j < max_colone; j++) {
				mat[i][j] = 0;
			}
		}
	}

	public int pos(Sommet leSommet) {
		return Fenetre.lePert.sommets.indexOf(leSommet);
	}

	public boolean orientation() {
		return Fenetre.leGraphe.estOriente();
	}

	public void addListeB() {

		ListeSommets listeA = new ListeSommets();
		ListeSommets listeB = new ListeSommets();

		int i;
		for (i = 0; i < listeB.size(); i++) {
			if (!listeA.contains((Sommet) listeB.elementAt(i))) {
				listeA.add((Sommet) listeB.elementAt(i));
			}
		}

	}

	public boolean connexite() {
		boolean vrai = false;
		ListeSommets l = new ListeSommets();
		ListeSommets listePrealable = (ListeSommets) sommets().clone();
		int nbreComposantes = 0;
		if (nbreSommets() > 0) {
			// Iterator<Sommet> it = listePrealable.iterator();
			while (!listePrealable.isEmpty()) {
				Sommet leSommet = (Sommet) listePrealable.firstElement();
				l.ajouterSommet(leSommet);
				l.ajouterNonRedandante(leSommet.getSuccesseurs());
				l.ajouterNonRedandante(leSommet.getPredecesseurs());
				// System.out.println(nbreComposantes);
				// Iterator<Sommet> it = l.iterator();
				// while (it.hasNext()) {
				for (int i = 0; i < l.size(); i++) {
					Sommet leSommet2 = (Sommet) l.elementAt(i);
					l.ajouterNonRedandante(leSommet2.getSuccesseurs());
					l.ajouterNonRedandante(leSommet2.getPredecesseurs());

				}
				// Fenetre.leGraphe.composantesConnexes[nbreComposantes] =
				// l.size();
				nbreComposantes++;
				listePrealable.removeAll(l);
				l.removeAllElements();
			}

			// System.out.println(nbreComposantes);

			if (nbreComposantes == 1) {
				// System.out.println("Le graph est connexe :");
				vrai = true;
			} else {
				// System.out.println("Le graph n'est pas connexe :" );
				vrai = false;
			}
		}
		listePrealable.removeAllElements();
		l.removeAllElements();
		return vrai;

	}

	// public void circuit() {
	// boolean circuit=false;
	// ListeSommets lC= new ListeSommets ();
	// // for (int i=0;i<=nbreSommets();i++){
	// // lC.addAll(((Sommet) sommets().elementAt(i)).getSuccesseurs());
	// // for (int j=0;j<=nbreSommets();j++){
	// // lC.addAll(((Sommet)sommets().elementAt(j)).getSuccesseurs());
	// // if(((Sommet)lC.elementAt(j)).getSuccesseurs()==(lC.firstElement())){
	// // circuit=true;
	// // System.out.println("le Graphe contient un circuit");
	// // }
	// // }
	// // }
	// int nbCircuit=0;
	// int j=0;
	// ListeSommets listePrealable = (ListeSommets) sommets().clone();
	// cas3:
	// for (int i=0;i<listePrealable.size();i++){
	// Sommet leSommet = (Sommet) listePrealable.elementAt(i);
	// lC.ajouterSommet(leSommet);
	// // recursive(leSommet) {
	// cas1:
	// if(leSommet.getSuccesseurs().size()==0){
	// //listePrealable.remove(leSommet);
	// lC.remove(leSommet);
	// GOTO cas3;
	// }
	//
	// else{
	// cas2:
	// while (j<leSommet.getSuccesseurs().size()){
	// Sommet suc =(Sommet) leSommet.getSuccesseurs().elementAt(j);
	// if (!lC.contains(suc)){
	// lC.addElement(suc);
	// leSommet=suc;
	// GOTO cas1;
	// // recursive(suc);
	// }
	// else{
	// nbCircuit++;
	// leSommet=suc;
	// j++;
	// GOTO cas2;
	//
	// }
	// }
	// listePrealable.remove(leSommet);
	// }
	//
	// }
	//
	// System.out.println("_____________________/n"+nbCircuit);
	// // circuit;
	// }

	public boolean circuit() {
		ListeSommets liste = (ListeSommets) sommets().clone();
		ListeSommets lC = new ListeSommets();
		ListeSommets LP = new ListeSommets();
		while (!liste.isEmpty()) {
			Sommet leSommet = (Sommet) liste.firstElement();
			if (!chercheCircuit(leSommet, lC, LP, liste)) {
				// System.out.println("Pas de circuits");
				return true;
			}
			// System.out.println("Circuits " + nbreCircuits);

		}
		// Sommet leSommet = (Sommet) sommets().firstElement();
		// if(!chercheCircuit(leSommet, lC, LP)){
		// System.out.println("pas de circuit");
		// }
		// System.out.println("il ya au moins un circuit " + nbreCircuits);

		return false;

	}

	public boolean chercheCircuit(Sommet leSommet, ListeSommets lC,
			ListeSommets LP, ListeSommets liste) {

		lC.ajouterSommet(leSommet);
		for (int i = 0; i < leSommet.getSuccesseurs().size(); i++) {
			Sommet v = leSommet.getSuccesseurs().elementAt(i);
			if (!lC.contains(v)) {
				LP.add(leSommet);
				// liste.removeElement(leSommet);
				if (chercheCircuit(v, lC, LP, liste)) {
					nbreCircuits++;
					return true;
				}
			} else {
				int j = 0;
				LP.add(leSommet);
				liste.removeElement(leSommet);
				// System.out.println(v.getNom());
				while (j < LP.size() && (LP.elementAt(j) == v)) {
					// System.out.println(((Sommet) LP.elementAt(j)).getNom());
					j++;
				}
				// afficherListe(LP);
				liste.removeAll(LP);
				LP.removeAllElements();
				// System.out.println("il ya au moins un circuit " +
				// nbreCircuits);
			}
			nbreCircuits++;
			//
			return true;
		}
		return false;
	}

	public boolean testConjonctivite() {
		System.out.println("**************************************");
		System.out.println("***      Test de conjonctivite     ***");
		System.out.println("**************************************");
		System.out.println("*                                    *");
		if (orientation()) {
			System.out.println("*         Le graph est oriente       *");
			trouverSources();
			trouverPuits();
			if (nbreSources() == 1 && nbrePuits() == 1) {
				System.out.println("* Nombre de sources : 1 / puits : 1  *");
				if (connexite()) {
					System.out
							.println("*         Le graph est connexe       *");
					if (circuit()) {
						System.out
								.println("* Le graph ne contient aucun circuit *");
						System.out
								.println("*                                    *");
						System.out
								.println("**************************************");
						System.out
								.println("***     Le graph est conjonctif    ***");
						System.out
								.println("**************************************\n");
						return true;
					} else {
						System.out
								.println("*   Le graph contient des circuits   *");
					}
				} else
					System.out
							.println("*      Le graph n'est pas connexe    *");
			} else
				System.out.println("* Nombre de sources : " + nbreSources()
						+ " / puits : " + nbrePuits() + "  *");
		} else
			System.out.println("*     Le graph n'est pas oriente     *");
		System.out.println("*                                    *");
		System.out.println("**************************************");
		System.out.println("***  Le graph n'est pas conjonctif ***");
		System.out.println("**************************************");
		return false;
	}

	public int[][] adjacence() {
		int i;
		int matrice[][] = new int[Fenetre.lePert.sommets.size()][Fenetre.lePert.sommets
				.size()];
		for (i = 0; i < Fenetre.lePert.arcs.size(); i++) {
			if (Fenetre.lePert.arcs.elementAt(i).getExtremiteTerminale() != null) {
				Sommet ET = Fenetre.lePert.arcs.elementAt(i)
						.getExtremiteTerminale();
				Sommet EI = Fenetre.lePert.arcs.elementAt(i)
						.getExtremiteInitiale();
				int l = pos(EI);
				int c = pos(ET);
				matrice[l][c] = 1;
			}
		}
		return matrice;
	}

	public int[][] Incidence() {
		int i;
		int matrice1[][] = new int[Fenetre.lePert.arcs.size()][Fenetre.lePert.sommets
				.size()];

		for (i = 0; i < Fenetre.lePert.arcs.size(); i++) {
			// if( (((Arc)arcs().elementAt(i)).getExtremiteInitiale())!= null &&
			// (((Arc)arcs().elementAt(i)).getExtremiteTerminale()!= null )){
			Sommet ET = Fenetre.lePert.arcs.elementAt(i)
					.getExtremiteTerminale();
			Sommet EI = Fenetre.lePert.arcs.elementAt(i).getExtremiteInitiale();
			matrice1[i][pos(EI)] = 1;
			matrice1[i][pos(ET)] = -1;
			// }
		}
		return matrice1;
	}

	public void niveau() {
		int max = 0;
		ListeSommets liste = new ListeSommets();
		Fenetre.leGraphe.source.niveau = 0;
		liste.add(Fenetre.leGraphe.source);
		for (int i = 0; i < liste.size(); i++) {
			liste.addAll(liste.elementAt(i).getSuccesseurs());
			Iterator<Sommet> it = liste.elementAt(i).getSuccesseurs()
					.iterator();
			while (it.hasNext()) {
				Sommet leSommet2 = it.next();
				// if (!Fenetre.leGraphe.tachesFictives.contains((Sommet)
				// liste.elementAt(i))){
				leSommet2.niveau = liste.elementAt(i).niveau + 1;
				if (leSommet2.niveau > max)
					max = leSommet2.niveau;
				// }
			}
		}
		for (int i = 1; i < max; i++) {
			int j = 1;
			for (Iterator<Sommet> it2 = sommets().iterator(); it2.hasNext();) {
				Sommet leSommet3 = it2.next();
				if (leSommet3.niveau == i) {
					leSommet3.ordre = j;
					j++;
				}
			}
		}
		liste = null;
	}

	public void niveauPert() {
		int max = 0;
		ListeSommets liste = new ListeSommets();
		Fenetre.lePert.source.niveau = 0;
		liste.add(Fenetre.lePert.source);
		for (int i = 0; i < liste.size(); i++) {
			liste.addAll(liste.elementAt(i).getSuccesseurs());
			Iterator<Sommet> it = liste.elementAt(i).getSuccesseurs()
					.iterator();
			while (it.hasNext()) {
				Sommet leSommet2 = it.next();
				leSommet2.niveau = liste.elementAt(i).niveau + 1;
				if (leSommet2.niveau > max)
					max = leSommet2.niveau;
			}
		}
		for (int i = 1; i < max; i++) {
			int j = 1;
			for (Iterator<Sommet> it2 = Fenetre.lePert.sommets.iterator(); it2
					.hasNext();) {
				Sommet leSommet3 = it2.next();
				if (leSommet3.niveau == i) {
					leSommet3.ordre = j;
					j++;
				}
			}
		}
		liste = null;
	}

	public void organiserParNiveau() {
		Iterator<Sommet> it = sommets().iterator();
		while (it.hasNext()) {
			Sommet leSommet = it.next();

			// System.out.println("Sommet " + leSommet.getNom() +
			// "  Niveau "+leSommet.niveau + "  Ordre "+leSommet.ordre);
			leSommet.setCoordonnees(new Point((leSommet.niveau + 1) * 100,
					leSommet.ordre * 100));
		}

		// Iterator<Sommet> it2 = Fenetre.leGraphe.tachesFictives.iterator();
		// while (it2.hasNext()) {
		// Sommet leSommet = it2.next();
		// //System.out.println("Sommet " + leSommet.getNom() +
		// "  Niveau "+leSommet.niveau + "  Ordre "+leSommet.ordre);
		// leSommet.setCoordonnees(new
		// Point((((Sommet)(leSommet.getPredecesseurs().firstElement())).niveau+1)
		// * 100+50,
		// ((Sommet)(leSommet.getPredecesseurs().firstElement())).ordre *
		// 100+50));
		// }

		Fenetre.leGraphe.source.setCoordonnees(new Point(
				(Fenetre.leGraphe.source.niveau + 1) * 100,
				Fenetre.leGraphe.source.getSuccesseurs().size() * 50 + 50));
		Fenetre.leGraphe.puit.setCoordonnees(new Point(
				(Fenetre.leGraphe.puit.niveau + 1) * 100, Fenetre.leGraphe.puit
						.getPredecesseurs().size() * 50 + 50));
		Fenetre.jPnCanvas.repaint();
	}

	int[] maxNiveaux = new int[100];

	void trouverMaxNiveaux() {
		for (int i = 0; i < 100; i++) {
			int max = 0;
			for (Iterator<Sommet> it = Fenetre.lePert.sommets.iterator(); it
					.hasNext();) {
				Sommet leSommet = it.next();
				if (leSommet.niveau == i)
					max++;
			}
			maxNiveaux[i] = max;
		}
	}

	public void organiserParNiveauPert() {
		Iterator<Sommet> it = Fenetre.lePert.sommets.iterator();
		while (it.hasNext()) {
			Sommet leSommet = it.next();
			// System.out.println("Sommet " + leSommet.getNom() +
			// "  Niveau "+leSommet.niveau + "  Ordre "+leSommet.ordre);
			leSommet.setCoordonnees(new Point((leSommet.niveau + 1) * 100,
					leSommet.ordre * 100));
		}

		leDebut.setCoordonnees(new Point(20, Fenetre.lePert.source.ordre * 100));
		laFin.setCoordonnees(new Point((Fenetre.lePert.puit.niveau + 2) * 100,
				Fenetre.lePert.puit.ordre * 100));
		FenetreCPM.graphe.repaint();
	}

	public boolean estCommun(ListeSommets L1, ListeSommets L2) {
		boolean contient = false;
		Iterator<Sommet> it = L1.iterator();
		while (it.hasNext()) {
			Sommet leSommet = it.next();
			if (L2.contains(leSommet)) {
				contient = true;
				break;
			}
		}
		return contient;
	}

	public ListeSommets leCommun(ListeSommets L1, ListeSommets L2) {
		ListeSommets LC = new ListeSommets();
		Iterator<Sommet> it = L1.iterator();
		while (it.hasNext()) {
			Sommet leSommet = it.next();
			if (L2.contains(leSommet)) {
				LC.add(leSommet);
			}
		}

		return LC;
	}

	public ListeSommets leNonCommun(ListeSommets L1, ListeSommets L2) {
		ListeSommets LN = new ListeSommets();
		Iterator<Sommet> it = L1.iterator();
		while (it.hasNext()) {
			Sommet leSommet = it.next();
			if (!L2.contains(leSommet)) {
				LN.add(leSommet);
			}
		}
		Iterator<Sommet> it2 = L2.iterator();
		while (it2.hasNext()) {
			Sommet leSommet = it2.next();
			if ((!L1.contains(leSommet)) && !(LN.contains(leSommet))) {
				LN.add(leSommet);
			}
		}

		return LN;
	}

	public Arc getleArc(Sommet EI, Sommet ET) {
		Iterator<Arc> it = arcs().iterator();
		Arc leArc = null;
		while (it.hasNext()) {
			leArc = it.next();
			if ((leArc.getExtremiteInitiale() == EI)
					&& (leArc.getExtremiteTerminale() == ET)) {
				return leArc;
			}
		}
		return leArc;
	}

	public void afficherListe(ListeSommets liste) {
		Iterator<Sommet> it = liste.iterator();
		while (it.hasNext()) {
			Sommet leSommet = it.next();
			System.out.println(" :: " + leSommet.getNom());
		}
	}

	public void repererZ() {
		Fenetre.leGraphe.lesZ = new ListeArcs();
		ListeSommets LC = new ListeSommets();
		ListeSommets LN = new ListeSommets();
		Sommet lePredCommun = null;
		Sommet leSuccCommun = null;
		Arc leZ;
		for (int i = 0; i < nbreSommets(); i++) {
			// System.out.println("_____________________");
			for (int j = 0; j < nbreSommets(); j++) {
				if (i == j) {
					// System.out.println(" i = j : " + i + " = "+ j);
					continue;
				} else {
					if (!estCommun(sommets().elementAt(i).getSuccesseurs(),
							sommets().elementAt(j).getSuccesseurs())) {
						// System.out.println("pas de commun");
						// System.out.println("i = "+i+ " j = "+j);
						continue;
					} else {
						if ((sommets()
								.elementAt(i)
								.getSuccesseurs()
								.containsAll(
										sommets().elementAt(j).getSuccesseurs()) && sommets()
								.elementAt(j)
								.getSuccesseurs()
								.containsAll(
										sommets().elementAt(i).getSuccesseurs()))) {
							// System.out.println("communs egaux");
							continue;
						} else {
							// System.out.println("begin");
							LC = leCommun(sommets().elementAt(i)
									.getSuccesseurs(), sommets().elementAt(j)
									.getSuccesseurs());
							LN = leNonCommun(sommets().elementAt(i)
									.getSuccesseurs(), sommets().elementAt(j)
									.getSuccesseurs());
							// afficherListe(LC);
							// System.out.println("  ***");
							// afficherListe(LN);
							Iterator<Sommet> it = LN.iterator();
							while (it.hasNext()) {

								Sommet leSommet = it.next();
								if (leSommet.getPredecesseurs().contains(
										sommets().elementAt(i))) {
									lePredCommun = sommets().elementAt(i);
									// System.out.println("le predCommun = "+i);
								} else {
									if (leSommet.getPredecesseurs().contains(
											sommets().elementAt(j))) {
										lePredCommun = sommets().elementAt(j);
										// System.out.println("le predCommun = "+j);
									}
									// else {System.out.println("ERROR");}
								}
								Iterator<Sommet> it2 = lePredCommun
										.getSuccesseurs().iterator();
								while (it2.hasNext()) {
									Sommet unSommet = it2.next();
									if (LC.contains(unSommet)) {
										leSuccCommun = unSommet;
										// System.out.println(lePredCommun.getNom()+
										// " -> "+ leSuccCommun.getNom());
										leZ = getleArc(lePredCommun,
												leSuccCommun);
										if (!Fenetre.leGraphe.lesZ
												.contains(leZ))
											Fenetre.leGraphe.lesZ.add(leZ);
									}
								}
							}
							LC.removeAllElements();
							LN.removeAllElements();
						}
					}
				}
			}
		}
		Fenetre.jPnCanvas.repaint();
	}

	public void eliminerLesZ() {
		// Fenetre.leGraphe.tachesFictives = new ListeSommets();
		for (Iterator<Arc> it = Fenetre.leGraphe.lesZ.iterator(); it.hasNext();) {
			Arc leArc = it.next();
			// System.out.println("Z : ");
			Sommet tacheFictive = new Sommet(new Point());
			tacheFictive.setLibelle(Fenetre.leGraphe.nbreTachesF);
			tacheFictive.setNom("f" + Fenetre.leGraphe.nbreTachesF);
			tacheFictive.ajouterPredecesseur(leArc.getExtremiteInitiale());
			tacheFictive.ajouterSuccesseur(leArc.getExtremiteTerminale());
			leArc.getExtremiteInitiale().ajouterSuccesseur(tacheFictive);
			leArc.getExtremiteTerminale().ajouterPredecesseur(tacheFictive);
			// afficherListe(tacheFictive.getPredecesseurs());
			// afficherListe(tacheFictive.getSuccesseurs());
			Fenetre.leGraphe.sommets.addElement(tacheFictive);
			Fenetre.leGraphe.tachesFictives.addElement(tacheFictive);
			Arc arc1 = new Arc(leArc.getExtremiteInitiale(), tacheFictive);
			Arc arc2 = new Arc(tacheFictive, leArc.getExtremiteTerminale());
			leArc.getExtremiteInitiale().sorties.add(arc1);
			tacheFictive.entrees.add(arc1);
			tacheFictive.sorties.add(arc2);
			leArc.getExtremiteTerminale().entrees.add(arc2);
			Fenetre.leGraphe.arcs.addElement(arc1);
			Fenetre.leGraphe.arcs.addElement(arc2);
			Fenetre.leGraphe.arcs.removeElement(leArc);
			leArc.getExtremiteInitiale().supprimerSuccesseur(
					leArc.getExtremiteTerminale());
			leArc.getExtremiteTerminale().supprimerPredecesseur(
					leArc.getExtremiteInitiale());
			Fenetre.leGraphe.nbreTachesF++;
		}
		Fenetre.leGraphe.lesZ.removeAllElements();
	}

	public void normaliserContraintesDurees() {
		// Fenetre.leGraphe.tachesFictives = new ListeSommets();
		for (int i = 0; i < sommets().size(); i++) {
			Sommet leSommet = sommets().elementAt(i);
			int min = 10000000;

			for (Iterator<Arc> it2 = leSommet.sorties.iterator(); it2.hasNext();) {
				Arc leArc = it2.next();
				if (leArc.getValeur() < min)
					min = leArc.getValeur();
			}

			for (Iterator<Arc> it3 = leSommet.sorties.iterator(); it3.hasNext();) {
				Arc leArc = it3.next();
				if (leArc.getValeur() > min) {
					int diff = leArc.getValeur() - min;

					Sommet tacheFictive = new Sommet(new Point());
					tacheFictive.setLibelle(Fenetre.leGraphe.nbreTachesF);
					tacheFictive.setNom("g" + Fenetre.leGraphe.nbreTachesF);
					tacheFictive.setDuree(min);
					tacheFictive.ajouterPredecesseur(leArc
							.getExtremiteInitiale());
					tacheFictive.ajouterSuccesseur(leArc
							.getExtremiteTerminale());
					leArc.getExtremiteInitiale()
							.ajouterSuccesseur(tacheFictive);
					leArc.getExtremiteTerminale().ajouterPredecesseur(
							tacheFictive);
					Fenetre.leGraphe.sommets.addElement(tacheFictive);
					Fenetre.leGraphe.tachesFictives.addElement(tacheFictive);
					Arc arc1 = new Arc(leArc.getExtremiteInitiale(),
							tacheFictive);
					arc1.setValeur(min);
					Arc arc2 = new Arc(tacheFictive,
							leArc.getExtremiteTerminale());
					arc2.setValeur(diff);
					Fenetre.leGraphe.arcs.addElement(arc1);
					Fenetre.leGraphe.arcs.addElement(arc2);
					Fenetre.leGraphe.arcs.removeElement(leArc);
					// tacheFictive.entrees.addElement(arc1);
					// tacheFictive.sorties.addElement(arc2);
					leArc.getExtremiteInitiale().supprimerSuccesseur(
							leArc.getExtremiteTerminale());
					leArc.getExtremiteTerminale().supprimerPredecesseur(
							leArc.getExtremiteInitiale());
					Fenetre.leGraphe.nbreTachesF++;
				}
			}
		}
	}

	// public boolean bipartiExiste(Sommet leBiparti){
	// for (Iterator<Sommet> it = Fenetre.pert.sommets.iterator();
	// it.hasNext();){
	// Sommet leSommet = it.next();
	// if (leBiparti.output.containsAll(leSommet.output))
	// return true;
	// }
	// return false;
	// }

	public Sommet getBiparti1(ListeSommets leOutput) {
		// if (!Fenetre.pert.sommets.isEmpty()){
		for (Iterator<Sommet> it = Fenetre.lePert.sommets.iterator(); it
				.hasNext();) {
			Sommet leSommet = it.next();
			if (leSommet.output.containsAll(leOutput))
				return leSommet;
		}
		// }
		return null;
	}

	public Sommet getBiparti2(ListeSommets leInput) {
		// if (!Fenetre.pert.sommets.isEmpty()){
		for (Iterator<Sommet> it = Fenetre.lePert.sommets.iterator(); it
				.hasNext();) {
			Sommet leSommet = it.next();
			if (leSommet.input.contains(leInput))
				return leSommet;
		}
		// }
		return null;
	}

	public void extraireBipartis() {
		Fenetre.lePert.sommets = new ListeSommets();
		for (int i = 0; i < sommets().size(); i++) {
			Sommet leSommet = sommets().elementAt(i);
			for (int j = 0; j < sommets().size(); j++) {
				Sommet leSommet2 = sommets().elementAt(j);
				if (leSommet.getSuccesseurs().isEmpty()) {
					// System.out.println(" i  j : " + i + " : "+ j);
					// System.out.println(leSommet.getNom()+
					// " : "+leSommet2.getNom());
					break;
				}

				else {
					// System.out.println(" i j : " + i + " "+ j);
					if ((leSommet.getSuccesseurs().containsAll(leSommet2
							.getSuccesseurs()))
							&& (leSommet2.getSuccesseurs().containsAll(leSommet
									.getSuccesseurs()))) {
						if (getBiparti1(leSommet.getSuccesseurs()) == null) {
							// System.out.println("exist not "+ i + " "+ j);
							Sommet leBiparti = new Sommet();
							ListeSommets liste = new ListeSommets();
							liste.add(leSommet);
							liste.add(leSommet2);
							leBiparti.input.ajouterNonRedandante(liste);
							leBiparti.output.addAll(leSommet.getSuccesseurs());
							Fenetre.lePert.sommets.add(leBiparti);
							leBiparti.setLibelle(Fenetre.lePert.nbreBiparti);
							leBiparti.setNom("B" + Fenetre.lePert.nbreBiparti);
							Fenetre.lePert.nbreBiparti++;
							// System.out.println(leBiparti.getNom()+" ** ");
							// afficherListe(leBiparti.input);
							// System.out.println("***");
							// afficherListe(leBiparti.output);
						} else {
							// System.out.println("exist "+ i + " "+ j);
							ListeSommets liste = new ListeSommets();
							liste.add(leSommet);
							liste.add(leSommet2);
							getBiparti1(leSommet.getSuccesseurs()).input
									.ajouterNonRedandante(liste);
							// System.out.println(getBiparti(leSommet.getSuccesseurs()).getNom()+
							// " -- ");
							// afficherListe(getBiparti(leSommet.getSuccesseurs()).input);
						}

					}
					// else System.out.println("hola");
				}
			}

		}

		// for (Iterator<Sommet> it = Fenetre.pert.sommets.iterator();
		// it.hasNext();){
		// Sommet leSommet = it.next();
		// System.out.println(leSommet.getNom());
		// afficherListe(leSommet.input);
		// System.out.println("*****");
		// afficherListe(leSommet.output);
		// }
	}

	Sommet leDebut = new Sommet();
	Sommet laFin = new Sommet();

	public void passagePert() {
		Fenetre.lePert.arcs = new ListeArcs();
		for (int i = 0; i < Fenetre.lePert.sommets.size(); i++) {
			Sommet biparti1 = Fenetre.lePert.sommets.elementAt(i);
			for (int j = 0; j < Fenetre.lePert.sommets.size(); j++) {
				Sommet biparti2 = Fenetre.lePert.sommets.elementAt(j);
				for (int k = 0; k < biparti1.output.size(); k++) {
					Sommet leSommet3 = (Sommet) biparti1.output.elementAt(k);
					if (biparti2.input.contains(leSommet3)) {
						// System.out.println(" :::: " + biparti1.getNom() +
						// "->"+biparti2.getNom());
						Arc leArc = new Arc(biparti1, biparti2);
						biparti2.ajouterPredecesseur(biparti1);
						biparti1.ajouterSuccesseur(biparti2);
						biparti1.sorties.addElement(leArc);
						biparti2.entrees.addElement(leArc);
						leArc.setNom(leSommet3.getNom());
						Fenetre.lePert.arcs.addElement(leArc);
						// System.out.println(leArc.getNom() + " : " +
						// biparti1.getNom() + "->"+biparti2.getNom());
						leArc.setValeur((int) biparti2.getDuree());
					}
				}
			}
		}

		Iterator<Sommet> it = Fenetre.lePert.sommets.iterator();
		while (it.hasNext()) {
			Sommet leSommet = it.next();
			if ((leSommet.getSuccesseurs()).size() == 0) {
				Fenetre.lePert.puit = leSommet;
				break;
			}
		}
		Iterator<Sommet> it2 = Fenetre.lePert.sommets.iterator();
		while (it2.hasNext()) {
			Sommet leSommet = it2.next();
			if ((leSommet.getPredecesseurs()).size() == 0) {
				Fenetre.lePert.source = leSommet;
				break;
			}
		}

		leDebut.setNom(".");
		laFin.setNom(".");
		Arc alpha = new Arc(leDebut, Fenetre.lePert.source);
		alpha.setNom(Fenetre.leGraphe.source.getNom());
		Arc omega = new Arc(Fenetre.lePert.puit, laFin);
		omega.setNom(Fenetre.leGraphe.puit.getNom());
		Fenetre.lePert.source.entrees.addElement(alpha);
		Fenetre.lePert.puit.sorties.addElement(omega);
		Fenetre.lePert.arcs.addElement(alpha);
		Fenetre.lePert.arcs.addElement(omega);

	}

	public void run() {

		if (testConjonctivite()) {
			// repererZ();
			// eliminerLesZ();
			// normaliserContraintesDurees();
			// niveau();
			// organiserParNiveau();
			extraireBipartis();
			passagePert();
			niveauPert();
			organiserParNiveauPert();
			ok = true;
			// for (Iterator<Sommet> it =
			// Fenetre.leGraphe.tachesFictives.iterator(); it.hasNext();){
			// Sommet leSommet = it.next();
			// //dessinerSommet(leSommet, Color.red, g2d);
			// System.out.println(leSommet.getNom());
			// }

			// for (Iterator<Arc> it = Fenetre.lePert.arcs.iterator();
			// it.hasNext();){
			// Arc leArc = it.next();
			// System.out.println(leArc.getNom()+ " : "+
			// leArc.getExtremiteInitiale().getNom()+" -> "+
			// leArc.getExtremiteTerminale().getNom());
			// }
		}

		// remplire_zero(Fenetre.lePert.matriceAdjacense,
		// Fenetre.lePert.sommets.size(), Fenetre.lePert.sommets.size());
		// adjacence();
		// afficher(adjacence(), Fenetre.lePert.sommets.size(),
		// Fenetre.lePert.sommets.size(), "adjacense");
		// afficher(Incidence(),Fenetre.lePert.arcs.size(),Fenetre.lePert.sommets.size(),"indicence");

		// System.out.println("Les Z ************");
		// Iterator<Arc> it2 = Fenetre.leGraphe.lesZ.iterator();
		// while (it2.hasNext()) {
		// Arc leArc=it2.next();
		// System.out.println("Z : "+
		// leArc.getExtremiteInitiale().getNom()+" -> "+
		// leArc.getExtremiteTerminale().getNom());
		// }

	}

}
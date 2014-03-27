package me.mouhoub.alphaGraph.core;

public class ConstructionMPM extends AlphaGraph {
	public void run() {
		eliminerLesZ();
		normaliserContraintesDurees();
		niveau();
		organiserParNiveau();
	}
}

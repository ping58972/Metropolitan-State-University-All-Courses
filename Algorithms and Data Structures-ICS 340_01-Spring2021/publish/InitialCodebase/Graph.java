package InitialCodebase;

import java.util.ArrayList;

public class Graph {

	ArrayList<Node> nodeList;
	ArrayList<Edge> edgeList;

	public Graph() {
		nodeList = new ArrayList<Node>();
		edgeList = new ArrayList<Edge>();
	}

	public ArrayList<Node> getNodeList() {
		return nodeList;
	}

	public ArrayList<Edge> getEdgeList() {
		return edgeList;
	}

	public void addNode(Node n) {
		nodeList.add(n);
	}

	public void addEdge(Edge e) {
		edgeList.add(e);
	}

	// Add toString() method for debug.
	@Override
	public String toString() {
		return "Graph [nodeList=" + nodeList + ", edgeList=" + edgeList + "]\n";
	}

}

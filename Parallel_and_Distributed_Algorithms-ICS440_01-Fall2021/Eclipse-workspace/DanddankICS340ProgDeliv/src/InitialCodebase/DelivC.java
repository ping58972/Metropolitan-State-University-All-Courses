/**
 * Class DelivC does the work for deliverable DelivC of the Prog340
 * ICS 340
 * Project programming Deliverable C
 * @author Nalongsone Danddank
 *
 */
package InitialCodebase;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

// Class DelivC does the work for deliverable DelivC of the Prog340

public class DelivC {

	File inputFile;
	File outputFile;
	PrintWriter output;
	Graph g;

	public DelivC(File in, Graph gr) {
		inputFile = in;
		g = gr;

		// Get output file name.
		String inputFileName = inputFile.toString();
		String baseFileName = inputFileName.substring(0, inputFileName.length() - 4); // Strip off ".txt"
		String outputFileName = baseFileName.concat("_out.txt");
		outputFile = new File(outputFileName);
		if (outputFile.exists()) { // For retests
			outputFile.delete();
		}

		try {
			output = new PrintWriter(outputFile);
		} catch (Exception x) {
			System.err.format("Exception: %s%n", x);
			System.exit(0);
		}
		// Print out the final Goal path and lenght.
		printGoal(ID_search(g));
		output.flush();
	}

	/**
	 * search for the correct path.
	 * 
	 * @param the start path - Graph
	 * @return the correct path - Graph
	 */
	private Graph ID_search(Graph G) {
		// flag for loop until found out the Goal Node "G".
		boolean hasAnyNeighbors[] = { true };
		// beginning with Depth 1
		int bound = 1;
		// find out the start Node "S"
		Node startNode = findNode(G, "S");
		if (startNode.getOutgoingEdges().size() == 0) {
			// display message if the start Node does not have any out going edge.
			System.out.println("The start Node Not aviable, have No Out going edge!");
			return null;
		}
		// create a path to store in the priority Queue.
		Graph path = new Graph();
		path.addNode(startNode);
		PriorityQueue<GraphWithLength> pathPQ = createPriorityQueuePath();
		// Looping until find out the rith shortest path with the goal node.
		while (hasAnyNeighbors[0]) {
			hasAnyNeighbors[0] = false;
			Graph res = depth_bounded_search(path, bound, pathPQ, hasAnyNeighbors);
			if (res != null) {
				// return the path when we found.
				return res;
			}
			bound++;
		}
		return null;
	}

	/**
	 * use recursive function for run all the Graph and find out the shortest path
	 * to reach the goal node. search by depth bound for the correct path.
	 * 
	 * @param the path - Graph (for find out the goal node), b - int (bound:
	 *            increasing by 1 for testing each path), pathPQ -
	 *            PriorityQueue<GraphWithLength> (store each path and pop out the
	 *            smallest path lenght)
	 * @return the next path - Graph
	 */
	private Graph depth_bounded_search(Graph path, int b, PriorityQueue<GraphWithLength> pathPQ,
			boolean[] hasAnyNeighbors) {

		// add a new path to PathPQ when start the looping.
		if ((b == 1) && pathPQ.isEmpty()) {
			Node startNode = path.getNodeList().get(path.getNodeList().size() - 1);
			addNewPathToPathPQ(pathPQ, path, startNode);
			String str = "Yields output:\n\nDepth 1:\n" + startNode.abbrev + "\n";
			System.out.println(str);
			output.println(str);
		}

		// return the path which need.
		if (path.getNodeList().get(path.getNodeList().size() - 1).getVal().equals("G")) {
			return path;
		} else if ((b == pathPQ.peek().length) && !pathPQ.isEmpty()) {
			// when the increasing bound or depth equal to a path's length, the shortest
			// length in queue. Pop out the path and test if it is the goal node "G" then
			// return it.
			GraphWithLength gLPop = pathPQ.poll();
			printEachDepth(gLPop);// print out each depth of path and length.
			Graph currPath = gLPop.graph;
			Node tailNode = currPath.getNodeList().get(currPath.getNodeList().size() - 1);
			if (tailNode.getOutgoingEdges().size() == 0) {
				return null;
			}
			// create new path from a tail node of current path and add to pathPQ
			addNewPathToPathPQ(pathPQ, currPath, tailNode);
			// do recursion until found the right path.
			Graph foundPath = depth_bounded_search(currPath, b, pathPQ, hasAnyNeighbors);
			if (foundPath != null) {
				return foundPath;
			}
		} else if (path.getNodeList().get(path.getNodeList().size() - 1).getOutgoingEdges().size() != 0) {
			// do the flag to true for looping until found the right path.
			hasAnyNeighbors[0] = true;
		} else {
			hasAnyNeighbors[0] = false;
		}
		// return null if is not the right path.
		return null;
	}

	/**
	 * create new path from a tail node of current path and add to pathPQ.
	 * 
	 * @param pathPQ - PriorityQueue<GraphWithLength>, path - Graph, n - Node
	 * 
	 * @return the correct path - Graph
	 */
	private void addNewPathToPathPQ(PriorityQueue<GraphWithLength> pathPQ, Graph path, Node n) {
		ArrayList<Edge> edges = n.getOutgoingEdges();
		ArrayList<Node> nodes = path.nodeList;
		for (Edge edge : edges) {
			if (!nodes.contains(edge.head)) {
				GraphWithLength newPath = new GraphWithLength(path, edge);
				pathPQ.add(newPath);
			}
		}
	}

	/**
	 * create Priority Queue that store object of path and length. sorted by path's
	 * length. In case of a tie, break ties alphabetically.
	 * 
	 * @return a new Priority Queue - PriorityQueue<GraphWithLength>
	 */
	private PriorityQueue<GraphWithLength> createPriorityQueuePath() {
		return new PriorityQueue<GraphWithLength>(new Comparator<GraphWithLength>() {
			@Override
			public int compare(GraphWithLength g1, GraphWithLength g2) {
				int gInt = g1.length - g2.length;
				if (gInt == 0) {
					// In case of a tie, break ties alphabetically.
					return g1.abbrev.compareTo(g2.abbrev);
				}
				return gInt;
			}
		});
	}

	/**
	 * find the node from graph with a value string.
	 * 
	 * @param pathPQ - PriorityQueue<GraphWithLength>, path - Graph, n - Node
	 * 
	 * @return the found node - Node
	 */
	private Node findNode(Graph G, String s) {
		for (Node n : G.nodeList) {
			if (n.getVal().equals(s)) {
				return n;
			}
		}
		return null;
	}

	/**
	 * print out each depth of path and length.
	 * 
	 * @param instance of GraphWithLength class
	 */
	private void printEachDepth(GraphWithLength gl) {
		String str = "Depth " + gl.length + ":\n";
		for (Node n : gl.graph.nodeList) {
			str += n.abbrev;
		}
		str += "\n";
		System.out.println(str);
		output.println(str);
	}

	/**
	 * print out the final goal depth of path and length.
	 * 
	 * @param path - Graph
	 */
	private void printGoal(Graph path) {
		String str = "Path found: ";
		int totalLength = 0;
		for (Node node : path.nodeList) {
			str += node.abbrev;
		}
		for (Edge e : path.edgeList) {
			totalLength += e.dist;
		}
		str += ", length = " + totalLength;
		System.out.println(str);
		output.println(str);
	}
}

/**
 * create this class for content a path and it length and the final abbrev of
 * node of path.
 * 
 * To store in the Priority Queue then sorted by the length.
 * 
 */
class GraphWithLength {
	Graph graph;
	int length;
	String abbrev;

	/**
	 * Constructor for build a object to store in Priority Queue.
	 * 
	 * @param path - Graph, e - Edge
	 */
	public GraphWithLength(Graph path, Edge e) {
		this.graph = createGraph(path, e);
		this.length = totalEdgeDistanceInGraph(graph);
		this.abbrev = e.getHead().getAbbrev();
	}

	/**
	 * create a new path by cloning form old path and add a new edge and node to the
	 * end.
	 * 
	 * @param path - Graph, e - Edge
	 * 
	 * @return a new path - Graph
	 */
	private Graph createGraph(Graph path, Edge e) {
		Graph newG = new Graph();
		newG.edgeList.addAll(path.getEdgeList());
		newG.nodeList.addAll(path.getNodeList());
		newG.addEdge(e);
		newG.addNode(e.head);
		return newG;
	}

	/**
	 * Calculate the length of path by sum all the distance of each edge.
	 * 
	 * @param path - Graph
	 * 
	 * @return sum - int
	 */
	private int totalEdgeDistanceInGraph(Graph path) {
		int sum = 0;
		for (Edge e : path.edgeList) {
			sum += e.getDist();
		}
		return sum;
	}

	@Override
	public String toString() {
		return "GraphWithLength [graph=" + graph + ", length=" + length + ", abbrev=" + abbrev + "]\n\n";
	}

}
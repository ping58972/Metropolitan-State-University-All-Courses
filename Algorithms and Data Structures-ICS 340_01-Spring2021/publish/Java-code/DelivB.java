/**
 * Class DelivB does the work for deliverable DelivB of the Prog340
 * ICS 340
 * Project programming Deliverable B
 * @author Nalongsone Danddank
 *
 */
package InitialCodebase;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Stack;

//Class DelivB does the work for deliverable DelivB of the Prog340

public class DelivB {

	File inputFile;
	File outputFile;
	PrintWriter output;
	Graph g;

	public DelivB(File in, Graph gr) {
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
		String result = yieldsOutput(bitonicTourPath(output));
		System.out.println(result);
		output.println(result);
		output.flush();
	}

	/**
	 * construct Bitonic tour Graph.
	 * 
	 * @return BitonicGraph - Graph
	 */
	private Graph bitonicTourPath(PrintWriter output) {
		// take the nodes from original graph and sorted them.
		ArrayList<Node> sortedNodes = this.g.getNodeList();
		Collections.sort(sortedNodes, new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				// Fixed this bug, and get correct answer.
				if (Double.parseDouble(o1.getVal()) > Double.parseDouble(o2.getVal())) {
					return -1;
				} else if (Double.parseDouble(o1.getVal()) == Double.parseDouble(o2.getVal())) {
					return 0;
				} else {
					return 1;
				}
//				return Double.parseDouble(o1.getVal()) > Double.parseDouble(o2.getVal()) ? -1
//						: (Double.parseDouble(o1.getVal()) > Double.parseDouble(o2.getVal())) ? 1 : 0;
			}
		});
		for (Node node : sortedNodes) {
			String printString = node.getAbbrev() + ": " + node.getVal();
			System.out.println(printString);
			output.println(printString);
		}
		// initialize Bitonic table and index of node of table to build the table.
		int n = sortedNodes.size();
		int[][] bTable = new int[n][n];
		int[][] nTable = new int[n][n];
		// builded then construct and return the graph.
		buildBitonicTable(sortedNodes, bTable, nTable, n);
		printOutTable(output, sortedNodes, bTable, nTable, n);
		Graph graphResult = constructBitonicGraph(sortedNodes, buildPathArray(sortedNodes, bTable, nTable, n));

		return graphResult;
	}

	/**
	 * build Bitonic tour Table.
	 * 
	 * @param sortedNodes - ArrayList<Node>;
	 * 
	 *                    bTable - int[][]; nTable - int[][]; n - int;
	 */
	private void buildBitonicTable(ArrayList<Node> sortedNodes, int[][] bTable, int[][] nTable, int n) {
		bTable[0][1] = getTwoCitiesDistance(sortedNodes.get(0), sortedNodes.get(1));
		nTable[0][1] = 0;
		for (int j = 2; j < n; j++) {
			for (int i = 0; i < j; i++) {
				int min = Integer.MAX_VALUE;
				if (j <= i + 1) {
					for (int k = 0; k < i; k++) {
						int temp = bTable[k][i] + getTwoCitiesDistance(sortedNodes.get(k), sortedNodes.get(j));
						if (min > temp) {
							min = temp;
							nTable[i][j] = k;
						}
					}
					bTable[i][j] = min;
				} else {
					bTable[i][j] = bTable[i][j - 1] + getTwoCitiesDistance(sortedNodes.get(j - 1), sortedNodes.get(j));
					nTable[i][j] = j - 1;
				}
			}
		}
	}

	/**
	 * get Two Cities Distance.
	 * 
	 * @param city1 - Node; city2 - Node;
	 * 
	 * @return distance of Edge - int
	 */
	private int getTwoCitiesDistance(Node city1, Node city2) {
		ArrayList<Edge> edges = city1.getOutgoingEdges();
		Edge distancEdge = null;
		// find out the edge between two cities and return the distance.
		for (Edge edge : edges) {
			if (edge.getHead().getName().equals(city2.getName())) {
				distancEdge = edge;
			}
		}
		return distancEdge.getDist();
	}

	/**
	 * build Path Array.
	 * 
	 * @param sortedNodes - ArrayList<Node>;
	 * 
	 *                    bTable - int [][]; nTable - int[][]; n - int;
	 * @return originalPath - ArrayList<Integer>
	 */
	private ArrayList<Integer> buildPathArray(ArrayList<Node> sortedNodes, int[][] bTable, int[][] nTable, int n) {
		Stack<Integer> stack1 = new Stack<>();
		Stack<Integer> stack2 = new Stack<>();
		int i = n - 2;
		int k = 0;
		int j = n - 1;
		while (j > 0) {
			if (k == 0) {
				stack1.push(j);
			} else {
				stack2.push(j);
			}
			j = nTable[i][j];
			if (j < i) {
				int temp = i;
				i = j;
				j = temp;
				if (k == 0) {
					k = 1;
				} else {
					k = 0;
				}
			}
		}
		stack1.push(0);
		while (!stack2.empty()) {
			stack1.push(stack2.pop());
		}
		// convert index of path stack to array list.
		ArrayList<Integer> originalPath = new ArrayList<>(stack1);
		return fixedPathArray(originalPath);
	}

	/**
	 * fixed Path Array.
	 * 
	 * @param originalPath - ArrayList<Integer>; pathArray - (ArrayList<Integer>);
	 * @return pathIndexList - ArrayList<Integer>
	 */
	private ArrayList<Integer> fixedPathArray(ArrayList<Integer> originalPath) {
		ArrayList<Integer> pathIndexList = new ArrayList<Integer>();
		int zeroIndexFlag = -1;
		// take the original path from bitonic and arrange to correct one.
		for (int i = 0; i < originalPath.size(); i++) {
			if (originalPath.get(i) == 0)
				zeroIndexFlag = i;
			if (zeroIndexFlag != -1)
				pathIndexList.add(originalPath.get(i));

		}
		for (int j = 0; j < zeroIndexFlag; j++) {
			pathIndexList.add(originalPath.get(j));
		}
		pathIndexList.add(0);
		return pathIndexList;
	}

	/**
	 * construct Bitonic Graph cycle path.
	 * 
	 * @param sortedNodes - (ArrayList<Node>); pathArray - (ArrayList<Integer>);
	 * @return bitonicPath - Graph
	 */
	private Graph constructBitonicGraph(ArrayList<Node> sortedNodes, ArrayList<Integer> pathArray) {
		Graph bitonicPath = new Graph();
		// find out the node which index following by pathArray.
		for (int i = 0; i < pathArray.size() - 1; i++) {
			Node headNode = sortedNodes.get(pathArray.get(i));
			Node tailNode = sortedNodes.get(pathArray.get(i + 1));
			bitonicPath.addNode(headNode);
			for (Edge edge : headNode.getOutgoingEdges()) {
				if (edge.getHead().getName().equals(tailNode.getName())) {
					bitonicPath.addEdge(edge);
					continue;
				}
			}

		}
		// adding the first node to the end to be circle path.
		bitonicPath.addNode(sortedNodes.get(0));
		return bitonicPath;
	}

	/**
	 * sum up all edge of the circle path and find out all node's abbreviation to
	 * show the path link.
	 * 
	 * @param gr - the circle path (Graph)
	 * 
	 * @return output - String
	 */
	private String yieldsOutput(Graph gr) {
		String output = "";
		int sum = 0;

		// to sum up all need of distance.
		for (Edge e : gr.getEdgeList()) {
			sum += e.getDist();
		}
		int i = 0;
		String path = "";
		// find out all node's abbreviation of circle path.
		for (Node n : gr.getNodeList()) {

			if (i < gr.getNodeList().size() - 1) {
				path += n.getAbbrev() + "->";
			} else {
				path += n.getAbbrev();
			}
			i++;
		}
		// find out all reversed node's abbreviation of circle path.
		ArrayList<Node> reversedNodeList = gr.getNodeList();
		Collections.reverse(reversedNodeList);
		int j = 0;
		String reversedPath = "";
		for (Node n : reversedNodeList) {

			if (j < reversedNodeList.size() - 1) {
				reversedPath += n.getAbbrev() + "->";
			} else {
				reversedPath += n.getAbbrev();
			}
			j++;
		}
		// format all.
		output = "Shortest bitonic tour has distance " + sum + "\nTour is: " + path + "\nReversed Tour is: "
				+ reversedPath;
		return output;
	}

	/**
	 * Print out sortedArray and all table.
	 * 
	 * @param sortedNodes - ArrayList<Node> bTable - int[][] nTable - int[][] n -
	 *                    int output PrintWriter
	 * 
	 */
	private void printOutTable(PrintWriter output, ArrayList<Node> sortedNodes, int[][] bTable, int[][] nTable, int n) {
		bTable[n - 1][n - 1] = bTable[n - 2][n - 1]
				+ getTwoCitiesDistance(sortedNodes.get(n - 1), sortedNodes.get(nTable[0][n - 1]));
		nTable[n - 1][n - 1] = nTable[0][n - 1];
		String string = "     |";
		for (int i = 0; i < n; i++) {
			string += String.format("%5s", sortedNodes.get(i).getAbbrev());
		}
		string += "\n";
		string += "-----|";
		for (int i = 0; i < n; i++) {
			string += String.format("%5s", "-----");
		}
		string += "\n";
		for (int i = 0; i < n; i++) {
			string += String.format("%5s|", sortedNodes.get(i).getAbbrev());
			for (int j = 0; j < n; j++) {
				if (bTable[i][j] == 0) {
					string += String.format("%5s", ".");
					continue;
				}
				string += String.format("%5d", bTable[i][j]);
			}
			string += "\n";
		}

		string += "\n     |";
		for (int i = 0; i < n; i++) {
			string += String.format("%5s", sortedNodes.get(i).getAbbrev());
		}
		string += "\n";
		string += "-----|";
		for (int i = 0; i < n; i++) {
			string += String.format("%5s", "-----");
		}
		string += "\n";
		for (int i = 0; i < n; i++) {
			string += String.format("%5s|", sortedNodes.get(i).getAbbrev());
			for (int j = 0; j < n; j++) {
				if (bTable[i][j] == 0) {
					string += String.format("%5s", ".");
					continue;
				}
				string += String.format("%5s", sortedNodes.get(nTable[i][j]).getAbbrev());
			}
			string += "\n";
		}
		System.out.println(string);
		output.println(string);
	}

}

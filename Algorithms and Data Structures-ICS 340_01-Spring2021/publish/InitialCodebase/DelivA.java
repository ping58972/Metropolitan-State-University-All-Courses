/**
 * Class DelivA does the work for deliverable DelivA of the Prog340
 * ICS 340
 * 
 * @author Nalongsone Danddank
 *
 */
package InitialCodebase;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

public class DelivA {

	File inputFile;
	File outputFile;
	PrintWriter output;
	Graph g;

	public DelivA(File in, Graph gr) {
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
		// get the result of String from each method, then print out.
		String result = yieldsOutput(circlePathByValS());
		System.out.println(result);
		output.println(result);
		output.flush();
	}

	/**
	 * Find out the circle path of city that start wiht Val = S and end with S in
	 * the given file
	 * 
	 * @return the circle path (Graph) object.
	 */
	private Graph circlePathByValS() {
		// create a new Graph object for the circle path.
		Graph circlePath = new Graph();
		ArrayList<Node> nodes = this.g.getNodeList();
		int totalNode = nodes.size();
		boolean flag = false;
		String startCity = null;
		// loop for all nodes two time for create the circle path that we need.
		for (int index = 0; index < totalNode * 2; index++) {
			Node prevNode = null;
			if (index == 0) {
				prevNode = nodes.get(totalNode - 1);
			} else {
				prevNode = nodes.get((index - 1) % totalNode);
			}

			Node currNode = nodes.get(index % totalNode);
			Node nextNode = nodes.get((index + 1) % totalNode);
			if (currNode.getVal().equals("S")) {
				flag = true;
				startCity = currNode.getAbbrev();
			}
			if (flag) {
				Node node = new Node(currNode.getAbbrev());
				node.setName(currNode.getName());
				node.setVal(currNode.getVal());
				for (Edge e : currNode.getIncomingEdges()) {
					if (e.getTail().getName().equals(prevNode.getName())
							&& e.getHead().getName().equals(currNode.getName())) {
						node.addIncomingEdge(e);
					}

				}
				for (Edge e : currNode.getOutgoingEdges()) {
					if (e.getHead().getName().equals(nextNode.getName())
							&& e.getTail().getName().equals(currNode.getName())) {
						node.addOutgoingEdge(e);
						// add the edge that only need to the path.
						circlePath.addEdge(e);
					}

				}
				// add the node to the circle path which begins by Val=S follow by input file.
				circlePath.addNode(node);

				// when the loop meets to the city of the beginning again or Val=s, then stop!
				if (nextNode.getAbbrev().equals(startCity)) {
					break;
				}
			}
		}

		return circlePath;
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
		String path = "";
		// to sum up all need of distance.
		for (Edge e : gr.getEdgeList()) {
			sum += e.getDist();
		}
		// find out all node's abbreviation of circle path.
		for (Node n : gr.getNodeList()) {
			path += n.getAbbrev() + "->";
		}
		// format all.
		output = "Path " + path + gr.getNodeList().get(0).getAbbrev() + " has distance " + sum;
		return output;
	}
}

import java.io.File;
import java.io.PrintWriter;

// Class DelivFX is for monkeying around with Prog340
// Print out (to the console and/or a file) each node pair AB for which there is an edge from node A to node B and an edge from node B to node A.
// Do this by only modifying your Prog340.java file to allow for DelivFX, and modifying this DelivFX method.  
// Submit only those two methods to the final exam takehome Part B dropbox.  
// I will combine them with the last version of code that you submitted this year.

public class DelivFX {

	File inputFile;
	File outputFile;
	PrintWriter output;
	Graph g;
	
	public DelivFX( File in, Graph gr ) {
		inputFile = in;
		g = gr;
		
		// Get output file name.
		String inputFileName = inputFile.toString();
		String baseFileName = inputFileName.substring( 0, inputFileName.length()-4 ); // Strip off ".txt"
		String outputFileName = baseFileName.concat( "_out.txt" );
		outputFile = new File( outputFileName );
		if ( outputFile.exists() ) {    // For retests
			outputFile.delete();
		}
		
		try {
			output = new PrintWriter(outputFile);			
		}
		catch (Exception x ) { 
			System.err.format("Exception: %s%n", x);
			System.exit(0);
		}

		// Given degrees, minutes, seconds, turn into fractions of a degree.
		findPairsOfEdges();
		output.flush();
	}
	
	// FInd pairs of edges.
	public void findPairsOfEdges() {
		
		// Iterate through each Node in the graph.
		for ( Node n : g.getNodeList() ) {
		
		// Iterate through each outgoing edge in which the Node at the head of the Edge is later in the alphabet than this node at the tail of the Edge.
			for ( Edge e : n.getOutgoingEdges() ) {
				Node head = e.getHead();
				
				if ( n.getName().compareTo( head.getName() ) > 0 ) {
					// Look for Edge in opposite direction.
					for ( Edge reverseEdge : head.getOutgoingEdges() ) {
						// Go to the Node at the head, and search for an edge with the initial Node at the head.  If it exists, print the node pair.
						if ( reverseEdge.getHead().getName().contentEquals( n.getName() ) ) {
							System.out.println( "There are edges in both directions between " + n.getName() + " and " + head.getName() ) ;
						}
					}
				}
			}
		}
	}
}



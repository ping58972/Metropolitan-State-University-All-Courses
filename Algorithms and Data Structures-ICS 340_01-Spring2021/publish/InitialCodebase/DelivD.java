/**
 * Class DelivD does the work for deliverable DelivD of the Prog340
 * ICS 340
 * Project programming Deliverable D
 * @author Nalongsone Danddank
 *
 */
package InitialCodebase;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

// Class DelivD does the work for deliverable DelivD of the Prog340

public class DelivD {

	File inputFile;
	File outputFile;
	PrintWriter output;
	Graph g;

	public DelivD(File in, Graph gr) {
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

		// initial parameter for program.
		int limitedTime = 3; // set time seconds for limited time of program running.
		int maxNumberStep = 400000; // set maximum of number step for generic algorithm
									// to prevent run out of memory.
		double temp = 100000.0; // initial 100000 temperate for the calculating.
		double coolingRate = 0.0003; // 0.0003 Cooling rate

		// Try finding Ham cycle path using Generic algorithm by limited time.
		localSearch_genericAlgorithm(limitedTime, maxNumberStep);

		// Try finding Ham cycle path using Simulated Anneling algorithm by limited
		// time, temperate, and cooling rate.
		localSearch_simulatedAnnelingAlgorithm(limitedTime, temp, coolingRate);

		// Try finding Ham cycle path using combine generic and Simulated Anneling
		// algorithm by limited time, temperate, and cooling rate.
		localSearch_genericAndSimulatedAnnelingAlgorithm(limitedTime, temp, coolingRate);

		output.flush();
	}

	/**
	 * finding Ham cycle path local Search by generic Algorithm
	 * 
	 * @param time_sec - int - second
	 * @return
	 */
	private void localSearch_genericAlgorithm(int time_sec, int maxNumberStep) {
		// find out index of Node Goal "G" and node Start "S"
		// create random intial solution
		Path path = initialPath();

		/* print out initial info. */
		printOut("beginPrint", "Using generic Algorithm and time limited by ", path, time_sec);

		Path bestPath = null;
		int grahpSize = g.nodeList.size();
		// taking a number of steps by (grahpSize - 2)!
		// but less than maxNumberStep = 400000.
		int numberSteps = numberStepByFactory(grahpSize, maxNumberStep);
		if (grahpSize < 4) {
			// if the size of Graph is less than 4 that mean no calculating.
			bestPath = path;
		} else {
			bestPath = path;
			// create a trace list path to remember each path, and to prevent calculating
			// a duplicate one.
			ArrayList<ArrayList<Integer>> tracePaths = new ArrayList<ArrayList<Integer>>();
			tracePaths.add(path.pathIndexs);
			ArrayList<Integer> offSpring = null;
			// set a start time to stop program by the initial time
			long startTime = System.currentTimeMillis();
			for (int i = 0; i < numberSteps; i++) {
				// evaluate the path is better the old one or not.
				bestPath = evaluatePath(path, bestPath, i);
				do {
					// random selection for create two parents.
					ArrayList<Integer> parent1 = randomSelection(path.pathIndexs);
					ArrayList<Integer> parent2 = randomSelection(path.pathIndexs);
					// processing crossover of two parents to create a new offspring.
					offSpring = crossover(parent1, parent2);
					// processing the mutation step of offspring.
					mutation(offSpring);
					if (timeLimit(startTime, time_sec)) {
						/* Stop program when run out of time . */
						break;
					}
					// check the new offspring if have created before or not/
				} while (isNewOffSpring(offSpring, tracePaths));
				// record the new offspring to trace
				tracePaths.add(offSpring);
				path = new Path(g, offSpring);
				if (timeLimit(startTime, time_sec)) {
					/* Stop program when run out of time . */
					break;
				}
			}
		}
		/* print out the best path */
		printOut("endPrint", "", bestPath, time_sec);
	}

	/**
	 * finding Ham cycle path local Search by Simulated Anneling Algorithm
	 * 
	 * @param time_sec    - int - second
	 * @param temp        - double - temperate of initial.
	 * @param coolingRate - double - cooling Rate of initial.
	 * @return
	 */
	private void localSearch_simulatedAnnelingAlgorithm(int time_sec, double temp, double coolingRate) {
		// create random intial solution
		// find out index of Node Goal "G" and node Start "S"
		Path path = initialPath();

		/* print out initial info. */
		printOut("beginPrint", "Using simulated Anneling Algorithm and time limited by ", path, time_sec);

		int grahpSize = g.nodeList.size();
		Path bestPath = path;
		if (grahpSize < 4) {
			// if the size of Graph is less than 4 that mean no calculating.
			bestPath = path;
		} else {
			// taking a number of steps by (n!-1) but less than 100000
			long startTime = System.currentTimeMillis();
			// create a trace list path to remember each path, and to prevent calculating
			// a duplicate one.
			ArrayList<ArrayList<Integer>> tracePaths = new ArrayList<ArrayList<Integer>>();
			tracePaths.add(path.pathIndexs);
			// Loop until system has cooled
			while (temp > 1) {
				// Create new neighbour tour
				ArrayList<Integer> newPathIndexs = null;
				do {
					// processing random selection for create a new path index array
					newPathIndexs = randomSelection(path.pathIndexs);
					if (timeLimit(startTime, time_sec)) {
						/* Stop program when run out of time . */
						break;
					}
					// check the new offspring if have created before or not/
				} while (isNewOffSpring(newPathIndexs, tracePaths));
				Path newPath = new Path(g, newPathIndexs);
				tracePaths.add(newPathIndexs);
				// Get energy of solutions
				int currentDistance = path.dist;
				int neighbourDistance = newPath.dist;
				// Decide if we should accept the neighbour
				double rand = randomDouble();
				if (acceptanceProbability(currentDistance, neighbourDistance, temp) > rand) {
					path = newPath;
				}
				// Keep track of the best solution found
				bestPath = evaluatePath(path, bestPath, temp);
				// Cool system
				temp *= 1 - coolingRate;
				// Limited time for over running
				if (timeLimit(startTime, time_sec)) {
					/* Stop program when run out of time . */
					break;
				}

			}

		}
		/* print out the best path */
		printOut("endPrint", "", bestPath, time_sec);
	}

	/**
	 * finding Ham cycle path local Search by combine Generic And Simulated Anneling
	 * Algorithm
	 * 
	 * @param time_sec    - int - second
	 * @param temp        - double - temperate of initial.
	 * @param coolingRate - double - cooling Rate of initial.
	 * @return
	 */
	private void localSearch_genericAndSimulatedAnnelingAlgorithm(int time_sec, double temp, double coolingRate) {
		// create random intial solution
		// find out index of Node Goal "G" and node Start "S"
		Path path = initialPath();
		/* print out initial info. */
		printOut("beginPrint", "Using generic And simulated Anneling Algorithm and time limited by ", path, time_sec);

		int grahpSize = g.nodeList.size();
		Path bestPath = path;
		if (grahpSize < 4) {
			bestPath = path;
		} else {
			long startTime = System.currentTimeMillis();
			ArrayList<ArrayList<Integer>> tracePaths = new ArrayList<ArrayList<Integer>>();
			tracePaths.add(path.pathIndexs);
			// Loop until system has cooled
			while (temp > 1) {
				// Create new neighbour tour
				ArrayList<Integer> offSpring = null;
				do {
					// combine Generic algorithm to Simulated Anneling algiorithm here.
					// by using the process of generate a new offspring to create a new path
					ArrayList<Integer> parent1 = randomSelection(path.pathIndexs);
					ArrayList<Integer> parent2 = randomSelection(path.pathIndexs);
					offSpring = crossover(parent1, parent2);
					mutation(offSpring);
					if (timeLimit(startTime, time_sec)) {
						break;
					}
				} while (isNewOffSpring(offSpring, tracePaths));
				Path newPath = new Path(g, offSpring);
				tracePaths.add(offSpring);
				// Get energy of solutions
				int currentDistance = path.dist;
				int neighbourDistance = newPath.dist;
				// Decide if we should accept the neighbour
				double rand = randomDouble();
				if (acceptanceProbability(currentDistance, neighbourDistance, temp) > rand) {
					path = newPath;

				}
				// Keep track of the best solution found
				bestPath = evaluatePath(path, bestPath, temp);
				// Cool system
				temp *= 1 - coolingRate;

				if (timeLimit(startTime, time_sec)) {
					/* Stop program when run out of time . */
					break;
				}
			}

		}
		/* print out the best path */
		printOut("endPrint", "", bestPath, time_sec);
	}

	/**
	 * processing initial Path.
	 * 
	 * @param
	 * @return a new random path - Path
	 */
	private Path initialPath() {
		int totalNode = g.nodeList.size();
		int index_S = 0;
		int index_G = totalNode - 1;
		ArrayList<Integer> pathList = new ArrayList<>();
		for (int i = 0; i < totalNode; i++) {
			if (g.nodeList.get(i).val.equals("S")) {
				index_S = i;
			} else if (g.nodeList.get(i).val.equals("G")) {
				index_G = i;
			} else {
				pathList.add(i);
			}
		}

		Collections.shuffle(pathList, new Random());
		pathList.add(0, index_S);
		pathList.add(index_G);
		return new Path(g, pathList);
	}

	/**
	 * evaluate two Paths and select the better one
	 * 
	 * @param path     - Path - new path
	 * @param bestPath - Path - the best Path before.
	 * @param number   - Number - number of each step for print out.
	 * @return the new better - Path
	 */
	private Path evaluatePath(Path path, Path bestPath, Number number) {
		if (path.dist > 500000) {
			return bestPath;
		} else if (path.dist < bestPath.dist) {
			// print out each temperate if the program get better path.
			printOut("eachStepPrint", "", path, number);
			return new Path(path);
		}
		return bestPath;
	}

	/**
	 * calculating acceptance Probability
	 * 
	 * @param currentDistance   - int
	 * @param neighbourDistance - int
	 * @param temp              - double
	 * @return an acceptance probability - double
	 */
	private double acceptanceProbability(int currentDistance, int neighbourDistance, double temp) {
		// If the new solution is better, accept it
		if (neighbourDistance < currentDistance) {
			return 1.0;
		}
		// If the new solution is worse, calculate an acceptance probability
		return Math.exp((currentDistance - neighbourDistance) / temp);
	}

	/**
	 * calculating random Double
	 * 
	 * @param
	 * @return random number - double
	 */
	private double randomDouble() {
		Random r = new Random();
		return r.nextInt(1000) / 1000.0;
	}

	/**
	 * calculating number Step By Factory
	 * 
	 * @param size of graph - int
	 * @return max number Step - int
	 */
	private int numberStepByFactory(int grahpSize, int maxNumberStep) {
		int result = 1;
		int number = grahpSize - 2;
		if (number > 9) {
			// because the limiting of computer CPU
			// if size of graph large than 9, give the max number to 100000.
			return maxNumberStep;
		}
		// if the graph is small, just use factory n! for looping of the step.
		for (int factor = 2; factor <= number; factor++) {
			result *= factor;
		}
		return result - 1;
	}

	/**
	 * check the new offspring if have created before or not
	 * 
	 * @param offSpring  - ArrayList<Integer>
	 * @param tracePaths - ArrayList<ArrayList<Integer>>
	 * @return is New OffSpring or not - boolean
	 */
	private boolean isNewOffSpring(ArrayList<Integer> offSpring, ArrayList<ArrayList<Integer>> tracePaths) {
		for (ArrayList<Integer> offSp : tracePaths) {
			if (offSp.equals(offSpring)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * creating a new Index arrays path from random Selection from path index arrays
	 * 
	 * @param pathIndexs - ArrayList<Integer>
	 * @return new path indexs - ArrayList<Integer>
	 */
	private ArrayList<Integer> randomSelection(ArrayList<Integer> pathIndexs) {

		ArrayList<Integer> newIndexs = new ArrayList<>(pathIndexs.subList(1, pathIndexs.size() - 1));
		// using java collection shuffle function to process the random arrange to be a
		// new path.
		Collections.shuffle(newIndexs);
		newIndexs.add(0, pathIndexs.get(0));
		newIndexs.add(pathIndexs.get(pathIndexs.size() - 1));
		return newIndexs;
	}

	/**
	 * processing a crossover of two parents to create a new offspring.
	 * 
	 * @param parent1 - ArrayList<Integer>
	 * @param parent2 - ArrayList<Integer>
	 * @return offSpring - ArrayList<Integer>
	 */
	private ArrayList<Integer> crossover(ArrayList<Integer> parent1, ArrayList<Integer> parent2) {
		int size = parent1.size() - 2;
		int beginNodeIndex = parent1.get(0);
		int lastNodeIndex = parent1.get(parent1.size() - 1);
		ArrayList<Integer> offSpring = null;
		int start;
		int end;
		start = size / 3;
		end = 2 * start;
		ArrayList<Integer> parentTemp1 = new ArrayList<Integer>(parent1.subList(1, parent1.size() - 1));
		ArrayList<Integer> parentTemp2 = new ArrayList<Integer>(parent2.subList(1, parent2.size() - 1));
		offSpring = new ArrayList<>(parentTemp1.subList(start, end));
		ArrayList<Integer> temp = new ArrayList<>(parentTemp2.subList(end, parentTemp2.size()));
		temp.addAll(parentTemp2.subList(0, end));
		temp.addAll(parentTemp1.subList(0, start));
		temp.addAll(parentTemp1.subList(end, parentTemp1.size()));
		for (int i = 0; i < temp.size(); i++) {
			if (!offSpring.contains(temp.get(i)) && offSpring.size() <= parentTemp1.size()) {
				if (offSpring.size() <= parentTemp1.size() - start) {
					offSpring.add(temp.get(i));
				} else {
					offSpring.add(0, temp.get(i));
				}
			}
		}
		offSpring.add(0, beginNodeIndex);
		offSpring.add(lastNodeIndex);
		return offSpring;
	}

	/**
	 * processing a mutation of a offspring.
	 * 
	 * @param offSpring - ArrayList<Integer>
	 * @return
	 */
	private void mutation(ArrayList<Integer> offSpring) {
		Random random = new Random();
		int m1 = random.nextInt(offSpring.size() - 2) + 1;
		int m2 = random.nextInt(offSpring.size() - 2) + 1;
		int temp = offSpring.get(m1);
		offSpring.set(m1, offSpring.get(m2));
		offSpring.set(m2, temp);
	}

	/**
	 * checking time is running out or not
	 * 
	 * @param start Time - long
	 * @param Time  - int
	 * @return is running out or not - boolean
	 */
	private boolean timeLimit(long startTime, int time_sec) {
		long endTime = System.currentTimeMillis();
		if (endTime - startTime > time_sec * 1000) {

			return true;
		}
		return false;
	}

	/**
	 * print out all info
	 * 
	 * @param func   - String - indicate which one to print out
	 * @param title  - String
	 * @param path   - Path - a path to print
	 * @param number - Number - a number to print
	 * @return
	 */
	private void printOut(String func, String title, Path path, Number number) {
		if (func.equals("beginPrint")) {
			/* print out initial info. */
			String printString = title + number + " seconds: ";
			System.out.println(printString);
			output.println(printString);
			String initCycStr = "Initial cycle: " + path;
			System.out.println(initCycStr);
			output.println(initCycStr);
		} else if (func.equals("eachStepPrint")) {
			// print out each temperate if the program get better path.
			String betterCycStr = null;
			if (number.getClass().getSimpleName().equals("Double")) {
				betterCycStr = "Better cycle ( temp = " + number + " ) : " + path;
			} else if (number.getClass().getSimpleName().equals("Integer")) {
				betterCycStr = "Better cycle ( i = " + number + " ) : " + path;
			}

			System.out.println(betterCycStr);
			output.println(betterCycStr);
		} else if (func.equals("endPrint")) {
			/* print out the best path */
			String bestCycStr = "-->Best cycle: ";
			if (path.dist >= 500000) {
				// when the random algorithm can not find out the correct path, tell user to try
				// again next time.
				bestCycStr += "Unfortunately, run out of time! " + "Just try to run again! "
						+ "You may find out the best path in the next time." + "\n\n";
			} else {
				bestCycStr += path + "\n\n";
			}
			System.out.println(bestCycStr);
			output.println(bestCycStr);
		}
	}
}

/**
 * The Path class using for handle each path that we want.
 * 
 */
class Path {
	ArrayList<Node> nodes;
	ArrayList<Integer> pathIndexs;
	int dist;
	String path;

	// constructor to build a path by a graph and a path index array.
	public Path(Graph g, ArrayList<Integer> pathIndexs) {
		this.pathIndexs = pathIndexs;
		buildPathByIndexs(g, pathIndexs);
		builPathStr();
		calcPathDist();
	}

	// constructor for copy a path
	public Path(Path copyPath) {
		nodes = copyPath.nodes;
		pathIndexs = copyPath.pathIndexs;
		dist = copyPath.dist;
		path = copyPath.path;
	}

	// calculate the path total distance.
	private void calcPathDist() {
		dist = 0;
		for (int i = 0; i < nodes.size() - 1; i++) {
			dist += dist2Node(nodes.get(i), nodes.get(i + 1));
		}
	}

	// calculating distance of two node.
	private int dist2Node(Node node, Node node2) {
		for (Edge e : node.outgoingEdges) {
			if (e.head.name.equals(node2.name)) {
				return e.dist;
			}
		}
		return 500000;

	}

	// build the path string
	private void builPathStr() {
		path = "";
		for (Node n : nodes) {
			path += n.abbrev + " ";
		}

	}

	private void buildPathByIndexs(Graph g, ArrayList<Integer> pathIndexs) {
		nodes = new ArrayList<Node>(pathIndexs.size());
		for (int i = 0; i < pathIndexs.size(); i++) {
			nodes.add(g.nodeList.get(pathIndexs.get(i)));
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + dist;
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		return result;
	}

	/**
	 *
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Path other = (Path) obj;
		if (dist != other.dist)
			return false;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		return true;
	}

	// for printing out the path.
	@Override
	public String toString() {
		return path + ", dist = " + dist;
	}

}

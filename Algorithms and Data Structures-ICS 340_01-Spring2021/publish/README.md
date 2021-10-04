Nalongsone Danddank 
Student ID : 14958950 
StarID: jf3893pd 
Email: nalongsone.danddank@my.metrostate.edu 


# Advance Algorithm Project
### ICS 340_01 Spring 2021
### Algorithms and Data Structures 

### ICS 340 Programming Project, Deliverable D
Motivation
For finding a good Ham cycle path of a really large graph, it may be better to use random methods to get a good solution in a reasonable time, rather than to try to get the optimal solution by brute force, or even using Dynamic Programming (which is still time O(n2n).  For this deliverable, you will use one or more of a variety of randomized methods for finding a low-cost TSP path.
Specification:	
A Hamiltonian Cycle of a graph is a path that starts at one node of a graph, and visits every other node exactly once.  It’s like a TSP problem except that you don’t go back to the starting city.
Start with your Java program “prog340”, ideally it should be working for Deliverables A and B.
Algorithm
Start from any city you want, it really doesn’t matter which.  I start from the first city in the file because it’s easiest.  Create some Ham cycle.  Note that if you assign a very long “edge” between each pair of Nodes with no edge between them, as I suggest above, you are guaranteed to have some Ham cycle.
Using the techniques of local search, try to find a better tour.

![Figure 1-1](DelivD_screenshot1.png?raw=true)
![Figure 1-1](DelivD_screenshot2.png?raw=true)


### ICS 340 Programming Project, Deliverable C
Specification:	
Start with your (ideally working) submission of Deliverable B.  Read a file of the name “F[<whatever>]c.txt.  This is a file of distances between cities.  Note that it won’t be a complete graph, unlike the case for Deliverable B.
From the starting city (val = “S”) use depth first search with iterative deepening to find the nearest goal city (val = “G”).  Start with a depth bound of 1, and increase the depth bound by 1 each time through the graph.  Print out your results for each depth bound that causes a change over the previous depth bound.  
While doing the DFS, if you have a choice of two nodes to visit, visit the nearer node first.  In case of a tie, break ties alphabetically.
When your program finishes, print the path to the nearest goal node, and its distance.
The “prog340” handout describes the format of the input file for this and all program deliverables.
As will always be the case in this class, the program must be written in Java and must run on the University Windows computer systems.  To ensure this I strongly recommend that you:
1.Use only Oracle Java 13 SE and earlier constructs, and 
2.Test it on the University systems before submission if you have any doubts about its ability to run on the University Windows.  
Submit the Java source code to the open Deliverable A submission folder. You may submit either the source code or a full Eclipse package.
Algorithm:
The iterative deepening algorithm with depth first search is well described in the notes and in the Poole text, Chapter 3.

Program C Output 1:

    ~   val   A   B   C   D   E 
    A     ~   ~   3   2   ~   5
    B     S   4   ~   5   3   ~
    C     ~   9   ~   ~   ~   ~
    D     ~   6   ~   5   ~   1
    E     G   ~   6   4   2   ~

![Figure 1-1](Picture1.png?raw=true)


Program C Output3:

    ~          val    A    B    C    T    D    L    X    M    N    F    S    W
    Atlanta      ~    ~    ~  581  725    ~    ~  608    ~    ~    ~    ~  538
    Boston       G    ~    ~  859    ~    ~    ~    ~ 1127  207    ~    ~    ~
    Chicago      ~  581  859    ~  803  928    ~    ~  354  718    ~    ~  601
    Dallas       ~  725    ~  803    ~  667 1248 1116    ~    ~    ~    ~    ~
    Denver       ~    ~    ~  928  667    ~  812    ~  697    ~  957 1033    ~
    LosAngeles   S    ~    ~    ~ 1248  812    ~    ~    ~    ~  313    ~    ~
    Miami        ~  608    ~    ~ 1116    ~    ~    ~    ~    ~    ~    ~    ~
    Minneapolis  ~    ~ 1127  354    ~  697    ~    ~    ~    ~    ~ 1415    ~
    NewYork      ~    ~  207  718    ~    ~    ~    ~    ~    ~    ~    ~  213
    SanFrancisco ~    ~    ~    ~    ~  957  313    ~    ~    ~    ~  691    ~
    Seattle      ~    ~    ~    ~    ~ 1033    ~    ~ 1415    ~  691    ~    ~
    Washington   ~  538    ~  601    ~    ~    ~    ~    ~  213    ~    ~    ~

![Figure 1-1](Picture2.png?raw=true)

### ICS 340 Programming Project, Deliverable B
Specification:	
Start with your (ideally working) submission of Deliverable A.  Read a file of the name “F[<whatever>]b.txt.  This is a file of distances between cities.  It’s a symmetrical complete graph; there is a distance listed between each pair of cities, and the distance from city A to city B is the same as the distance from city B to city A. Enhance this program to find the shortest bitonic tour of the cities.
A bitonic tour of cities traditionally starts at the westernmost city, goes strictly east to the easternmost city (passing through zero or more intermediate cities on the way) and returns to the westernmost city, picking up any cities not visited on the way out, going strictly east to west.  Of course, a bitonic tour could also start at the southernmost, easternmost, or northernmost city.  
The “val” field lists a floating point number that indicates either the latitude or longitude of the city.  Start at the city with the highest number, go by decreasing numbers to the city with the lowest number, and then go back by increasing numbers through all unvisited cities to the city with the highest number.  (This will turn out to be going from westeastwest or northsouthnorth, depending on the file.)
Output the distance of the shortest bitonic tour, and the order of cities in the tour.  Note that most of the points will be for outputting the correct distance, and fewer points will be for the harder problem of listing the cities.  Note that in each case there are two shortest tours, and they are the mirror images of each other.  Either is worth full credit.
The “prog340” handout describes the format of the input file for this and all program deliverables.
As will always be the case in this class, the program must be written in Java and must run on the University Windows computer systems.  To ensure this I strongly recommend that you:
1.Use only Oracle Java 13 SE and earlier constructs, and 
2.Test it on the University systems before submission if you have any doubts about its ability to run on the University Windows.  
Submit the Java source code to the open Deliverable A submission folder. You may submit either the source code or a full Eclipse package.
Algorithm:
The algorithm for the bitonic tour is well known.  I have uploaded two files that describe the algorithm.  They are the PDFs “bitonic_tsp” and “BitonicTour Paper”.  You may use either one of them as the basis for your building of the algorithm.


![Figure 1-1](DelivB_screenshot1.png?raw=true)

![Figure 1-1](DelivB_screenshot2.png?raw=true)

![Figure 1-1](DelivB_screenshot3.png?raw=true)

# Triangular-Mesh-Analysis
Data Structures &amp; Algorithms Course, IIT Delhi
# Problem Statement: http://web.iitd.ac.in/~csz188551/COL106_2019/assignment6/index.html

# Report for my implementation:
Point Class:
stores all the three coordinates and triangles incident on the point.
Edge Class:
stores both the endpoints and triangles incident.
Triangle class:
stores all the three endpoints.

Shape :
I have used two graphs one is of Points and Edges, and other one with Triangles and Edges. And one arraylist of all Edges.
For Graphs I have used ajacency list implementation.

1.) Add Triangle: I am creating new objects of points and edges, and then checking if they already exist in the Shape. If they don't exist I create new points and edges and add them to respective data structures. Then triangle is created and then the triangle is added to the triangles list of edge and point. And then from respective edges I can find the neighbours of this new triangle. And create new GraphEdges.
Time Complexity:= O(n+e+d(e1)+d(e2)+d(e3)) where n->no. of points, 	e->no. of edges, d(ei) is number of triangles incident on ith edge.

2.)Type_MESH:= In add triangle whenever a triangle is added to an edgeI update the three counters count1(no. of edges with 1 incident triangle), count2(no. of edges with 2 incident triangle), count3(no. of edges with more than or equal to 3 incident triangle). And the in type_mesh returnng the answer.
Time Complexity: O(1).

3.)Count_Connected_components(): Using DFS.
Time Complexity O(n+m) n-> number of nodes in Graph of triangles and e is respectives number of edges.

4.) Boundary_Edges : Traversed the list of all edges and checked for edges which are part of more than 2 triangles.
Time Complexity O(no of edges)

5,6,7.)NEIGHBORS_OF_TRIANGLE, EDGE_NEIGHBOR_TRIANGLE, 	VERTEX_NEIGHBOR_TRIANGLE: Found the triangle from the graph data structure and answeres respective queries.
Time Complexity is O(n) n is number of nodes in triangle graph. Additionally number of neighbours to convert arraylist to array for triangle neighbor.

8,9.)INCIDENT_TRIANGLES, FACE_NEIGHBORS_OF_POINT: Found the point from the point Data Structures. O(n+n1) n-> no. of points  n1-> no. of triangles on that point.

10,11.) EDGE_NEIGHBORS_OF_POINT : Found the point from the point graph data structure, and eturned the adjacency list O(n+d(v)) n-> no.of points d(v) is degree of the node.

12.) IS_CONNECTED : DFS.

13.)TRIANGLE_NEIGHBOR_OF_EDGE: Searched for the edge in the all edges list and returned the stored triangles in that list.  O(n+n1) n-> no. of edges  n1-> no. of triangles on that edge.

14.)MAXIMUM_DIAMETER : repeated bfs from every node .
O(n(n+m)) n is no. of nodes and m is no. of edges in triangle graph

15.)EXTENDED_NEIGHBOR_TRIANGLE; Searched the triangle in the list and from the corresponding points returned the triangles arraylist after sorting.

16,17.)CENTROID, Centroid of component: After doing dfs once stored the components in which each point lies and then found the centroid of each component.O(n+m+x) where n and m is no. of nodes and edges in the graph of triangles and x is no. of points.

18.) Closest Component : firstly did dfs and stored which point belongs to which components and then if any point belongs to more than two components it is the answer with distance zero otherwise searched for minimum distance withn different components. Time Complexity O(n+m+x*x) where n and m is no. of nodes and edges in the graph of triangles and x is no. of points.


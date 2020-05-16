package util;

import java.util.ArrayList;

public interface InterfaceGraph<G, T> {
	
	//Add
	void addVertex(T node);
	void addEdge(T nodeF, T nodeC, int weight);
	//Remove
	void removeVertex(T node);
	void removeEdge(T nodeF, T nodeC, int weight);
	//Method
	G bfs(T node);
	G dfs();
	G prim(T node);
	G kruskal();
	ArrayList<SearchNode<T>> dijkstra(T node);
	int[][] floydWarshall();
	
}

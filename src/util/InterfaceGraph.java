package util;

import java.util.ArrayList;

public interface InterfaceGraph<G, T, E> {
	
	//Add
	void addVertex(T node);
	void addEdge(T nodeF, T nodeC, int weight);
	//Remove
	void removeVertex(T node);
	void removeEdge(T nodeF, T nodeC, int weight);
	//Element
	ArrayList<T> getVertices();
	T getVertex(int pos);
	ArrayList<ArrayList<E>> getEdges();
	E getEdge(T nodeF, T nodeC);
	//Method
	G bfs(T node);
	G dfs();
	G prim(T node);
	G kruskal();
	ArrayList<SearchNode<T>> dijkstra(T node);
	int[][] floydWarshall();
	
}

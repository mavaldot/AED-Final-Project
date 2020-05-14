package util;

import java.util.ArrayList;

import exception.LoopException;
import exception.MultipleEdgesException;
import exception.NodeNotFoundException;
import util.PriorityQueue.Priority;

public class MatrixGraph<T> {

	//Attributes
	public boolean directed;
	private boolean multipleEdges;
	private boolean loop;
	
	private ArrayList<T> vertices;
	private ArrayList<ArrayList<ArrayList<Integer>>> edges;
	private int time;
	private int sets;
	
	//Constructor
	public MatrixGraph(boolean directed, boolean multipleEdges, boolean loop) {
		
		this.directed=directed;
		this.multipleEdges=multipleEdges;
		this.loop=loop;
		
		this.time = 0;
		this.sets = 0;
		this.vertices = new ArrayList<T>();
		this.edges = new ArrayList<ArrayList<ArrayList<Integer>>>();
		
	}
	
	//Methods
	//Add
	public void addVertex(T node) {
		
		vertices.add(node);
		
		edges.add(new ArrayList<ArrayList<Integer>>());
		
		for (int i = 0; i < edges.size(); i++){
			edges.get(i).add(new ArrayList<Integer>());
			if (i!=(edges.size() - 1)){
				edges.get(edges.size() - 1).add(new ArrayList<Integer>());
			}
		}
	}
	
	public void addEdge(T nodeF, T nodeC, int weight) {
		
		int row = vertices.indexOf(nodeF);
		int column = vertices.indexOf(nodeC);
		
		if((row == -1) || (column == -1)){
			throw new NodeNotFoundException();
		}
		else if((!loop) && (row == column)){
			throw new LoopException();
		}
		else{
			ArrayList<Integer> pos = edges.get(row).get(column);
			
			if((!multipleEdges) && (pos.size() >= 1)){
				throw new MultipleEdgesException();
			}
			else {
				edges.get(row).get(column).add(weight);
				if((!directed) && !((loop) && (row == column))){
					edges.get(column).get(row).add(weight);
				}
			}
		}
		
	}
	
	//Remove
	public void removeVertex(T node){
		
		int index = vertices.indexOf(node);
		
		if(index != -1) {
			vertices.remove(index);
			
			edges.remove(index);
			edges.forEach((n) -> n.remove(index));
		}
		else {
			throw new NodeNotFoundException();
		}
		
	}
	
	public void removeEdge(T nodeF, T nodeC, int weight){
		
		int row = vertices.indexOf(nodeF);
		int column = vertices.indexOf(nodeC);
		
		if((row == -1) || (column == -1)){
			throw new NodeNotFoundException();
		}
		else {
			boolean run = true;
			
			for(int i = 0; (i < edges.get(row).get(column).size()) && run; i++){
				
				if(edges.get(row).get(column).get(i).intValue() == weight){
					edges.get(row).get(column).remove(i);
					if((!directed) && (row != column)){
						edges.get(column).get(row).remove(i);
					}
					run = false;
				}
				
			}
		}
		
	}
	
	//Pro
	public MatrixGraph<SearchNode<T>> bfs(T node) {
		MatrixGraph<SearchNode<T>> tree=new MatrixGraph<SearchNode<T>>(false, false, false);
		for(T vertex: vertices) {
			tree.addVertex(new SearchNode<T>(vertex));
		}
		
		int index = vertices.indexOf(node);
		tree.getVertex(index).setColor(SearchNode.GRAY);
		tree.getVertex(index).setDistance(0);
		tree.getVertex(index).setAncestor(null);
		
		Queue<SearchNode<T>> queue = new Queue<SearchNode<T>>();
		queue.enqueue(tree.getVertex(index));
		
		while(!queue.isEmpty()) {
			
			SearchNode<T> element = queue.dequeue();
			int eIndex = vertices.indexOf(element.getObject());
			ArrayList<ArrayList<Integer>> eEdges=edges.get(eIndex);
			
			for(int i = 0; i < eEdges.size(); i++){
				
				if((eEdges.get(i).size() != 0) && (eIndex != i)){
					
					SearchNode<T> cElement = tree.getVertex(i);
					
					if(cElement.getColor() == SearchNode.WHITE) {
						
						int weight = Integer.MAX_VALUE;
						for(int j = 0; j < eEdges.get(i).size(); j++){
							
							if(eEdges.get(i).get(j) < weight){
								weight = eEdges.get(i).get(j);
							}
							
						}
						
						tree.addEdge(element, cElement, weight);
						
						cElement.setColor(SearchNode.BLACK);
						cElement.setDistance(element.getDistance() + 1);//Es con el peso o solo sumo 1
						cElement.setAncestor(element);
						queue.enqueue(cElement);
						
					}
					
				}
			}
			
			element.setColor(SearchNode.BLACK);
			
		}
		
		return tree;
	}
	
	public MatrixGraph<SearchNode<T>> dfs() {
		
		MatrixGraph<SearchNode<T>> forest = new MatrixGraph<SearchNode<T>>(false, false, false);
		
		for(T v : vertices) {
			forest.addVertex(new SearchNode<T>(v));
		}
		
		for(SearchNode<T> v : forest.getVertices()) {
			if(v.getColor() == SearchNode.WHITE)
				dfsAux(forest, v);
		}
		
		return forest;	
	}
	
	private void dfsAux(MatrixGraph<SearchNode<T>> forest, SearchNode<T> node) {
		
		forest.setTime(forest.getTime()+1);
		node.setDTimestamps(forest.getTime());
		node.setColor(SearchNode.GRAY);
		
		int index = vertices.indexOf(node.getObject());
		ArrayList<ArrayList<Integer>> nodeEdges = edges.get(index);
		
		for(int i = 0; i < nodeEdges.size(); i++) {
			
			if(nodeEdges.get(i).size() != 0 && i != index) {
				
				SearchNode<T> e = forest.getVertex(i);
				
				if(e.getColor() == SearchNode.WHITE) {
					
					int weight = Integer.MAX_VALUE;
					for(int j = 0; j < nodeEdges.get(i).size(); j++) {
						
						if(nodeEdges.get(i).get(j) < weight) {
							weight = nodeEdges.get(i).get(j);
						}		
					}
					
					forest.addEdge(node, e, weight);
					e.setAncestor(node);
					dfsAux(forest, e);
				}
			}
		}
		
		node.setColor(SearchNode.BLACK);
		forest.setTime(forest.getTime()+1);
		node.setFTimestamps(forest.getTime());
	}
	
	public MatrixGraph<SearchNode<T>> prim(T node) {
		MatrixGraph<SearchNode<T>> tree=new MatrixGraph<SearchNode<T>>(false, false, false);
		for(T vertex: vertices) {
			tree.addVertex(new SearchNode<T>(vertex));
		}
		
		int index = vertices.indexOf(node);
		tree.getVertex(index).setDistance(0);
		
		PriorityQueue<SearchNode<T>> queue=new PriorityQueue<SearchNode<T>>(Priority.MIN);
		for(SearchNode<T> vertex: tree.getVertices()) {
			queue.enqueue(vertex, vertex.getDistance());
		}
		
		while(!queue.isEmpty()){
			SearchNode<T> element = queue.dequeue();
			
			int eIndex = vertices.indexOf(element.getObject());
			
			for(int i = 0; i < edges.get(eIndex).size(); i++){
				if(edges.get(eIndex).get(i).size() != 0){
					
					int edge = Integer.MAX_VALUE;
					for(Integer tEdge :edges.get(eIndex).get(i)){
						if(tEdge < edge){
							edge = tEdge;
						}
					}
					
					if((tree.getVertex(i).getColor() == SearchNode.WHITE) && (edge < tree.getVertex(i).getDistance())){
						tree.getVertex(i).setDistance(edge);
						queue.decreaseKey(tree.getVertex(i), edge);
						tree.getVertex(i).setAncestor(element);
						for(int j = 0; j < tree.getEdges().get(i).size(); j++) {
							tree.getEdges().get(i).get(j).clear();
							tree.getEdges().get(j).get(i).clear();
						}
						tree.addEdge(element, tree.getVertex(i), edge);
					}
					
				}
			}
			
			element.setColor(SearchNode.BLACK);
			
		}
		
		return tree;
	}
	
	public MatrixGraph<SearchNode<T>> kruskal() {
		
		MatrixGraph<SearchNode<T>> tree = new MatrixGraph<SearchNode<T>>(false, false, false);
		DisjointSet<T> sets = new DisjointSet<>();
		
		for(T v : vertices) {
			sets.makeSet(v);
			tree.addVertex(new SearchNode<T>(v));
		}
		
		PriorityQueue<Tuple<SearchNode<T>, SearchNode<T>>> queue = new PriorityQueue<Tuple<SearchNode<T>, SearchNode<T>>>(Priority.MIN);
		
		for(int i = 0; i < edges.size(); i++) {
			for(int j = i; j < edges.get(0).size(); j++) {
				
				if(edges.get(i).get(j).size() > 0) {
					
					int edge = Integer.MAX_VALUE;
					for(Integer tEdge :edges.get(i).get(j)){
						if(tEdge < edge){
							edge = tEdge;
						}
					}
					
					queue.enqueue(new Tuple<SearchNode<T>, SearchNode<T>>(tree.getVertex(i), tree.getVertex(j)), edge);
				}
			}
		}
		while(!queue.isEmpty()) {
			
			int weight = queue.peekPriority();
			Tuple<SearchNode<T>, SearchNode<T>> edge = queue.dequeue();
			
			if(sets.union(edge.getVal1().getObject(), edge.getVal2().getObject())) {

				tree.addEdge(edge.getVal1(), edge.getVal2(), weight);
			}
		}
		tree.setSets(sets.getSets());
		return tree;
	}
	
	public ArrayList<SearchNode<T>> dijkstra(T node){
		ArrayList<SearchNode<T>> list = new ArrayList<SearchNode<T>>();
		for(T vertex : vertices){
			list.add(new SearchNode<T>(vertex));
		}
		
		int index = vertices.indexOf(node);
		
		if(index != -1){
			
			list.get(index).setDistance(0);
			
			PriorityQueue<SearchNode<T>> queue = new PriorityQueue<SearchNode<T>>(Priority.MIN);
			for(SearchNode<T> vertex : list){
				queue.enqueue(vertex, vertex.getDistance());
			}
			
			while(!queue.isEmpty()) {
				SearchNode<T> actualVertex = queue.dequeue();
				int actualIndex = vertices.indexOf(actualVertex.getObject());
				
				for(int i = 0; i < edges.get(actualIndex).size(); i++){
					if(edges.get(actualIndex).get(i).size() > 0){
						
						int edge = Integer.MAX_VALUE;
						for(Integer tEdge :edges.get(actualIndex).get(i)){
							if(tEdge < edge){
								edge = tEdge;
							}
						}
						
						int testDistance = actualVertex.getDistance() + edge;
						if(testDistance < list.get(i).getDistance()){
							list.get(i).setDistance(testDistance);
							queue.decreaseKey(list.get(i), testDistance);
							
							list.get(i).setAncestor(actualVertex);
						}
						
						
					}
				}
				
			}
			
		}
		else{
			//Error
		}
		
		return list;
	}
	
	public int[][] floydWarshall(){
		int[][] distances = new int[vertices.size()][vertices.size()];
		
		for(int i = 0; i < distances.length; i++) {
			for(int j = 0; j < distances.length; j++){
				
				if(i == j){
					distances[i][j] = 0;
				}
				else{
					if(edges.get(i).get(j).size() != 0){
						
						int min = Integer.MAX_VALUE;
						for(int value : edges.get(i).get(j)) {
							if(value < min) {
								min = value;
							}
						}
						distances[i][j] = min;
						
					}
					else {
						distances[i][j] = Integer.MAX_VALUE;
					}
				}
				
			}
		}
		
		for(int k = 0; k < distances.length; k++) {
			
			for(int i = 0; i < distances.length; i++) {
				for(int j = 0; j < distances.length; j++) {
					
					if((distances[i][k] != Integer.MAX_VALUE) && (distances[k][j] != Integer.MAX_VALUE)) {
						if((distances[i][k] + distances[k][j]) < distances[i][j]){
							distances[i][j] = (distances[i][k] + distances[k][j]);
						}
					}
					
				}
			}
			
		}
		
		return distances;
	}
	
	//Set
	public void setTime(int time) {
		this.time = time;
	}
	
	//Get
	public boolean getDirected() {
		return directed;
	}
	
	public int getSets() {
		return sets;
	}
	
	public void setSets(int sets) {
		this.sets = sets;
	}
	
	public boolean getMultipleEdges() {
		return multipleEdges;
	}
	
	public boolean getLoop() {
		return loop;
	}
	
	public T getVertex(int index){
		return vertices.get(index);
	}
	
	public ArrayList<Integer> getEdge(int row, int column) {
		return edges.get(row).get(column);
	}
	
	public ArrayList<Integer> getEdge(T nodeF, T nodeC) {
		int row = vertices.indexOf(nodeF);
		int column = vertices.indexOf(nodeC);
		
		return edges.get(row).get(column);
	}
	
	public ArrayList<T> getVertices() {
		return vertices;
	}

	public ArrayList<ArrayList<ArrayList<Integer>>> getEdges() {
		return edges;
	}
	
	public int getTime() {
		return time;
	}
}

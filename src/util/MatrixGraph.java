package util;

import java.util.ArrayList;

import exception.LoopException;
import exception.MultipleEdgesException;
import exception.NodeNotFoundException;
import util.PriorityQueue.Priority;

public class MatrixGraph<T> {

	private ArrayList<T> nodes;
	private ArrayList<ArrayList<ArrayList<Integer>>> edges;
	public boolean directed;
	private boolean multipleEdges;
	private boolean loop;
	private int time;
	
	public MatrixGraph(boolean directed, boolean multipleEdges, boolean loop) {
		
		this.directed=directed;
		this.multipleEdges=multipleEdges;
		this.loop=loop;
		
		time = 0;
		nodes = new ArrayList<T>();
		edges = new ArrayList<ArrayList<ArrayList<Integer>>>();
		
	}
	
	public void addNode(T node) {
		
		nodes.add(node);
		
		edges.add(new ArrayList<ArrayList<Integer>>());
		
		for (int i = 0; i < edges.size(); i++){
			edges.get(i).add(new ArrayList<Integer>());
			if (i!=(edges.size() - 1)){
				edges.get(edges.size() - 1).add(new ArrayList<Integer>());
			}
		}
	}
	
	public void addEdge(T nodeF, T nodeC, int weight) {
		
		int row = nodes.indexOf(nodeF);
		int column = nodes.indexOf(nodeC);
		
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
	
	public void removeNode(T node){
		
		int index = nodes.indexOf(node);
		
		if(index != -1) {
			nodes.remove(index);
			
			edges.remove(index);
			edges.forEach((n) -> n.remove(index));
		}
		else {
			throw new NodeNotFoundException();
		}
		
	}
	
	public void removeEdge(T nodeF, T nodeC, int weight){
		
		int row = nodes.indexOf(nodeF);
		int column = nodes.indexOf(nodeC);
		
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
	
	public MatrixGraph<SearchNode<T>> bfs(T node) {
		MatrixGraph<SearchNode<T>> tree=new MatrixGraph<SearchNode<T>>(false, false, false);
		for(T vertex: nodes) {
			tree.addNode(new SearchNode<T>(vertex));
		}
		
		int index = nodes.indexOf(node);
		tree.getNode(index).setColor(SearchNode.GRAY);
		tree.getNode(index).setDistance(0);
		tree.getNode(index).setAncestor(null);
		
		Queue<SearchNode<T>> queue = new Queue<SearchNode<T>>();
		queue.enqueue(tree.getNode(index));
		
		while(!queue.isEmpty()) {
			
			SearchNode<T> element = queue.dequeue();
			int eIndex = nodes.indexOf(element.getObject());
			ArrayList<ArrayList<Integer>> eEdges=edges.get(eIndex);
			
			for(int i = 0; i < eEdges.size(); i++){
				
				if((eEdges.get(i).size() != 0) && (eIndex != i)){
					
					SearchNode<T> cElement = tree.getNode(i);
					
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
	
	public MatrixGraph<SearchNode<T>> DFS() {
		
		MatrixGraph<SearchNode<T>> forest = new MatrixGraph<SearchNode<T>>(false, false, false);
		
		for(T v : nodes) {
			forest.addNode(new SearchNode<T>(v));
		}
		
		for(SearchNode<T> v : forest.getNodes()) {
			if(v.getColor() == SearchNode.WHITE)
				DFSAux(forest, v);
		}
		
		return forest;	
	}
	
	private void DFSAux(MatrixGraph<SearchNode<T>> forest, SearchNode<T> node) {
		
		forest.setTime(forest.getTime()+1);
		node.setDTimestamps(forest.getTime());
		node.setColor(SearchNode.GRAY);
		
		int index = nodes.indexOf(node.getObject());
		ArrayList<ArrayList<Integer>> nodeEdges = edges.get(index);
		
		for(int i = 0; i < nodeEdges.size(); i++) {
			
			if(nodeEdges.get(i).size() != 0 && i != index) {
				
				SearchNode<T> e = forest.getNode(i);
				
				if(e.getColor() == SearchNode.WHITE) {
					
					int weight = Integer.MAX_VALUE;
					for(int j = 0; j < nodeEdges.get(i).size(); j++) {
						
						if(nodeEdges.get(i).get(j) < weight) {
							weight = nodeEdges.get(i).get(j);
						}		
					}
					
					forest.addEdge(node, e, weight);
					e.setAncestor(node);
					DFSAux(forest, e);
				}
			}
		}
		
		node.setColor(SearchNode.BLACK);
		forest.setTime(forest.getTime()+1);
		node.setFTimestamps(forest.getTime());
	}
	
	public MatrixGraph<SearchNode<T>> prim(T node) {
		MatrixGraph<SearchNode<T>> tree=new MatrixGraph<SearchNode<T>>(false, false, false);
		for(T vertex: nodes) {
			tree.addNode(new SearchNode<T>(vertex));
		}
		
		int index = nodes.indexOf(node);
		tree.getNode(index).setDistance(0);
		
		PriorityQueue<SearchNode<T>> queue=new PriorityQueue<SearchNode<T>>(Priority.MIN);
		for(SearchNode<T> vertex: tree.getNodes()) {
			queue.enqueue(vertex, vertex.getDistance());
		}
		
		while(!queue.isEmpty()){
			SearchNode<T> element = queue.dequeue();
			
			int eIndex = nodes.indexOf(element.getObject());
			
			for(int i = 0; i < edges.get(eIndex).size(); i++){
				if(edges.get(eIndex).get(i).size() != 0){
					
					int edge = Integer.MAX_VALUE;
					for(Integer tEdge :edges.get(eIndex).get(i)){
						if(tEdge < edge){
							edge = tEdge;
						}
					}
					
					if((tree.getNode(i).getColor() == SearchNode.WHITE) && (edge < tree.getNode(i).getDistance())){
						tree.getNode(i).setDistance(edge);
						queue.decreaseKey(tree.getNode(i), edge);
						tree.getNode(i).setAncestor(element);
						for(int j = 0; j < tree.getEdges().get(i).size(); j++) {
							tree.getEdges().get(i).get(j).clear();
							tree.getEdges().get(j).get(i).clear();
						}
						tree.addEdge(element, tree.getNode(i), edge);
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
		
		for(T v : nodes) {
			sets.makeSet(v);
			tree.addNode(new SearchNode<T>(v));
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
					
					queue.enqueue(new Tuple<SearchNode<T>, SearchNode<T>>(tree.getNode(i), tree.getNode(j)), edge);
				}
			}
		}
		while(!queue.isEmpty()) {
			
			int weight = queue.peekPriory();
			Tuple<SearchNode<T>, SearchNode<T>> edge = queue.dequeue();
			
			if(sets.union(edge.getVal1().getObject(), edge.getVal2().getObject())) {

				tree.addEdge(edge.getVal1(), edge.getVal2(), weight);
			}
		}
		
		return tree;
	}
	
	public T getNode(int index){
		return nodes.get(index);
	}
	
	public ArrayList<Integer> getEdge(int row, int column) {
		return edges.get(row).get(column);
	}
	
	public ArrayList<T> getNodes() {
		return nodes;
	}

	public ArrayList<ArrayList<ArrayList<Integer>>> getEdges() {
		return edges;
	}
	
	public void setTime(int time) {
		this.time = time;
	}
	
	public int getTime() {
		return time;
	}
}

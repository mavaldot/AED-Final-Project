package model;

import java.util.ArrayList;

public class Graph<T> {

	private ArrayList<T> nodes;
	private ArrayList<ArrayList<ArrayList<Integer>>> edges;
	public boolean directed;
	private boolean multipleEdges;
	private boolean loop;
	
	public Graph(boolean directed, boolean multipleEdges, boolean loop) {
		
		this.directed=directed;
		this.multipleEdges=multipleEdges;
		this.loop=loop;
		
		nodes = new ArrayList<T>();
		edges = new ArrayList<ArrayList<ArrayList<Integer>>>();
		
	}
	
	public void addNode(T node) {
		
		nodes.add(node);
		
		ArrayList<ArrayList<Integer>> listNewNode = new ArrayList<ArrayList<Integer>>();
		listNewNode.add(new ArrayList<Integer>());
		edges.add(listNewNode);
		
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
		
		if((!loop) && (row == column)){
			//Excep LOOP
		}
		else{
			ArrayList<Integer> pos = edges.get(row).get(column);
			
			if((!multipleEdges) && (pos.size() >= 1)){
				//Excep MULTIPLE
			}
			else {
				edges.get(row).get(column).add(weight);
				if(!directed){
					edges.get(column).get(row).add(weight);
				}
			}
		}
		
	}
	
	public void removeNode(T node){
		
		int index = nodes.indexOf(node);
		
		nodes.remove(index);
		
		edges.remove(index);
		edges.forEach((n) -> n.remove(index));
		
	}
	
	public void removeEdge(T nodeF, T nodeC, int weight){
		
		int row = nodes.indexOf(nodeF);
		int column = nodes.indexOf(nodeC);
		
		for(int i = 0; i < edges.get(row).get(column).size(); i++){
			
			if(edges.get(row).get(column).get(i).intValue() == weight){
				edges.get(row).get(column).remove(i);
				if(!directed){
					edges.get(column).get(row).remove(i);
				}
			}
			
		}
		
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
}

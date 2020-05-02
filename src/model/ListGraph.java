package model;

import java.util.ArrayList;

import exception.LoopException;
import exception.MultipleEdgesException;
import exception.NodeNotFoundException;



public class ListGraph<T>{
	
	private ArrayList<T> nodes;
	private ArrayList<ArrayList<Tuple<Integer, Integer>>> edges;
	private boolean direction;
	private boolean multiple;
	private boolean loop;
	
	public ListGraph(boolean dir, boolean multi, boolean lp) {
		
		direction = dir;
		multiple = multi;
		loop = lp;
		
	}
	
	public void addNode(T node) {
		nodes.add(node);
		edges.add(new ArrayList<>());
		
	}
	
	// Adds an edge from node1 to node2
	public void addEdge(T node1, T node2) throws LoopException, NodeNotFoundException {
		
		int index1 = nodes.indexOf(node1);
		int index2 = nodes.indexOf(node2);
		
		if (!loop && index1 == index2) throw new LoopException("ERROR: Cannot add a loop in this graph");
		
		if (index1 == -1) throw new NodeNotFoundException("Could not find node #1");
		if (index2 == -1) throw new NodeNotFoundException("Could not find node #2");
		
		if (!multiple) {
			ArrayList<Tuple<Integer, Integer>> list = edges.get(index1);
			
			list.forEach(tuple -> {
				if (tuple.getVal1() == index2) {
					throw new MultipleEdgesException("ERROR: Multiple edges");
				}
			});
		}
		
		edges.get(index1).add(new Tuple<Integer, Integer>(index2, 1));
		
		// If there is no direction then it also adds an edge from node2 to node1
		if (!direction) {
			
			if (!multiple) {
				ArrayList<Tuple<Integer, Integer>> list = edges.get(index2);
				
				list.forEach(tuple -> {
					if (tuple.getVal1() == index1) {
						throw new MultipleEdgesException("ERROR: Multiple edges");
					}
				});
			}
			
			edges.get(index2).add(new Tuple<Integer, Integer>(index1, 1));
		}
		
	}
	
	// Same as the last one but takes the weight of the edge into account
	public void addEdge(T node1, T node2, int weight) throws LoopException, NodeNotFoundException {
		
		int index1 = nodes.indexOf(node1);
		int index2 = nodes.indexOf(node2);
		
		if (!loop && index1 == index2) throw new LoopException("ERROR: Cannot add a loop in this graph");
		
		if (index1 == -1) throw new NodeNotFoundException("Could not find node #1");
		if (index2 == -1) throw new NodeNotFoundException("Could not find node #2");
		
		
		if (!multiple) {
			ArrayList<Tuple<Integer, Integer>> list = edges.get(index1);
			
			list.forEach(tuple -> {
				if (tuple.getVal1() == index2) {
					throw new MultipleEdgesException("ERROR: Multiple edges");
				}
			});
		}
		
		edges.get(index1).add(new Tuple<Integer, Integer>(index2, weight));
		
		// If there is no direction then I must also add an edge from node2 to node1
		if (!direction) {
			
			if (!multiple) {
				ArrayList<Tuple<Integer, Integer>> list = edges.get(index2);
				
				list.forEach(tuple -> {
					if (tuple.getVal1() == index1) {
						throw new MultipleEdgesException("ERROR: Multiple edges");
					}
				});
			}
			
			edges.get(index2).add(new Tuple<Integer, Integer>(index1, weight));
		}
		
	}
	
	public void removeNode(T node) throws NodeNotFoundException {
		
		int index = nodes.indexOf(node);
		
		if (index == -1) throw new NodeNotFoundException("Could not find the node");
		
		nodes.remove(index);
		edges.remove(index);
	}
	
	
	
}

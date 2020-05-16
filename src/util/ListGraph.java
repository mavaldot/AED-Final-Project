package util;
import java.util.ArrayList;
import exception.LoopException;
import exception.MultipleEdgesException;
import exception.NodeNotFoundException;

public class ListGraph<T> implements InterfaceGraph<ListGraph<SearchNode<T>>, T>{
	
	private ArrayList<T> nodes;
	private ArrayList<ArrayList<Tuple<Integer, Integer>>> edges;
	private boolean direction;
	private boolean multiple;
	private boolean loop;
	//Aux
	private int time;
	
	public ListGraph(boolean dir, boolean multi, boolean lp) {
		
		direction = dir;
		multiple = multi;
		loop = lp;
		nodes = new ArrayList<>();
		edges = new ArrayList<>();
		
	}
	
	public void addVertex(T node) {
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
		if (!direction && index1 != index2) {
			
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
	
//	public void removeNode(T node) throws NodeNotFoundException {
//		
//		int index = nodes.indexOf(node);
//		
//		if (index == -1) throw new NodeNotFoundException("Could not find the node");
//		
//		nodes.remove(index);
//		edges.remove(index);
//	}
	
	public void removeVertex(T node){

        int index = nodes.indexOf(node);

        nodes.remove(index);
        edges.remove(index);

        for (int i = 0; i < edges.size(); i++) {

            for (int j = 0; j < edges.get(i).size(); j++) {

                if (edges.get(i).get(j).getVal1() > index) {
                    edges.get(i).get(j).setVal1(edges.get(i).get(j).getVal1() - 1);
                }
                else if (edges.get(i).get(j).getVal1().equals(index)) {
                    edges.get(i).remove(j);
                    if (j < edges.get(i).size()) {
                        j--;
                    }
                }
            }

        }

    }
	
	public void removeEdge(T node1, T node2, int weight) {//Revisar[MATEO]
		
		boolean deleted = false;
		
		int index1 = nodes.indexOf(node1);
		int index2 = nodes.indexOf(node2);
		
		if (index1 == -1) throw new NodeNotFoundException("Could not find node #1");
		if (index2 == -1) throw new NodeNotFoundException("Could not find node #2");
		
		ArrayList<Tuple<Integer, Integer>> list1 = edges.get(index1);
		
		for (int i = 0; i < list1.size() && !deleted; i++) {
			
			if (list1.get(i).getVal1().equals(index2)) {
				list1.remove(i);
				deleted = true;
			}
			
		}
		
		if (!direction) {
			
			boolean deleted2 = false;
			
			ArrayList<Tuple<Integer, Integer>> list2 = edges.get(index2);
			
			for (int i = 0; i < list2.size() && !deleted2; i++) {
				
				if (list2.get(i).getVal1().equals(index1)) {
					list2.remove(i);
					deleted2 = true;
				}
			}
			
		}
		
	}
	
	public T getNode(int index) {
		
		return nodes.get(index);
		
	}
	
	public Tuple<Integer, Integer> getEdge(T node1, T node2) {
		
		int index1 = nodes.indexOf(node1);
		int index2 = nodes.indexOf(node2);
		
		if (index1 == -1) throw new NodeNotFoundException("Could not find node #1");
		if (index2 == -1) throw new NodeNotFoundException("Could not find node #2");
		
		Tuple<Integer, Integer> retTuple = null;
		
		ArrayList<Tuple<Integer, Integer>> list = edges.get(index1);
		
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getVal1().equals(index2)) {
				retTuple = list.get(i);
			}
		}
		
		return retTuple;
		
	}
	
	public ArrayList<T> getNodes() {
		return nodes;
	}

	public ArrayList<ArrayList<Tuple<Integer, Integer>>> getEdges() {
		return edges;
	}
	
	public ListGraph<SearchNode<T>> bfs(T source) {
		
		ListGraph<SearchNode<T>> bfsTree = new ListGraph<>(false, false, false);
		ArrayList<SearchNode<T>> sNodes = new ArrayList<>();
		
		for (T node : nodes) {
			if (node.equals(source)) {
				sNodes.add(new SearchNode<T>(node, SearchNode.GRAY, 0, null));
			}
			else {
				sNodes.add(new SearchNode<T>(node));
			}
		}
		
		Queue<Integer> queue = new Queue<>();
		queue.enqueue(nodes.indexOf(source));
		
		bfsTree.addVertex(sNodes.get(nodes.indexOf(source)));
		
		while (queue.size() > 0) {
			
			int u = queue.dequeue();
			
			ArrayList<Tuple<Integer, Integer>> adj = edges.get(u);
			
			for (Tuple<Integer, Integer> tuple : adj) {
				
				int v = tuple.getVal1();
				
				if (sNodes.get(v).getColor() == SearchNode.WHITE) {
					
					bfsTree.addVertex(sNodes.get(v));
					
					sNodes.get(v).setColor(SearchNode.GRAY);
					sNodes.get(v).setDistance(sNodes.get(u).getDistance() + 1);
					sNodes.get(v).setAncestor(sNodes.get(u));
					queue.enqueue(v);
					
					int minWeight = tuple.getVal2();
					
					for (Tuple<Integer, Integer> tuple2 : adj) {
						if (tuple2.getVal1().equals(v) && tuple2.getVal2() < minWeight) {
							minWeight = tuple2.getVal2();
						}
					}
					
					bfsTree.addEdge(sNodes.get(u), sNodes.get(v), minWeight);
				}
			}
			
			sNodes.get(u).setColor(SearchNode.BLACK);
			
		}
		
		return bfsTree;
		
	}
	
	public ListGraph<SearchNode<T>> dfs(){
		ListGraph<SearchNode<T>> forest=new ListGraph<SearchNode<T>>(false, false, false);
		for(T vertex: nodes) {
			forest.addVertex(new SearchNode<T>(vertex));
		}
		
		for(SearchNode<T> vertex: forest.getNodes()) {
			if(vertex.getColor() == SearchNode.WHITE){
				dfsVisit(forest, vertex);
			}
		}
		
		return forest;
	}
	
	private void dfsVisit(ListGraph<SearchNode<T>> forest, SearchNode<T> vertex){
		forest.time = forest.time + 1;
		vertex.setDTimestamps(forest.time);
		vertex.setColor(SearchNode.GRAY);
		
		int index = nodes.indexOf(vertex.getObject());
		ArrayList<Tuple<Integer, Integer>> adj=edges.get(index);
		
		for(int i = 0; i < forest.getNodes().size(); i++){
			
			Tuple<Integer, Integer> info = null;
			
			for(int j = 0; j < adj.size(); j++){
				if(adj.get(j).getVal1() == i) {
					if( (info == null) || (adj.get(j).getVal2() < info.getVal2())) {
						info = adj.get(j);
					}
				}
			}
			
			if(info != null){
				SearchNode<T> cVertex = forest.getNode(info.getVal1());
				if(cVertex.getColor() == SearchNode.WHITE) {
					forest.addEdge(vertex, cVertex, info.getVal2());
					
					cVertex.setAncestor(vertex);
					dfsVisit(forest, cVertex);
				}
			}
			
		}
		 
		vertex.setColor(SearchNode.BLACK);
		forest.time = forest.time + 1;
		vertex.setFTimestamps(forest.time);
	}

	@Override
	public ListGraph<SearchNode<T>> prim(T node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ListGraph<SearchNode<T>> kruskal() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<SearchNode<T>> dijkstra(T node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[][] floydWarshall() {
		// TODO Auto-generated method stub
		return null;
	}
	
}

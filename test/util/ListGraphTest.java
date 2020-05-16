package util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import exception.LoopException;
import exception.MultipleEdgesException;
import util.ListGraph;

class ListGraphTest {

	ListGraph<String> lGraph;
	
	private void setSimpleGraph() {
		lGraph = new ListGraph<String>(false, false, false);
		lGraph.addVertex("Ariza");
		lGraph.addVertex("Johan");
		lGraph.addVertex("Mateo");
		lGraph.addVertex("Restrepo");
	}
	
	private void setDirectedGraph() {
		
		lGraph = new ListGraph<String>(true, false, false);
		lGraph.addVertex("Ariza");
		lGraph.addVertex("Johan");
		lGraph.addVertex("Mateo");
		lGraph.addVertex("Restrepo");
		
	}
	
	private void setMultipleGraph() {
		
		lGraph = new ListGraph<String>(false, true, false);
		lGraph.addVertex("Ariza");
		lGraph.addVertex("Johan");
		lGraph.addVertex("Mateo");
		lGraph.addVertex("Restrepo");
		
	}
	
	private void setLoopGraph() {
		
		lGraph = new ListGraph<String>(false, false, true);
		lGraph.addVertex("Ariza");
		lGraph.addVertex("Johan");
		lGraph.addVertex("Mateo");
		lGraph.addVertex("Restrepo");
		
	}

	public void setUpSceneMultiDirectedGraph() {
		
		lGraph = new ListGraph<String>(true, true, true);
		lGraph.addVertex("Ariza");
		lGraph.addVertex("Johan");
		lGraph.addVertex("Mateo");
		lGraph.addVertex("Restrepo");
		
	}
	

	@Test
	void testAddNode1() {
		
		setSimpleGraph();	
		assertEquals(lGraph.getNode(0), "Ariza");
		assertEquals(lGraph.getNode(1), "Johan");
		assertEquals(lGraph.getNode(2), "Mateo");
		assertEquals(lGraph.getNode(3), "Restrepo");
		
	}
	
	@Test
	void testAddEdge1() {
		
		setSimpleGraph();
		lGraph.addEdge("Ariza", "Johan");
		lGraph.addEdge("Johan", "Mateo");
		lGraph.addEdge("Restrepo", "Johan");

		try {
			lGraph.addEdge("Restrepo", "Johan");
			fail();
		}
		catch (MultipleEdgesException e) {
			
		}
		
		try {
			lGraph.addEdge("Ariza", "Ariza");
			fail();
		}
		catch (LoopException e) {
			// Do nothing
		}
		
		ArrayList<ArrayList<Tuple<Integer, Integer>>> edges = lGraph.getEdges();
		assertEquals(edges.get(0).get(0).getVal1().intValue(), 1);
		assertEquals(edges.get(1).get(0).getVal1().intValue(), 0);
		assertEquals(edges.get(3).get(0).getVal1().intValue(), 1);
		assertEquals(edges.get(1).get(1).getVal1().intValue(), 2);
		
	}
	
	@Test
	void testAddEdge2() {
		
		setDirectedGraph();
		lGraph.addEdge("Ariza", "Johan");
		lGraph.addEdge("Johan", "Mateo");
		lGraph.addEdge("Restrepo", "Johan");

		try {
			lGraph.addEdge("Restrepo", "Johan");
			fail();
		}
		catch (MultipleEdgesException e) {
			// Do nothing
		}
		
		try {
			lGraph.addEdge("Ariza", "Ariza");
			fail();
		}
		catch (LoopException e) {
			// Do nothing
		}
		
		ArrayList<ArrayList<Tuple<Integer, Integer>>> edges = lGraph.getEdges();
		assertEquals(edges.get(0).get(0).getVal1().intValue(), 1);
		assertEquals(edges.get(1).get(0).getVal1().intValue(), 2);
		assertEquals(edges.get(3).get(0).getVal1().intValue(), 1);
		
	}
	
	@Test
	void testAddEdge3() {
		
		setMultipleGraph();
		lGraph.addEdge("Ariza", "Johan");
		lGraph.addEdge("Johan", "Mateo");
		lGraph.addEdge("Restrepo", "Johan");
		
		try {
			lGraph.addEdge("Restrepo", "Johan");
		}
		catch (MultipleEdgesException e) {
			fail();
		}
		
		try {
			lGraph.addEdge("Ariza", "Ariza");
			fail();
		}
		catch (LoopException e) {
			// Do nothing
		}
		
		ArrayList<ArrayList<Tuple<Integer, Integer>>> edges = lGraph.getEdges();
		assertEquals(edges.get(0).get(0).getVal1().intValue(), 1);
		assertEquals(edges.get(1).get(0).getVal1().intValue(), 0);
		assertEquals(edges.get(3).get(0).getVal1().intValue(), 1);
		assertEquals(edges.get(1).get(1).getVal1().intValue(), 2);
		assertEquals(edges.get(3).get(1).getVal1().intValue(), 1);
	}
	
	@Test
	void testAddEdge4() {
		
		setLoopGraph();
		lGraph.addEdge("Ariza", "Johan");
		lGraph.addEdge("Johan", "Mateo");
		lGraph.addEdge("Restrepo", "Johan");

		try {
			lGraph.addEdge("Restrepo", "Johan");
			fail();
		}
		catch (MultipleEdgesException e) {
			// Do nothing
		}
		
		try {
			lGraph.addEdge("Ariza", "Ariza");
		}
		catch (LoopException e) {
			fail();
		}
		
		ArrayList<ArrayList<Tuple<Integer, Integer>>> edges = lGraph.getEdges();
		assertEquals(edges.get(0).get(0).getVal1().intValue(), 1);
		assertEquals(edges.get(1).get(0).getVal1().intValue(), 0);
		assertEquals(edges.get(3).get(0).getVal1().intValue(), 1);
		assertEquals(edges.get(1).get(1).getVal1().intValue(), 2);
	}
	
	@Test
	void testRemoveNode() {
		
		setSimpleGraph();
		lGraph.addEdge("Ariza", "Johan");
		lGraph.addEdge("Johan", "Mateo");
		lGraph.addEdge("Restrepo", "Johan");
		lGraph.removeVertex("Ariza");
		lGraph.removeVertex("Mateo");
		assertEquals(lGraph.getNode(0), "Johan");
		assertEquals(lGraph.getNode(1), "Restrepo");
		assertEquals(lGraph.getEdges().get(0).get(0).getVal1().intValue(), 1);
		assertEquals(lGraph.getEdges().get(1).get(0).getVal1().intValue(), 0);
//		System.out.println(lGraph.getEdges());
		
		
	}
	
	@Test
	void testRemoveEdge() {
		
		setSimpleGraph();
		lGraph.addEdge("Ariza", "Johan");
		lGraph.addEdge("Johan", "Mateo");
		lGraph.addEdge("Restrepo", "Johan");

		lGraph.removeEdge("Johan", "Mateo");
		
		ArrayList<ArrayList<Tuple<Integer, Integer>>> edges = lGraph.getEdges();
		assertEquals(edges.get(0).get(0).getVal1().intValue(), 1);
		assertEquals(edges.get(1).get(0).getVal1().intValue(), 0);
		assertEquals(edges.get(3).get(0).getVal1().intValue(), 1);
		assertEquals(edges.get(1).get(1).getVal1().intValue(), 3);
		
	}
	
	@Test
	void testGetNode() {
		
		setSimpleGraph();
		assertEquals(lGraph.getNode(0), "Ariza");
		assertEquals(lGraph.getNode(1), "Johan");
		assertEquals(lGraph.getNode(2), "Mateo");
		assertEquals(lGraph.getNode(3), "Restrepo");
		
	}
	
	@Test 
	void testGetEdges() {
		
		setSimpleGraph();
		lGraph.addEdge("Ariza", "Johan");
		lGraph.addEdge("Johan", "Mateo");
		lGraph.addEdge("Restrepo", "Johan");
		
		assertEquals(lGraph.getEdge("Ariza", "Johan").getVal1().intValue(), 1);
		assertEquals(lGraph.getEdge("Ariza", "Johan").getVal2().intValue(), 1);
		assertEquals(lGraph.getEdge("Johan", "Mateo").getVal1().intValue(), 2);
		assertEquals(lGraph.getEdge("Ariza", "Johan").getVal2().intValue(), 1);
		assertEquals(lGraph.getEdge("Restrepo", "Johan").getVal1().intValue(), 1);
		assertEquals(lGraph.getEdge("Restrepo", "Johan").getVal2().intValue(), 1);
		
	}
	
	@Test
	void testbfs1() {
		
		setSimpleGraph();
		lGraph.addEdge("Ariza", "Johan");
		lGraph.addEdge("Johan", "Mateo");
		lGraph.addEdge("Restrepo", "Johan");
		
		ListGraph<SearchNode<String>> bfsTree = lGraph.bfs("Ariza");
		
		assertEquals(bfsTree.getNode(0).getObject(), "Ariza");
		assertEquals(bfsTree.getNode(1).getObject(), "Johan");
		assertEquals(bfsTree.getNode(2).getObject(), "Mateo");
		assertEquals(bfsTree.getNode(3).getObject(), "Restrepo");
		
		assertEquals(bfsTree.getEdges().get(0).get(0).getVal1().intValue(), 1);
		assertEquals(bfsTree.getEdges().get(1).get(0).getVal1().intValue(), 0);
		assertEquals(bfsTree.getEdges().get(1).get(1).getVal1().intValue(), 2);
		assertEquals(bfsTree.getEdges().get(1).get(2).getVal1().intValue(), 3);
		assertEquals(bfsTree.getEdges().get(2).get(0).getVal1().intValue(), 1);
		assertEquals(bfsTree.getEdges().get(3).get(0).getVal1().intValue(), 1);
		
		assertEquals(bfsTree.getNode(0).getDistance(), 0);
		assertEquals(bfsTree.getNode(1).getDistance(), 1);
		assertEquals(bfsTree.getNode(2).getDistance(), 2);
		assertEquals(bfsTree.getNode(3).getDistance(), 2);
	
		assertEquals(bfsTree.getNode(0).getColor(), SearchNode.BLACK);
		assertEquals(bfsTree.getNode(1).getColor(), SearchNode.BLACK);
		assertEquals(bfsTree.getNode(2).getColor(), SearchNode.BLACK);
		assertEquals(bfsTree.getNode(3).getColor(), SearchNode.BLACK);
	}
	
	@Test
	void testbfs2() {
		
		setUpSceneMultiDirectedGraph();
		lGraph.addEdge("Ariza", "Ariza", 3);
		lGraph.addEdge("Ariza", "Johan", 2);
		lGraph.addEdge("Ariza", "Johan", 1);
		lGraph.addEdge("Johan", "Mateo", 1);
		lGraph.addEdge("Johan", "Restrepo", 2);
		lGraph.addEdge("Mateo", "Restrepo", 2);
	
		ListGraph<SearchNode<String>> bfsTree = lGraph.bfs("Ariza");
		
		assertEquals(bfsTree.getNode(0).getObject(), "Ariza");
		assertEquals(bfsTree.getNode(1).getObject(), "Johan");
		assertEquals(bfsTree.getNode(2).getObject(), "Mateo");
		assertEquals(bfsTree.getNode(3).getObject(), "Restrepo");
		
		assertEquals(bfsTree.getEdges().get(0).get(0).getVal1().intValue(), 1);
		assertEquals(bfsTree.getEdges().get(1).get(0).getVal1().intValue(), 0);
		assertEquals(bfsTree.getEdges().get(1).get(1).getVal1().intValue(), 2);
		assertEquals(bfsTree.getEdges().get(1).get(2).getVal1().intValue(), 3);
		
		assertEquals(bfsTree.getNode(0).getDistance(), 0);
		assertEquals(bfsTree.getNode(1).getDistance(), 1);
		assertEquals(bfsTree.getNode(2).getDistance(), 2);
		assertEquals(bfsTree.getNode(3).getDistance(), 2);
	
		assertEquals(bfsTree.getNode(0).getColor(), SearchNode.BLACK);
		assertEquals(bfsTree.getNode(1).getColor(), SearchNode.BLACK);
		assertEquals(bfsTree.getNode(2).getColor(), SearchNode.BLACK);
		assertEquals(bfsTree.getNode(3).getColor(), SearchNode.BLACK);
		
		assertEquals(bfsTree.getEdges().get(0).get(0).getVal2().intValue(), 1);
		assertEquals(bfsTree.getEdges().get(1).get(0).getVal2().intValue(), 1);
		assertEquals(bfsTree.getEdges().get(3).get(0).getVal2().intValue(), 2);
		
	}
	
	@Test
	void testdfs() {
		//Simple Graph
		setSimpleGraph();
		lGraph.addEdge("Ariza", "Johan", 2);
		lGraph.addEdge("Johan", "Mateo", 1);
		
		ListGraph<SearchNode<String>> forest = lGraph.dfs();
		
		assertEquals(forest.getNode(0).getColor(), SearchNode.BLACK);
		assertEquals(forest.getNode(0).getTimestamps().getVal1().intValue(), 1);
		assertEquals(forest.getNode(0).getTimestamps().getVal2().intValue(), 6);
		assertNull(forest.getNode(0).getAncestor());
		
		assertEquals(forest.getEdges().get(0).get(0).getVal1().intValue(), 1);
		assertEquals(forest.getEdges().get(0).get(0).getVal2().intValue(), 2);
		assertEquals(forest.getEdges().get(1).get(0).getVal1().intValue(), 0);
		assertEquals(forest.getEdges().get(1).get(0).getVal2().intValue(), 2);
		
		assertEquals(forest.getNode(1).getColor(), SearchNode.BLACK);
		assertEquals(forest.getNode(1).getTimestamps().getVal1().intValue(), 2);
		assertEquals(forest.getNode(1).getTimestamps().getVal2().intValue(), 5);
		assertEquals(forest.getNode(1).getAncestor(), forest.getNode(0));
		
		assertEquals(forest.getEdges().get(1).get(1).getVal1().intValue(), 2);
		assertEquals(forest.getEdges().get(1).get(1).getVal2().intValue(), 1);
		assertEquals(forest.getEdges().get(2).get(0).getVal1().intValue(), 1);
		assertEquals(forest.getEdges().get(2).get(0).getVal2().intValue(), 1);
		
		assertEquals(forest.getNode(2).getColor(), SearchNode.BLACK);
		assertEquals(forest.getNode(2).getTimestamps().getVal1().intValue(), 3);
		assertEquals(forest.getNode(2).getTimestamps().getVal2().intValue(), 4);
		assertEquals(forest.getNode(2).getAncestor(), forest.getNode(1));
		
		assertEquals(forest.getNode(3).getColor(), SearchNode.BLACK);
		assertEquals(forest.getNode(3).getTimestamps().getVal1().intValue(), 7);
		assertEquals(forest.getNode(3).getTimestamps().getVal2().intValue(), 8);
		assertNull(forest.getNode(3).getAncestor());
		//...
		
		//MultiDirectedGraph
		setUpSceneMultiDirectedGraph();
		lGraph.addEdge("Ariza", "Ariza", 3);
		lGraph.addEdge("Ariza", "Johan", 2);
		lGraph.addEdge("Ariza", "Johan", 1);
		lGraph.addEdge("Johan", "Mateo", 1);
		
		forest = lGraph.dfs();
		
		assertEquals(forest.getNode(0).getColor(), SearchNode.BLACK);
		assertEquals(forest.getNode(0).getTimestamps().getVal1().intValue(), 1);
		assertEquals(forest.getNode(0).getTimestamps().getVal2().intValue(), 6);
		assertNull(forest.getNode(0).getAncestor());
		
		assertEquals(forest.getEdges().get(0).get(0).getVal1().intValue(), 1);
		assertEquals(forest.getEdges().get(0).get(0).getVal2().intValue(), 1);
		assertEquals(forest.getEdges().get(1).get(0).getVal1().intValue(), 0);
		assertEquals(forest.getEdges().get(1).get(0).getVal2().intValue(), 1);
		
		assertEquals(forest.getNode(1).getColor(), SearchNode.BLACK);
		assertEquals(forest.getNode(1).getTimestamps().getVal1().intValue(), 2);
		assertEquals(forest.getNode(1).getTimestamps().getVal2().intValue(), 5);
		assertEquals(forest.getNode(1).getAncestor(), forest.getNode(0));
		
		assertEquals(forest.getEdges().get(1).get(1).getVal1().intValue(), 2);
		assertEquals(forest.getEdges().get(1).get(1).getVal2().intValue(), 1);
		assertEquals(forest.getEdges().get(2).get(0).getVal1().intValue(), 1);
		assertEquals(forest.getEdges().get(2).get(0).getVal2().intValue(), 1);
		
		assertEquals(forest.getNode(2).getColor(), SearchNode.BLACK);
		assertEquals(forest.getNode(2).getTimestamps().getVal1().intValue(), 3);
		assertEquals(forest.getNode(2).getTimestamps().getVal2().intValue(), 4);
		assertEquals(forest.getNode(2).getAncestor(), forest.getNode(1));
		
		assertEquals(forest.getNode(3).getColor(), SearchNode.BLACK);
		assertEquals(forest.getNode(3).getTimestamps().getVal1().intValue(), 7);
		assertEquals(forest.getNode(3).getTimestamps().getVal2().intValue(), 8);
		assertNull(forest.getNode(3).getAncestor());
		//...
	}
	
}

package utilTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import exception.LoopException;
import exception.MultipleEdgesException;
import util.ListGraph;
import util.SearchNode;
import util.Tuple;

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
		assertEquals(lGraph.getVertex(0), "Ariza");
		assertEquals(lGraph.getVertex(1), "Johan");
		assertEquals(lGraph.getVertex(2), "Mateo");
		assertEquals(lGraph.getVertex(3), "Restrepo");
		
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
		assertEquals(lGraph.getVertex(0), "Johan");
		assertEquals(lGraph.getVertex(1), "Restrepo");
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
		assertEquals(lGraph.getVertex(0), "Ariza");
		assertEquals(lGraph.getVertex(1), "Johan");
		assertEquals(lGraph.getVertex(2), "Mateo");
		assertEquals(lGraph.getVertex(3), "Restrepo");
		
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
		
		assertEquals(bfsTree.getVertex(0).getObject(), "Ariza");
		assertEquals(bfsTree.getVertex(1).getObject(), "Johan");
		assertEquals(bfsTree.getVertex(2).getObject(), "Mateo");
		assertEquals(bfsTree.getVertex(3).getObject(), "Restrepo");
		
		assertEquals(bfsTree.getEdges().get(0).get(0).getVal1().intValue(), 1);
		assertEquals(bfsTree.getEdges().get(1).get(0).getVal1().intValue(), 0);
		assertEquals(bfsTree.getEdges().get(1).get(1).getVal1().intValue(), 2);
		assertEquals(bfsTree.getEdges().get(1).get(2).getVal1().intValue(), 3);
		assertEquals(bfsTree.getEdges().get(2).get(0).getVal1().intValue(), 1);
		assertEquals(bfsTree.getEdges().get(3).get(0).getVal1().intValue(), 1);
		
		assertEquals(bfsTree.getVertex(0).getDistance(), 0);
		assertEquals(bfsTree.getVertex(1).getDistance(), 1);
		assertEquals(bfsTree.getVertex(2).getDistance(), 2);
		assertEquals(bfsTree.getVertex(3).getDistance(), 2);
	
		assertEquals(bfsTree.getVertex(0).getColor(), SearchNode.BLACK);
		assertEquals(bfsTree.getVertex(1).getColor(), SearchNode.BLACK);
		assertEquals(bfsTree.getVertex(2).getColor(), SearchNode.BLACK);
		assertEquals(bfsTree.getVertex(3).getColor(), SearchNode.BLACK);
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
		
		assertEquals(bfsTree.getVertex(0).getObject(), "Ariza");
		assertEquals(bfsTree.getVertex(1).getObject(), "Johan");
		assertEquals(bfsTree.getVertex(2).getObject(), "Mateo");
		assertEquals(bfsTree.getVertex(3).getObject(), "Restrepo");
		
		assertEquals(bfsTree.getEdges().get(0).get(0).getVal1().intValue(), 1);
		assertEquals(bfsTree.getEdges().get(1).get(0).getVal1().intValue(), 0);
		assertEquals(bfsTree.getEdges().get(1).get(1).getVal1().intValue(), 2);
		assertEquals(bfsTree.getEdges().get(1).get(2).getVal1().intValue(), 3);
		
		assertEquals(bfsTree.getVertex(0).getDistance(), 0);
		assertEquals(bfsTree.getVertex(1).getDistance(), 1);
		assertEquals(bfsTree.getVertex(2).getDistance(), 2);
		assertEquals(bfsTree.getVertex(3).getDistance(), 2);
	
		assertEquals(bfsTree.getVertex(0).getColor(), SearchNode.BLACK);
		assertEquals(bfsTree.getVertex(1).getColor(), SearchNode.BLACK);
		assertEquals(bfsTree.getVertex(2).getColor(), SearchNode.BLACK);
		assertEquals(bfsTree.getVertex(3).getColor(), SearchNode.BLACK);
		
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
		
		assertEquals(forest.getVertex(0).getColor(), SearchNode.BLACK);
		assertEquals(forest.getVertex(0).getTimestamps().getVal1().intValue(), 1);
		assertEquals(forest.getVertex(0).getTimestamps().getVal2().intValue(), 6);
		assertNull(forest.getVertex(0).getAncestor());
		
		assertEquals(forest.getEdges().get(0).get(0).getVal1().intValue(), 1);
		assertEquals(forest.getEdges().get(0).get(0).getVal2().intValue(), 2);
		assertEquals(forest.getEdges().get(1).get(0).getVal1().intValue(), 0);
		assertEquals(forest.getEdges().get(1).get(0).getVal2().intValue(), 2);
		
		assertEquals(forest.getVertex(1).getColor(), SearchNode.BLACK);
		assertEquals(forest.getVertex(1).getTimestamps().getVal1().intValue(), 2);
		assertEquals(forest.getVertex(1).getTimestamps().getVal2().intValue(), 5);
		assertEquals(forest.getVertex(1).getAncestor(), forest.getVertex(0));
		
		assertEquals(forest.getEdges().get(1).get(1).getVal1().intValue(), 2);
		assertEquals(forest.getEdges().get(1).get(1).getVal2().intValue(), 1);
		assertEquals(forest.getEdges().get(2).get(0).getVal1().intValue(), 1);
		assertEquals(forest.getEdges().get(2).get(0).getVal2().intValue(), 1);
		
		assertEquals(forest.getVertex(2).getColor(), SearchNode.BLACK);
		assertEquals(forest.getVertex(2).getTimestamps().getVal1().intValue(), 3);
		assertEquals(forest.getVertex(2).getTimestamps().getVal2().intValue(), 4);
		assertEquals(forest.getVertex(2).getAncestor(), forest.getVertex(1));
		
		assertEquals(forest.getVertex(3).getColor(), SearchNode.BLACK);
		assertEquals(forest.getVertex(3).getTimestamps().getVal1().intValue(), 7);
		assertEquals(forest.getVertex(3).getTimestamps().getVal2().intValue(), 8);
		assertNull(forest.getVertex(3).getAncestor());
		//...
		
		//MultiDirectedGraph
		setUpSceneMultiDirectedGraph();
		lGraph.addEdge("Ariza", "Ariza", 3);
		lGraph.addEdge("Ariza", "Johan", 2);
		lGraph.addEdge("Ariza", "Johan", 1);
		lGraph.addEdge("Johan", "Mateo", 1);
		
		forest = lGraph.dfs();
		
		assertEquals(forest.getVertex(0).getColor(), SearchNode.BLACK);
		assertEquals(forest.getVertex(0).getTimestamps().getVal1().intValue(), 1);
		assertEquals(forest.getVertex(0).getTimestamps().getVal2().intValue(), 6);
		assertNull(forest.getVertex(0).getAncestor());
		
		assertEquals(forest.getEdges().get(0).get(0).getVal1().intValue(), 1);
		assertEquals(forest.getEdges().get(0).get(0).getVal2().intValue(), 1);
		assertEquals(forest.getEdges().get(1).get(0).getVal1().intValue(), 0);
		assertEquals(forest.getEdges().get(1).get(0).getVal2().intValue(), 1);
		
		assertEquals(forest.getVertex(1).getColor(), SearchNode.BLACK);
		assertEquals(forest.getVertex(1).getTimestamps().getVal1().intValue(), 2);
		assertEquals(forest.getVertex(1).getTimestamps().getVal2().intValue(), 5);
		assertEquals(forest.getVertex(1).getAncestor(), forest.getVertex(0));
		
		assertEquals(forest.getEdges().get(1).get(1).getVal1().intValue(), 2);
		assertEquals(forest.getEdges().get(1).get(1).getVal2().intValue(), 1);
		assertEquals(forest.getEdges().get(2).get(0).getVal1().intValue(), 1);
		assertEquals(forest.getEdges().get(2).get(0).getVal2().intValue(), 1);
		
		assertEquals(forest.getVertex(2).getColor(), SearchNode.BLACK);
		assertEquals(forest.getVertex(2).getTimestamps().getVal1().intValue(), 3);
		assertEquals(forest.getVertex(2).getTimestamps().getVal2().intValue(), 4);
		assertEquals(forest.getVertex(2).getAncestor(), forest.getVertex(1));
		
		assertEquals(forest.getVertex(3).getColor(), SearchNode.BLACK);
		assertEquals(forest.getVertex(3).getTimestamps().getVal1().intValue(), 7);
		assertEquals(forest.getVertex(3).getTimestamps().getVal2().intValue(), 8);
		assertNull(forest.getVertex(3).getAncestor());
		//...
	}
	
	@Test
	void testPrim() {
		
		setMultipleGraph();
		lGraph.addEdge("Mateo", "Ariza", 2);
		lGraph.addEdge("Mateo", "Ariza", 1);
		lGraph.addEdge("Ariza", "Restrepo", 3);
		lGraph.addEdge("Ariza", "Restrepo", 6);
		lGraph.addEdge("Mateo", "Restrepo", 5);
		lGraph.addEdge("Mateo", "Johan", 4);
		lGraph.addEdge("Restrepo", "Johan", 2);
		
		ListGraph<SearchNode<String>> tree = lGraph.prim("Mateo");
		
		assertEquals(tree.getVertex(0).getColor(), SearchNode.BLACK);
		assertEquals(tree.getVertex(1).getColor(), SearchNode.BLACK);
		assertEquals(tree.getVertex(2).getColor(), SearchNode.BLACK);
		assertEquals(tree.getVertex(3).getColor(), SearchNode.BLACK);
		
		assertEquals(tree.getVertex(0).getObject(), "Mateo");
		assertEquals(tree.getVertex(1).getObject(), "Ariza");
		assertEquals(tree.getVertex(2).getObject(), "Restrepo");
		assertEquals(tree.getVertex(3).getObject(), "Johan");
		
		Tuple<Integer, Integer> edge1 = tree.getEdge(tree.getVertex(0), tree.getVertex(1));
		Tuple<Integer, Integer> edge2 = tree.getEdge(tree.getVertex(1), tree.getVertex(2));
		Tuple<Integer, Integer> edge3 = tree.getEdge(tree.getVertex(2), tree.getVertex(3));
		
		assertEquals(edge1.getVal1().intValue(), 1);
		assertEquals(edge1.getVal2().intValue(), 1);
		assertEquals(edge2.getVal1().intValue(), 2);
		assertEquals(edge2.getVal2().intValue(), 3);
		assertEquals(edge3.getVal1().intValue(), 3);
		assertEquals(edge3.getVal2().intValue(), 2);
	}
	
	@Test
	void testKruskal() {
		
		setMultipleGraph();
		lGraph.addEdge("Mateo", "Ariza", 10);
		lGraph.addEdge("Johan", "Ariza", 5);
		lGraph.addEdge("Ariza", "Restrepo", 3);
		lGraph.addEdge("Mateo", "Johan", 7);
		lGraph.addEdge("Mateo", "Ariza", 20);
		lGraph.addEdge("Johan", "Ariza", 50);
		lGraph.addEdge("Ariza", "Restrepo", 33);
		lGraph.addEdge("Mateo", "Johan", 72);
		
		ListGraph<SearchNode<String>> tree = lGraph.kruskal();
		
		assertEquals(tree.getVertex(0).getObject(), "Ariza");
		assertEquals(tree.getVertex(1).getObject(), "Johan");
		assertEquals(tree.getVertex(2).getObject(), "Mateo");
		assertEquals(tree.getVertex(3).getObject(), "Restrepo");
		
		Tuple<Integer, Integer> edge1 = tree.getEdge(tree.getVertex(0), tree.getVertex(3));
		Tuple<Integer, Integer> edge2 = tree.getEdge(tree.getVertex(0), tree.getVertex(1));
		Tuple<Integer, Integer> edge3 = tree.getEdge(tree.getVertex(1), tree.getVertex(2));
		
		assertEquals(edge1.getVal2().intValue(), 3);
		assertEquals(edge2.getVal2().intValue(), 5);
		assertEquals(edge3.getVal2().intValue(), 7);
		
	}
	
	@Test
	void testDjikstra() {
		
		setSimpleGraph();
		lGraph.addEdge("Mateo", "Ariza", 2);
		lGraph.addEdge("Restrepo", "Ariza", 3);
		lGraph.addEdge("Ariza", "Johan", 5);
		lGraph.addEdge("Mateo", "Johan", 2);
		lGraph.addEdge("Mateo", "Restrepo", 9);
		
		ArrayList<SearchNode<String>> list = lGraph.dijkstra("Ariza");
		
		assertEquals(list.get(0).getObject(), "Ariza");
		assertEquals(list.get(1).getObject(), "Johan");
		assertEquals(list.get(2).getObject(), "Mateo");
		assertEquals(list.get(3).getObject(), "Restrepo");
		
		assertEquals(list.get(0).getAncestor(), null);
		assertEquals(list.get(1).getAncestor(), list.get(2));
		assertEquals(list.get(2).getAncestor(), list.get(0));
		assertEquals(list.get(3).getAncestor(), list.get(0));
		
		assertEquals(list.get(0).getDistance(), 0);
		assertEquals(list.get(1).getDistance(), 4);
		assertEquals(list.get(2).getDistance(), 2);
		assertEquals(list.get(3).getDistance(), 3);
		
	}
	
	@Test
	void testFloydWarshall() {
		
		setDirectedGraph();
		lGraph.addEdge("Ariza", "Mateo", -2);
		lGraph.addEdge("Mateo", "Restrepo", 2);
		lGraph.addEdge("Johan", "Mateo", 3);
		lGraph.addEdge("Johan", "Ariza", 4);
		lGraph.addEdge("Restrepo", "Johan", -1);
		
		int[][] matrix = lGraph.floydWarshall();
		
		assertEquals(matrix[0][0], 0);
		assertEquals(matrix[0][1], -1);
		assertEquals(matrix[0][2], -2);
		assertEquals(matrix[0][3], 0);
		assertEquals(matrix[1][0], 4);
		assertEquals(matrix[1][1], 0);
		assertEquals(matrix[1][2], 2);
		assertEquals(matrix[1][3], 4);
		assertEquals(matrix[2][0], 5);
		assertEquals(matrix[2][1], 1);
		assertEquals(matrix[2][2], 0);
		assertEquals(matrix[2][3], 2);
		assertEquals(matrix[3][0], 3);
		assertEquals(matrix[3][1], -1);
		assertEquals(matrix[3][2], 1);
		assertEquals(matrix[3][3], 0);
		
	}
	
}

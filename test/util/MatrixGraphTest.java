package util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exception.LoopException;
import exception.MultipleEdgesException;
import exception.NodeNotFoundException;
import util.MatrixGraph;

class MatrixGraphTest {

	//Tested Class
	private MatrixGraph<String> matrixGraph;
	
	//Scene
	private void setUpSceneSimpleGraphEmpty() {
		this.matrixGraph=new MatrixGraph<String>(false,false,false);
	}
	
	private void setUpSceneSimpleGraphVertex() {
		this.matrixGraph=new MatrixGraph<String>(false,false,false);
		
		matrixGraph.addNode("Johan");
		matrixGraph.addNode("Esteban");
		matrixGraph.addNode("Mateo");
	}
	
	private void setUpSceneMultiDirectedGraphVertex() {
		this.matrixGraph=new MatrixGraph<String>(true,true,true);
		
		matrixGraph.addNode("Johan");
		matrixGraph.addNode("Esteban");
		matrixGraph.addNode("Mateo");
	}
	
	private void setUpSceneSimpleGraph() {
		this.matrixGraph=new MatrixGraph<String>(false,false,false);
		
		matrixGraph.addNode("Johan");
		matrixGraph.addNode("Esteban");
		matrixGraph.addNode("Mateo");
		
		matrixGraph.addEdge(matrixGraph.getNode(0),matrixGraph.getNode(1),2);
		matrixGraph.addEdge(matrixGraph.getNode(1),matrixGraph.getNode(2),1);
	}
	
	private void setUpSceneSimpleGraph2() {
		this.matrixGraph=new MatrixGraph<String>(false,false,false);
		
		matrixGraph.addNode("Johan");
		matrixGraph.addNode("Esteban");
		matrixGraph.addNode("Mateo");
		matrixGraph.addNode("Juan");
		
		matrixGraph.addEdge(matrixGraph.getNode(0),matrixGraph.getNode(1),2);
		matrixGraph.addEdge(matrixGraph.getNode(1),matrixGraph.getNode(2),1);
	}
	
	private void setUpSceneSimpleGraph3() {
		this.matrixGraph=new MatrixGraph<String>(false,false,false);
		
		matrixGraph.addNode("Johan");
		matrixGraph.addNode("Esteban");
		matrixGraph.addNode("Mateo");
		matrixGraph.addNode("Juan");
		
		matrixGraph.addEdge(matrixGraph.getNode(0),matrixGraph.getNode(1),5);
		matrixGraph.addEdge(matrixGraph.getNode(1),matrixGraph.getNode(2),3);
		matrixGraph.addEdge(matrixGraph.getNode(2),matrixGraph.getNode(3),1);
		matrixGraph.addEdge(matrixGraph.getNode(3),matrixGraph.getNode(0),6);
		
		matrixGraph.addEdge(matrixGraph.getNode(0),matrixGraph.getNode(2),2);
		matrixGraph.addEdge(matrixGraph.getNode(1),matrixGraph.getNode(3),4);
	}
	
	private void setUpSceneSimpleGraph4() {
		matrixGraph = new MatrixGraph<String>(false,false,false);
		
		matrixGraph.addNode("a");
		matrixGraph.addNode("b");
		matrixGraph.addNode("c");
		matrixGraph.addNode("d");
		
		matrixGraph.addEdge("a", "b", 10);
		matrixGraph.addEdge("a", "c", 7);
		matrixGraph.addEdge("c", "b", 5);
		matrixGraph.addEdge("b", "d", 3);
	}
	
	private void setUpSceneMultiDirectedGraph() {
		this.matrixGraph=new MatrixGraph<String>(true,true,true);
		
		matrixGraph.addNode("Johan");
		matrixGraph.addNode("Esteban");
		matrixGraph.addNode("Mateo");
		
		matrixGraph.addEdge(matrixGraph.getNode(0),matrixGraph.getNode(1),2);
		matrixGraph.addEdge(matrixGraph.getNode(0),matrixGraph.getNode(0),4);
		matrixGraph.addEdge(matrixGraph.getNode(1),matrixGraph.getNode(2),1);
		matrixGraph.addEdge(matrixGraph.getNode(2),matrixGraph.getNode(0),3);
		matrixGraph.addEdge(matrixGraph.getNode(2),matrixGraph.getNode(0),0);
		matrixGraph.addEdge(matrixGraph.getNode(2),matrixGraph.getNode(0),3);
	}
	
	private void setUpSceneMultiDirectedGraph2() {
		this.matrixGraph=new MatrixGraph<String>(true,true,true);
		
		matrixGraph.addNode("Johan");
		matrixGraph.addNode("Esteban");
		matrixGraph.addNode("Mateo");
		matrixGraph.addNode("Juan");
		
		matrixGraph.addEdge(matrixGraph.getNode(0),matrixGraph.getNode(1),2);
		matrixGraph.addEdge(matrixGraph.getNode(0),matrixGraph.getNode(0),4);
		matrixGraph.addEdge(matrixGraph.getNode(1),matrixGraph.getNode(2),1);
		matrixGraph.addEdge(matrixGraph.getNode(1),matrixGraph.getNode(2),5);
		matrixGraph.addEdge(matrixGraph.getNode(2),matrixGraph.getNode(0),3);
	}
	
	private void setUpSceneMultiDirectedGraph3() {
		this.matrixGraph=new MatrixGraph<String>(false,true,true);
		
		matrixGraph.addNode("Johan");
		matrixGraph.addNode("Esteban");
		matrixGraph.addNode("Mateo");
		matrixGraph.addNode("Juan");
		
		matrixGraph.addEdge(matrixGraph.getNode(0),matrixGraph.getNode(1),2);
		matrixGraph.addEdge(matrixGraph.getNode(0),matrixGraph.getNode(0),4);
		matrixGraph.addEdge(matrixGraph.getNode(1),matrixGraph.getNode(2),1);
		matrixGraph.addEdge(matrixGraph.getNode(1),matrixGraph.getNode(2),5);
		matrixGraph.addEdge(matrixGraph.getNode(2),matrixGraph.getNode(1),5);
		matrixGraph.addEdge(matrixGraph.getNode(2),matrixGraph.getNode(0),3);
	}
	
	//Test
	@Test
	void testAddNode() {
		
		setUpSceneSimpleGraphEmpty();
		matrixGraph.addNode("Johan");
		matrixGraph.addNode("Esteban");
		matrixGraph.addNode("Mateo");
		
		assertEquals(matrixGraph.getNode(0),"Johan");
		assertEquals(matrixGraph.getNode(1),"Esteban");
		assertEquals(matrixGraph.getNode(2),"Mateo");
		assertEquals(matrixGraph.getEdges().size(),3);
		for(int i = 0; i < 3; i++){
			assertEquals(matrixGraph.getEdges().get(i).size(),3);
		}
		
	}
	
	@Test
	void testAddEdge(){
		
		//Simple Graph
		setUpSceneSimpleGraphVertex();
		matrixGraph.addEdge(matrixGraph.getNode(0),matrixGraph.getNode(1),2);
		matrixGraph.addEdge(matrixGraph.getNode(1),matrixGraph.getNode(2),1);
		
		assertThrows(NodeNotFoundException.class, ()->{
			matrixGraph.addEdge(matrixGraph.getNode(0),"Juan",1);
		});
		assertThrows(LoopException.class, ()->{
			matrixGraph.addEdge(matrixGraph.getNode(0),matrixGraph.getNode(0),1);
		});
		assertThrows(MultipleEdgesException.class, ()->{
			matrixGraph.addEdge(matrixGraph.getNode(0),matrixGraph.getNode(1),2);
		});
		
		assertEquals(matrixGraph.getEdge(0, 1).get(0).intValue(),2);
		assertEquals(matrixGraph.getEdge(1, 0).get(0).intValue(),2);
		
		assertEquals(matrixGraph.getEdge(1, 2).get(0).intValue(),1);
		assertEquals(matrixGraph.getEdge(2, 1).get(0).intValue(),1);
		//...
		
		//Multi Directed Graph
		setUpSceneMultiDirectedGraphVertex();
		matrixGraph.addEdge(matrixGraph.getNode(0),matrixGraph.getNode(1),2);
		matrixGraph.addEdge(matrixGraph.getNode(1),matrixGraph.getNode(2),1);
		matrixGraph.addEdge(matrixGraph.getNode(2),matrixGraph.getNode(0),3);
		matrixGraph.addEdge(matrixGraph.getNode(2),matrixGraph.getNode(0),0);
		matrixGraph.addEdge(matrixGraph.getNode(2),matrixGraph.getNode(0),3);
		
		assertThrows(NodeNotFoundException.class, ()->{
			matrixGraph.addEdge(matrixGraph.getNode(0),"Juan",1);
		});
		
		assertEquals(matrixGraph.getEdge(0, 1).get(0).intValue(), 2);
		assertEquals(matrixGraph.getEdge(1, 0).size(), 0);
		
		assertEquals(matrixGraph.getEdge(1, 2).get(0).intValue(), 1);
		assertEquals(matrixGraph.getEdge(2, 1).size(), 0);
		
		assertEquals(matrixGraph.getEdge(2, 0).get(0).intValue(), 3);
		assertEquals(matrixGraph.getEdge(2, 0).get(1).intValue(), 0);
		assertEquals(matrixGraph.getEdge(2, 0).get(2).intValue(), 3);
		assertEquals(matrixGraph.getEdge(0, 2).size(), 0);
		//...
		
	}
	
	@Test
	void testRemoveNode() {
		
		setUpSceneSimpleGraph();
		matrixGraph.removeNode(matrixGraph.getNode(1));
		
		assertThrows(NodeNotFoundException.class, ()->{
			matrixGraph.removeNode("Juan");
		});
		
		assertEquals(matrixGraph.getNodes().size(), 2);
		assertEquals(matrixGraph.getEdges().size(), 2);
		
		for(int i = 0; i < matrixGraph.getEdges().size(); i++){
			assertEquals(matrixGraph.getEdges().get(i).size(), 2);
			
			for(int j = 0; j < matrixGraph.getEdges().get(i).size(); j++){
				assertEquals(matrixGraph.getEdges().get(i).get(i).size(), 0);
			}
		}
		
	}
	
	@Test
	void testRemoveEdge() {
		//Simple Graph
		setUpSceneSimpleGraph();
		matrixGraph.removeEdge(matrixGraph.getNode(1), matrixGraph.getNode(0), 2);
		
		assertThrows(NodeNotFoundException.class, ()->{
			matrixGraph.removeEdge(matrixGraph.getNode(1), "Juan", 1);
		});
		
		assertEquals(matrixGraph.getEdge(0,1).size(), 0);
		assertEquals(matrixGraph.getEdge(1,0).size(), 0);
		//...
		
		//Multi Directed Graph
		setUpSceneMultiDirectedGraph();
		matrixGraph.removeEdge(matrixGraph.getNode(0),matrixGraph.getNode(0),4);
		matrixGraph.removeEdge(matrixGraph.getNode(2),matrixGraph.getNode(0),3);
		
		assertThrows(NodeNotFoundException.class, ()->{
			matrixGraph.removeEdge(matrixGraph.getNode(1), "Juan", 1);
		});
		
		assertEquals(matrixGraph.getEdge(0,0).size(), 0);
		assertEquals(matrixGraph.getEdge(2,0).size(), 2);
		
		assertEquals(matrixGraph.getEdge(2,0).get(0).intValue(), 0);
		assertEquals(matrixGraph.getEdge(2,0).get(1).intValue(), 3);
		//...
	}
	
	@Test
	void testBfs() {
		//Simple Graph
		setUpSceneSimpleGraph();
			//1
		MatrixGraph<SearchNode<String>> tree = matrixGraph.bfs(matrixGraph.getNode(0));
		
		assertEquals(tree.getNode(0).getColor(), SearchNode.BLACK);
		assertEquals(tree.getNode(0).getDistance(), 0);
		
		assertEquals(tree.getEdge(1, 0).get(0).intValue(), 2);
		assertEquals(tree.getEdge(0, 1).get(0).intValue(), 2);
		
		assertEquals(tree.getNode(1).getColor(), SearchNode.BLACK);
		assertEquals(tree.getNode(1).getDistance(), 1);
		assertEquals(tree.getNode(1).getAncestor(), tree.getNode(0));
		
		assertEquals(tree.getEdge(1, 2).get(0).intValue(), 1);
		assertEquals(tree.getEdge(2, 1).get(0).intValue(), 1);
		
		assertEquals(tree.getNode(2).getColor(), SearchNode.BLACK);
		assertEquals(tree.getNode(2).getDistance(), 2);
		assertEquals(tree.getNode(2).getAncestor(), tree.getNode(1));
			//2
		tree = matrixGraph.bfs(matrixGraph.getNode(2));
		
		assertEquals(tree.getNode(2).getColor(), SearchNode.BLACK);
		assertEquals(tree.getNode(2).getDistance(), 0);
		
		assertEquals(tree.getEdge(1, 2).get(0).intValue(), 1);
		assertEquals(tree.getEdge(2, 1).get(0).intValue(), 1);
		
		assertEquals(tree.getNode(1).getColor(), SearchNode.BLACK);
		assertEquals(tree.getNode(1).getDistance(), 1);
		assertEquals(tree.getNode(1).getAncestor(), tree.getNode(2));
		
		assertEquals(tree.getEdge(1, 0).get(0).intValue(), 2);
		assertEquals(tree.getEdge(0, 1).get(0).intValue(), 2);
		
		assertEquals(tree.getNode(0).getColor(), SearchNode.BLACK);
		assertEquals(tree.getNode(0).getDistance(), 2);
		assertEquals(tree.getNode(0).getAncestor(), tree.getNode(1));
		//...
		
		//Multi Directed Graph
		setUpSceneMultiDirectedGraph();
			//1
		tree = matrixGraph.bfs(matrixGraph.getNode(1));
		
		assertEquals(tree.getNode(1).getColor(), SearchNode.BLACK);
		assertEquals(tree.getNode(1).getDistance(), 0);
		
		assertEquals(tree.getEdge(2, 1).get(0).intValue(), 1);
		assertEquals(tree.getEdge(1, 2).get(0).intValue(), 1);
		
		assertEquals(tree.getNode(2).getColor(), SearchNode.BLACK);
		assertEquals(tree.getNode(2).getDistance(), 1);
		assertEquals(tree.getNode(2).getAncestor(), tree.getNode(1));
		
		assertEquals(tree.getEdge(0, 2).get(0).intValue(), 0);
		assertEquals(tree.getEdge(2, 0).get(0).intValue(), 0);
		
		assertEquals(tree.getNode(0).getColor(), SearchNode.BLACK);
		assertEquals(tree.getNode(0).getDistance(), 2);
		assertEquals(tree.getNode(0).getAncestor(), tree.getNode(2));
			//2
		matrixGraph.removeNode(matrixGraph.getNode(2));
		tree = matrixGraph.bfs(matrixGraph.getNode(1));
		
		assertEquals(tree.getNode(1).getColor(), SearchNode.BLACK);
		assertEquals(tree.getNode(1).getDistance(), 0);
		
		assertEquals(tree.getNode(0).getColor(), SearchNode.WHITE);
		assertEquals(tree.getNode(0).getDistance(), Integer.MAX_VALUE);
		//...
	}
	
	@Test
	void testDFS() {
		//Simple Graph
		setUpSceneSimpleGraph2();
		
		MatrixGraph<SearchNode<String>> forest = matrixGraph.dfs();
		
		assertEquals(forest.getNode(0).getColor(), SearchNode.BLACK);
		assertEquals(forest.getNode(0).getTimestamps().getVal1(), 1);
		assertEquals(forest.getNode(0).getTimestamps().getVal2(), 6);
		assertNull(forest.getNode(0).getAncestor());
		
		assertEquals(forest.getEdge(0, 1).get(0), 2);
		assertEquals(forest.getEdge(1, 0).get(0), 2);
		
		assertEquals(forest.getNode(1).getColor(), SearchNode.BLACK);
		assertEquals(forest.getNode(1).getTimestamps().getVal1(), 2);
		assertEquals(forest.getNode(1).getTimestamps().getVal2(), 5);
		assertEquals(forest.getNode(1).getAncestor(), forest.getNode(0));
		
		assertEquals(forest.getEdge(1, 2).get(0), 1);
		assertEquals(forest.getEdge(2, 1).get(0), 1);
		
		assertEquals(forest.getNode(2).getColor(), SearchNode.BLACK);
		assertEquals(forest.getNode(2).getTimestamps().getVal1(), 3);
		assertEquals(forest.getNode(2).getTimestamps().getVal2(), 4);
		assertEquals(forest.getNode(2).getAncestor(), forest.getNode(1));
		
		assertEquals(forest.getNode(3).getColor(), SearchNode.BLACK);
		assertEquals(forest.getNode(3).getTimestamps().getVal1(), 7);
		assertEquals(forest.getNode(3).getTimestamps().getVal2(), 8);
		assertNull(forest.getNode(3).getAncestor());
		//...
		
		//MultiDirectedGraph
		setUpSceneMultiDirectedGraph2();
		
		forest = matrixGraph.dfs();
		
		assertEquals(forest.getNode(0).getColor(), SearchNode.BLACK);
		assertEquals(forest.getNode(0).getTimestamps().getVal1().intValue(), 1);
		assertEquals(forest.getNode(0).getTimestamps().getVal2().intValue(), 6);
		assertNull(forest.getNode(0).getAncestor());
		
		assertEquals(forest.getEdge(0, 1).get(0), 2);
		assertEquals(forest.getEdge(1, 0).get(0), 2);
		
		assertEquals(forest.getNode(1).getColor(), SearchNode.BLACK);
		assertEquals(forest.getNode(1).getTimestamps().getVal1().intValue(), 2);
		assertEquals(forest.getNode(1).getTimestamps().getVal2().intValue(), 5);
		assertEquals(forest.getNode(1).getAncestor(), forest.getNode(0));
		
		assertEquals(forest.getEdge(1, 2).get(0), 1);
		assertEquals(forest.getEdge(2, 1).get(0), 1);
		
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
	
	@Test
	void testPrim(){
		//Simple Graph
		setUpSceneSimpleGraph3();
		
		MatrixGraph<SearchNode<String>> tree = matrixGraph.prim(matrixGraph.getNode(0));
		
		assertEquals(tree.getNode(0).getColor(), SearchNode.BLACK);
		assertEquals(tree.getNode(0).getDistance(), 0);
		assertNull(tree.getNode(0).getAncestor());
		
		assertEquals(tree.getEdge(0, 2).get(0), 2);
		assertEquals(tree.getEdge(2, 0).get(0), 2);
		
		assertEquals(tree.getNode(2).getColor(), SearchNode.BLACK);
		assertEquals(tree.getNode(2).getDistance(), 2);
		assertEquals(tree.getNode(2).getAncestor(), tree.getNode(0));
		
		assertEquals(tree.getEdge(1, 2).get(0), 3);
		assertEquals(tree.getEdge(2, 1).get(0), 3);
		
		assertEquals(tree.getNode(1).getColor(), SearchNode.BLACK);
		assertEquals(tree.getNode(1).getDistance(), 3);
		assertEquals(tree.getNode(1).getAncestor(), tree.getNode(2));
		
		assertEquals(tree.getEdge(3, 2).get(0), 1);
		assertEquals(tree.getEdge(2, 3).get(0), 1);
		
		assertEquals(tree.getNode(3).getColor(), SearchNode.BLACK);
		assertEquals(tree.getNode(3).getDistance(), 1);
		assertEquals(tree.getNode(3).getAncestor(), tree.getNode(2));
		//...
		
		//Multi Directed Graph
		setUpSceneMultiDirectedGraph3();
		
		tree = matrixGraph.prim(matrixGraph.getNode(2));
		
		assertEquals(tree.getNode(2).getColor(), SearchNode.BLACK);
		assertEquals(tree.getNode(2).getDistance(), 0);
		assertNull(tree.getNode(2).getAncestor());
		
//		assertEquals(tree.getEdge(0, 2).get(0), 3);
//		assertEquals(tree.getEdge(2, 0).get(0), 3);
//		
//		assertEquals(tree.getNode(0).getColor(), SearchNode.BLACK);
//		assertEquals(tree.getNode(0).getDistance(), 3);
//		assertEquals(tree.getNode(0).getAncestor(), tree.getNode(2));
//		
//		assertEquals(tree.getEdge(0, 1).get(0), 2);
//		assertEquals(tree.getEdge(1, 0).get(0), 2);
//		
//		assertEquals(tree.getNode(1).getColor(), SearchNode.BLACK);
//		assertEquals(tree.getNode(1).getDistance(), 2);
//		assertEquals(tree.getNode(1).getAncestor(), tree.getNode(0));
//		
//		assertEquals(tree.getNode(3).getColor(), SearchNode.BLACK);
//		assertEquals(tree.getNode(3).getDistance(), Integer.MAX_VALUE);
//		assertNull(tree.getNode(2).getAncestor());
		//...
	}
	
	@Test
	void testKruskal(){
		setUpSceneSimpleGraph4();
		
		MatrixGraph<SearchNode<String>> tree = matrixGraph.kruskal();
		
		assertEquals(7, tree.getEdge(0, 2).get(0));
		assertEquals(5, tree.getEdge(1, 2).get(0));
		assertEquals(3, tree.getEdge(1, 3).get(0));
		assertEquals(7, tree.getEdge(2, 0).get(0));
		assertEquals(5, tree.getEdge(2, 1).get(0));
		assertEquals(3, tree.getEdge(3, 1).get(0));
		
		int edges = 0;
		
		for(int i = 0; i < tree.getEdges().size(); i++) {
			for(int j = 0; j < tree.getEdges().get(0).size(); j++) {
				if(tree.getEdges().get(i).get(j).size() != 0) {
					edges++;
				}
			}
		}
		
		assertEquals(6, edges);
		
		setUpSceneMultiDirectedGraph3();
		
		tree = matrixGraph.kruskal();
		
		assertEquals(2, tree.getEdge(0, 1).get(0));
		assertEquals(2, tree.getEdge(1, 0).get(0));
		assertEquals(1, tree.getEdge(1, 2).get(0));
		assertEquals(1, tree.getEdge(2, 1).get(0));
		
		edges = 0;
		
		for(int i = 0; i < tree.getEdges().size(); i++) {
			for(int j = 0; j < tree.getEdges().get(0).size(); j++) {
				if(tree.getEdges().get(i).get(j).size() != 0) {
					edges++;
				}
			}
		}
		
		assertEquals(4, edges);
	}
}

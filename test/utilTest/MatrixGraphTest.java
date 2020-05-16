package utilTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import exception.LoopException;
import exception.MultipleEdgesException;
import exception.NodeNotFoundException;
import util.MatrixGraph;
import util.SearchNode;

class MatrixGraphTest {

	//Tested Class
	private MatrixGraph<String> matrixGraph;
	
	//Scene
	private void setUpSceneSimpleGraphEmpty() {
		this.matrixGraph=new MatrixGraph<String>(false,false,false);
	}
	
	private void setUpSceneSimpleGraphVertex() {
		this.matrixGraph=new MatrixGraph<String>(false,false,false);
		
		matrixGraph.addVertex("Johan");
		matrixGraph.addVertex("Esteban");
		matrixGraph.addVertex("Mateo");
	}
	
	private void setUpSceneMultiDirectedGraphVertex() {
		this.matrixGraph=new MatrixGraph<String>(true,true,true);
		
		matrixGraph.addVertex("Johan");
		matrixGraph.addVertex("Esteban");
		matrixGraph.addVertex("Mateo");
	}
	
	private void setUpSceneSimpleGraph() {
		this.matrixGraph=new MatrixGraph<String>(false,false,false);
		
		matrixGraph.addVertex("Johan");
		matrixGraph.addVertex("Esteban");
		matrixGraph.addVertex("Mateo");
		
		matrixGraph.addEdge(matrixGraph.getVertex(0),matrixGraph.getVertex(1),2);
		matrixGraph.addEdge(matrixGraph.getVertex(1),matrixGraph.getVertex(2),1);
	}
	
	private void setUpSceneSimpleGraph2() {
		this.matrixGraph=new MatrixGraph<String>(false,false,false);
		
		matrixGraph.addVertex("Johan");
		matrixGraph.addVertex("Esteban");
		matrixGraph.addVertex("Mateo");
		matrixGraph.addVertex("Juan");
		
		matrixGraph.addEdge(matrixGraph.getVertex(0),matrixGraph.getVertex(1),2);
		matrixGraph.addEdge(matrixGraph.getVertex(1),matrixGraph.getVertex(2),1);
	}
	
	private void setUpSceneSimpleGraph3() {
		this.matrixGraph=new MatrixGraph<String>(false,false,false);
		
		matrixGraph.addVertex("Johan");
		matrixGraph.addVertex("Esteban");
		matrixGraph.addVertex("Mateo");
		matrixGraph.addVertex("Juan");
		
		matrixGraph.addEdge(matrixGraph.getVertex(0),matrixGraph.getVertex(1),5);
		matrixGraph.addEdge(matrixGraph.getVertex(1),matrixGraph.getVertex(2),3);
		matrixGraph.addEdge(matrixGraph.getVertex(2),matrixGraph.getVertex(3),1);
		matrixGraph.addEdge(matrixGraph.getVertex(3),matrixGraph.getVertex(0),6);
		
		matrixGraph.addEdge(matrixGraph.getVertex(0),matrixGraph.getVertex(2),2);
		matrixGraph.addEdge(matrixGraph.getVertex(1),matrixGraph.getVertex(3),4);
	}
	
	private void setUpSceneSimpleGraph4() {
		matrixGraph = new MatrixGraph<String>(false,false,false);
		
		matrixGraph.addVertex("a");
		matrixGraph.addVertex("b");
		matrixGraph.addVertex("c");
		matrixGraph.addVertex("d");
		
		matrixGraph.addEdge("a", "b", 10);
		matrixGraph.addEdge("a", "c", 7);
		matrixGraph.addEdge("c", "b", 5);
		matrixGraph.addEdge("b", "d", 3);
	}
	
	private void setUpSceneMultiDirectedGraph() {
		this.matrixGraph=new MatrixGraph<String>(true,true,true);
		
		matrixGraph.addVertex("Johan");
		matrixGraph.addVertex("Esteban");
		matrixGraph.addVertex("Mateo");
		
		matrixGraph.addEdge(matrixGraph.getVertex(0),matrixGraph.getVertex(1),2);
		matrixGraph.addEdge(matrixGraph.getVertex(0),matrixGraph.getVertex(0),4);
		matrixGraph.addEdge(matrixGraph.getVertex(1),matrixGraph.getVertex(2),1);
		matrixGraph.addEdge(matrixGraph.getVertex(2),matrixGraph.getVertex(0),3);
		matrixGraph.addEdge(matrixGraph.getVertex(2),matrixGraph.getVertex(0),0);
		matrixGraph.addEdge(matrixGraph.getVertex(2),matrixGraph.getVertex(0),3);
	}
	
	private void setUpSceneMultiDirectedGraph2() {
		this.matrixGraph=new MatrixGraph<String>(true,true,true);
		
		matrixGraph.addVertex("Johan");
		matrixGraph.addVertex("Esteban");
		matrixGraph.addVertex("Mateo");
		matrixGraph.addVertex("Juan");
		
		matrixGraph.addEdge(matrixGraph.getVertex(0),matrixGraph.getVertex(1),2);
		matrixGraph.addEdge(matrixGraph.getVertex(0),matrixGraph.getVertex(0),4);
		matrixGraph.addEdge(matrixGraph.getVertex(1),matrixGraph.getVertex(2),1);
		matrixGraph.addEdge(matrixGraph.getVertex(1),matrixGraph.getVertex(2),5);
		matrixGraph.addEdge(matrixGraph.getVertex(2),matrixGraph.getVertex(0),3);
	}
	
	private void setUpScenePseudoGraph() {
		this.matrixGraph=new MatrixGraph<String>(false,true,true);
		
		matrixGraph.addVertex("Johan");
		matrixGraph.addVertex("Esteban");
		matrixGraph.addVertex("Mateo");
		matrixGraph.addVertex("Juan");
		
		matrixGraph.addEdge(matrixGraph.getVertex(0),matrixGraph.getVertex(1),2);
		matrixGraph.addEdge(matrixGraph.getVertex(0),matrixGraph.getVertex(0),4);
		matrixGraph.addEdge(matrixGraph.getVertex(1),matrixGraph.getVertex(2),1);
		matrixGraph.addEdge(matrixGraph.getVertex(1),matrixGraph.getVertex(2),5);
		matrixGraph.addEdge(matrixGraph.getVertex(2),matrixGraph.getVertex(1),5);
		matrixGraph.addEdge(matrixGraph.getVertex(2),matrixGraph.getVertex(0),3);
	}
	
	//Test
	@Test
	void testAddNode() {
		
		setUpSceneSimpleGraphEmpty();
		matrixGraph.addVertex("Johan");
		matrixGraph.addVertex("Esteban");
		matrixGraph.addVertex("Mateo");
		
		assertEquals(matrixGraph.getVertex(0),"Johan");
		assertEquals(matrixGraph.getVertex(1),"Esteban");
		assertEquals(matrixGraph.getVertex(2),"Mateo");
		assertEquals(matrixGraph.getEdges().size(),3);
		for(int i = 0; i < 3; i++){
			assertEquals(matrixGraph.getEdges().get(i).size(),3);
		}
		
	}
	
	@Test
	void testAddEdge(){
		
		//Simple Graph
		setUpSceneSimpleGraphVertex();
		matrixGraph.addEdge(matrixGraph.getVertex(0),matrixGraph.getVertex(1),2);
		matrixGraph.addEdge(matrixGraph.getVertex(1),matrixGraph.getVertex(2),1);
		
		assertThrows(NodeNotFoundException.class, ()->{
			matrixGraph.addEdge(matrixGraph.getVertex(0),"Juan",1);
		});
		assertThrows(LoopException.class, ()->{
			matrixGraph.addEdge(matrixGraph.getVertex(0),matrixGraph.getVertex(0),1);
		});
		assertThrows(MultipleEdgesException.class, ()->{
			matrixGraph.addEdge(matrixGraph.getVertex(0),matrixGraph.getVertex(1),2);
		});
		
		assertEquals(matrixGraph.getEdge(0, 1).get(0).intValue(),2);
		assertEquals(matrixGraph.getEdge(1, 0).get(0).intValue(),2);
		
		assertEquals(matrixGraph.getEdge(1, 2).get(0).intValue(),1);
		assertEquals(matrixGraph.getEdge(2, 1).get(0).intValue(),1);
		//...
		
		//Multi Directed Graph
		setUpSceneMultiDirectedGraphVertex();
		matrixGraph.addEdge(matrixGraph.getVertex(0),matrixGraph.getVertex(1),2);
		matrixGraph.addEdge(matrixGraph.getVertex(1),matrixGraph.getVertex(2),1);
		matrixGraph.addEdge(matrixGraph.getVertex(2),matrixGraph.getVertex(0),3);
		matrixGraph.addEdge(matrixGraph.getVertex(2),matrixGraph.getVertex(0),0);
		matrixGraph.addEdge(matrixGraph.getVertex(2),matrixGraph.getVertex(0),3);
		
		assertThrows(NodeNotFoundException.class, ()->{
			matrixGraph.addEdge(matrixGraph.getVertex(0),"Juan",1);
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
		matrixGraph.removeVertex(matrixGraph.getVertex(1));
		
		assertThrows(NodeNotFoundException.class, ()->{
			matrixGraph.removeVertex("Juan");
		});
		
		assertEquals(matrixGraph.getVertices().size(), 2);
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
		matrixGraph.removeEdge(matrixGraph.getVertex(1), matrixGraph.getVertex(0), 2);
		
		assertThrows(NodeNotFoundException.class, ()->{
			matrixGraph.removeEdge(matrixGraph.getVertex(1), "Juan", 1);
		});
		
		assertEquals(matrixGraph.getEdge(0,1).size(), 0);
		assertEquals(matrixGraph.getEdge(1,0).size(), 0);
		//...
		
		//Multi Directed Graph
		setUpSceneMultiDirectedGraph();
		matrixGraph.removeEdge(matrixGraph.getVertex(0),matrixGraph.getVertex(0),4);
		matrixGraph.removeEdge(matrixGraph.getVertex(2),matrixGraph.getVertex(0),3);
		
		assertThrows(NodeNotFoundException.class, ()->{
			matrixGraph.removeEdge(matrixGraph.getVertex(1), "Juan", 1);
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
		MatrixGraph<SearchNode<String>> tree = matrixGraph.bfs(matrixGraph.getVertex(0));
		
		assertEquals(tree.getVertex(0).getColor(), SearchNode.BLACK);
		assertEquals(tree.getVertex(0).getDistance(), 0);
		
		assertEquals(tree.getEdge(1, 0).get(0).intValue(), 2);
		assertEquals(tree.getEdge(0, 1).get(0).intValue(), 2);
		
		assertEquals(tree.getVertex(1).getColor(), SearchNode.BLACK);
		assertEquals(tree.getVertex(1).getDistance(), 1);
		assertEquals(tree.getVertex(1).getAncestor(), tree.getVertex(0));
		
		assertEquals(tree.getEdge(1, 2).get(0).intValue(), 1);
		assertEquals(tree.getEdge(2, 1).get(0).intValue(), 1);
		
		assertEquals(tree.getVertex(2).getColor(), SearchNode.BLACK);
		assertEquals(tree.getVertex(2).getDistance(), 2);
		assertEquals(tree.getVertex(2).getAncestor(), tree.getVertex(1));
			//2
		tree = matrixGraph.bfs(matrixGraph.getVertex(2));
		
		assertEquals(tree.getVertex(2).getColor(), SearchNode.BLACK);
		assertEquals(tree.getVertex(2).getDistance(), 0);
		
		assertEquals(tree.getEdge(1, 2).get(0).intValue(), 1);
		assertEquals(tree.getEdge(2, 1).get(0).intValue(), 1);
		
		assertEquals(tree.getVertex(1).getColor(), SearchNode.BLACK);
		assertEquals(tree.getVertex(1).getDistance(), 1);
		assertEquals(tree.getVertex(1).getAncestor(), tree.getVertex(2));
		
		assertEquals(tree.getEdge(1, 0).get(0).intValue(), 2);
		assertEquals(tree.getEdge(0, 1).get(0).intValue(), 2);
		
		assertEquals(tree.getVertex(0).getColor(), SearchNode.BLACK);
		assertEquals(tree.getVertex(0).getDistance(), 2);
		assertEquals(tree.getVertex(0).getAncestor(), tree.getVertex(1));
		//...
		
		//Multi Directed Graph
		setUpSceneMultiDirectedGraph();
			//1
		tree = matrixGraph.bfs(matrixGraph.getVertex(1));
		
		assertEquals(tree.getVertex(1).getColor(), SearchNode.BLACK);
		assertEquals(tree.getVertex(1).getDistance(), 0);
		
		assertEquals(tree.getEdge(2, 1).get(0).intValue(), 1);
		assertEquals(tree.getEdge(1, 2).get(0).intValue(), 1);
		
		assertEquals(tree.getVertex(2).getColor(), SearchNode.BLACK);
		assertEquals(tree.getVertex(2).getDistance(), 1);
		assertEquals(tree.getVertex(2).getAncestor(), tree.getVertex(1));
		
		assertEquals(tree.getEdge(0, 2).get(0).intValue(), 0);
		assertEquals(tree.getEdge(2, 0).get(0).intValue(), 0);
		
		assertEquals(tree.getVertex(0).getColor(), SearchNode.BLACK);
		assertEquals(tree.getVertex(0).getDistance(), 2);
		assertEquals(tree.getVertex(0).getAncestor(), tree.getVertex(2));
			//2
		matrixGraph.removeVertex(matrixGraph.getVertex(2));
		tree = matrixGraph.bfs(matrixGraph.getVertex(1));
		
		assertEquals(tree.getVertex(1).getColor(), SearchNode.BLACK);
		assertEquals(tree.getVertex(1).getDistance(), 0);
		
		assertEquals(tree.getVertex(0).getColor(), SearchNode.WHITE);
		assertEquals(tree.getVertex(0).getDistance(), Integer.MAX_VALUE);
		//...
	}
	
	@Test
	void testDFS() {
		//Simple Graph
		setUpSceneSimpleGraph2();
		
		MatrixGraph<SearchNode<String>> forest = matrixGraph.dfs();
		
		assertEquals(forest.getVertex(0).getColor(), SearchNode.BLACK);
		assertEquals(forest.getVertex(0).getTimestamps().getVal1(), 1);
		assertEquals(forest.getVertex(0).getTimestamps().getVal2(), 6);
		assertNull(forest.getVertex(0).getAncestor());
		
		assertEquals(forest.getEdge(0, 1).get(0), 2);
		assertEquals(forest.getEdge(1, 0).get(0), 2);
		
		assertEquals(forest.getVertex(1).getColor(), SearchNode.BLACK);
		assertEquals(forest.getVertex(1).getTimestamps().getVal1(), 2);
		assertEquals(forest.getVertex(1).getTimestamps().getVal2(), 5);
		assertEquals(forest.getVertex(1).getAncestor(), forest.getVertex(0));
		
		assertEquals(forest.getEdge(1, 2).get(0), 1);
		assertEquals(forest.getEdge(2, 1).get(0), 1);
		
		assertEquals(forest.getVertex(2).getColor(), SearchNode.BLACK);
		assertEquals(forest.getVertex(2).getTimestamps().getVal1(), 3);
		assertEquals(forest.getVertex(2).getTimestamps().getVal2(), 4);
		assertEquals(forest.getVertex(2).getAncestor(), forest.getVertex(1));
		
		assertEquals(forest.getVertex(3).getColor(), SearchNode.BLACK);
		assertEquals(forest.getVertex(3).getTimestamps().getVal1(), 7);
		assertEquals(forest.getVertex(3).getTimestamps().getVal2(), 8);
		assertNull(forest.getVertex(3).getAncestor());
		//...
		
		//MultiDirectedGraph
		setUpSceneMultiDirectedGraph2();
		
		forest = matrixGraph.dfs();
		
		assertEquals(forest.getVertex(0).getColor(), SearchNode.BLACK);
		assertEquals(forest.getVertex(0).getTimestamps().getVal1().intValue(), 1);
		assertEquals(forest.getVertex(0).getTimestamps().getVal2().intValue(), 6);
		assertNull(forest.getVertex(0).getAncestor());
		
		assertEquals(forest.getEdge(0, 1).get(0), 2);
		assertEquals(forest.getEdge(1, 0).get(0), 2);
		
		assertEquals(forest.getVertex(1).getColor(), SearchNode.BLACK);
		assertEquals(forest.getVertex(1).getTimestamps().getVal1().intValue(), 2);
		assertEquals(forest.getVertex(1).getTimestamps().getVal2().intValue(), 5);
		assertEquals(forest.getVertex(1).getAncestor(), forest.getVertex(0));
		
		assertEquals(forest.getEdge(1, 2).get(0), 1);
		assertEquals(forest.getEdge(2, 1).get(0), 1);
		
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
	void testPrim(){
		//Simple Graph
		setUpSceneSimpleGraph3();
		
		MatrixGraph<SearchNode<String>> tree = matrixGraph.prim(matrixGraph.getVertex(0));
		
		assertEquals(tree.getVertex(0).getColor(), SearchNode.BLACK);
		assertEquals(tree.getVertex(0).getDistance(), 0);
		assertNull(tree.getVertex(0).getAncestor());
		
		assertEquals(tree.getEdge(0, 2).get(0), 2);
		assertEquals(tree.getEdge(2, 0).get(0), 2);
		
		assertEquals(tree.getVertex(2).getColor(), SearchNode.BLACK);
		assertEquals(tree.getVertex(2).getDistance(), 2);
		assertEquals(tree.getVertex(2).getAncestor(), tree.getVertex(0));
		
		assertEquals(tree.getEdge(1, 2).get(0), 3);
		assertEquals(tree.getEdge(2, 1).get(0), 3);
		
		assertEquals(tree.getVertex(1).getColor(), SearchNode.BLACK);
		assertEquals(tree.getVertex(1).getDistance(), 3);
		assertEquals(tree.getVertex(1).getAncestor(), tree.getVertex(2));
		
		assertEquals(tree.getEdge(3, 2).get(0), 1);
		assertEquals(tree.getEdge(2, 3).get(0), 1);
		
		assertEquals(tree.getVertex(3).getColor(), SearchNode.BLACK);
		assertEquals(tree.getVertex(3).getDistance(), 1);
		assertEquals(tree.getVertex(3).getAncestor(), tree.getVertex(2));
		//...
		
		//Pseudo Graph
		setUpScenePseudoGraph();
		
		tree = matrixGraph.prim(matrixGraph.getVertex(2));
		
		assertEquals(tree.getVertex(2).getColor(), SearchNode.BLACK);
		assertEquals(tree.getVertex(2).getDistance(), 0);
		assertNull(tree.getVertex(2).getAncestor());
		
		assertEquals(tree.getEdge(2, 1).get(0), 1);
		assertEquals(tree.getEdge(1, 2).get(0), 1);
		
		assertEquals(tree.getVertex(1).getColor(), SearchNode.BLACK);
		assertEquals(tree.getVertex(1).getDistance(), 1);
		assertEquals(tree.getVertex(1).getAncestor(), tree.getVertex(2));
		
		assertEquals(tree.getEdge(0, 1).get(0), 2);
		assertEquals(tree.getEdge(1, 0).get(0), 2);
		
		assertEquals(tree.getVertex(0).getColor(), SearchNode.BLACK);
		assertEquals(tree.getVertex(0).getDistance(), 2);
		assertEquals(tree.getVertex(0).getAncestor(), tree.getVertex(1));
		
		assertEquals(tree.getVertex(3).getColor(), SearchNode.BLACK);
		assertEquals(tree.getVertex(3).getDistance(), Integer.MAX_VALUE);
		assertNull(tree.getVertex(2).getAncestor());
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
		
		setUpScenePseudoGraph();
		
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
	
	@Test
	void testDijkstra() {
		//Simple Graph
		setUpSceneSimpleGraph3();
		ArrayList<SearchNode<String>> list = matrixGraph.dijkstra(matrixGraph.getVertex(3));
		
		assertEquals(list.get(0).getObject(), matrixGraph.getVertex(0));
		assertEquals(list.get(0).getDistance(), 3);
		assertEquals(list.get(0).getAncestor(), list.get(2));
		
		assertEquals(list.get(1).getObject(), matrixGraph.getVertex(1));
		assertEquals(list.get(1).getDistance(), 4);
		assertEquals(list.get(1).getAncestor(), list.get(3));
		
		assertEquals(list.get(2).getObject(), matrixGraph.getVertex(2));
		assertEquals(list.get(2).getDistance(), 1);
		assertEquals(list.get(2).getAncestor(), list.get(3));
		
		assertEquals(list.get(3).getObject(), matrixGraph.getVertex(3));
		assertEquals(list.get(3).getDistance(), 0);
		assertNull(list.get(3).getAncestor());
		//...
		
		//Multi Directed Graph
		setUpSceneMultiDirectedGraph2();
		list = matrixGraph.dijkstra(matrixGraph.getVertex(0));
		
		assertEquals(list.get(0).getObject(), matrixGraph.getVertex(0));
		assertEquals(list.get(0).getDistance(), 0);
		assertNull(list.get(0).getAncestor());
		
		assertEquals(list.get(1).getObject(), matrixGraph.getVertex(1));
		assertEquals(list.get(1).getDistance(), 2);
		assertEquals(list.get(1).getAncestor(), list.get(0));
		
		assertEquals(list.get(2).getObject(), matrixGraph.getVertex(2));
		assertEquals(list.get(2).getDistance(), 3);
		assertEquals(list.get(2).getAncestor(), list.get(1));
		
		assertEquals(list.get(3).getObject(), matrixGraph.getVertex(3));
		assertEquals(list.get(3).getDistance(), Integer.MAX_VALUE);
		assertNull(list.get(3).getAncestor());
		//...
	}
	
	@Test
	void testFloydWarshall() {
		//Simple Graph
		setUpSceneSimpleGraph3();
		int[][] distances = matrixGraph.floydWarshall();
		
		assertEquals(distances[0][0], 0);
		assertEquals(distances[0][1], 5);
		assertEquals(distances[0][2], 2);
		assertEquals(distances[0][3], 3);
		
		assertEquals(distances[1][0], 5);
		assertEquals(distances[1][1], 0);
		assertEquals(distances[1][2], 3);
		assertEquals(distances[1][3], 4);
		
		assertEquals(distances[2][0], 2);
		assertEquals(distances[2][1], 3);
		assertEquals(distances[2][2], 0);
		assertEquals(distances[2][3], 1);
		
		assertEquals(distances[3][0], 3);
		assertEquals(distances[3][1], 4);
		assertEquals(distances[3][2], 1);
		assertEquals(distances[3][3], 0);
		//...
		
		//Multi Directed Graph
		setUpSceneMultiDirectedGraph2();
		distances = matrixGraph.floydWarshall();
		
		assertEquals(distances[0][0], 0);
		assertEquals(distances[0][1], 2);
		assertEquals(distances[0][2], 3);
		assertEquals(distances[0][3], Integer.MAX_VALUE);
		
		assertEquals(distances[1][0], 4);
		assertEquals(distances[1][1], 0);
		assertEquals(distances[1][2], 1);
		assertEquals(distances[1][3], Integer.MAX_VALUE);
		
		assertEquals(distances[2][0], 3);
		assertEquals(distances[2][1], 5);
		assertEquals(distances[2][2], 0);
		assertEquals(distances[2][3], Integer.MAX_VALUE);
		
		assertEquals(distances[3][0], Integer.MAX_VALUE);
		assertEquals(distances[3][1], Integer.MAX_VALUE);
		assertEquals(distances[3][2], Integer.MAX_VALUE);
		assertEquals(distances[3][3], 0);
		//...
	}
	
}

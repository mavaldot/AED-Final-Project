package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import exception.AlreadyExistException;
import exception.LoopException;
import exception.MultipleEdgesException;
import exception.NodeNotFoundException;

class MatrixGraphTest {

	//Tested Class
	private MatrixGraph<String> matrixGraph;
	
	//Scene
	private void setUpSceneSimpleGraphEmpty() {
		this.matrixGraph=new MatrixGraph<String>(false,false,false);
	}
	
	private void setUpSceneSimpleGraph() {
		this.matrixGraph=new MatrixGraph<String>(false,false,false);
		matrixGraph.addNode("Johan");
		matrixGraph.addNode("Esteban");
		matrixGraph.addNode("Mateo");
	}
	
	private void setUpSceneMultiGraph() {
		this.matrixGraph=new MatrixGraph<String>(false,true,false);
		matrixGraph.addNode("Johan");
		matrixGraph.addNode("Esteban");
		matrixGraph.addNode("Mateo");
	}

	private void setUpScenePseudoGraph() {
		this.matrixGraph=new MatrixGraph<String>(false,true,true);
		matrixGraph.addNode("Johan");
		matrixGraph.addNode("Esteban");
		matrixGraph.addNode("Mateo");
	}
	
	private void setUpSceneDirectedGraph() {
		this.matrixGraph=new MatrixGraph<String>(true,false,true);
		matrixGraph.addNode("Johan");
		matrixGraph.addNode("Esteban");
		matrixGraph.addNode("Mateo");
	}
	
	private void setUpSceneMultiDirectedGraph() {
		this.matrixGraph=new MatrixGraph<String>(true,true,true);
		matrixGraph.addNode("Johan");
		matrixGraph.addNode("Esteban");
		matrixGraph.addNode("Mateo");
	}
	
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
		setUpSceneSimpleGraph();
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
		
		assertEquals(matrixGraph.getEdge(0, 1).get(0),2);
		assertEquals(matrixGraph.getEdge(1, 0).get(0),2);
		
		assertEquals(matrixGraph.getEdge(1, 2).get(0),1);
		assertEquals(matrixGraph.getEdge(2, 1).get(0),1);
		//...
		
		//Multi Directed Graph
		setUpSceneMultiDirectedGraph();
		matrixGraph.addEdge(matrixGraph.getNode(0),matrixGraph.getNode(1),2);
		matrixGraph.addEdge(matrixGraph.getNode(1),matrixGraph.getNode(2),1);
		matrixGraph.addEdge(matrixGraph.getNode(2),matrixGraph.getNode(0),3);
		matrixGraph.addEdge(matrixGraph.getNode(2),matrixGraph.getNode(0),0);
		matrixGraph.addEdge(matrixGraph.getNode(2),matrixGraph.getNode(0),3);
		
		assertThrows(NodeNotFoundException.class, ()->{
			matrixGraph.addEdge(matrixGraph.getNode(0),"Juan",1);
		});
		
		assertEquals(matrixGraph.getEdge(0, 1).get(0), 2);
		assertEquals(matrixGraph.getEdge(1, 0).size(), 0);
		
		assertEquals(matrixGraph.getEdge(1, 2).get(0), 1);
		assertEquals(matrixGraph.getEdge(2, 1).size(), 0);
		
		assertEquals(matrixGraph.getEdge(2, 0).get(0), 3);
		assertEquals(matrixGraph.getEdge(2, 0).get(1), 0);
		assertEquals(matrixGraph.getEdge(2, 0).get(2), 3);
		assertEquals(matrixGraph.getEdge(0, 2).size(), 0);
		//...
		
	}
	
	@Test
	void testRemoveNode() {
		
	}
	
}

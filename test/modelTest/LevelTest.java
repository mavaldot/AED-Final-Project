package modelTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.Level;
import model.Node;
import model.Level.Color;
import util.MatrixGraph;

class LevelTest {
	
	//Tested Class
	private Level level;
	
	//Scene
	private void setUpSceneNormalLevel() {
		MatrixGraph<Node> graph = new MatrixGraph<Node>(true, false, false);
		graph.addVertex(new Node(1,1));
		graph.addVertex(new Node(2,2));
		graph.addVertex(new Node(3,3));
		graph.addEdge(graph.getVertex(0), graph.getVertex(1), 1);
		graph.addEdge(graph.getVertex(1), graph.getVertex(2), 2);
		graph.addEdge(graph.getVertex(2), graph.getVertex(1), 1);
		
		level = new Level("Johan", 2, Color.RED, 0, 2, graph, 0);
	}
	
	private void setUpSceneStarsLevel() {
		MatrixGraph<Node> graph = new MatrixGraph<Node>(true, false, false);
		
		graph.addVertex(new Node(0,0));
		graph.addVertex(new Node(1,1));
		graph.addVertex(new Node(2,2));
		graph.addVertex(new Node(3,3));
		graph.addVertex(new Node(4,4));
		
		graph.addEdge(graph.getVertex(0), graph.getVertex(1), 1);
		graph.addEdge(graph.getVertex(0), graph.getVertex(2), 1);
		graph.addEdge(graph.getVertex(0), graph.getVertex(3), 1);
		
		graph.addEdge(graph.getVertex(1), graph.getVertex(4), 2);
		graph.addEdge(graph.getVertex(2), graph.getVertex(4), 3);
		graph.addEdge(graph.getVertex(3), graph.getVertex(4), 6);
		
		level = new Level("Johan", 2, Color.RED, 0, 4, graph, 0);
	}
	
	//Test
	@Test
	void testMove() {
		setUpSceneNormalLevel();
		
		assertEquals(level.getPlayer(), 0);
		assertEquals(level.getMovements(), 7);
		
		assertTrue(level.move(1));
		assertEquals(level.getPlayer(), 1);
		assertEquals(level.getMovements(), 6);
		
		assertTrue(level.move(2));
		assertEquals(level.getPlayer(), 2);
		assertEquals(level.getMovements(), 4);
		
		assertTrue(level.move(1));
		assertEquals(level.getPlayer(), 1);
		assertEquals(level.getMovements(), 3);
		
		assertFalse(level.move(0));
		assertEquals(level.getPlayer(), 1);
		assertEquals(level.getMovements(), 3);
		
		assertTrue(level.move(2));
		assertEquals(level.getPlayer(), 2);
		assertEquals(level.getMovements(), 1);
		
		assertTrue(level.move(1));
		assertEquals(level.getPlayer(), 1);
		assertEquals(level.getMovements(), 0);
		
		assertFalse(level.move(2));
		assertEquals(level.getPlayer(), 1);
		assertEquals(level.getMovements(), 0);
	}
	
	@Test
	void testWin() {
		setUpSceneNormalLevel();
		
		level.move(1);
		assertFalse(level.win());
		
		level.move(2);
		assertTrue(level.win());
	}
	
	@Test
	void testLose() {
		setUpSceneNormalLevel();
		
		level.move(1);
		assertFalse(level.lose());
		
		level.move(2);
		assertFalse(level.lose());
		
		level.move(1);
		assertFalse(level.lose());
		
		level.move(2);
		assertFalse(level.lose());
		
		level.move(1);
		assertTrue(level.lose());
	}
	
	@Test
	void testStarsEarned() {
		//3
		setUpSceneStarsLevel();
		
		level.move(1);
		assertEquals(level.starsEarned(), 0);
		
		level.move(4);
		assertEquals(level.starsEarned(), 3);
		//...
		
		//2
		setUpSceneStarsLevel();
		
		level.move(2);
		assertEquals(level.starsEarned(), 0);
		
		level.move(4);
		assertEquals(level.starsEarned(), 2);
		//...
		
		//1
		setUpSceneStarsLevel();
		
		level.move(3);
		assertEquals(level.starsEarned(), 0);
		
		level.move(4);
		assertEquals(level.starsEarned(), 1);
		//...
	}
	
	@Test
	public void testStarsInGame() {
		//3
		setUpSceneStarsLevel();
		
		level.move(1);
		assertEquals(level.starsInGame(), 0);
		
		level.move(4);
		assertEquals(level.starsInGame(), 3);
		//...
		
		//2
		setUpSceneStarsLevel();
		
		level.move(2);
		assertEquals(level.starsInGame(), 0);
		
		level.move(4);
		assertEquals(level.starsInGame(), 2);
		//...
		
		//1
		setUpSceneStarsLevel();
		
		level.move(3);
		assertEquals(level.starsInGame(), 0);
		
		level.move(4);
		assertEquals(level.starsInGame(), 1);
		//...
	}
	
	@Test
	void testCheckPlayable() {
		MatrixGraph<Node> graph = new MatrixGraph<Node>(true, false, false);
		graph.addVertex(new Node(1,1));
		graph.addVertex(new Node(2,2));
		
		assertFalse(Level.checkPlayable(graph, 0, 1));
		
		graph.addEdge(graph.getVertex(0), graph.getVertex(1), 1);
		
		assertTrue(Level.checkPlayable(graph, 0, 1));
	}

}

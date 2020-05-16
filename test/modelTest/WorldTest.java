package modelTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exception.AlreadyExistException;
import model.Level;
import model.Level.Color;
import model.Node;
import model.World;
import util.MatrixGraph;

class WorldTest {

	//Tested Class
	private World world;
	
	//Scene
	private void setUpSceneEmptyWorld() {
		world = new World("World", 0);
	}
	
	private void setUpSceneNormalWorld() {
		world = new World("World", 0);
		
		MatrixGraph<Node> graph1 = new MatrixGraph<Node>(true, false, false);
		graph1.addVertex(new Node(1,1));
		graph1.addVertex(new Node(2,2));
		graph1.addVertex(new Node(3,3));
		graph1.addEdge(graph1.getVertex(0), graph1.getVertex(1), 1);
		
		world.addLevel(new Level("Johan", 2, Color.RED, 0, 1, graph1, 0));
		world.addLevel(new Level("Esteban", 0, Color.GREEN, 0, 1, graph1, 0));
	}
	
	//Test
	@Test
	void testAdd() {
		setUpSceneEmptyWorld();
		
		MatrixGraph<Node> graph1 = new MatrixGraph<Node>(true, false, false);
		graph1.addVertex(new Node(1,1));
		graph1.addVertex(new Node(2,2));
		graph1.addEdge(graph1.getVertex(0), graph1.getVertex(1), 1);
		Level lvl1 = new Level("Johan", 2, Color.RED, 0, 1, graph1, 0);
		
		world.addLevel(lvl1);
		
		Level lvl2 = new Level("Esteban", 0, Color.GREEN, 0, 1, graph1, 0);
		
		world.addLevel(lvl2);
		
		assertThrows(AlreadyExistException.class, ()->{
			world.addLevel(lvl1);
		});
		
		assertEquals(world.getLevels().get(0), lvl2);
		assertEquals(world.getLevels().get(1), lvl1);
	}
	
	@Test
	void searchLevel() {
		setUpSceneNormalWorld();
		
		assertNotNull(world.searchLevel("Johan"));
		assertNotNull(world.searchLevel("Esteban"));
		assertNull(world.searchLevel("Mateo"));
	}
	

}

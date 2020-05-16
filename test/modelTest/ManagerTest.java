package modelTest;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import model.Level;
import model.Level.Color;
import model.Manager;

class ManagerTest {

	//Tested Class
	private Manager manager;
	
	//Scene
	private void setUpSceneEmptyManager() {
		this.manager = new Manager();
	}
	
	//Test
	@Test
	void testImportWorld() {
		setUpSceneEmptyManager();
		manager.importWorld("filesTest/world/");
		
		assertEquals(manager.getWorld().getName(), "World");
		assertEquals(manager.getWorld().getStars(), 2);
		
		ArrayList<Level> levels = manager.getWorld().getLevels();
		assertEquals(levels.size(), 3);
		
		//Johan
		assertEquals(levels.get(0).getName(), "Johan");
		assertEquals(levels.get(0).getUnlock(), 0);
		assertEquals(levels.get(0).getColor(), Color.GREEN);
		assertEquals(levels.get(0).getStart(), 0);
		assertEquals(levels.get(0).getEnd(), 4);
		assertEquals(levels.get(0).getStars(), 2);
		
		assertEquals(levels.get(0).getGraph().getDirected(), false);
		
		assertEquals(levels.get(0).getGraph().getVertices().size(), 5);
		assertEquals(levels.get(0).getGraph().getVertex(0).getX(), 1);
		assertEquals(levels.get(0).getGraph().getVertex(0).getY(), 1);
		assertEquals(levels.get(0).getGraph().getVertex(1).getX(), 2);
		assertEquals(levels.get(0).getGraph().getVertex(1).getY(), 2);
		assertEquals(levels.get(0).getGraph().getVertex(2).getX(), 3);
		assertEquals(levels.get(0).getGraph().getVertex(2).getY(), 3);
		assertEquals(levels.get(0).getGraph().getVertex(3).getX(), 4);
		assertEquals(levels.get(0).getGraph().getVertex(3).getY(), 4);
		assertEquals(levels.get(0).getGraph().getVertex(4).getX(), 5);
		assertEquals(levels.get(0).getGraph().getVertex(4).getY(), 5);
		
		assertEquals(levels.get(0).getGraph().getEdge(0,1).get(0), 1);
		assertEquals(levels.get(0).getGraph().getEdge(1,0).get(0), 1);
		assertEquals(levels.get(0).getGraph().getEdge(1,2).get(0), 1);
		assertEquals(levels.get(0).getGraph().getEdge(2,1).get(0), 1);
		assertEquals(levels.get(0).getGraph().getEdge(2,3).get(0), 1);
		assertEquals(levels.get(0).getGraph().getEdge(3,2).get(0), 1);
		assertEquals(levels.get(0).getGraph().getEdge(3,4).get(0), 1);
		assertEquals(levels.get(0).getGraph().getEdge(4,3).get(0), 1);
		//...
		
		//Mateo
		assertEquals(levels.get(1).getName(), "Mateo");
		assertEquals(levels.get(1).getUnlock(), 2);
		assertEquals(levels.get(1).getColor(), Color.RED);
		assertEquals(levels.get(1).getStart(), 0);
		assertEquals(levels.get(1).getEnd(), 3);
		assertEquals(levels.get(1).getStars(), 0);
		
		assertEquals(levels.get(1).getGraph().getDirected(), false);
		
		assertEquals(levels.get(1).getGraph().getVertices().size(), 4);
		assertEquals(levels.get(1).getGraph().getVertex(0).getX(), 1);
		assertEquals(levels.get(1).getGraph().getVertex(0).getY(), 1);
		assertEquals(levels.get(1).getGraph().getVertex(1).getX(), 2);
		assertEquals(levels.get(1).getGraph().getVertex(1).getY(), 2);
		assertEquals(levels.get(1).getGraph().getVertex(2).getX(), 3);
		assertEquals(levels.get(1).getGraph().getVertex(2).getY(), 3);
		assertEquals(levels.get(1).getGraph().getVertex(3).getX(), 4);
		assertEquals(levels.get(1).getGraph().getVertex(3).getY(), 4);
		
		assertEquals(levels.get(1).getGraph().getEdge(0,1).get(0), 1);
		assertEquals(levels.get(1).getGraph().getEdge(1,0).get(0), 1);
		assertEquals(levels.get(1).getGraph().getEdge(0,2).get(0), 1);
		assertEquals(levels.get(1).getGraph().getEdge(2,0).get(0), 1);
		assertEquals(levels.get(1).getGraph().getEdge(0,3).get(0), 1);
		assertEquals(levels.get(1).getGraph().getEdge(3,0).get(0), 1);
		assertEquals(levels.get(1).getGraph().getEdge(1,2).get(0), 1);
		assertEquals(levels.get(1).getGraph().getEdge(2,1).get(0), 1);
		assertEquals(levels.get(1).getGraph().getEdge(1,3).get(0), 1);
		assertEquals(levels.get(1).getGraph().getEdge(3,1).get(0), 1);
		assertEquals(levels.get(1).getGraph().getEdge(2,3).get(0), 1);
		assertEquals(levels.get(1).getGraph().getEdge(3,2).get(0), 1);
		//...
		
		//Esteban
		assertEquals(levels.get(2).getName(), "Esteban");
		assertEquals(levels.get(2).getUnlock(), 4);
		assertEquals(levels.get(2).getColor(), Color.YELLOW);
		assertEquals(levels.get(2).getStart(), 0);
		assertEquals(levels.get(2).getEnd(), 2);
		assertEquals(levels.get(2).getStars(), 0);
		
		assertEquals(levels.get(2).getGraph().getDirected(), true);
		
		assertEquals(levels.get(2).getGraph().getVertices().size(), 3);
		assertEquals(levels.get(2).getGraph().getVertex(0).getX(), 1);
		assertEquals(levels.get(2).getGraph().getVertex(0).getY(), 1);
		assertEquals(levels.get(2).getGraph().getVertex(1).getX(), 2);
		assertEquals(levels.get(2).getGraph().getVertex(1).getY(), 2);
		assertEquals(levels.get(2).getGraph().getVertex(2).getX(), 3);
		assertEquals(levels.get(2).getGraph().getVertex(2).getY(), 3);
		
		assertEquals(levels.get(2).getGraph().getEdge(0,1).get(0), 2);
		assertEquals(levels.get(2).getGraph().getEdge(1,2).get(0), 3);
		assertEquals(levels.get(2).getGraph().getEdge(2,0).get(0), 4);
		assertEquals(levels.get(2).getGraph().getEdge(0,2).get(0), 3);
		//...
	}

}

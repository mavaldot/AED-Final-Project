package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class GraphTest {

	Graph<String> g;
	
	
	//Simply initializes a graph
	private void setScene1() {
		
		g = new Graph<>();
		
	}

	@Test
	void testAddNode() {
		
		setScene1();
		
		g.addNode("Johan");
		g.addNode("Nahoj");
		
		ArrayList<String> nodes = g.getNodes();
		
		assertEquals(nodes.get(0), "Johan");
		assertEquals(nodes.get(1), "Nahoj");
		
	}
}

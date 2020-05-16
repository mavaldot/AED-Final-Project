package utilTest;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import util.DisjointSet;

class DisjointSetTest {
	
	private DisjointSet<String> sets;

	private void setUpScene1() {		
		sets = new DisjointSet<>();
		sets.makeSet("a");
		sets.makeSet("b");
		sets.makeSet("c");
		sets.makeSet("d");
	}
	
	@Test
	void testFind() {
		setUpScene1();
		assertEquals("a", sets.find("a").getElement());
		assertEquals("b", sets.find("b").getElement());
		sets.union("a", "b");
		assertEquals("a", sets.find("b").getElement());
		assertEquals("a", sets.find("a").getElement());
	}
	
	@Test
	void testUnion() {
		setUpScene1();
		assertFalse(sets.union("d", "d"));
		assertTrue(sets.union("b", "d"));
		assertFalse(sets.union("b", "d"));
		assertTrue(sets.union("b", "c"));
		assertTrue(sets.union("a", "c"));
		assertFalse(sets.union("a", "b"));
		assertFalse(sets.union("b", "a"));
		assertFalse(sets.union("c", "d"));
	}
}

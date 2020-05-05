package util;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

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
	void test() {
		setUpScene1();
		assertTrue(sets.union("b", "d"));
		assertTrue(sets.union("b", "c"));
		assertTrue(sets.union("a", "c"));
		assertFalse(sets.union("a", "b"));
		sets.print();
	}
}

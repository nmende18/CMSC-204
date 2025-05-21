import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/*
 * @author Napoleon Mendez
 * @CMSC-204 
 * Assignment 6 - find shortest path
 */
class Road_STUDENT_TEST {

	
	private Town t1;
	private Town t2;
	@BeforeEach
	void setUp() throws Exception {
		t1 = new Town("Rockville");
		t2 = new Town("Miami");
	}

	@AfterEach
	void tearDown() throws Exception {
		t1 = null;
		t2 = null;
	}

	@Test
	public void testContains() {
		Road road = new Road(t1, t2, 2, "Elsdale");
		assertTrue(road.contains(t1));
		assertTrue(road.contains(t2));
		assertFalse(road.contains(new Town("Lol")));
	}
	
	@Test
	public void testToString() {
		Road road = new Road(t1, t2, "Elsdale");
		assertEquals("Elsdale", road.toString());
	}
	
	@Test
	public void testHashCode() {
		Road road1 = new Road(t1, t2, 5, "Malz");
		Road road2 = new Road(t1, t2, 5, "Malz");
		assertEquals(road1, road2);
		assertEquals(road1.hashCode(), road2.hashCode());
	}

}

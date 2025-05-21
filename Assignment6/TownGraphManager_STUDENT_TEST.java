import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/*
 * @author Napoleon Mendez
 * @CMSC-204 
 * Assignment 6 - find shortest path
 */
class TownGraphManager_STUDENT_TEST {
	
	private TownGraphManager manager;

	@BeforeEach
	void setUp() throws Exception {
		manager = new TownGraphManager();
		manager.addTown("Rockville");
		manager.addTown("Silver Spring");
		manager.addRoad("Rockville", "Silver Spring", 4, "Main Road");
	}

	@AfterEach
	void tearDown() throws Exception {
		manager = null;
	}

	@Test
	public void testRoad() {
		assertTrue(manager.addRoad("Rockville", "Baltimore", 3 , "Main Road"));
		assertEquals("Main Road", manager.getRoad("Rockville", "Baltimore"));
	}
	
	@Test
	public void testgetRoad() {
		assertEquals("Main Road", manager.getRoad("Rockville", "Silver Spring"));
	}
	
	@Test
	public void testAllTowns() {
		ArrayList<String> list = manager.allTowns();
		assertEquals(2, list.size());
		assertTrue(list.contains("Rockville"));
		assertTrue(list.contains("Silver Spring"));
	}

}

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/*
 * @author Napoleon Mendez
 * @CMSC-204 
 * Assignment 6 - find shortest path
 */
class Town_STUDENT_TEST {

	@BeforeEach
	void setUp() throws Exception {
		
	}
	
	@Test
	public void constructor(){
		Town town = new Town("Rockville");
		assertEquals("Rockville", town.getName());
	}
	@Test
	public void testName() {
		Town town = new Town("Rockville");
		town.setName("Bethesda");
		assertEquals("Bethesda", town.getName());
	}
	@Test
	public void testEqualsHashCode() {
		Town t1 = new Town("Springfield");
		Town t2 = new Town("Springfield");
		assertEquals(t1, t2);
		assertEquals(t1.hashCode(), t2.hashCode());
	}
	@Test
	public void testCompare() {
		Town t1 = new Town("Atlanta");
		Town t2 = new Town("Chicago");
		assertTrue(t1.compareTo(t2) < 0);
		assertTrue(t2.compareTo(t1) > 0);
		assertEquals(0, t1.compareTo(new Town("Atlanta")));
	}

}

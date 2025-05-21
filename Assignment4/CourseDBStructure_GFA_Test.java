
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CourseDBStructure_GFA_Test {
	
	CourseDBStructure CDS, testStructure;

	@BeforeEach
	void setUp() throws Exception {
		CDS = new CourseDBStructure(20);
		testStructure = new CourseDBStructure("Test", 20);
	}

	@AfterEach
	void tearDown() throws Exception {
		CDS = testStructure = null;
	}

	@Test
	public void testGetTableSize() {
		assertEquals(19, CDS.getTableSize());
		assertEquals(20, testStructure.getTableSize());
	}
	
	@Test
	public void testHashTable() {
		CourseDBElement cde1 = new CourseDBElement("CMSC540", 39999, 4, "SC150", "Aint nobody here");
		
		CDS.add(cde1);  
	 
		ArrayList<String> list = CDS.showAll(); 
		assertTrue(list.size()==1);  
		
		
		CourseDBElement cde2 = new CourseDBElement("CMSC320", 4000, 4, "SS150", "Aint nobody here");
	 
 		try {
			assertEquals(39999, CDS.get(cde1.getCRN()).getCRN());  
			CDS.get(cde2.getCRN()).getCRN(); 
		} catch (IOException e) {

			assertTrue("threw Exception successfuly for the course not found", true);
		}
		
 		CDS.add(cde2);
 		list = CDS.showAll(); 
		assertTrue(list.size()==2);  
		
		try {
			assertEquals(4000, CDS.get(cde2.getCRN()).getCRN());
		} catch (IOException e) {
			 
			fail("Should not throw exception");
		}  
		CourseDBElement cde1Update = new CourseDBElement("CMSC320-updated", 39999, 4, "SS150", "updated");
		CDS.add(cde1Update);  
 		list = CDS.showAll(); 
		assertTrue(list.size()==2);  
		
		try {
			assertEquals(39999, CDS.get(cde1Update.getCRN()).getCRN());
			assertEquals("CMSC320-updated", CDS.get(cde1Update.getCRN()).getID());
		} catch (IOException e) {
			 
			fail("Should not throw exception");
		}  
		testStructure.add(cde1); 
		list = testStructure.showAll(); 
		assertTrue(list.size()==1); 
		
		
		
		
		
		
		
	}

}

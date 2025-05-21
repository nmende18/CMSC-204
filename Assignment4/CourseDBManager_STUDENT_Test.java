import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;

class CourseDBManager_STUDENT_Test {

	private CourseDBManagerInterface manager = new CourseDBManager();
	
	@Before
	public void setUp() throws Exception{
		manager = new CourseDBManager();
	}
	
	@After
	public void tearDown() throws Exception{
		manager = new CourseDBManager();
	}
	
	@Test
	public void testAdd() {
		try {
			manager.add("CMSC204", 33224, 4, "SC-101", "Jonas-M");
		}
		catch(Exception e) {
			fail("This should not have caused an Exception");
		}
	}
	@Test
	public void testShowAll() {
		manager.add("CMSC204", 33224, 4, "SC-101", "Jonas-M");
		
		ArrayList<String> courses = manager.showAll();
		assertEquals("List added!", 1, courses.size());
		assertTrue("List completed", courses.get(0).contains("33224"));
	}
	
	@Test
	public void testFile() throws IOException{
		try {
		File inputFile = new File("Test1.txt");
		PrintWriter inFile = new PrintWriter(inputFile);
		inFile.println("CMSC204 33224 4 SC-101 Jonas-M");
		
		inFile.close();
		manager.readFile(inputFile);
		assertEquals("CMSC204", manager.get(33224).getID());
		assertEquals("SC-101", manager.get(33224).getRoomNum());
	}
	catch(Exception e) {
		fail("Should not have thrown an exception");
	}

	}
}

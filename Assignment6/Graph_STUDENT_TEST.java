import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
/*
 * @author Napoleon Mendez
 * @CMSC-204 
 * Assignment 6 - find shortest path
 */
class Graph_STUDENT_TEST {
	
	private Graph graph;
	private Town t1;
	private Town t2;
	private Road road;

	@BeforeEach
	void setUp() throws Exception {
		graph = new Graph();
		t1 = new Town("Rockville");
		t2 = new Town("Wheaton");
		graph.addVertex(t1);
		graph.addVertex(t2);
		graph.addEdge(t1, t2, 2, "Main Road");
	}

	@AfterEach
	void tearDown() throws Exception {
		graph = null;
		t1 = null;
		t2 = null;
	}

	@Test
	public void addVertex() {
		Town t3 = new Town("Baltimore");
		assertTrue(graph.addVertex(t3));
		assertTrue(graph.containsVertex(t3));
	}
	
	@Test
	public void testGetEdge() {
		Road view = new Road(t1, t2, 2, "Main Road");
		assertEquals(view, graph.getEdge(t1, t2));
	}
	

}

import java.util.ArrayList;
import java.util.Collections;
/*
 * @author Napoleon Mendez
 * @CMSC-204 
 * Assignment 6 - find shortest path
 */
public class TownGraphManager implements TownGraphManagerInterface {
	
	private Graph<Town, Road> graph = new Graph<>();

	@Override
	public boolean addRoad(String town1, String town2, int weight, String roadName) {
		Town t1 = new Town(town1);
		Town t2 = new Town(town2);
		
		if(!graph.containsVertex(t1)) {
			graph.addVertex(t1);
		}
		if(!graph.containsVertex(t2)) {
			graph.addVertex(t2);
		}
		Road road = graph.addEdge(t1, t2, weight, roadName);
		if(road == null) return false;
		else return true;
	}

	@Override
	public String getRoad(String town1, String town2) {
		Town t1 = new Town(town1);
		Town t2 = new Town(town2);
		Road road = graph.getEdge(t1, t2);
		if(road == null) return null;
		else return road.getName();
	}

	@Override
	public boolean addTown(String v) {
		Town town = new Town(v);
		if(!graph.containsVertex(town)) {
			graph.addVertex(town);
			return true;
		}
		else return false;
	}

	@Override
	public Town getTown(String name) {
		Town town = new Town(name);
		if(graph.containsVertex(town)) {
			for(Town towns : graph.vertexSet()) {
				if(towns.equals(town)) {
					return towns;
				}
			}
		}
		return null;
		
	}

	@Override
	public boolean containsTown(String v) {
		for(Town town : graph.vertexSet()) {
			if(town.getName().equals(v)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean containsRoadConnection(String town1, String town2) {
		Town t1 = new Town(town1);
		Town t2 = new Town(town2);
		if(graph.getEdge(t1, t2) == null) return false;
		else return true;
	}

	@Override
	public ArrayList<String> allRoads() {
		ArrayList<String> list = new ArrayList<>();
		
		for(Road road: graph.edgeSet()) {
			if(road != null) list.add(road.getName());
		}
		if(list.isEmpty()) return new ArrayList<>();
		else Collections.sort(list);
		return list;
		
	}

	@Override
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		Town t1 = new Town(town1);
		Town t2 = new Town(town2);
		
		Road roads = graph.getEdge(t1, t2);
		if(roads.getName().equals(road) && roads != null) {
			if(graph.removeEdge(t1, t2, roads.getWeight(), roads.getName()) != null) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean deleteTown(String v) {
		Town t1 = new Town(v);
		boolean checkDeletedTown = graph.removeVertex(t1);
		if(graph.containsVertex(t1)) {
			if(checkDeletedTown == true) return true;
		}
		return false;		
	}

	@Override
	public ArrayList<String> allTowns() {
		ArrayList<String> list = new ArrayList<>();
		for(Town town : graph.vertexSet()) {
			if(town != null) list.add(town.getName());
		}
		if(list.isEmpty()) return null;
		else Collections.sort(list);
		return list;
	}

	@Override
	public ArrayList<String> getPath(String town1, String town2) {
		Town t1 = new Town(town1);
		Town t2 = new Town(town2);
		ArrayList<String> list = graph.shortestPath(t1, t2);
		if(list == null) return new ArrayList<>();
		if(list.isEmpty()) return null;
		else return list;
	}

}

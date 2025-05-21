import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
/*
 * @author Napoleon Mendez
 * @CMSC-204 
 * Assignment 6 - find shortest path
 */
public class Graph<T, V> implements GraphInterface<Town, Road> {
	
	private Map<Town, List<Road>> list;
	
	public Graph() {
		list = new HashMap<>();
	}

	@Override
	public Road getEdge(Town sourceVertex, Town destinationVertex) {
		if(sourceVertex == null || destinationVertex == null) return null;
		
		List<Road> road = list.get(sourceVertex);
		if(road != null) {
			for(Road roads: road) {
				if((roads.getSource().equals(sourceVertex) && roads.getDestination().equals(destinationVertex))
						|| roads.getSource().equals(destinationVertex) && roads.getDestination().equals(sourceVertex)) {
					return roads;
				}
			}
		}
		return null;
		
	}

	@Override
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		if(!list.containsKey(sourceVertex) || !list.containsKey(destinationVertex)) {
			throw new IllegalArgumentException();
		}
		if(sourceVertex == null || destinationVertex == null) {
			throw new NullPointerException();
		}
		Road road = new Road(sourceVertex, destinationVertex, weight, description);
		list.get(sourceVertex).add(road);
		list.get(destinationVertex).add(road);
		return road;
		
	}

	@Override
	public boolean addVertex(Town v) {
		if(v == null) throw new NullPointerException();
		if(!list.containsKey(v)) {
			list.put(v, new ArrayList<>());
			return true;
		}
		else return false;
	}

	@Override
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		if(sourceVertex == null || destinationVertex == null) return false;
		Road road = getEdge(sourceVertex, destinationVertex);
		if(road == null) return false;
		else return true;
	}

	@Override
	public boolean containsVertex(Town v) {
		if(list.containsKey(v)) return true;
		else return false;
	}

	@Override
	public Set<Road> edgeSet() {
		Set<Road> edges = new HashSet<>();
		for(List<Road> road: list.values()) {
			edges.addAll(road);
		}
		return edges;
	}

	@Override
	public Set<Road> edgesOf(Town vertex) {
		List<Road> roadOf = list.get(vertex);
		if(roadOf == null) throw new IllegalArgumentException();
		if(vertex == null) throw new NullPointerException();
		else {
			Set<Road> edges = new LinkedHashSet<>();
			for(Road road: roadOf) {
				edges.add(road);
			}
			return edges;
		}
	}

	@Override
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		if(sourceVertex == null || destinationVertex == null) return null;
		Road road = getEdge(sourceVertex, destinationVertex);
		if(road != null) {
			if(road.getWeight() == weight && road.getName().equals(description)) {
				list.get(sourceVertex).remove(road);
				list.get(destinationVertex).remove(road);
				return road;
			}
		}
		return null;
	}

	@Override
	public boolean removeVertex(Town v) {
		if(v == null) return false;
		if(!list.containsKey(v)) return false;
		list.remove(v);
		for(Map.Entry<Town, List<Road>> entry: list.entrySet()) {
			List<Road> road = entry.getValue();
			Iterator<Road> it = road.iterator();
			while(it.hasNext()) {
				Road roads = it.next();
				if(roads.contains(v)) {
					it.remove();
				}
			}
		}
		return true;
	}

	@Override
	public Set<Town> vertexSet() {
		Set<Town> vertex = new HashSet<>();
		for(Town town : list.keySet()) {
			vertex.add(town);
		}
		return vertex;
	}

	@Override
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
		Map<Town, Town> previousTown = new HashMap<>();
		Map<Town, Integer> distance = new HashMap<>();
		PriorityQueue<Town> queue = new PriorityQueue<>(new Comparator<Town>() {
			@Override
			public int compare(Town o1, Town o2) {
				return Integer.compare(distance.get(o1), distance.get(o2));
			}
			
		});
		for(Town town : list.keySet()) {
			distance.put(town, Integer.MAX_VALUE);
		}
		distance.put(sourceVertex, 0);
		queue.add(sourceVertex);
		
		while(!queue.isEmpty()) {
			Town presentTown = queue.poll();
			List<Road> roads = list.getOrDefault(presentTown, new ArrayList<>());
			for(Road road : roads) {
				Town close;
				if(road.getSource().equals(presentTown)) {
					close = road.getDestination();
				}
				else close = road.getSource();
				int calcDistance = distance.get(presentTown) + road.getWeight();
				if(calcDistance < distance.get(close)) {
					distance.put(close, calcDistance);
					previousTown.put(close, presentTown);
					queue.add(close);
				}
			}
		}
		ArrayList<String> current = new ArrayList<>();
		Town presentTown = destinationVertex;
		while(presentTown != null && !presentTown.equals(sourceVertex)) {
			Town previous = previousTown.get(presentTown);
			if(previous == null) break;
			Road road = getEdge(previous, presentTown);
			if(road == null) break;
			else {
				current.add(0, String.format("%s via %s to %s %d mi", previous.getName(), road.getName(), 
						presentTown.getName(), road.getWeight()));
				presentTown = previous;
			}
		}
		if(current.isEmpty()) return current;
		return current;

	}

	@Override
	public void dijkstraShortestPath(Town sourceVertex) {
		Map<Town, Town> previousTown = new HashMap<>();
		Map<Town, Integer> distance = new HashMap<>();
		PriorityQueue<Town> queue = new PriorityQueue<>(new Comparator<Town>() {
			@Override
			public int compare(Town o1, Town o2) {
				return Integer.compare(distance.get(o1), distance.get(o2));
			}
			
		});
		for(Town town : list.keySet()) {
			distance.put(town, Integer.MAX_VALUE);
		}
		distance.put(sourceVertex, 0);
		queue.add(sourceVertex);
		
		while(!queue.isEmpty()) {
			Town currentTown = queue.poll();
			for(Road road : list.get(currentTown)) {
				Town close;
				if(road.getSource().equals(currentTown)) close = road.getDestination();
				else close = road.getSource();
				
				int calcDistance = distance.get(currentTown) + road.getWeight();
				if(calcDistance < distance.getOrDefault(close, Integer.MAX_VALUE)) {
					distance.put(close, calcDistance);
					previousTown.put(close, currentTown);
					queue.add(close);
				}
			}
		}
		
	}

	

}

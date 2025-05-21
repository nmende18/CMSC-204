import java.util.Objects;
/*
 * @author Napoleon Mendez
 * @CMSC-204 
 * Assignment 6 - find shortest path
 */
public class Road implements Comparable<Road> {
	
	private Town source;
	private Town destination;
	private String name;
	private int weight;
	
	public Road(Town source, Town destination, int degrees, String name) {
		this.source = source;
		this.destination = destination;
		this.weight = degrees;
		this.name = name;
	}
	
	public Road(Town source, Town destination, String name) {
		this(source, destination, 1, name);
	}
	
	@Override
	public int compareTo(Road o) {
		return name.compareTo(o.name);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(this == null || getClass() != obj.getClass()) return false;
		Road road = (Road) obj;
		return Objects.equals(destination, road.destination) &&
				Objects.equals(source, road.source) || Objects.equals(source, road.destination) 
				&& Objects.equals(destination, road.source);
		
	}
	
	public boolean contains(Town town) {
		return source.equals(town) || destination.equals(town);
	}
	
	
	
	public String getName() {
		return name;
	}
	
	public Town getSource() {
		return source;
	}
	
	public int getWeight() {
		return weight;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(source) + Objects.hash(destination);
	}

	

	public Town getDestination() {
		return destination;
	}

	
	

}

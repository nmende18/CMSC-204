import java.util.Objects;
/*
 * @author Napoleon Mendez
 * @CMSC-204 
 * Assignment 6 - find shortest path
 */
public class Town implements Comparable<Town>{
	
	private String name;
	
	public Town(String name) {
		this.setName(name);
	}
	
	public Town(Town templateTown) {
		this.name = templateTown.getName();		
	}

	@Override
	public int compareTo(Town o) {
		return name.compareTo(o.name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(this == null || getClass() != obj.getClass())return false;
		Town tw = (Town) obj;
		return Objects.equals(name, tw.name);
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	
	

}

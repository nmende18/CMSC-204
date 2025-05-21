import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
/*
 * @author Napoleon Mendez
 * @CMSC-204 
 * Assignment 4 - Data-Base of courses
 */
public class CourseDBStructure implements CourseDBStructureInterface {
	
	private static final double LOAD_FACTOR = 1.5;
	private LinkedList<CourseDBElement>[] table;
	private int size;
	
	
	
	@SuppressWarnings("unchecked")
	public CourseDBStructure(int n) {
		 int prime = getNextPrime((int)(n / LOAD_FACTOR));
		 this.size = prime;
		 table = new LinkedList[size];
		 for(int i = 0; i < size; i++) {
			 table[i] = new LinkedList<>();
		 }
	}
	
	@SuppressWarnings("unchecked")
	public CourseDBStructure(String s, int n) {
		this.size = n;
		table = new LinkedList[size];
		for(int i = 0; i < size; i++) {
			 table[i] = new LinkedList<>();
		 }
	}
	@Override
	public void add(CourseDBElement element) {

		int hashCode = String.valueOf(element.getCRN()).hashCode();
		int index = Math.abs(hashCode % size);
		
		for(CourseDBElement current : table[index]) {
			if(current.getCRN() == element.getCRN()) {
				current.setId(element.getID());
				current.setCRN(element.getCRN());
				current.setCredits(element.getCredits());
				current.setRoomNum(element.getRoomNum());
				current.setInstructor(element.getInstructor());
				return;
			}
		}
		table[index].add(element);
		
	}

	@Override
	public CourseDBElement get(int crn) throws IOException {
		
		int hashCode = String.valueOf(crn).hashCode();
		int index = Math.abs(hashCode % size);
		
		if(table[index] == null) throw new IOException();
		for(CourseDBElement current : table[index]) {
			if(current.getCRN() == crn) {
				return current;
			}
		}
		
		throw new IOException();
	}
	
	@Override
	public ArrayList<String> showAll() {
		ArrayList<String> list = new ArrayList<>();
		
		for(LinkedList<CourseDBElement> bucket: table) {
			if(bucket != null && !bucket.isEmpty()) {
				for(CourseDBElement element : bucket) {
					list.add("\n" + element.toString());
				}
			}
		}
		 return list;
	
		
	}

	@Override
	public int getTableSize() {
		return size;
	}
	
	 private boolean isPrime(int num) {
	        if (num < 2) return false;
	        for (int i = 2; i * i <= num; i++) {
	            if (num % i == 0) return false;
	        }
	        return true;
	    }
	 
	 private int getNextPrime(int num) {
	        while (true) {
	           if(isPrime(num) && (num % 4 == 3)) {
	        	   return num;
	           }
	           num++;
	        }
	    }

}

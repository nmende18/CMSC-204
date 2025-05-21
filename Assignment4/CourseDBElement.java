/*
 * @author Napoleon Mendez
 * @CMSC-204 
 * Assignment 4 - DataN-Base of courses
 */

public class CourseDBElement implements Comparable<CourseDBElement> {
	
	
	private String id;
	private int crn;
	private int credits;
	private String roomNum;
	private String instructor;


	public CourseDBElement(String id, int crn, int credits, String roomNum, String instructor) {
		this.id = id;
		this.crn = crn;
		this.credits = credits;
		this.roomNum = roomNum;
		this.instructor = instructor;
	}
	
	public CourseDBElement() {}

	@Override
	public int compareTo(CourseDBElement o) {
		if(this.crn < o.crn) return -1;
		else if(this.crn > o.crn) return 1;
		else return 0;
	}
	
	@Override
	public int hashCode() {
		return Integer.hashCode(crn);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null || getClass() != obj.getClass()) return false;
		
		CourseDBElement other = (CourseDBElement) obj;
		return crn == other.crn;
	}



	public String getID() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public int getCredits() {
		return credits;
	}



	public void setCredits(int credits) {
		this.credits = credits;
	}



	public int getCRN() {
		return crn;
	}



	public void setCRN(int crn) {
		this.crn = crn;
	}



	public String getRoomNum() {
		return roomNum;
	}



	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}



	public String getInstructor() {
		return instructor;
	}



	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}

	
	@Override
	public String toString() {
		String s = "Course:" + id + " CRN:" + crn + " Credits:" + credits +
				" Instructor:" + instructor + " Room:" + roomNum;
		return s;
	}
	

}

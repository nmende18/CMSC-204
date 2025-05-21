import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
/*
 * @author Napoleon Mendez
 * @CMSC-204
 * Assignment 4 - Data-Base of courses
 */
public class CourseDBManager implements CourseDBManagerInterface {
	
	private CourseDBStructure CDS;
	
	public CourseDBManager() {
		CDS = new CourseDBStructure(50);
		
	}

	@Override
	public void add(String id, int crn, int credits, String roomNum, String instructor) {
		CourseDBElement element = new CourseDBElement(id, crn, credits, roomNum, instructor);
		CDS.add(element);
	}

	@Override
	public CourseDBElement get(int crn) {
		try {
			return CDS.get(crn);
		} catch (IOException e) {
			return null;
		}
	}

	@Override
	public void readFile(File input) throws FileNotFoundException {
		try {
			Scanner scanner = new Scanner(input);
			while(scanner.hasNextLine()) {
				Scanner line = new Scanner(scanner.nextLine());
				
			if(!line.hasNext())continue;
				
				String id =line.next();
				int crn = line.nextInt();
				int credits = line.nextInt();
				String roomNum = line.next();
				String instructor = line.nextLine().trim();
				add(id, crn, credits, roomNum, instructor);
				line.close();
			}
		} catch(FileNotFoundException e) {
			throw new FileNotFoundException();
		}
		
	}

	@Override
	public ArrayList<String> showAll() {
		return CDS.showAll();
	}

}

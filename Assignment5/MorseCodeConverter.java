import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/*
 * @author Napoleon Mendez
 * @CMSC-204 
 * Assignment 5 - MorseCode
 */
public class MorseCodeConverter {
	
	private static MorseCodeTree<String> morse = new MorseCodeTree<>();
	
	public MorseCodeConverter() {
		morse = new MorseCodeTree<>();
	}
	
	public static String printTree() {
		ArrayList<String> tree = morse.toArrayList();
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < tree.size(); i++) {
			sb.append(tree.get(i));
			if(i < tree.size() - 1) {
				sb.append(" ");
			}
		}
		return sb.toString();

		
	}
	
	public static String convertToEnglish(String code) {
		StringBuilder sb = new StringBuilder();
		String[] words = code.trim().split("/");
		String[] letters;
		for(int i = 0; i < words.length; i++) {
			letters = words[i].split(" ");
			for(String morseCode : letters) {
				if(!morseCode.equals("")) {
					sb.append(morse.fetch(morseCode));
				}
			}
			sb.append(" ");
		}
		return sb.toString().trim();
	}
	
	public static String convertToEnglish(File codeFile) throws FileNotFoundException{
		if(codeFile == null) {
			throw new FileNotFoundException();
		}
		Scanner scanner = new Scanner(codeFile);
		StringBuilder sb = new StringBuilder();
		
		while(scanner.hasNext()) {
			String s = scanner.nextLine();
			sb.append(convertToEnglish(s));
			sb.append("\n");
		}
		scanner.close();
		
		return sb.toString().trim();

	}

}

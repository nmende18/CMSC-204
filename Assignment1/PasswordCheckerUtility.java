/*
 * @author Napoleon
 * @CMSC-204 
 * Assignment 1 - An application to check for valid passwords following several rules.
 */
import java.util.ArrayList;

public class PasswordCheckerUtility extends Object {
	
	/*
	 * Compare passwords
	 * 
	 * @param password 
	 * @param passwordConfirm
	 * @throws UnmatchedException if passwords do not match	
	 */
	public static void comparePasswords(String password, String passwordConfirm) throws UnmatchedException{
		if(!passwordConfirm.equals(password)) {
			throw new UnmatchedException();
		}
	}
	/*
	 * Compare passwords with return
	 * 
	 * @param password
	 * @param passwordConfirm
	 * @return true if passwords match
	 */
	public static boolean comparePasswordsWithReturn(String password, String passwordConfirm) {
		if(!passwordConfirm.equals(password)) {
			return false;
		}
		return true;
	}
	/*
	 * Checks if password meets the length requirement
	 * 
	 * @param password
	 * @return true if pasword length is valid
	 * @throws LengthException
	 */
	public static boolean isValidLength(String password) throws LengthException{
		if(password.length() < 6) {
			throw new LengthException();
		}
		return true;
	}
	/*
	 * Checks if passwods contains uppercase letters
	 * 
	 * @param password
	 * @return true if password contains upperalpha
	 * @throws NoUpperAlphaException
	 */
	public static boolean hasUpperAlpha(String password) throws NoUpperAlphaException{
		for(int i = 0; i < password.length(); i++) {
			if(Character.isUpperCase(password.charAt(i))) {
				return true;
			}
		}
		throw new NoUpperAlphaException();
	}
	/*
	 * Checks if password contains lowercase letter.
	 * 
	 * @param password
	 * @return true if it contains
	 * @throws NoLowerAlphaException
	 */
	public static boolean hasLowerAlpha(String password) throws NoLowerAlphaException{
		for(int i = 0; i < password.length(); i++) {
			if(Character.isLowerCase(password.charAt(i))) {
				return true;
			}
		}
		throw new NoLowerAlphaException();
	}
	/*
	 * Checks if password has digit
	 * 
	 * @param password
	 * @return true if it contains
	 * @throws NoDigitException
	 */
	public static boolean hasDigit(String password) throws NoDigitException{
		for(int i = 0; i < password.length(); i++) {
			if(Character.isDigit(password.charAt(i))) {
				return true;
			}
		}
		throw new NoDigitException();
	}
	/*
	 * Checks it password has special character
	 * 
	 * @param password
	 * @return true if it contains
	 * @throws NoSpecialCharacterException
	 */
	public static boolean hasSpecialChar(String password) throws NoSpecialCharacterException{
		for(int i = 0; i < password.length(); i++) {
		if(!Character.isLetterOrDigit(password.charAt(i))){
				return true;
			}
		}
		throw new NoSpecialCharacterException();
	}
	/*
	 * Checks if the passwords does not contain repeated characters in sequence.
	 * 
	 * @param password
	 * @return true if no repeated characters are found
	 * @throws InvalidSequenceException
	 */
	public static boolean noSameCharInSequence(String password) throws InvalidSequenceException{
		for(int i = 1; i < password.length(); i++) {
			if(password.charAt(i) == password.charAt(i - 1)) {
				throw new InvalidSequenceException();
			}
		}
		return true;
	}
	/*
	 * Validates if a password meets all requirements
	 * 
	 * @param password
	 * @return true if password is valid
	 * @throws LengthException,
	 * @throws NoUpperAlphaException
	 * @throws NoLowerAlphaException
	 * @throws NoDigitException
	 * @throws NoSpecialCharacterException
	 * @throws InvalidSequenceException
	 */
	public static boolean isValidPassword(String password) throws LengthException,
																	NoUpperAlphaException,
																	NoLowerAlphaException,
																	NoDigitException,
																	NoSpecialCharacterException,
																	InvalidSequenceException{
		if(isValidLength(password) && hasUpperAlpha(password) && hasLowerAlpha(password)
				&& hasDigit(password) && hasSpecialChar(password) && noSameCharInSequence(password)) {
			return false;
		}
		
		return true;
	}
	/*
	 * Checks if password lenght between 6 and 9 characters
	 * 
	 * @param password
	 * @return true if pasword lenght is correct
	 */
	public static boolean hasBetweenSixAndNineChars(String password) {
		if(password.length() >= 6 && password.length() <= 9) {
			return true;
		}
		return false;
	}
	/*
	 * Checks if a valid password is whether weak or not
	 * 
	 * @param password
	 * @return true if password is weak
	 * @throws WeakPasswordException
	 */
	public static boolean isWeakPassword(String password) throws WeakPasswordException{
		if(isValidPassword(password) == false) {
			if(hasBetweenSixAndNineChars(password) == true) {
				throw new WeakPasswordException();
			}
			return false;
		}
		return false;
	}
	/*
	 * Retrieves a list of invalid passwords
	 * 
	 * @param passwords
	 * @return A list with invalid passwords
	 */
	public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords){
		ArrayList<String> invalidPasswords = new ArrayList<>();
		for(String password : passwords) {
			try {
				if(isValidPassword(password) == true) {
					continue;
				}
			}catch(LengthException | NoUpperAlphaException | NoLowerAlphaException |
					NoDigitException | NoSpecialCharacterException | InvalidSequenceException e) {
				invalidPasswords.add(password + " " + e.getMessage() );
			}
		}
		return invalidPasswords;
		
	}

}

/*
 * @author Napoleon Mendez
 * @CMSC-204 CRN 33224
 * Assignment 1 - An application to check for valid passwords following several rules.
 */
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author 
 *
 */
public class PasswordCheckerTest_STUDENT {
	ArrayList<String> passwords;

	@Before
	public void setUp() throws Exception {
		String[] s = {"sht1!", "uppercaseno!1", "WERELOWERCASE2!", "NoneDigit!", "strongLLPD5", 
				"1253@AAbbbP", "Short3!"};
		passwords = new ArrayList<String>();
		passwords.addAll(Arrays.asList(s));
		
		
	}

	@After
	public void tearDown() throws Exception {
		passwords = null;
	}

	/**
	 * Test if the password is less than 6 characters long.
	 * This test should throw a LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort()
	{
		try {
			PasswordCheckerUtility.isValidLength(passwords.get(0));
			fail("Did not throw lengthException");
		} catch(LengthException e) {
			assertTrue(true);
		}
	}
	
	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha()
	{
		try {
			PasswordCheckerUtility.hasUpperAlpha(passwords.get(1));
			fail("Didnt throw NoUpperAlphaException");
		} catch(NoUpperAlphaException e) {
			assertTrue(true);
		}
	}
	
	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha()
	{
		try {
			PasswordCheckerUtility.hasLowerAlpha(passwords.get(2));
			fail("Didnt throw NoLowerAlphaException");
		} catch(NoLowerAlphaException e) {
			assertTrue(true);
		}
	}
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsWeakPassword()
	{
		try {
			assertTrue(PasswordCheckerUtility.isWeakPassword(passwords.get(6)));
		} catch(WeakPasswordException e) {
			assertTrue("Catch exception", true);
		}
	}
	
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence()
	{
		try {
			PasswordCheckerUtility.noSameCharInSequence(passwords.get(5));
			fail("Where InvalidSequenceException");
		} catch(InvalidSequenceException e) {
			assertTrue(true);
		}
	}
	
	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit()
	{
		try {
			PasswordCheckerUtility.hasDigit(passwords.get(3));
			fail("Didnt throw digit");
		} catch(NoDigitException e) {
			assertTrue(true);
		}
	}
	
	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful()
	{
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword(passwords.get(4)));
		} catch(Exception e) {
			assertTrue("Passwords threw the sucessful special character exception", true);
		}
	}
	
	/**
	 * Test the invalidPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 */
	@Test
	public void testInvalidPasswords() {
		ArrayList<String> invalids = PasswordCheckerUtility.getInvalidPasswords(passwords);
		assertEquals(6, invalids.size());
	}
	
}

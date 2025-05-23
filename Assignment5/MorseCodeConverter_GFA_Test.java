/*
 * @author Napoleon Mendez
 * @CMSC-204 
 * Assignment 5 - MorseCode
 */
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class MorseCodeConverter_GFA_Test {
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testConvertToEnglishString() {	
		String converter1 = MorseCodeConverter.convertToEnglish(".... . .-.. .-.. --- / .-- --- .-. .-.. -.. ");
		assertEquals("hello world",converter1);
	}
	
	@Test
	public void testConvertAnotherMorseStringToEnglishString() {
		String converter1 = MorseCodeConverter.convertToEnglish(".-- . .-.. -.-. --- -- . / - --- / - .... . / .--- .- ...- .- / .-- --- .-. .-.. -..");
		assertEquals("welcome to the java world", converter1);
	}

}
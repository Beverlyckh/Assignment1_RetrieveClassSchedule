/*
 * Beverly ACKAH
 * Winter 2018
 * Professor: Fatma Serce
 * Description: A code that reads a file source file and extract all the
 *  				variable defined in this class. Sample file is A.java
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Variable_Parser {
	public static void main(String[] args) throws IOException {
		
		
	
		File file = new File("A.java");
		Scanner scanner = new Scanner(file);
		
		String input = "";
		String text = "";
		
		while(scanner.hasNextLine()) {
			input = scanner.nextLine();
			text += input + "\n";
		}
	//System.out.println(text);
		
		Pattern p = Pattern.compile("([a-zA-Z]*)\\s([a-zA-Z]*)*\\s*(=(.*))?;");
		
		Matcher m = p.matcher(text);
		
		while(m.find()) {
			
		String variableType =  m.group(1);
		String variableName = m.group(2); //issue here!
		String variableValue = m.group(3);
		
		System.out.println("Type: " + variableType);
		System.out.println("Variable: " + variableName);
		System.out.println("Value: " + variableValue);
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^");
			
		}
		
	}
}

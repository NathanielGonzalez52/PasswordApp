package files;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class PasswordApp {

	public static void main(String[] args) {
		
		// Write the name of the file to be found  
		String filename = "C:\\Users\\natha\\OneDrive\\Desktop\\passwords.txt";
		
		// Store the various passwords in the notepad 
		String[] passwords = new String[13];
		// Creates a file object 
		File file = new File(filename);
		// try block is used in an attempt to read the file 
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			// for loop reads each line of the file 
			for (int i = 0; i<passwords.length; i++) {
				passwords[i] = br.readLine();
			}
			
			// Possible errors to catch: 
			// Could not find the file 
		} catch (FileNotFoundException e) {System.out.println("ERROR: Could not open file.");
			// Could not read the file
		} catch (IOException e) {System.out.println("ERROR: Could not read file");}
		
		// Test against our criteria
		for (String password : passwords) {
			System.out.println("****\n" + password);
			boolean hasNumber = false;
			boolean hasLetter = false;
			boolean hasSpecialChar = false;
	
			
			for (int n=0; n<password.length();n++) {
			// Condition 1: Contains number
			if ("0123456789".contains(password.substring(n,n+1))) {
				hasNumber = true;
//				System.out.println("Position " + n + " contains a number");
			}
			// Condition 2: Contains letter
			else if ("abcdefghijklmnopqrstuvwxyz".contains(password.substring(n,n+1).toLowerCase())) {
				hasLetter = true;
//				System.out.println("Position " + n + " contains a letter");
			}
			// Condition 3: contains special character, !@#
			else if ("!@%#$^&*()_-+=".contains(password.substring(n,n+1))) {
				hasSpecialChar = true;
//				System.out.println("Position " + n + " contains a special character");
			}
			else {
				try { throw new InvalidCharacterException(password.substring(n,n+1));
				} catch (InvalidCharacterException e) {e.toString();}
			}
		}
			
			// Test & Exception
		try {
			if (!hasNumber) {throw new NumberCriteriaException(password); }
			else if (!hasLetter) {throw new LetterCriteriaException(password); }
			else if (!hasSpecialChar) {throw new SpecialCharCriteriaException(password); }	
			else {System.out.println("Valid Password"); }
		} catch (NumberCriteriaException | LetterCriteriaException | SpecialCharCriteriaException e) {
			System.out.println("Invalid password");
			System.out.println(e.toString());
		}
			
			
		}
	}
}
class InvalidCharacterException extends Exception {
	String ch; 
	public InvalidCharacterException(String ch) {
		this.ch = ch;
	}
	public String toString() {
		return "InvalidCharacterException: " + ch;
	}
}

class NumberCriteriaException extends Exception {
	String password; 
	public NumberCriteriaException(String password) {
		this.password = password;
	}
	public String toString() {
		return "NumberCriteriaException: " + password;
	}
}

class LetterCriteriaException extends Exception {
	String password; 
	public LetterCriteriaException(String password) {
		this.password = password;
	}
	public String toString() {
		return "LetterCriteriaException: " + password;
	}
}

class SpecialCharCriteriaException extends Exception {
	String password; 
	public SpecialCharCriteriaException(String password) {
		this.password = password;
	}
	public String toString() {
		return "SpecialCharCriteriaException: " + password;
	}

}
			
//			
//				
////			if (!hasNumber || !hasLetter || !hasSpecialChar) {
////				// Throw CriterError
////				System.out.println("Missing Criteria");
////		}
			
//	}
//		}
//
//}

//
//
//

//
//
//	
//	

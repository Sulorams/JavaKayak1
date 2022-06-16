package assignment;

import java.util.Scanner;

public class Q2checkForDigitInString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Enter input string:");
		Scanner s=new Scanner(System.in);
		
		String input=s.next();
		
		//Method 1
		System.out.println("Does String contains only digits? "+input.matches("[0-9]+"));
		
		
		//Method 2
		for(int i=0;i<input.length();i++)
		{
			if(Character.isDigit(input.charAt(i))==false)
			{
				System.out.println("String not only contains number");
				break;
			}
		}
		s.close();		
		
	}

}

package assignment;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Q1findDuplicateInString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub 
		
		System.out.println("Enter the String"); 
		Scanner s=new Scanner(System.in);
		String input=s.next();
	    	
	   char stringArray[]=input.toLowerCase().toCharArray();
	   Set<Character> output=new HashSet<Character>();
	   for(int i=0;i<stringArray.length;i++)
	   {
		   if(output.contains(stringArray[i]))
			   System.out.println("Duplicate :"+stringArray[i]);
		   else
			   output.add(stringArray[i]);
	   }
		s.close();
	}

}

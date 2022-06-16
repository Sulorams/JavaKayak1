package assignment;

import java.util.Scanner;

public class Q3countingVowelsConsonantsInString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner s=new Scanner(System.in); 
		System.out.println("Enter the input:");
		String input=s.next(); 
		
		char inputChar[]=input.toLowerCase().toCharArray();
		int vowCount = 0,conCount = 0;
		for(int i=0;i<inputChar.length;i++)
		{
			if(inputChar[i]=='a'|| inputChar[i]=='e'||inputChar[i]=='i'||inputChar[i]=='o'||inputChar[i]=='u')
			{
				vowCount++;
			}
			else if(Character.isLetter(inputChar[i])==true)
				conCount++;
		}
		
		System.out.println("Vowels Count in the given string :"+vowCount);
		System.out.println("Consonants Count in the given string :"+conCount);
		
		s.close();
		
	}

}

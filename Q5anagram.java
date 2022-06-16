package assignment;

import java.util.Arrays;
import java.util.Scanner;

public class Q5anagram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        System.out.println("Enter the string1");
		Scanner s1=new Scanner(System.in);
		String string1=s1.next();
		
		System.out.println("Enter the string2");
		Scanner s2=new Scanner(System.in);
		String string2=s1.next();
		
		string1=string1.toLowerCase();
		string2=string2.toLowerCase();
		if(string1.length()==string2.length())
		{
			char[] charArray1=string1.toCharArray();
			Arrays.sort(charArray1);
			
			char[] charArray2=string2.toCharArray();
			Arrays.sort(charArray2);
			
			if(Arrays.equals(charArray1, charArray2)==true)
				System.out.println("Given two strings are anagarams");
			else
				System.out.println("Given two strings are not anagarams");
		}
		else
			System.out.println("Given two strings are not anagarams");
		
		s1.close();
		s2.close();
	}

}

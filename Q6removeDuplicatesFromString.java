package assignment;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;

public class Q6removeDuplicatesFromString {
	public static void main(String[] args) {
		
		System.out.println("Enter the input string");
		Scanner s=new Scanner(System.in);
		String string=s.next();
		
		char[] charArray=string.toLowerCase().toCharArray();
		Set<Character> strSet=new LinkedHashSet<Character>();
		for(int i=0;i<charArray.length;i++)
		{
			strSet.add(charArray[i]);
		}
		System.out.println("String after removing dulpicates :"+strSet);
		System.out.print("String after removing dulpicates :");
		Iterator<Character> itr=strSet.iterator();
		while(itr.hasNext())
		{
			System.out.print(itr.next());
		}
		s.close();
	}
}

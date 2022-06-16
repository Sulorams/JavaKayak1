package assignment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Q4countNoOfOccurenceOfCharInString {

	@SuppressWarnings("resource")
	public static void main(String[] args) {

		System.out.println("Enter the input String:");
		Scanner s=new Scanner(System.in);
		String input=s.next();
		System.out.println("Enter the Character:");
		Scanner s1=new Scanner(System.in);
		String inputChar=s1.next();
		inputChar=inputChar.toLowerCase();
		if(inputChar.length()>1)
		{
			System.out.println("Enter only one character in the given string");
		    return;
		}
		else if(!input.contains(inputChar))
		{
				System.out.println("Entered character is not present in the given string ");
				return;
		}
		char charArray[]=input.toLowerCase().toCharArray();
		s.close();
		s1.close();
		Map<Character,Integer> m=new HashMap<Character,Integer>();
		
		List<Character> lArray=new ArrayList<Character>();
		for(int i=0;i<charArray.length;i++)
		{
			lArray.add(charArray[i]);
		}
		Collections.sort(lArray);
		int rep=1;
		int i;
		for( i=0;i<(lArray.size()-1);i++)
		{
				if(lArray.get(i)==lArray.get(i+1))
				{
					rep++;
				}
				else
				{
				m.put(lArray.get(i),rep);
				rep=1;
				}
		}
			
		m.put(lArray.get(i),rep);
	
		 System.out.println("No of Occurances of given letter in the String:"+m.get(inputChar.charAt(0)));
		Set<Character> keys=m.keySet();
	     Iterator<Character> itr=keys.iterator();
	     System.out.println();
	     System.out.println(m);
	     System.out.println("No of Occurances of each letter in the given string");
		while(itr.hasNext())
		{
			Character key=(Character) itr.next();
			System.out.println(key +" : "+m.get(key));
		}
	
	}

}

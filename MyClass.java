import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Scanner;

public class MyClass {

	public static void main(String[] args) {
	   
		  SortedSet<String> set = new TreeSet<String>(); 
		  set.add("1");
		  set.add("3");
		  set.add("9");
		  set.add("5");
		  set.add("6");
	
	      Iterator<String> it = set.iterator();
	      while (it.hasNext()) {
	         Object ele = it.next();
	         System.out.println("Value = " + ele.toString());
	      }	      
		
	}
	
}
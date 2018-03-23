package solutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import interfaces.MySet;
import mySetImplementations.Set2;
import setIntersectionFinders.AbstractIntersectionFinder;

public class P3Solution <E> extends AbstractIntersectionFinder<E>{
	
	 
	public P3Solution(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	

	@Override
	public MySet<E> intersectSets(MySet<E>[] t) {
			ArrayList<Integer>  allElements = new ArrayList(); 
		
		if (this.getName() == "3"){ 
		for (MySet<E> subset : t)
	    {
			Iterator iter = subset.iterator();
			while(iter.hasNext()){
			allElements.add((Integer) iter.next());
			}
	    }		
		}	
		allElements.sort(null);
		MySet set = new Set2(); 
		Integer e = allElements.get(0);
		Integer c = 1;
		for (int i=1; i<=allElements.size(); i++) {
		      if (i < allElements.size() && allElements.get(i).equals(e)) 
		          c++;
		      else { 
		          if (c == t.length) 
		             set.add(e);   
		          e = allElements.get(i); 
		          c = 1; 
		      } 
		}
		
		
		
		return set;
	}
		
		
	}


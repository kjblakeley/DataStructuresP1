/*
Gian Acevedo  802120065 Seccion 090
Kevin J Blakeley 802120763 Seccion 030

*/
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

	
	ArrayList<E>  allElements = new ArrayList(); 
	@Override
	public MySet<E> intersectSets(MySet<E>[] t) {


			for (MySet<E> subset : t)
			{
				Iterator<E> iter = subset.iterator();
				while(iter.hasNext()){

					allElements.add(iter.next());

				}
			}		
			
		allElements.sort(null);
		MySet<E> set = new Set2<E>(); 
		E e = allElements.get(0);
		Integer c = 1;
		for (int i=1; i<allElements.size(); i++) {
			if (allElements.get(i).equals(e)) 
				c++;
			else { 
		          if (c == t.length) 
		             set.add(e);   
		          e = allElements.get(i); 
		          c = 1; 
		      } 
		}
		if (c == t.length) 
            set.add(allElements.get(allElements.size()-1));  
		return set;
	}
		
		
	}


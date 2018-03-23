package solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.naming.InvalidNameException;

import interfaces.MySet;
import mySetImplementations.Set2;
import setIntersectionFinders.AbstractIntersectionFinder;

public class P4Solution<E> extends AbstractIntersectionFinder<E>  {

	public P4Solution(String name){
		super(name);
	}


//
	@SuppressWarnings("unchecked")
	@Override
	public MySet<E> intersectSets(MySet<E>[] t) {

		
		ArrayList<E> allElements = new ArrayList<>();


				for(MySet<E> subset: t){
					Iterator<E> iter = subset.iterator();
				while(iter.hasNext())
				allElements.add(iter.next());
			
				}
			
		HashMap<Integer, Integer> map = new HashMap<>(); 
		for (E e : allElements) { 
			Integer c = map.getOrDefault(e, 0); 
			map.put((Integer) e, c+1); 
		}
		MySet<E> theSet = new Set2<>(); 
		for (Map.Entry<Integer, Integer> entry : map.entrySet())
			if (entry.getValue() == t.length) 
				theSet.add((E)entry.getKey());

		return theSet;
	}
}

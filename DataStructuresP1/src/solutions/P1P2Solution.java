/*
Gian Acevedo  802120065 Seccion 090
Kevin J Blakeley 802120763 Seccion 030

*/
package solutions;

import javax.swing.text.html.HTMLDocument.Iterator;

import interfaces.MySet;
import mySetImplementations.Set1;
import mySetImplementations.Set2;
import setIntersectionFinders.AbstractIntersectionFinder;

public class P1P2Solution<E> extends AbstractIntersectionFinder<E> {

	public P1P2Solution(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public MySet intersectSets(MySet[] t) {
		
		MySet set;   //creates default set for P1 in order to remove unwanted numbers
		
		
		
		if (this.getName() == "1") {
			set= new Set1();
			
			for(Object o: t[0])
				set.add(o);
			
			for(int i=1; i<t.length; i++) {
				java.util.Iterator iter = set.iterator();
				while(iter.hasNext()){
					if(!t[i].contains(iter.next()))
						iter.remove();
				}
			}
			return set;
		}
		
		
		if(this.getName() == "2") {
			set= new Set2() ;
			for(Object o: t[0])
				set.add(o);
			
			for(int i=1; i<t.length; i++) {
				java.util.Iterator iter = set.iterator();
				while(iter.hasNext()){
					if(!t[i].contains(iter.next()))
						iter.remove();
				}
			}
			return set;
		}
		
		return null;
	}

	
}

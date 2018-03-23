package p1MainClasses;

import java.io.FileNotFoundException;

import javax.naming.InvalidNameException;

import dataGenerator.DataReader;
import interfaces.MySet;
import mySetImplementations.Set1;
import mySetImplementations.Set2;
import solutions.P1P2Solution;
import solutions.P3Solution;
import solutions.P4Solution;

public class Part1Main<E> {
//
	public static void main(String[] args) throws FileNotFoundException, InvalidNameException {
		
		DataReader dr = new DataReader();
		Integer[][][] data = (Integer[][][]) dr.readDataFiles();
		
		P1P2Solution<Integer> finder = new P1P2Solution<Integer>("1");
		P1P2Solution<Integer> finder2 = new P1P2Solution<Integer>("2");
		P3Solution<Integer> finder3 = new P3Solution<Integer>("3");
		P4Solution<Integer> finder4 = new P4Solution<Integer>("4");
		
		MySet<Integer>[] unions = (MySet<Integer>[]) toSetArray1(data);
		Set1<Integer> set = (Set1<Integer>) finder.intersectSets(unions);
		System.out.println("Final set by P1: " + set);	
		
		MySet<Integer>[] unions2 = (MySet<Integer>[]) toSetArray2(data);
		Set2<Integer> set2 = (Set2<Integer>) finder2.intersectSets(unions2);
		System.out.println("Final set by P2: " + set2);	
		
		MySet<Integer>[] unions3 = (MySet<Integer>[]) toSetArray2(data);
		Set2<Integer> set3 = (Set2<Integer>) finder3.intersectSets(unions3);
		System.out.println("Final set by P3: " + set3);	
		
		MySet<Integer>[] unions4 = (MySet<Integer>[]) toSetArray2(data);
		Set2<Integer> set4 = (Set2<Integer>) finder4.intersectSets(unions4);
		System.out.println("Final set by P4: " + set4);	
		
		
		
	}
	
	private static <E> Object[] toSetArray1(E[][][] data){
		MySet[] setArray = new MySet[data[0].length];
		for(int i=0; i<50; i++){
			Set1<E> array = new Set1<E>();
			for(int j=0; j<20; j++){
				for(int k=0; k<data[j][i].length; k++){
					array.add(data[j][i][k]);
				}
			}
			setArray[i] = array;
		}
		return setArray;
	}
	
	private static <E> Object[] toSetArray2(E[][][] data){
		MySet[] setArray = new MySet[data[0].length];
		for(int i=0; i<50; i++){
			Set2<E> array = new Set2<E>();
			for(int j=0; j<20; j++){
				for(int k=0; k<data[j][i].length; k++){
					array.add(data[j][i][k]);
				}
			}
			setArray[i] = array;
		}
		return setArray;
	}
}

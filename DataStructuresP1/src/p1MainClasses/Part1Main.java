package p1MainClasses;

import java.io.FileNotFoundException;

import dataGenerator.DataReader;
import interfaces.MySet;
import mySetImplementations.Set1;
import solutions.P1andP2;

public class Part1Main<E> {

	public static void main(String[] args) throws FileNotFoundException {
		
		DataReader dr = new DataReader();
		P1andP2<Integer> finder = new P1andP2<Integer>("1");
		Integer[][][] data = (Integer[][][]) dr.readDataFiles();
		
		Set1<Integer>[] unions = (Set1<Integer>[]) toSetArray1(data);
		Set1<Integer> set = (Set1<Integer>) finder.intersectSets(unions);
		System.out.println("Final set by P1: " + set);	
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
}

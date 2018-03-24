/*
Gian Acevedo  802120065 Seccion 090
Kevin J Blakeley 802120763 Seccion 030

*/
package experimentalClasses;

import interfaces.IntersectionFinder;
import interfaces.MySet;
import mySetImplementations.Set1;
import mySetImplementations.Set2;

import java.util.ArrayList;
import java.util.Map;

import dataGenerator.DataGenerator;
import dataGenerator.DataReader;
import solutions.P1P2Solution;
import solutions.P3Solution;
import solutions.P4Solution;


/**
 * An object of this type will contain the results of random experiments
 * to estimate the average execution time per size of a particular strategy. 
 * It also stores the partial sum of the times that the particular strategy
 * has taken during the experimental trials. 
 * 
 * An object of this type will embed a particular strategy. When that particular
 * strategy is executed from an ExperimentController object, this object will 
 * contain the computed average execution times for each input size that it
 * has experimented with. 
 * 
 * Notice that this is implemented as a subclass of 
 * ArrayList<Matp.Entry<Integer, Float>>
 * 
 * @author pedroirivera-vega
 *
 */
public class StrategiesTimeCollection<E> extends ArrayList<Map.Entry<Integer, Float>> { 
    private IntersectionFinder<E> strategy;    // the strategy
    private float sum;   
    // variable to accumulate the sum of times that different
    // executions for the same time take. It is eventually used
    // to determine the average execution time for a particular 
    // size.....
    
    //Integer[][][] dataset = (Integer[][][]) new DataGenerator(n, m, size).generateData();
    
    public StrategiesTimeCollection(IntersectionFinder<E> strategy) { 
        this.strategy = strategy; 
    } 
    
    public StrategiesTimeCollection[] resultsPerStrategy = { 
       new StrategiesTimeCollection<Integer>(new P1P2Solution<Integer>("1")),
       new StrategiesTimeCollection<Integer>(new P1P2Solution<Integer>("2")),
       new StrategiesTimeCollection<Integer>(new P3Solution<Integer>("3")),
       new StrategiesTimeCollection<Integer>(new P4Solution<Integer>("4"))}; 
    
    public String getStrategyName() { 
        return strategy.getName(); 
    }
    
    public void runTrial(E[][][] data) { 
    	
    	MySet<E>[] t;
        if(getStrategyName() == "1"){
             t= new Set1[DataReader.getM()];
        }
        else{
             t= new Set2[DataReader.getM()];
        }
        for (int j=0; j<DataReader.getM(); j++) {
            if(getStrategyName() == "3" || getStrategyName() == "4"|| getStrategyName() == "2")
                t[j] = new Set2<>();
            else
                t[j] = new Set1<>();
            for (int i=0; i<DataReader.getN(); i++) {
                for (int k = 0; k < data[i][j].length; k++)
                    t[j].add(data[i][j][k]);  // add to set t[j] the element dataset[i][j][k]
            }
        }
        
    		strategy.intersectSets(t);
    	}

    
    
    public void resetSum() { 
    	sum = 0.0f; 
    }
    
    public void incSum(float t) { 
    	sum += t; 
    }
    
    public float getSum() { 
    	return sum; 
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
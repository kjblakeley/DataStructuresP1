package experimentalClasses;

import interfaces.IntersectionFinder;
import interfaces.MySet;

import java.util.ArrayList;
import java.util.Map;

import dataGenerator.DataGenerator;
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
    
    Integer[][][] dataset = (Integer[][][]) new DataGenerator(n, m, size).generateData();
    
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
    
    public void runTrial(MySet<E>[] data) { 
    	
    	strategy.intersectSets(data);
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
    
}
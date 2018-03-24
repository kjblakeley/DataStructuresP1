/*
Gian Acevedo  802120065 Seccion 090
Kevin J Blakeley 802120763 Seccion 030

*/
package p1MainClasses;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Map;

import dataGenerator.DataGenerator;
import experimentalClasses.StrategiesTimeCollection;


public class ExperimentController<E>{
	
	private int n;						//telephone companies
	private int m;						//crime events
	private int initialSize;           // initial size to be tested
	private int repetitionsPerSize;    // experimental repetitions per size
	private int incrementalSizeStep;   // change of sizes (size delta)
	private int finalSize;             // last size to be tested
	
	private ArrayList<StrategiesTimeCollection<Integer>> resultsPerStrategy; 
	
	
	public ExperimentController(int n,int m, int is, int fs, int iss, int rps) { 
		this.n=n;
		this.m=m;
		initialSize = is; 
		repetitionsPerSize = rps; 
		incrementalSizeStep = iss; 
		finalSize = fs; 
		resultsPerStrategy = new ArrayList<>(); 
		
        

	}
	
	public void addStrategy(StrategiesTimeCollection<E> strategiesTimeCollection) { 
		resultsPerStrategy.add((StrategiesTimeCollection<Integer>) strategiesTimeCollection); 
	}

	public void run() throws CloneNotSupportedException { 
		if (resultsPerStrategy.isEmpty())
			throw new IllegalStateException("No strategy has been added."); 
		for (int size=initialSize; size<=finalSize; size+=incrementalSizeStep) { 
			// For each strategy, reset the corresponding variable that will be used
			// to store the sum of times that the particular strategy exhibits for
			// the current size size
			for (StrategiesTimeCollection<Integer> strategy : resultsPerStrategy) 
				strategy.resetSum();  
			
			// Run all trials for the current size. 
			for (int r = 0; r<repetitionsPerSize; r++) {
				// The following will be the common dataset to be used in the current 
				// trial by all the strategies being tested.
				 Integer[][][] dataset = generateData(n, m, size);  
				
				// Apply each one of the strategies being tested using the previous 
				// dataset (of size size) as input; and, for each, estimate the time
				// that the execution takes. 
				for (StrategiesTimeCollection<Integer> strategy : resultsPerStrategy) {  
					// no need to clone the data set to be used by each strategy since
					// no modification of it is done in the process...
					 long startTime = System.nanoTime();  // Measure system’s clock time before.
			            strategy.runTrial(dataset);          // Run the strategy using the data in dataset.        
			            long endTime = System.nanoTime();    // Measure system’s clock time after.

			            int estimatedTime = (int) (endTime-startTime);   // The estimated time.
					// accumulate the estimated time (add it) to sum of times that
					// the current strategy has exhibited on trials for datasets
					// of the current size. 
					strategy.incSum((int) (endTime-startTime));    
					
				}
			}
			
			for (StrategiesTimeCollection<Integer> strategy : resultsPerStrategy) { 
				strategy.add(new AbstractMap.SimpleEntry<Integer, Float>
				(size, (strategy.getSum()/((float) repetitionsPerSize)))); 
			}

			System.out.println(size); 

		}
	}
	
	private Integer[][][] generateData(int n, int m, int size) {
		DataGenerator dg = new DataGenerator(n, m, size);
		//Integer[] data = dg.generateData();  
		Integer[][][] data = (Integer[][][]) dg.generateData();
		return data;
	}

	public void saveResults() throws FileNotFoundException { 
		
		PrintStream out = new PrintStream(new File("experimentalResults", "allResults.txt"));
		out.print("Size");
		for (StrategiesTimeCollection<Integer> trc : resultsPerStrategy) 
			out.print("\t" + trc.getStrategyName()); 
		out.println();

		int numberOfExperiments = resultsPerStrategy.get(0).size(); 
		for (int i=0; i<numberOfExperiments; i++) {
			out.print(resultsPerStrategy.get(0).get(i).getKey());
			for (StrategiesTimeCollection<Integer> trc : resultsPerStrategy)
				out.print("\t" + trc.get(i).getValue());
			out.println(); 
		}
			
		out.close();
		
	}
}



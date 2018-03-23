package p1MainClasses;

import java.io.FileNotFoundException;

import solutions.P1P2Solution;
import solutions.P3Solution;
import solutions.P4Solution;

import experimentalClasses.StrategiesTimeCollection;

//import strategiesClasses.BubbleSort;

/**
 * 
 * @author pedroirivera-vega
 *
 */
public class Part2Main {

	private static int[] parms = {10, 50,1000, 50000, 1000, 200};
	// number of companies ,number of crime events, min size, max size, size increment, number of trials per size/strategy
	
	public static void main(String[] args) throws CloneNotSupportedException {
		if (args.length > 6)
			System.out.println("Unexpected number of parameters. Must me <= 4.");
		for (int i=0; i<args.length; i++)
			parms[i] = Integer.parseInt(args[i]); 
		// Parm1: number of companies
		// Parm2: number of crime events
		// Parm3: initial size
		// Parm4: final size to consider
		// Parm5: incremental steps (size)
		// Parm6: trials per size
		ExperimentController ec = new ExperimentController(parms[0], parms[1], parms[2], parms[3],parms[4],parms[5]); 
		
		/**/	
		ec.addStrategy(new StrategiesTimeCollection<Integer>(new P1P2Solution<Integer>("1")));
		ec.addStrategy(new StrategiesTimeCollection<Integer>(new P1P2Solution<Integer>("2")));
		ec.addStrategy(new StrategiesTimeCollection<Integer>(new P3Solution<Integer>("3")));
		ec.addStrategy(new StrategiesTimeCollection<Integer>(new P4Solution<Integer>("4")));
		/**/

		ec.run();    // run the experiments on all the strategies added to the controller object (ec)
		
		// save the results for each strategy....
		try {
			ec.saveResults();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}

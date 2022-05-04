package generator;
import java.util.ArrayList;
import java.util.Collections;

import ereporter.ExceptionReporter;
import sequence.NumberSequence;

/**
 * This is the more efficient generator of the two currently implemented generators.
 * Here we trade the ability to influence how often we generate sequential integers with
 * the speed we get from generating large collections of integers.
 * 
 * This generator simply generates a list of numbers from MIN to MAX inclusive, then proceeds
 * to shuffle the list to produce a random list of integers.
 * 
 * @author Imtiyaaz Thompson
 *
 */
public class ShuffleGenerator extends IntegerGenerator {
	private int shuffles;
	
	public ShuffleGenerator(int min,int max) {
		super(min,max);
		this.shuffles = 1;
	}
	
	/**
	 * Allow for more shuffling of the list
	 * 
	 * @param times The amount of time to shuffle the list of generated numbers
	 * @return ShuffleGenerator
	 */
	public ShuffleGenerator shuffle(int times) {
		try {
			if (times <= 0) {
				throw new IllegalArgumentException("The number of shuffles must be greater than zero");
			}
			this.shuffles = times;
		} catch (IllegalArgumentException iae) {
			ExceptionReporter.errReasonAndExit(iae,"Ensure the amount of shuffles are greater than zero");
			System.exit(1);
		}
		
		return this;
	}
	
	public ArrayList<Integer> generate() {
		int n = this.size;
		for (int i = 0; i < n; i++) {
			this.integers.add(this.min + i);
		}
		
		for (int i = 0; i < this.shuffles; i++) {
			Collections.shuffle(this.integers);	
		}
		return this.integers;
	}
}

package generator;
import java.util.ArrayList;
import java.util.Collections;

import ereporter.ExceptionReporter;
import sequence.NumberSequence;

public class ShuffleGenerator extends IntegerGenerator {
	private int shuffles;
	
	public ShuffleGenerator(int min,int max) {
		super(min,max);
		this.shuffles = 1;
	}
	
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
	
	public static void main(String args[]) {
		System.out.println("\nAttempting shuffle generation of integers");
		ShuffleGenerator gen = new ShuffleGenerator(1,100);
		long start = System.nanoTime();
		ArrayList<Integer> newSequence = gen.generate();
		long end = System.nanoTime();
		long elapsedShuffle = (end - start)/1000000;
		NumberSequence newSeq = new NumberSequence(newSequence,NumberSequence.stringifySequence(newSequence));
		System.out.println(newSeq);
		System.out.println("Shuffle Generation: " + elapsedShuffle + "ms");
	}
}

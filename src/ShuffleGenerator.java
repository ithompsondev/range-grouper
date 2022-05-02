import java.util.ArrayList;
import java.util.Collections;

public class ShuffleGenerator extends IntegerGenerator {
	private int shuffles;
	
	public ShuffleGenerator(int size) {
		super(size);
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
	
	public ArrayList<Integer> generate(int min,int max) {
		this.checkBounds(min,max);
		
		int n = this.size;
		for (int i = 0; i < n; i++) {
			if (i >= n) {
				// break here to maintain a collection of unique random numbers
				break;
			}
			this.integers.add(min + i);
		}
		
		for (int i = 0; i < this.shuffles; i++) {
			Collections.shuffle(this.integers);	
		}
		return this.integers;
	}
	
	public static void main(String args[]) {
		System.out.println("\nAttempting shuffle generation of integers");
		ShuffleGenerator gen = new ShuffleGenerator(100);
		long start = System.nanoTime();
		ArrayList<Integer> newSequence = gen.generate(100,1);
		long end = System.nanoTime();
		long elapsedShuffle = (end - start)/1000000;
		NumberSequence newSeq = new NumberSequence(newSequence,NumberSequence.stringifySequence(newSequence));
		//System.out.println(newSeq);
		System.out.println("Shuffle Generation: " + elapsedShuffle + "ms");
	}
}

package generator;
import java.util.ArrayList;
import java.util.Collections;

import sequence.NumberSequence;

public class ControlledGenerator extends IntegerGenerator {
	private int step;
	// The chance the generator has of generating the next number in the sequence
	private double sequentialChance;
	
	// Init the generator with a default step of 1 and a random chance to produce a sequential
	// number
	public ControlledGenerator(int min,int max) {
		super(min,max);
		this.step = 1; // sequential numbers will have a difference of 1 if sequentially generated
		this.sequentialChance = Math.random();
	}
	
	public ControlledGenerator nextStep(int step) {
		this.step = step;
		return this;
	}
	
	public ControlledGenerator withChance(double sequentialChance) {
		this.sequentialChance = sequentialChance;
		return this;
	}
	
	// Generate the next integer in the sequence given the step
	private int generateNextInteger(int prev) {
		return (prev + this.step);
	}
	
	// Generate a list of (MAX - MIN + 1) random integers between MIN and MAX
	public ArrayList<Integer> generate() {
		int n = this.size;
		int prev = 0;
		int randomInteger = 0;
		this.integers.clear();
		
		for (int i = 0; i < n; i++) {
			if (i >= n) {
				// break here to maintain a collection of unique random numbers
				break;
			}
			
			if (this.canGenerateSequential()) {
				// There should be at least one element in the collection before generating the
				// next sequential number
				randomInteger = this.generateNextInteger(prev);
			} else {
				randomInteger = this.generateRandomInteger();
			}
			
			// If the collection already contains the currently generated integer OR
			// The currently generated integers is the product of a sequential generation
			// That is larger than the bounds set by the MAX variable, then generate a new 
			// random integer
			if (this.isNotBoundedOrContained(randomInteger,max)) {
				// Generate unique integers
				while (this.isNotBoundedOrContained(randomInteger,max)) {
					randomInteger = this.generateRandomInteger();
				}
			}
			
			this.integers.add(randomInteger);
			prev = randomInteger;
		}
		
		return this.integers;
	}
	
	private boolean canGenerateSequential() {
		// Check that there is at least one element in the collection
		return this.integers.size() > 0 && Math.random() < this.sequentialChance;
	}
	
	private boolean isBounded(int genInteger,int max) {
		return (genInteger <= max);
	}
	
	private boolean isNotBoundedOrContained(int genInteger,int max) {
		return (!this.isBounded(genInteger,max) || this.integers.contains(genInteger));
	}
	
	public static void main(String args[]) {
		System.out.println("Attempting primary generation of integers");
		ControlledGenerator gen = new ControlledGenerator(1,100).nextStep(1).withChance(0.5);
		System.out.printf("Sequential Chance %.2f\n",gen.sequentialChance);
		long start = System.nanoTime();
		ArrayList<Integer> sequence = gen.generate();
		long end = System.nanoTime();
		long elapsedPrimary = (end - start)/1000000;
		String strSequence = NumberSequence.stringifySequence(sequence);
		NumberSequence nseq = new NumberSequence(sequence,strSequence);
		System.out.println(nseq);
		System.out.println("Controlled Generation: " + elapsedPrimary + "ms");
	}
}

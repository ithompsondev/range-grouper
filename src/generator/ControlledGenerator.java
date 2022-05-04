package generator;
import java.util.ArrayList;
import java.util.Collections;

import sequence.NumberSequence;

/**
 * This proves to be the least efficient generator for generating unique integers
 * as opposed to the ShuffleGenerator. However, we trade efficiency with the ability to
 * generate more sequential integers, which is useful for testing the correctness 
 * of the solution.
 * 
 * We make our first assumption here: The numbers A and B are sequential if and only if
 * B = A + 1
 * 
 * @author Imtiyaaz Thompson
 *
 */
public class ControlledGenerator extends IntegerGenerator {
	private int step;
	// The chance the generator has of generating the next number in the sequence
	private double sequentialChance;

	public ControlledGenerator(int min,int max) {
		super(min,max);
		this.step = 1; // sequential numbers will have a difference of 1 if sequentially generated
		this.sequentialChance = Math.random();
	}
	
	/**
	 * We can later expand on the assumption where the numbers A and B are sequential if and
	 * only if: B = A + STEP
	 * 
	 * @param step The increment from the number A to B if B is sequential to A
	 * @return ControlledGenerator
	 */
	public ControlledGenerator nextStep(int step) {
		this.step = step;
		return this;
	}
	
	/**
	 * Control the chance with which this generator will produce a number sequential to
	 * the previously generated number
	 * 
	 * @param sequentialChance The chance as a probability from 0.0 to 1.0
	 * @return ControlledGenerator
	 */
	public ControlledGenerator withChance(double sequentialChance) {
		this.sequentialChance = sequentialChance;
		return this;
	}
	
	private int generateNextInteger(int prev) {
		return (prev + this.step);
	}
	
	public ArrayList<Integer> generate() {
		int n = this.size;
		int prev = 0;
		int randomInteger = 0;
		this.integers.clear();
		
		for (int i = 0; i < n; i++) {
			if (this.canGenerateSequential()) {
				// There should be at least one element in the collection before generating the
				// next sequential number
				randomInteger = this.generateNextInteger(prev);
			} else {
				randomInteger = this.generateRandomInteger();
			}
			
			/*
			 * If the collection already contains the currently generated integer OR
			 * The currently generated integer is the product of a sequential generation
			 * that is larger than the bounds set by the MAX variable, 
			 * then generate a new random integer (Generate unique integers).
			 */
			if (this.isNotBoundedOrContained(randomInteger,max)) {
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
		return this.integers.size() > 0 && Math.random() < this.sequentialChance;
	}
	
	private boolean isBounded(int genInteger,int max) {
		return (genInteger <= max);
	}
	
	private boolean isNotBoundedOrContained(int genInteger,int max) {
		return (!this.isBounded(genInteger,max) || this.integers.contains(genInteger));
	}
}

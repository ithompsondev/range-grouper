package generator;
import java.util.ArrayList;

import ereporter.ExceptionReporter;

/**
 * 
 * @author Imtiyaaz Thompson
 *
 * The base class for number generating classes that make use of a different method for
 * generating a collection of non-decimal whole numbers. Random or otherwise.
 */
public abstract class IntegerGenerator {
	
	protected int size;
	protected int min;
	protected int max;
	protected ArrayList<Integer> integers;
	
	/**
	 * Derive the size of the ArrayList using: MAX - MIN + 1. This primes the
	 * generator to generate numbers between MIN and MAX inclusive.
	 * 
	 * @param min The lower bound of the range of numbers to be generated
	 * @param max The upper bound of the range of numbers to be generated
	 */
	public IntegerGenerator(int min,int max) {
		this.checkBounds(min,max);
		this.size = max - min + 1;
		this.min = min;
		this.max = max;
		this.integers = new ArrayList<>(size);
	}
	
	protected void changeSize(int newSize) {
		this.size = newSize;
		this.integers = new ArrayList<>(size);
	}
	
	
	protected int generateRandomInteger() {
		return (int) Math.floor(Math.random()*(this.max - this.min + 1) + this.min);
	}
	
	protected void checkBounds(int min,int max) {
		try {
			if (min > max) {
				throw new UnsupportedOperationException("Reverse sequential order not supported");
			}
		} catch (UnsupportedOperationException uoe) {
			ExceptionReporter.errReasonAndExit(uoe,"Make sure the maximum number is greater than the minimum number");
		}
	}
	
	public abstract ArrayList<Integer> generate();
}

package generator;
import java.util.ArrayList;

import ereporter.ExceptionReporter;

public abstract class IntegerGenerator {
	
	protected int size;
	protected int min;
	protected int max;
	protected ArrayList<Integer> integers;
	
	// Derive the size of the collection from the (max - min + 1)
	// Emulates the Python range(min,max-1) function with inclusivity
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
	
	// Generate a random integer between min and max inclusive
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

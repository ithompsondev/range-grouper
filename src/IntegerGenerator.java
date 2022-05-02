import java.util.ArrayList;

public abstract class IntegerGenerator {
	
	protected int size;
	protected ArrayList<Integer> integers;
	
	public IntegerGenerator(int size) {
		this.size = size;
		this.integers = new ArrayList<>(size);
	}
	
	protected void changeSize(int newSize) {
		this.size = newSize;
		this.integers = new ArrayList<>(size);
	}
	
	// Generate a random integer between min and max inclusive
	protected int generateRandomInteger(int min,int max) {
		return (int) Math.floor(Math.random()*(max - min + 1) + min);
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
	
	public abstract ArrayList<Integer> generate(int min,int max);
}

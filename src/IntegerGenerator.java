import java.util.ArrayList;

public class IntegerGenerator {
	private ArrayList<Integer> integers;
	
	/*
	 * ASSUME THAT ALL GENERATED INTEGERS IN THE RANGE ARE UNIQUE
	 */
	
	public IntegerGenerator() {
		this.integers = new ArrayList<>();
	}
	
	// Generate a random integer between min and max inclusive
	private int generateRandomInteger(int min,int max) {
		return (int) Math.floor(Math.random()*(max - min + 1) + min);
	}
	
	// Generate a list of (MAX - MIN + 1) random integers between MIN and MAX
	// Assumes a step of 1
	public ArrayList<Integer> generate(int min, int max) {
		/*
		 * TODO: CHECK THAT MIN < MAX
		 */
		int n = max - min + 1;
		this.integers.clear();
		for (int i = 0; i < n; i++) {
			int randomInteger = this.generateRandomInteger(min,max);
			if (this.integers.contains(randomInteger)) {
				while (this.integers.contains(randomInteger)) {
					randomInteger = this.generateRandomInteger(min,max);
				}
			}
			
			this.integers.add(randomInteger);
		}
		
		return this.integers;
	}
	
	public static void main(String args[]) {
		IntegerGenerator gen = new IntegerGenerator();
		ArrayList<Integer> sequence = gen.generate(1,12);
		String strSequence = NumberSequence.stringifySequence(sequence);
		NumberSequence nseq = new NumberSequence(sequence,strSequence);
		
		System.out.println(nseq);
	}
}

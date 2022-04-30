import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class NumberSequence {
	private ArrayList<Integer> sequence;
	private String strSequence;
	
	// Create a number sequence from a integer collection AND delimited string
	public NumberSequence(Collection<Integer> sequence,String strSequence) {
		this.sequence = new ArrayList<>(sequence);
		this.strSequence = strSequence;
	}
	
	public ArrayList<Integer> getSequence() {
		return this.sequence;
	}
	
	@Override
	public String toString() {
		return this.strSequence;
	}
	
	public static String stringifySequence(Collection<Integer> seq) {
		return seq.stream().map(Object::toString).collect(Collectors.joining(","));
	}
	
	public static void main(String args[]) {
		ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1,2,3,4,5));
		String strSeq = NumberSequence.stringifySequence(numbers);
		NumberSequence sequence = new NumberSequence(numbers,strSeq);
		System.out.println(sequence);
		for (int number: numbers) {
			System.out.println(number);
		}
	}
}

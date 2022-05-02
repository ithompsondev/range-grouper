import java.util.ArrayList;

public class RangeGrouper {
	/*
	 * Consider range steps
	 * Meaning the default step is one and we consider 
	 * 1) two numbers to be sequential when a, a+1 AND/OR
	 * 2) two numbers to be sequential when a, a-1
	 * 
	 * Generally,
	 * 1) a, a+STEP AND/OR
	 * 2) a, a-STEP
	 * 
	 * Taking into account reverse sequential ordering
	 */
	
	private class Range {
		private int start;
		private int end;
		
		private Range(int start,int end) {
			this.start = start;
			this.end = end;
		}
		
		@Override
		public String toString() {
			return (start + " - " + end);
		}
	}
	
	private ArrayList<Range> rangeGroups;
	private NumberSequence sequence;
	
	public RangeGrouper(NumberSequence sequence) {
		this.sequence = sequence;
		this.rangeGroups = new ArrayList<>();
	}
	
	// Using only forward sequential numbers with step 1
	// Refactor
	public void findRanges() {
		ArrayList<Integer> seq = this.sequence.getSequence();
		boolean inRange = false;
		int prev = seq.get(0);
		int start = -1;
		int end = -1;
		for (int i = 1; i < seq.size(); i++) {
			int curr = seq.get(i);
			if (curr == (prev + 1) && !inRange) {
				start = prev;
				inRange = true;
			}
			
			if (curr != (prev + 1) && inRange) {
				end = prev;
				inRange = false;
				this.rangeGroups.add(new Range(start,end));
			}
			
			prev = curr;
		}
	}
	
	public String getRanges() {
		if (this.rangeGroups.size() == 0) {
			return "No ranges available in the given number sequence";
		}
		
		StringBuilder sb = new StringBuilder();
		for (Range range: this.rangeGroups) {
			sb.append(range.toString() + ", ");
		}
		
		return sb.toString();
	}
	
	public static void main(String args[]) {
		IntegerGenerator gen = new IntegerGenerator();
		ArrayList<Integer> seq = gen.generate(1,1000);
		String strSeq = NumberSequence.stringifySequence(seq);
		NumberSequence nseq = new NumberSequence(seq,strSeq);
		RangeGrouper ranges = new RangeGrouper(nseq);
		ranges.findRanges();
		System.out.println("Sequence: " + nseq);
		System.out.println("Ranges: " + ranges.getRanges());
	}
}

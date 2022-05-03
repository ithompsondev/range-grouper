package ranger;
import java.util.ArrayList;
import java.util.Collection;

import numberrangesummarizer.NumberRangeSummarizer;
import sequence.NumberSequence;

public class RangeSummarizer implements NumberRangeSummarizer {
	
	private ArrayList<Range> rangeGroups;
	
	@Override
	public Collection<Integer> collect(String input) {
		Collection<Integer> collection = NumberSequence.collectifySequence(input);
		return collection;
	}

	@Override
	public String summarizeCollection(Collection<Integer> input) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*
	 * 
	 * REFACTOR INTO SUMMARIZE COLLECTION
	 * 
	 */
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
		
		public String showSummary() {
			if (this.rangeGroups.size() == 0) {
				return "No ranges available in the given number sequence";
			}
			
			StringBuilder sb = new StringBuilder();
			for (Range range: this.rangeGroups) {
				sb.append(range.toString() + ", ");
			}
			
			return sb.toString();
		}

}

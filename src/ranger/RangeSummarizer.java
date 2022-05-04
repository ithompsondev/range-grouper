package ranger;
import java.util.Collection;
import java.util.Iterator;

import numberrangesummarizer.NumberRangeSummarizer;
import sequence.NumberSequence;

/**
 * Traverse the number sequence keeping track of when a range of numbers are
 * being processed and constantly saving the results before returning the range groups
 * as a comma delimited string.
 * 
 * @author Imtiyaaz Thompson
 *
 */
public class RangeSummarizer implements NumberRangeSummarizer {
	
	@Override
	public Collection<Integer> collect(String input) {
		Collection<Integer> collection = NumberSequence.collectifySequence(input);
		return collection;
	}

	@Override
	public String summarizeCollection(Collection<Integer> input) {
		StringBuilder ranges = new StringBuilder();
		Iterator<Integer> iter = input.iterator();
		if (!iter.hasNext()) {
			return "";
		}
		
		int prev = iter.next();
		int curr = prev;
		int end = prev;
		boolean processing = false; // track whether or not we are processing a range
		while (iter.hasNext()) {
			curr = iter.next();

			if (this.isSequential(prev,curr)) {
				if (!processing) {
					ranges.append(prev + "-");
					processing = true;
				} 

				end = curr;
			} else {
				if (processing) {
					ranges.append(end + ", ");
					processing = false;
				} else {
					ranges.append(prev + ", ");
				}
				
				end = curr;
			}
			
			prev = curr;
		}
		
		if (end == curr) {
			ranges.append(end + ", ");
		}
		
		// Safe to use since we always build a string to end with the ',' character
		ranges.delete(ranges.toString().lastIndexOf(","),ranges.length());
		return ranges.toString();
	}
	
	private boolean isSequential(int prev,int curr) {
		return (curr == (prev + 1));
	}
}

package ranger;
import java.util.ArrayList;
import java.util.Collection;

import sequence.NumberSequence;

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
	private NumberSequence sequence;
	private Collection<Integer> collection;
	private RangeSummarizer summarizer;
	
	public RangeGrouper(NumberSequence sequence) {
		this.sequence = sequence;
		this.summarizer = new RangeSummarizer();
		this.collection = this.summarizer.collect(sequence.toString());
	}
	
	public void groupRanges() {
		this.summarizer.summarizeCollection(this.collection);
	}
	
	public void display() {
		if (this.collection.size() <= 100) {
			System.out.println(this.sequence);
		}
		
		System.out.println(this.summarizer.showSummary());
	}
}

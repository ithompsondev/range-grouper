package ranger;
import java.util.ArrayList;
import java.util.Collection;

import sequence.NumberSequence;

public class RangeGrouper {
	// We assume two numbers a and b to be sequential if b = a + 1
	private NumberSequence sequence;
	private Collection<Integer> collection;
	private RangeSummarizer summarizer;
	
	public RangeGrouper(NumberSequence sequence) {
		this.sequence = sequence;
		this.summarizer = new RangeSummarizer();
		this.collection = this.summarizer.collect(sequence.toString());
	}
	
	public String groupRanges() {
		String ranges = this.summarizer.summarizeCollection(this.collection);
		return ranges;
	}
}

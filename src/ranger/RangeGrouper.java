package ranger;
import java.util.ArrayList;
import java.util.Collection;

import sequence.NumberSequence;

/**
 * An intermediary class used to interface between the client program: Ranger.java and
 * the number sequence traverser program: RangeSummarizer.java
 * 
 * @author Imtiyaaz Thompson
 *
 */
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
	
	public Collection<Integer> getCollection() {
		return this.collection;
	}
}

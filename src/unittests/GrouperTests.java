package unittests;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import ranger.RangeGrouper;
import sequence.NumberSequence;

public class GrouperTests {
	
	@Test
	public void Correct_Collection_From_Number_Sequence_Test() {
		NumberSequence sequence = new NumberSequence("src/test10.txt",",");
		RangeGrouper grouper = new RangeGrouper(sequence);
		
		Iterator<Integer> collectionIter = grouper.getCollection().iterator();
		Iterator<Integer> sequenceIter = sequence.getSequence().iterator();
		while (collectionIter.hasNext()) {
			int expected = sequenceIter.next();
			int actual = collectionIter.next();
			assertEquals(expected,actual);
		}
	}
	
	@Test
	public void Correct_Fully_Sequential_Sequence_Range_Test() {
		NumberSequence sequence = new NumberSequence("src/test10.txt",",");
		RangeGrouper grouper = new RangeGrouper(sequence);
		
		String expected = "1-10";
		String actual = grouper.groupRanges();
		assertEquals(expected,actual);
	}

	@Test
	public void Correct_Range_For_Single_Element_Collection_Test() {
		NumberSequence sequence = new NumberSequence("src/test1.txt",",");
		RangeGrouper grouper = new RangeGrouper(sequence);
		
		String expected = "1";
		String actual = grouper.groupRanges();
		assertEquals(expected,actual);
	}
}

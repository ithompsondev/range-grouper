package unittests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import sequence.NumberSequence;

public class NumberSequenceTests {

	@Test
	public void Correct_String_Sequence_From_Collection_Test() {
		ArrayList<Integer> integers = new ArrayList<>(Arrays.asList(1,2,3,4,5));
		String expected = "1,2,3,4,5";
		String actual = NumberSequence.stringifySequence(integers);
		
		assertEquals(expected,actual);
	}
	
	@Test
	public void Correct_Collection_From_String_Sequence_Test() {
		String strIntegers = "1,2,3,4,5";
		ArrayList<Integer> integers = new ArrayList<>(NumberSequence.collectifySequence(strIntegers));
		
		for (int i = 0; i < integers.size(); i++) {
			int expected = i + 1;
			int actual = integers.get(i);
			assertEquals(expected,actual);
		}
	}
	
	@Test
	public void Correct_Collection_From_File_String_Sequence_Test() {
		NumberSequence sequence = new NumberSequence("src/test10.txt",",");
		
		for (int i = 0; i < sequence.getSequence().size(); i++) {
			int expected = i + 1;
			int actual = sequence.getSequence().get(i);
			assertEquals(expected,actual);
		}
	}
	
	@Test
	public void Correct_String_Sequence_From_File_String_Sequence_Test() {
		NumberSequence sequence = new NumberSequence("src/test10.txt",",");
		
		String expected = "1,2,3,4,5,6,7,8,9,10";
		String actual = sequence.toString();
		
		assertEquals(expected,actual);
	}

}

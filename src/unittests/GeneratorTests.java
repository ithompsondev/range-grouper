package unittests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import generator.ControlledGenerator;
import generator.ShuffleGenerator;

public class GeneratorTests {

	@Test
	public void Single_Shuffle_Generated_Element_Test() {
		ShuffleGenerator generator = new ShuffleGenerator(1,1);
		ArrayList<Integer> integer = generator.generate();
		int expected = 1;
		int actual = integer.get(0);
		
		assertEquals(expected,actual);
	}

	@Test
	public void Single_Controlled_Generated_Element_Test() {
		ControlledGenerator generator = new ControlledGenerator(1,1);
		ArrayList<Integer> integer = generator.generate();
		int expected = 1;
		int actual = integer.get(0);
		
		assertEquals(expected,actual);
	}
	
	@Test 
	public void Actual_Size_Of_Shuffle_Generated_Collection_Test() {
		ShuffleGenerator generator = new ShuffleGenerator(1,10);
		ArrayList<Integer> integers = generator.generate();
		int expected = 10;
		int actual = integers.size();
	
		assertEquals(expected,actual);
	}
	
	@Test
	public void Actual_Size_Of_Controlled_Generated_Collection_Test() {
		ControlledGenerator generator = new ControlledGenerator(1,10);
		ArrayList<Integer> integers = generator.generate();
		int expected = 10;
		int actual = integers.size();
		
		assertEquals(expected,actual);
	}
	
	@Test
	public void Actual_Size_Of_Computed_Shuffle_Generated_Collection_Test() {
		int min = 1;
		int max = 10;
		ShuffleGenerator generator = new ShuffleGenerator(min,max);
		ArrayList<Integer> integers = generator.generate();
		int expected = max - min + 1;
		int actual = integers.size();
	
		assertEquals(expected,actual);
	}
	
	@Test
	public void Actual_Size_Of_Computed_Controlled_Generated_Collection_Test() {
		int min = 1;
		int max = 10;
		ControlledGenerator generator = new ControlledGenerator(min,max);
		ArrayList<Integer> integers = generator.generate();
		int expected = max - min + 1;
		int actual = integers.size();
	
		assertEquals(expected,actual);
	}
	
	@Test
	public void Forward_Generation_Of_Shuffle_Generated_Integers_Test() {
		int min = 10;
		int max = 1;
		assertThrows(UnsupportedOperationException.class,()->{new ShuffleGenerator(min,max)});
	}
}

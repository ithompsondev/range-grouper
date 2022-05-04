package unittests;

import static org.junit.Assert.*;

import org.junit.Test;

public class AllTests {

	@Test
	public void Run_All_Tests() {
		GeneratorTests gtests = new GeneratorTests();
		NumberSequenceTests nstests = new NumberSequenceTests();
		GrouperTests grtests = new GrouperTests();
		
		gtests.Actual_Size_Of_Computed_Controlled_Generated_Collection_Test();
		gtests.Actual_Size_Of_Computed_Shuffle_Generated_Collection_Test();
		gtests.Actual_Size_Of_Controlled_Generated_Collection_Test();
		gtests.Actual_Size_Of_Shuffle_Generated_Collection_Test();
		gtests.Single_Controlled_Generated_Element_Test();
		gtests.Single_Shuffle_Generated_Element_Test();
		
		nstests.Correct_Collection_From_File_String_Sequence_Test();
		nstests.Correct_Collection_From_String_Sequence_Test();
		nstests.Correct_String_Sequence_From_Collection_Test();
		nstests.Correct_String_Sequence_From_File_String_Sequence_Test();
		
		grtests.Correct_Collection_From_Number_Sequence_Test();
		grtests.Correct_Fully_Sequential_Sequence_Range_Test();
		grtests.Correct_Range_For_Single_Element_Collection_Test();
	}

}

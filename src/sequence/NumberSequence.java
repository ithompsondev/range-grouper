package sequence;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Collectors;

import ereporter.ExceptionReporter;

public class NumberSequence {
	private ArrayList<Integer> sequence;
	private String strSequence;
	private Scanner file;
	
	// Create a number sequence from a integer collection AND delimited string
	public NumberSequence(Collection<Integer> sequence,String strSequence) {
		this.sequence = new ArrayList<>(sequence);
		this.strSequence = strSequence;
 	}
	
	public NumberSequence(Collection<Integer> sequence) {
		this.sequence = new ArrayList<>(sequence);
		this.strSequence = stringifySequence(sequence);
	}

	public NumberSequence(String filePath,String delim) {
		this.sequence = new ArrayList<>();
		try {
			this.loadFile(filePath,delim);
			this.parseFile();
		} catch (FileNotFoundException fne) {
			System.err.println("The file: " + filePath + ", could not be found");
			ExceptionReporter.errReasonAndExit(fne,"Ensure that the file name and extension are entered correctly");
		} catch (InputMismatchException ime) {
			// Did not read an integer, file contains erroneous values
			ExceptionReporter.errReasonAndExit(ime,"Make sure that the file only contains non-decimal whole numbers");
		} catch (NoSuchElementException nse) {
			// Could no longer read for input
			System.out.println("No more input to be parsed");
			ExceptionReporter.errReasonAndExit(nse,"Ensure the read operation does not exceed the limit of the file");
		} catch (NumberFormatException nfe) {
			ExceptionReporter.errReasonAndExit(nfe,"Ensure the input file contains atleast one non-decimal whole number");
		} finally {
			file.close();
		}
		
		this.strSequence = stringifySequence(this.sequence);
	}
	
	public ArrayList<Integer> getSequence() {
		return this.sequence;
	}
	
	private void loadFile(String path,String delim) throws FileNotFoundException {
		this.file = new Scanner(new File(path)).useDelimiter(delim);
	}
	
	private void parseFile() {
		if (!file.hasNext()) {
			throw new NumberFormatException("The file contains no non-decimal whole numbers");
		}
		
		while (file.hasNext()) {
			if (file.hasNextInt()) {
				this.sequence.add(file.nextInt());
			} else {
				throw new InputMismatchException("File contains a non-integer or decimal number");
			}
		}
	}
	
	@Override
	public String toString() {
		return this.strSequence;
	}
	
	public static String stringifySequence(Collection<Integer> seq) {
		return seq.stream().map(Object::toString).collect(Collectors.joining(","));
	}
	
	public static Collection<Integer> collectifySequence(String seq) {
		int convertedArr[] = Arrays.stream(seq.split(",")).mapToInt(Integer::parseInt).toArray();
		Collection<Integer> collection = Arrays.stream(convertedArr).boxed().collect(Collectors.toList());
		
		return collection;
	}
	
	public static void main(String args[]) {
//		ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1,2,3,4,5));
//		String strSeq = NumberSequence.stringifySequence(numbers);
//		NumberSequence sequence = new NumberSequence(numbers,strSeq);
		NumberSequence sequence = new NumberSequence("src/test10.txt",",");
		System.out.println(sequence);
		System.out.println(sequence.getSequence().size());
	}
}

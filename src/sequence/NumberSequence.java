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

/**
 * A Data class used to encapsulate the Collection of integers as a Collection
 * or Object that implements the Collection interface, along with the string representation
 * of that collection as a comma delimited string.
 * 
 * This class also allows the collection of integers to be loaded externally from a text file.
 * 
 * @author Imtiyaaz Thompson
 *
 */
public class NumberSequence {
	private ArrayList<Integer> sequence;
	private String strSequence;
	private Scanner file;
	
	/**
	 * Create a number sequence from a pre-existing Collection and String representation
	 * of that collection
	 * 
	 * @param sequence The Collection of integers
	 * @param strSequence The string representation of the collection of integers
	 */
	public NumberSequence(Collection<Integer> sequence,String strSequence) {
		this.sequence = new ArrayList<>(sequence);
		this.strSequence = strSequence;
 	}
	
	/**
	 * Create a number sequence from only a Collection of integers by also
	 * converting the Collection to a comma delimited string that represents the
	 * collections
	 * 
	 * @param sequence The collection of integers
	 */
	public NumberSequence(Collection<Integer> sequence) {
		this.sequence = new ArrayList<>(sequence);
		this.strSequence = stringifySequence(sequence);
	}

	/**
	 * Load a number sequence from and external file by specifying the delimiter
	 * used to separate the integers. The string representation of the collection of 
	 * integers will be comma delimited regardless of the delimiter used in the file.
	 * 
	 * @param filePath The path to the file that contains the delimited number sequence
	 * @param delim The delimiter used in the file to seperate the integers
	 */
	public NumberSequence(String filePath,String delim) {
		this.sequence = new ArrayList<>();
		try {
			this.loadFile(filePath,delim);
			this.parseFile();
		} catch (FileNotFoundException fne) {
			System.err.println("The file: " + filePath + ", could not be found");
			ExceptionReporter.errReasonAndExit(fne,"Ensure that the file name and extension are entered correctly");
		} catch (InputMismatchException ime) {
			ExceptionReporter.errReasonAndExit(ime,"Make sure that the file only contains non-decimal whole numbers");
		} catch (NoSuchElementException nse) {
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
	
	/**
	 * Convert a Java Collection of integers into a comma delimited string
	 * 
	 * @param seq The Collection of integers
	 * @return The comma delimited string representing the Collection of integers
	 */
	public static String stringifySequence(Collection<Integer> seq) {
		return seq.stream().map(Object::toString).collect(Collectors.joining(","));
	}
	
	/**
	 * Convert a comma delimited string of integers into a Java Collection
	 * 
	 * @param seq The comma delimited string of integers
	 * @return Collection of integers
	 */
	public static Collection<Integer> collectifySequence(String seq) {
		int convertedArr[] = Arrays.stream(seq.split(",")).mapToInt(Integer::parseInt).toArray();
		Collection<Integer> collection = Arrays.stream(convertedArr).boxed().collect(Collectors.toList());
		
		return collection;
	}
}

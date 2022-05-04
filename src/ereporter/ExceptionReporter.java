package ereporter;

/**
 * 
 * @author Imtiyaaz Thompson
 *
 * A static library containing functions for reporting exceptions and displaying
 * their possible provides solutions
 */
public class ExceptionReporter {
	
	/**
	 * Display the exception message along with the proposed solution to
	 * fixing the exception and exit the program.
	 * 
	 * @param e The exception thrown and caught
	 * @param solution The provided solution
	 */
	public static void errReasonAndExit(Exception e,String solution) {
		System.err.println("Message: " + e.getMessage());
		if (e.getCause() != null) {
			System.err.println("Cause: " + e.getCause());
		}
		
		System.out.println("Solution: " + solution);
		System.exit(1);
	}
}

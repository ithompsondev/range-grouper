package ereporter;

public class ExceptionReporter {
	public static void errReasonAndExit(Exception e,String solution) {
		System.err.println("Message: " + e.getMessage());
		if (e.getCause() != null) {
			System.err.println("Cause: " + e.getCause());
		}
		
		System.out.println("Solution: " + solution);
		System.exit(1);
	}
}

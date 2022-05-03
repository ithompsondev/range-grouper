package ranger;
import java.util.Collection;

import ereporter.ExceptionReporter;
import sequence.NumberSequence;

public class Ranger {

	private static int MODE = 0;
	
	private static int FILE = 27;
	private static int GEN = 37;
	
	private static int PATH = 1;
	private static int DELIM = 2;
	
	private static int MIN = 1;
	private static int MAX = 2;
	
	private int mode;
	private String path;
	private String delim;
	private int min;
	private int max;
	
	public Ranger(String args[]) {
		this.setMode(args[MODE]);
		this.init(args);
	}
	
	private void setMode(String mode) {
		if (mode.equals("-f")) {
			this.mode = FILE;
		} else if (mode.equals("-g")) {
			this.mode = GEN;
		}
	}
	
	private void init(String args[]) {
		if (this.mode == FILE) {
			this.initFileConfig(args);
		} else if (this.mode == GEN) {
			this.initGenConfig(args);
		} else {
			this.usage();
		}
	}
	
	private void initFileConfig(String args[]) {
		int n = args.length;
		
		if (n != 3) {
			this.usage();
		}
		
		this.path = args[PATH];
		this.delim = args[DELIM];
	}
	
	private void initGenConfig(String args[]) {
		int n = args.length;
		
		if (n != 3) {
			this.usage();
		}
		
		try {
			this.min = Integer.parseInt(args[MIN]);
			this.max = Integer.parseInt(args[MAX]);
		} catch (NumberFormatException nfe) {
			ExceptionReporter.errReasonAndExit(nfe,"Ensure the min and max arguments are non-decimal integers");
			System.exit(1);
		}
	}
	
	public void usage() {
		System.out.println("Loading sequence from file");
		System.out.println("java Ranger -f path/to/file delimiter");
		System.out.println();
		System.out.println("Generating sequence");
		System.out.println("java Ranger -g min max");
	}
	
	public void showArgs(String args[]) {
		for (String arg: args) {
			System.out.println(arg);
		}
	}
	
	public int getMode() {
		return this.mode;
	}
	
	public String getPath() {
		return this.path;
	}
	
	public String getDelim() {
		return this.delim;
	}
	
	public int getMin() {
		return this.min;
	}
	
	public int getMax() {
		return this.max;
	}
	
	public static void main(String args[]) {
		Ranger ranger = new Ranger(args);
		if (ranger.getMode() == FILE) {
			String path = ranger.getPath();
			String delim = ranger.getDelim();
			NumberSequence sequence = new NumberSequence(path,delim);
			RangeGrouper grouper = new RangeGrouper(sequence);
			grouper.groupRanges();
			grouper.display();
		}
	}
}

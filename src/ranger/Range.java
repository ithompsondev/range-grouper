package ranger;

public class Range {
	private int start;
	private int end;
	
	public Range(int start,int end) {
		this.start = start;
		this.end = end;
	}
	
	@Override
	public String toString() {
		return (this.start + " - " + this.end);
	}
}

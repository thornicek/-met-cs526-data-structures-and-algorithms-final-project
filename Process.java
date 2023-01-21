package finalProject;

public class Process implements Comparable<Process> {
	
	// priority of the process
	public int pr;
	// process id
	public int id;
	// the time when the process arrives at the system
	public int arrivalTime;
	// execution of the process takes this amount of time
	public int duration;
	
	// Initialize object
	public Process(int pr, int id, int arrivalTime, int duration) {
		
		this.pr = pr;
		this.id = id;
		this.arrivalTime = arrivalTime;
		this.duration = duration;
	}
	// Overriding the toString method
	@Override
	public String toString() {
		return "Id = "+ this.id + ", priority = " + this.pr + ", duration = " + this.duration + ", arrival time = " + this.arrivalTime;
	}
	
	// Comparator
	@Override
	public int compareTo(Process otherProcess) {
		return Integer.compare(this.pr, otherProcess.pr);
	}
	
}

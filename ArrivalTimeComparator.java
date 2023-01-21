package finalProject;

import java.util.*; 

//Default Comparator class, used for merge and quick sort
public class ArrivalTimeComparator implements Comparator<Process> {
	public int compare(Process a, Process b) {
		return Integer.compare(a.arrivalTime, b.arrivalTime);
	}
}
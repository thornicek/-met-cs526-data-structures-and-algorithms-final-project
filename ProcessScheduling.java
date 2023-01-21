package finalProject;

import java.util.*;
import java.io.*;

public class ProcessScheduling {
	
	

	public static void main(String[] args) {
		// Initialize variables
		int totalWaitTime = 0;
		int remainingRunTime = 0;
		int maxWaitTime = 30;
		int currentTime = 0;
		Process topPriority = null;
		// Read the file with processes into priority queue ordered by arrival time
		PriorityQueue<Process> arrivalTimePQ = readFile("process_scheduling_input.txt");
		PrintPQ(arrivalTimePQ);
		int size = arrivalTimePQ.size();
		System.out.println("Maximum wait time = " + maxWaitTime);
		PriorityQueue<Process> pq = new PriorityQueue<Process>();
		boolean running = false;
		// Iterate until arrival time pq is empty
		while(arrivalTimePQ.size() != 0) {
			// Get process with lowest arrival time 
			Process mostRecent = arrivalTimePQ.peek();
			if(mostRecent.arrivalTime <= currentTime) {
				// Add process into priority queue ordered by process priority
				pq.add(mostRecent);
				// Remove the process from arrival time pq
				arrivalTimePQ.poll();
			}
			// Remove finished process from pq and compute wait time
			if(pq.size() != 0 && !running) {
				topPriority = pq.poll();
				running = true;
				remainingRunTime = topPriority.duration;
				int waitTime = currentTime - topPriority.arrivalTime; 
				totalWaitTime += waitTime;
				printRemoved(topPriority, currentTime, waitTime, totalWaitTime);
			}
			// Update priorities
			if (remainingRunTime == 0 && topPriority != null) {
				System.out.println("Process "+ topPriority.id + "finished at time " + currentTime);
				running = false;
				updatePriority(pq, currentTime, maxWaitTime);
				
			}
			currentTime++;
			// Update remaining run time of currently executed process
			if (running) {
				remainingRunTime--;
			}
		}
		while (pq.size() != 0) {
			if(pq.size() != 0 && !running) {
				topPriority = pq.poll();
				running = true;
				remainingRunTime = topPriority.duration;
				int waitTime = currentTime - topPriority.arrivalTime;
				totalWaitTime += waitTime;
				printRemoved(topPriority, currentTime, waitTime, totalWaitTime);
			}
			if (remainingRunTime == 0 && topPriority != null) {
				System.out.println("Process "+ topPriority.id + "finished at time " + currentTime);
				running = false;
				updatePriority(pq, currentTime, maxWaitTime);
				
			}
			currentTime++;
			if (running) {
				remainingRunTime--;
			}
		}	
		int averageWaitTime = totalWaitTime / size;
		System.out.println("Total wait time = " + totalWaitTime);
		System.out.println("Average wait time = " + averageWaitTime);
		
		
	}
	// Helper function to print removed process from pq
	public static void printRemoved(Process removedProcess, int currentTime, int waitTime, int totalWaitTime) {
		System.out.println("Process removed from queue is: id = "+ removedProcess.id + " , at time = "+ currentTime +" Total wait time = " + totalWaitTime);
		System.out.println("Process id = " + removedProcess.id);
		System.out.println("Priority = " + removedProcess.pr);
		System.out.println("Arrival = " + removedProcess.arrivalTime);
		System.out.println("Duration = " + removedProcess.duration);
		
	}
	
	// Helper function to update priority
	public static void updatePriority(PriorityQueue<Process> pq, int currentTime, int maxWaitTime) {
		Iterator<Process> iterator = pq.iterator();
		System.out.println("Update priority:");
		while (iterator.hasNext()) {
			Process currentProcess = iterator.next();
			int currentWaitTime = currentTime - currentProcess.arrivalTime;
			if(currentWaitTime > maxWaitTime) {
				currentProcess.pr--;
				System.out.println("PID = " + currentProcess.id + ", new priority = " + currentProcess.pr);
			}
			else {
				System.out.println("PID = " + currentProcess.id + ", wait time = " + currentWaitTime + ", current priority = "+ currentProcess.pr);
			}
			
		}
	
	}
	
	// Helper function to read txt file
	public static PriorityQueue<Process> readFile(String fileName) {
		PriorityQueue<Process> arrivalTimePQ = new PriorityQueue<Process>(new ArrivalTimeComparator()); 
		try {
	      File myObj = new File(fileName);
	      Scanner myReader = new Scanner(myObj);
	      while (myReader.hasNextLine()) {
	        String data = myReader.nextLine();
	        int[] processValues = new int[4];
	        int i = 0;
	        // Parse the data to create Process objects
	        StringTokenizer st = new StringTokenizer(data," ");
	        while(st.hasMoreTokens()) {
	        	int value = Integer.parseInt(st.nextToken());
	        	processValues[i] = value;
	        	i++;
	        }
	      Process myProcess = new Process(processValues[1], processValues[0], processValues[2], processValues[3]);
	      arrivalTimePQ.add(myProcess);  
	      }
	      myReader.close();
	    } catch (FileNotFoundException e) {
	      System.out.println("An error occurred.");
	      e.printStackTrace();
	    }
		return arrivalTimePQ;
	}
	// Print priority queue without affecting passed pq by creating a copy
	public static void PrintPQ(PriorityQueue<Process> pq) {
		int size = pq.size();
		PriorityQueue<Process> pqCopy = new PriorityQueue<Process>(pq);
		for(int i = 0; i < size; i++) {
			System.out.println(pqCopy.poll());
			
		}
	}

}

package simulator;

// **** First Come First Serve  ****

public class FCFS {
	
    public static void calcWaitingTime(int processId[], int n, int burstTime[], int arrivalTime[], int waitingTime[]) {
        // First process
        waitingTime[0] = 0;

        // Waiting time
        for (int i = 1; i < n; i++) {
            waitingTime[i] = (arrivalTime[i - 1]  + burstTime[i - 1] + waitingTime[i - 1]) - arrivalTime[i];

        }
    }

    // Calculate turn around time 
    public static void calcTurnAroundTime(int processId[], int n, int burstTime[], int arrivalTime[], int waitingTime[], int turnAroundTime[]) {
        // Turnaround time -> burstTime[i] + waitingTime[i]
        for (int i = 0; i < n; i++) {
            turnAroundTime[i] = burstTime[i] + waitingTime[i];
        }
    }

    // Average time
    public String calcAvgTime(int processId[], int n, int arrivalTime[], int burstTime[]) {
    	
    	String details;
    	
        int waitingTime[] = new int[n], turnAroundTime[] = new int[n];
        int total_waitingTime = 0, total_turnAroundTime = 0;

        // Waiting time and Turn Around Time for all processes
        calcWaitingTime(processId, n, burstTime, arrivalTime, waitingTime);
        calcTurnAroundTime(processId, n, burstTime, arrivalTime, waitingTime, turnAroundTime);

        // Details for each process
        details = "+-----+------------+--------------+-----------------+----------------+";
        details = details + "\n pid  " + " arrival_time  " + " burst_time  " + "  waiting_time  " + "   turnaround_time  ";
        details = details + "\n ----------------------------------------------------------------------";

        // Total waiting time and total turn around time
        for (int i = 0; i < n; i++) {
            total_waitingTime = total_waitingTime + waitingTime[i];
            total_turnAroundTime = total_turnAroundTime + turnAroundTime[i];
            details = details + "\n " + (i + 1) + "\t\t" + arrivalTime[i] + " \t\t"  + burstTime[i] + "\t\t"
                    + waitingTime[i] + " \t\t" + turnAroundTime[i];

        }
        float s = (float) total_waitingTime / (float) n;
        int t = total_turnAroundTime / n;
        details = details +("\n +-----+------------+--------------+-----------------+----------------+");
        details = details + "\n Average Waiting Time = " + s;
        details = details + "\n Average Turn Around Time = " + t;

        return details;
    }
}
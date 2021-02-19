package simulator;

// **** Round Robin ****

public class RR {
	
    public static void calcWaitingTime(int processId[], int n, int arrivalTime[], int burstTime[], int waitingTime[], int quantum) {
        // Copy of burst times burstTime[] to store remaining burst times
        int remaining_burstTime[] = new int[n];
        for (int i = 0; i < n; i++)
            remaining_burstTime[i] = burstTime[i];

        int currentTime = 0;

        while (true) {
            boolean completion = true;
            // Traverse through each process
            for (int i = 0; i < n; i++) {
                if (remaining_burstTime[i] > 0) {
                    completion = false; // pending process

                    if (remaining_burstTime[i] > quantum) {
                        currentTime += quantum;
                        remaining_burstTime[i] -= quantum;
                    }
                    else {
                        currentTime = currentTime + remaining_burstTime[i];
                        waitingTime[i] = currentTime - burstTime[i] - arrivalTime[i];
                        remaining_burstTime[i] = 0;
                    }
                }
            }
            if (completion == true)
                break;
        }
    }

    // Calculate turn around time
    public static void calcTurnAroundTime(int processId[], int n, int arrivalTime[], int burstTime[], int waitingTime[], int turnAroundTime[]) {
        // Turn around time -> burstTime[i] + waitingTime[i]
        for (int i = 0; i < n; i++)
            turnAroundTime[i] = burstTime[i] + waitingTime[i];
    }

    // Average time
    public String calcAvgTime(int processId[], int n, int arrivalTime[], int burstTime[], int quantum) {
        
    	String details;
    	
    	int waitingTime[] = new int[n], turnAroundTime[] = new int[n];
        int total_waitingTime = 0, total_turnAroundTime = 0;
        // Waiting time and Turn Around Time for all processes
        calcWaitingTime(processId, n, arrivalTime, burstTime, waitingTime, quantum);
        calcTurnAroundTime(processId, n, arrivalTime, burstTime, waitingTime, turnAroundTime);

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

        //System.out.println(details);
        return details;
    }
}
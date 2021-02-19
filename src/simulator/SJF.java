package simulator;

//**** Shortest Job First (Non-Preemptive) ****

public class SJF {
	
    public String main( int totalNumProcesses , int[] arrivalTime , int counter, int[] flag , int[] completionTime, int n , int[] burstTime, int[] turnAroundTime, int[] waitingTime , float avgTurnAroundTime, float avgWaitingTime ) {

        
        
        while (true) {
            int val = n, min = 100;
            if (totalNumProcesses == n) // completed process loop will be terminated
                break;

            for (int i = 0; i < n; i++) {
                /* If arrival time <= system's time, flag = 0 and burstTime < min...
                   Then that process will be executed first */
                if ((arrivalTime[i] <= counter) && (flag[i] == 0) && (burstTime[i] < min)) {
                    min = burstTime[i];
                    val = i;
                }
            }

            if (val == n) // val(value) cannot be updated because there's no arrival time
                counter++;
            else {
                completionTime[val] = counter + burstTime[val];
                counter += burstTime[val];
                turnAroundTime[val] = completionTime[val] - arrivalTime[val];
                waitingTime[val] = turnAroundTime[val] - burstTime[val];
                flag[val] = 1;
                totalNumProcesses++;
            }
        }

        // Details for each process
        String details = "";
        details = details + "\n +-----+------------+--------------+-----------------+----------------+";
        details = details + "\n pid  " + "arrival_time  " + " burst_time  " + "  waiting_time  " + "  turnaround_time  ";
        details = details + "\n ----------------------------------------------------------------------";
        for(int i = 0; i < n; i++) {
            avgWaitingTime += waitingTime[i];
            avgTurnAroundTime += turnAroundTime[i];
            details = details + "\n " + (i + 1) + "\t\t" + arrivalTime[i] + "\t\t "  + burstTime[i] + " \t\t "
                    + waitingTime[i] + " \t\t " + turnAroundTime[i];

        }
        details = details + "\n +-----+------------+--------------+-----------------+----------------+";
        details = details + "\n Average Waiting Time "+ (float)(avgWaitingTime / n);
        details = details + "\n Average Turn Around Time "+ (float)(avgTurnAroundTime / n);
        
        return details;
    }
}
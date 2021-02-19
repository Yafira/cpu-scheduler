package simulator;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class FileIO {

    public static void main(String[] args) {

        String filename;
        int quantum = 4; // chosen time quantum # for RR

        
        // Filename & file path 
        filename = "file.txt";
        String filePath = "/Users/username/Desktop/";
        String fullFilePath = filePath + filename;
        
    	File file = new File(fullFilePath);

        if (args.length == 3) {
            quantum = new Integer(args[2]);
        }


        Scanner kb = new Scanner(System.in);
        System.out.println ("Enter number of Processes: ");
        int n = kb.nextInt();
        int processId[] = new int[n];
        int arrivalTime[] = new int[n];
        int burstTime[] = new int[n];
        int completionTime[] = new int[n];
        int turnAroundTime[] = new int[n];
        int waitingTime[] = new int[n];
        int flag[] = new int[n];
        int counter = 0, totalNumProcesses = 0;
        float avgWaitingTime = 0, avgTurnAroundTime = 0;

        
        for (int i = 0; i < n; i++) {
            System.out.println ("Enter processId " + (i+1) + " arrival time:");
            arrivalTime[i] = kb.nextInt();
            System.out.println ("Enter processId " + (i+1) + " burst time:");
            burstTime[i] = kb.nextInt();
            processId[i] = i+1;
            flag[i] = 0;
            
        }
            // call other files, return String
            String details ="";
                
                FCFS fcfs = new FCFS();
                System.out.println("\n");
                details = details + "FIRST COME FIRST SERVE";
                details = details + "\n";
                details = details + fcfs.calcAvgTime(processId, n, arrivalTime, burstTime);
                //System.out.println("firstComeFirstServe completed");

                RR rr = new RR();
                details = details + "\n \n \n";
                details = details + "ROUND ROBIN \t\t quantum = 4";
                details = details + "\n";
                details = details + rr.calcAvgTime(processId, n, arrivalTime, burstTime, quantum);
                //System.out.println("roundRobin completed");
                
                SJF sjf = new SJF();
                details = details + "\n \n \n";
                details = details + "SHORTEST JOB FIRST";
                details = details + sjf.main(totalNumProcesses , arrivalTime , counter, flag , completionTime, n , burstTime, turnAroundTime, waitingTime , avgTurnAroundTime, avgWaitingTime);
                //System.out.println("shortestJobFirst completed");
                
                System.out.println(details);
          
            try {
            	//write to file
            	if (!file.exists()) {
                    file.createNewFile();
                } 
            	BufferedWriter out = new BufferedWriter(new FileWriter(fullFilePath));
                out.write(details);
                out.close();
                System.out.println("\n");
                System.out.println("File written successfully.   " + filename);
            }
            catch (IOException e) {
            }
            
          kb.close();  

    }
}

// for execution on command line: 
// java -cp 
// /Library/Java/JavaVirtualMachines/jdk1.8.0_144.jdk/Contents/Home/bin/java -Dfile.encoding=UTF-8 -classpath /Users/username/eclipse-workspace/CPUScheduler/bin simulator.FileIO


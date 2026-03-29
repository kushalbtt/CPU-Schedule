import java.io.*;
import java.util.*;

public class BHATTARAI_KUSHAL_CPUSched {

    static class Process {
        int id;
        int arrivalTime;
        int burstTime;
        int count;

        boolean completed = false;
        int completionTime = 0;
        int waitTime = 0;
        int turnAroundTime = 0;

        public Process(int id, int arrivalTime, int burstTime, int count) {
            this.id = id;
            this.arrivalTime = arrivalTime;
            this.burstTime = burstTime;
            this.count = count;
            this.completed = false;
        }
    }

    public static void main(String[] args) {

        File inputFile = new File("in.txt");
        if (!inputFile.exists()) {
            System.out.println("Input file not found.");
            return;

        }

        Scanner scanner = null;

        try {
            scanner = new Scanner(inputFile);
        } catch (Exception e) {
            System.out.println("Error reading giveninput file.");
            return;
        }

        String algo = "";
        List<Process> processes = new ArrayList<>();
        int count = 0;

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();

            if (!line.isEmpty()) {
                String[] parts = line.split("\\s+");

                if (parts[0].toLowerCase().equals("a")) {
                    algo = parts[1];
                } else if (parts[0].toLowerCase().equals("p")) {
                    int id = Integer.parseInt(parts[1]);
                    int arrivalTime = Integer.parseInt(parts[2]);
                    int burstTime = Integer.parseInt(parts[3]);
                    Process p = new Process(id, arrivalTime, burstTime, count++);
                    processes.add(p);
                }
            }

        }
        scanner.close();

        if (processes.isEmpty()) {
            System.out.println("No processes found in the input file.");
            return;
        }

        String outputFile = "";
        if (algo.equals("fcfs")) {
            outputFile = "fcfs_out.txt";
        } else {
            outputFile = "sjnnp_out.txt";
        }

        PrintWriter write = null;
        try {
            write = new PrintWriter(outputFile);
        } catch (Exception e) {
            System.out.println("Error creating output file.");
            return;
        }

        write.println("CPU sheduling algorithm: " + algo);
        write.println("Total number of CPU requests: " + processes.size());

        int currentCount = 0;
        int completed = 0;
        int totalprocesses = processes.size();

        List<Process> completedQueue = new ArrayList<>();

        while (completed < totalprocesses) {
            List<Process> readyQueue = new ArrayList<>();
            for (int i = 0; i < processes.size(); i++) {
                Process p = processes.get(i);
                if (!p.completed && p.arrivalTime <= currentCount) {
                    readyQueue.add(p);
                }
            }

            if (readyQueue.isEmpty()) {
                currentCount++;
                continue;
            }

            write.println("---------------------------------------------------------");
            write.println("Clock: " + currentCount);
            write.println("Pending CPU request(s): ");

            for (int i = 0; i < readyQueue.size(); i++) {
                Process p = readyQueue.get(i);
                write.println(p.id + " " + p.arrivalTime + " " + p.burstTime);
            }
            write.println();

            Process finalWrite = null;

            if (algo.equals("fcfs")) {
                finalWrite = readyQueue.get(0);
                for (int i = 1; i < readyQueue.size(); i++) {
                    Process p = readyQueue.get(i);
                    if (p.arrivalTime < finalWrite.arrivalTime) {
                        finalWrite = p;
                    }

                    else if (p.arrivalTime == finalWrite.arrivalTime) {
                        if (p.count < finalWrite.count) {
                            finalWrite = p;
                        }
                        ;
                    }
                }
            }

            else if (algo.equals("sjnnp")) {
                finalWrite = readyQueue.get(0);
                for (int i = 1; i < readyQueue.size(); i++) {
                    Process p = readyQueue.get(i);
                    if (p.burstTime < finalWrite.burstTime) {
                        finalWrite = p;
                    } else if (p.burstTime == finalWrite.burstTime) {
                        if (p.arrivalTime < finalWrite.arrivalTime) {
                            finalWrite = p;
                        } else if (p.arrivalTime == finalWrite.arrivalTime) {
                            if (p.count < finalWrite.count) {
                                finalWrite = p;
                            }
                        }
                    }
                }
            }

            write.println("CPU Request serviced during this clock interval: " + finalWrite.id + " "
                    + finalWrite.arrivalTime + " " + finalWrite.burstTime);

            finalWrite.completionTime = currentCount + finalWrite.burstTime;
            finalWrite.turnAroundTime = finalWrite.completionTime - finalWrite.arrivalTime;
            finalWrite.waitTime = finalWrite.turnAroundTime - finalWrite.burstTime;

            finalWrite.completed = true;

            completedQueue.add(finalWrite);
            completed++;
            currentCount += finalWrite.burstTime;
        }

        // Printing the values of TWT and TAT for each process
        write.println("---------------------------------------------------------");
        write.println("Turn-Around Time Computations");
        write.println();

        double TotalTAT = 0;

        for (Process p : completedQueue) {
            write.println("TAT(" + p.id + ") = " + p.turnAroundTime);
            TotalTAT += p.turnAroundTime;
        }
        write.printf("Average Turn Around Time: " + (TotalTAT / totalprocesses) + "\n");

        write.println("---------------------------------------------------------");
        write.println("Wait Time Computations: ");
        double TotalWT = 0;
        for (Process p : completedQueue) {
            write.println("WT(" + p.id + ") = " + p.waitTime);
            TotalWT += p.waitTime;
        }
        write.printf("Average Wait Time Computations: " + (TotalWT / totalprocesses) + "\n");
        write.close();

    }
}
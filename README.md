🧠 CPU Scheduling Algorithms (Java)

This project implements two fundamental CPU scheduling algorithms using Java:
. First Come First Served (FCFS)
. Shortest Job Next Non-Preemptive (SJNNP)

The program reads process data from an input file, simulates CPU scheduling step-by-step, and generates detailed output including execution order, waiting time, and turnaround time.

📌 Features
Reads input from a file (in.txt)

Supports two scheduling algorithms:
FCFS (First Come First Served)
SJNNP (Shortest Job Next Non-Preemptive)

Simulates CPU scheduling with a system clock
Displays:
Pending processes at each clock cycle
Selected process for execution

Calculates:
Completion Time
Turnaround Time (TAT)
Waiting Time (WT)

Outputs results to file:
fcfs_out.txt
sjnnp_out.txt

Handles edge cases:
Empty input file
Missing files
CPU idle time (no available processes)

📂 Input Format (in.txt)
The input file must follow this structure:
a fcfs -
p 1 0 8 -
p 2 1 4 -
p 3 2 9 -
p 4 3 5 -

Explanation:
First line:
a fcfs → selects FCFS algorithm
or a sjnnp → selects SJNNP algorithm

Each p line represents a process:
p <processID> <arrivalTime> <burstTime>

⚙️ How It Works
🔹 FCFS (First Come First Served)
Processes are executed in order of arrival
If two processes arrive at the same time, the one listed first is executed first

🔹 SJNNP (Shortest Job Next Non-Preemptive)
Among available processes, the one with the shortest burst time is selected
Ties are broken by:
Earlier arrival time
Input order

🔹 CPU Behavior
If no process is available at a given time, the clock advances
Once a process starts, it runs until completion (non-preemptive)

▶️ How to Run
Compile:
javac BHATTARAI_KUSHAL_CPUSched.java
Run:
java BHATTARAI_KUSHAL_CPUSched

📊 Output
The program generates detailed logs showing:

Current clock time
Pending processes
Selected process for execution
Final statistics:
Turnaround Time (TAT)
Waiting Time (WT)
Averages


Example snippet:

Clock: 0
Pending CPU request(s):
1 0 8
CPU request serviced during this clock cycle: 1 0 8

🧩 Concepts Demonstrated
. CPU scheduling algorithms
. Process management
. Queue handling
. File I/O in Java
. Simulation of real-time systems
. Performance metrics calculation

🚀 Future Improvements
. Add Gantt chart visualization
. Support additional algorithms (SJF Preemptive, Round Robin)
. Improve UI/CLI interaction
. Export results to CSV or JSON

📄 License
This project is for educational and demonstration purposes.

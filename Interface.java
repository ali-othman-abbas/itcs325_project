import java.util.Scanner;

class Interface{
    private Scanner scanner;

    public Interface() {
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        Scheduler scheduler = new Scheduler();

        System.out.println("Enter number of processes:");
        int n = Integer.parseInt(scanner.nextLine());

        int pid;
        int pids [] = new int [n];

        for (int i = 0; i < n; i++) {
            boolean flag = true;
            System.out.println("Enter details for Process " + (i + 1) + ":");

            while (flag){
                flag = false;
                System.out.print("Process ID: ");
                pid = Integer.parseInt(scanner.nextLine());

                if(i != 0) {
                    for (int j = 0; j < i; j++) {
                        if (pids [j] == pid){
                            flag = true;
                            System.out.println("Enter a unique process ID");
                        }
                    }
                }pids [i] = pid;
            }pid = pids[i];

            System.out.print("Arrival Time: ");
            int arrivalTime = Integer.parseInt(scanner.nextLine());

            System.out.print("Burst Time: ");
            int burstTime = Integer.parseInt(scanner.nextLine());

            System.out.print("Priority: ");
            int priority = Integer.parseInt(scanner.nextLine());
            
            scheduler.addProcess(new Process(pid, arrivalTime, burstTime,priority));
            System.out.println();
        }

        scheduler.run(); 

        System.out.println("\n--- Process Statistics ---");
        for (Process p : scheduler.getProcesses()) {
            System.out.println("Process ID: " + p.getPid());
            System.out.println("Waiting Time: " + p.getWaitingTime());
            System.out.println("Turnaround Time: " + p.getTurnaroundTime());
            System.out.println("Response Time: " + p.getResponseTime());
            System.out.println("------------------------");
        }

        System.out.println("\n--- Gantt Chart ---");
        for (Timeline t : scheduler.getTimelines()) {
            System.out.print("| " + t.getPid() + " ");
        }
        System.out.println("|");

        for (Timeline t : scheduler.getTimelines()) {
            System.out.print(t.getStartTime() + "     ");
        }
        System.out.println(scheduler.getTimelines().get(scheduler.getTimelines().size() - 1).getEndTime());

        /*Todo */

        /*The class process has various attributes related to processes */
        /*The class scheduler handles all the logic related scheduling, the method
         * The method addProcess() adds a process to be scheduled.
         * run() simulates the schudling algorithm.
         * The method getProcesses() returns an arraylist processes with required data(waiting time, turnaround time, response time) added.
         * Sorted from smallest arrival time to biggest arrival time.
         * The method getTimelines() returns an arraylist of all timelines in correct order, A timeline is made of
         * a process ID, time of admission to cpu, and time of discharge from cpu.
         */
        /*Please create the interface, the interface must consist of waiting times, response times, turnaround times
         * for all processes (make use of getProcesses() method in the scheduler class). It must also contain the gantt chart depictiing the scheduler activite.
         * (make use of the getTimelines() method for the scheduler method.)
         */
        //make sure to call run() first, than you can use getProcesses() and getTimelines().
    }
}

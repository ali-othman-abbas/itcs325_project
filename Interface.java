import java.util.Scanner;

class Interface{
    private Scanner scanner;

    public Interface() {
        this.scanner = new Scanner(System.in);
    }

    public void start() {
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
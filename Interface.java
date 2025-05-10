import java.util.Scanner;

class Interface{
    private Scanner scanner;

    public Interface() {
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        Scheduler scheduler = new Scheduler();
        scanner = new Scanner(System.in);
        int countP = 1;

        System.out.println("Enter Process ID, arrival time, burst time, priority for each process.");
        System.out.println("All the fields are to be seperated by space.");
        System.out.println("Enter (0 0 0 0) to stop.");
        while(true) {
            System.out.print("enter fields for process number "+countP+" : ");
            String input = scanner.nextLine();
            String[] fields = input.split("[ ]+");
            if(fields.length != 4) {
                System.out.println("Enter only 4 numbers, each seperated by spaces");
                continue;
            }
            if(fields[0].equals("0")
                    && fields[1].equals("0")
                    && fields[2].equals("0")
                    && fields[3].equals("0")) {
                break;
            }
            int pid = 0;
            int arriveTime = 0;
            int burstTime = 0;
            int priority = 0;

            try {
                pid = Integer.parseInt(fields[0]);
                arriveTime = Integer.parseInt(fields[1]);
                burstTime = Integer.parseInt(fields[2]);
                priority = Integer.parseInt(fields[3]);

                if (pid < 0 || arriveTime < 0 || burstTime < 0 || priority < 0){
                    throw new Exception("Make sure that all the fields are non-negative numbers.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Make sure that all the fields are numbers.");
                continue;
            } catch (Exception e) {
                System.out.println(e.getMessage());
                continue;
            }

            Process process = new Process(pid, arriveTime, burstTime, priority);
            boolean unique = scheduler.addProcess(process);
            if(!unique) {
                System.out.println("No two processes should have the same pid.");
                continue;
            }

            countP++;
        }

        scheduler.run();

        System.out.println("\n--- Process Statistics ---");
        double sumWaitingTime = 0;
        double sumTurnaroundTime = 0;
        double sumResponseTime = 0;
        int count = 0;
        for (Process p : scheduler.getProcesses()) {
            count++;
            System.out.println("Process ID: " + p.getPid());
            System.out.println("Waiting Time: " + p.getWaitingTime());
            System.out.println("Turnaround Time: " + p.getTurnaroundTime());
            System.out.println("Response Time: " + p.getResponseTime());
            System.out.println("------------------------");
            sumWaitingTime = sumWaitingTime + p.getWaitingTime();
            sumTurnaroundTime = sumTurnaroundTime + p.getTurnaroundTime();
            sumResponseTime = sumResponseTime + p.getResponseTime();
        }

        System.out.println("Average Waiting Time: " + sumWaitingTime / count);
        System.out.println("Average Turnaround Time: " + sumTurnaroundTime / count);
        System.out.println("Average Response Time: " + sumResponseTime / count);

        System.out.println("\n--- Gantt Chart ---");

        for (Timeline timeline : scheduler.getTimelines()) {
            System.out.println(timeline);
        }

    }
}

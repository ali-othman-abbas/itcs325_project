import java.util.Comparator;
import java.util.PriorityQueue;

class Scheduler {
    private PriorityQueue<Process> arrivalQueue;
    private PriorityQueue<Process> readyQueue;

    public Scheduler() {
        Comparator<Process> comp = (process1, process2) -> {
                if(process1.getPriority() == process2.getPriority()) {
                    return process1.getBurstTime() - process2.getBurstTime();
                }
                if(
                    process1.getPriority() == process2.getPriority() &&
                    process1.getBurstTime() == process2.getBurstTime()
                ) {
                    return process1.getArrivalTime() - process2.getArrivalTime();
                }

                return process1.getPriority() - process2.getPriority();

            };
        
        this.arrivalQueue = new PriorityQueue<>(
            (process1, process2) -> 
                process1.getArrivalTime() - process2.getArrivalTime()
        );
        this.readyQueue = new PriorityQueue<>(comp);
    }

    public void addProcess(Process process) {
        arrivalQueue.add(process);
        readyQueue.add(process);
    }

    public void run() {

    }
}
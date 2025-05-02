import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

class Scheduler {
    private PriorityQueue<Process> arrivalQueue;
    private PriorityQueue<Process> readyQueue;
    private ArrayList<Process> finishedProcs;
    private Processor processor;

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
        this.processor = new Processor();
    }

    public void addProcess(Process process) {
        arrivalQueue.add(process);
    }

    public void run() {
        int time = 0;
        while(!readyQueue.isEmpty() || !arrivalQueue.isEmpty() || !processor.isEmpty()) {
            while(time == arrivalQueue.peek().getArrivalTime()) {
                readyQueue.add(arrivalQueue.poll());
            }

            if(!processor.isEmpty()) {
                processor.decBurstTime();
            }

            if(processor.isFinished()) {
                Process finished = processor.discharge();
                finished.setCompletionTime(time);
                finishedProcs.add(finished);
            }
            
            Process admited = null;
            if(!readyQueue.isEmpty() && processor.isEmpty()) {
                admited = readyQueue.poll();
                processor.admit(admited);
            }else if(!readyQueue.isEmpty() && processor.canPrempt(readyQueue.peek())) {
                Process temp = processor.discharge();
                admited = readyQueue.poll();
                processor.admit(admited);
                readyQueue.add(temp);
            }

            if(admited != null && !admited.wasAdmited()) {
                admited.setResponseTime(time);
            }

            time++;
        }

        updateSchedulingInfo();
    }

    private void updateSchedulingInfo() {
        for (Process process : finishedProcs) {
            process.updateTurnAroundTime();
            process.updateWaitingTime();
        }
    }
}
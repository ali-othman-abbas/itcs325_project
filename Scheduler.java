import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

class Scheduler {
    private PriorityQueue<Process> arrivalQueue;
    private PriorityQueue<Process> readyQueue;
    private ArrayList<Process> finishedProcs;
    private ArrayList<Timeline> timelines;
    private Processor processor;

    public Scheduler() {
        Comparator<Process> comp = (p1, p2) -> {
                if(p1.getPriority() == p2.getPriority()) {
                    return p1.getBurstTime() - p2.getBurstTime();
                }
                if(
                    p1.getPriority() == p2.getPriority() &&
                    p1.getBurstTime() == p2.getBurstTime()
                ) {
                    return p1.getArrivalTime() - p2.getArrivalTime();
                }

                return p1.getPriority() - p2.getPriority();

            };

        this.arrivalQueue = new PriorityQueue<>(
            (p1, p2) -> 
                p1.getArrivalTime() - p2.getArrivalTime()
        );
        this.readyQueue = new PriorityQueue<>(comp);
        this.processor = new Processor();
    }

    public void addProcess(Process process) {
        arrivalQueue.add(process);
    }

    public void run() {
        int time = 0;
        int startTime = 0;
        int endTime = 0;
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
                endTime = time;
                timelines.add(new Timeline(finished.getPid(), startTime, endTime));
                finishedProcs.add(finished);
            }
            
            Process admited = null;
            if(!readyQueue.isEmpty() && processor.isEmpty()) {
                admited = readyQueue.poll();
                startTime = time;
                processor.admit(admited);
            }else if(!readyQueue.isEmpty() && processor.canPrempt(readyQueue.peek())) {
                Process temp = processor.discharge();
                admited = readyQueue.poll();
                processor.admit(admited);
                endTime = time;
                timelines.add(new Timeline(temp.getPid(), startTime, endTime));
                startTime = time;
                readyQueue.add(temp);
            }

            if(admited != null && !admited.wasAdmited()) {
                admited.setResponseTime(time);
            }

            time++;
        }

        updateSchedulingInfo();

    }

    public ArrayList<Process> getProcesses() {
        ArrayList<Process> result = new ArrayList<>(finishedProcs);
        Collections.sort(result, (proc1, proc2) -> proc1.getArrivalTime() - proc2.getArrivalTime());

        return result;
    }

    public ArrayList<Timeline> getTimelines() {
        ArrayList<Timeline> result = new ArrayList<>(timelines);
        return result;
    }

    private void updateSchedulingInfo() {
        for (Process process : finishedProcs) {
            process.updateTurnAroundTime();
            process.updateWaitingTime();
        }
    }
}
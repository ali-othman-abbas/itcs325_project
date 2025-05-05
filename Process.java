class Process {
    private int pid;
    private int arrivalTime;
    private int originalBurstTime;
    private int burstTime;
    private int waitingTime;
    private int turnaroundTime;
    private int completionTime;
    private int responseTime;
    private int priority;

    public Process(int pid, int arrivalTime, int burstTime, int priority) {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.originalBurstTime = burstTime;
        this.priority = priority;
        waitingTime = 0;
        responseTime = 0;
        turnaroundTime = 0;
        responseTime = -1;
    }

    public int getPid() {
        return pid;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public int getPriority() {
        return priority;
    }

    public int getResponseTime() {
        return responseTime;
    }

    public int getTurnaroundTime() {
        return turnaroundTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }



    public void decrementBurstTime() {
        this.burstTime = this.burstTime - 1;
    }
    
    public void updateTurnAroundTime() {
        this.turnaroundTime = this.completionTime - this.arrivalTime;
    }

    public void updateWaitingTime() {
        this.waitingTime = this.turnaroundTime - this.originalBurstTime;
    }

    public boolean wasAdmited() {
        return responseTime != -1;
    }

    public void setResponseTime(int responseTime) {
        this.responseTime = responseTime;
    }

    public void setCompletionTime(int completionTime) {
        this.completionTime = completionTime;
    }
}
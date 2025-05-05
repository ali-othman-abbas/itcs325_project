public class Timeline {
    private int pid;
    private int startTime;
    private int endTime;

    public Timeline(int pid, int startTime, int endTime) {
        this.pid = pid;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public int getPid() {
        return pid;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public String toString() {
        return pid + ": " + startTime + " - " + endTime;
    }
    
}
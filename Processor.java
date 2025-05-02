class Processor {
    private Process process;

    public void admit(Process process) {
        this.process = process;
    }

    public boolean isEmpty() {
        return process == null;
    }

    public boolean canPrempt(Process process) {
        return (this.process.getPriority() > process.getPriority()) ||
                (
                    this.process.getPriority() == process.getPriority() &&
                    this.process.getBurstTime() > process.getBurstTime()
                );
    }

    public Process discharge() {
        Process temp = this.process;
        this.process = null;
        return temp;
    }

    public void decBurstTime() {
        this.process.setBurstTime(this.process.getBurstTime() - 1);
    }

    public boolean isFinished() {
        return this.process.getBurstTime() == 0;
    }
}
package com.ugmc.smartops.model;

/**
 * Represents a record of an algorithm experiment run, including empirical
 * runtime and memory measurements for the performance-analysis lab.
 * @author UGMC Smart Operations Team
 */
public class AlgorithmRun {
    private final String runId;
    private final String algorithmName;
    private final int inputSize;
    private final long timeNs;
    private final long memoryKb;
    private final String dateRun;

    public AlgorithmRun(String runId, String algorithmName, int inputSize,
                        long timeNs, long memoryKb, String dateRun) {
        this.runId = runId;
        this.algorithmName = algorithmName;
        this.inputSize = inputSize;
        this.timeNs = timeNs;
        this.memoryKb = memoryKb;
        this.dateRun = dateRun;
    }

    public String getRunId() { return runId; }
    public String getAlgorithmName() { return algorithmName; }
    public int getInputSize() { return inputSize; }
    public long getTimeNs() { return timeNs; }
    public long getMemoryKb() { return memoryKb; }
    public String getDateRun() { return dateRun; }

    @Override
    public String toString() {
        return "AlgorithmRun{" + runId + ", " + algorithmName + ", size=" + inputSize
                + ", timeNs=" + timeNs + ", memKb=" + memoryKb + ", " + dateRun + "}";
    }
}

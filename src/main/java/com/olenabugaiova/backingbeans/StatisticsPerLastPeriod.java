package com.olenabugaiova.backingbeans;

/**
 * Created by elena on 01.04.17.
 */
public class StatisticsPerLastPeriod {

    private double sum;
    private long count;
    private double avg;
    private double max;
    private double min;

    public StatisticsPerLastPeriod(double sum, long count, double avg, double max, double min) {
        this.sum = sum;
        this.count = count;
        this.avg = avg;
        this.max = max;
        this.min = min;
    }

    public StatisticsPerLastPeriod() {
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }
}

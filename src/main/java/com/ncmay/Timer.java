package com.ncmay;

import java.time.Duration;
import java.time.Instant;

public class Timer {
    private Instant startT;
    private Duration accum = Duration.ZERO;
    private boolean running = false;

    public void start() {
        if (!running) {
            startT = Instant.now();
            running = true;
        }

    }

    public void pause() {
        if (running) {
            accum = accum.plus(Duration.between(startT, Instant.now()));
            running = false;
        }
    }

    public Duration getElapsed() {
        if (running) {
            return accum.plus(Duration.between(startT, Instant.now()));
        }

        return accum; 
    }

    public String getFormattedTime() {
        Duration d = getElapsed();
        long hours = d.toHours();
        long mins = d.toMinutesPart();
        long secs = d.toSecondsPart();

        return formatTime(hours, mins, secs);
    }

    public static String formatTime(long h, long m, long s) {
        return String.format("%02d:%02d:%02d", h, m, s);
    }

    public boolean isRunning() {
        return running;
    }


}
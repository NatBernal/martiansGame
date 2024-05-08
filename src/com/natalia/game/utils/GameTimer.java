package com.natalia.game.utils;

import java.util.Timer;
import java.util.TimerTask;

public class GameTimer {
    private int hours;
    private int minutes;
    private int seconds;

    private Timer timer;
    private TimerTask task;

    public GameTimer() {
        this.hours = 0;
        this.minutes = 0;
        this.seconds = 0;
    }

    public void startTime() {
        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                incrementSecond();
            }
        };
        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    public void stopTime() {
        timer.cancel();
        task.cancel();
    }

    private void incrementSecond() {
        seconds++;
        if (seconds >= 60) {
            seconds = 0;
            minutes++;
            if (minutes >= 60) {
                minutes = 0;
                hours++;
            }
        }
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public String getFormattedTime() {
        String hoursStr = String.format("%02d", hours);
        String minutesStr = String.format("%02d", minutes);
        String secondsStr = String.format("%02d", seconds);
        return "Time Game: "+hoursStr + ":" + minutesStr + ":" + secondsStr;
    }
}

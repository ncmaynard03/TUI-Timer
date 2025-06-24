package com.ncmay;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;

public class TimerList {
    private  Timer t;
    private  Label timeLabel;
    private final WindowBasedTextGUI gui;
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    
    public TimerList(WindowBasedTextGUI gui){
        this.gui = gui;
    }

    public void setTimer(Timer ti, Label la) {
        this.t = ti;
        this.timeLabel = la;
    }

    public void tickUpdate() throws IOException { 
        String time = t.getFormattedTime();
        timeLabel.setText(time);
        gui.updateScreen();
    }
    
    
}
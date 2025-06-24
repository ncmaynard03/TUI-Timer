package com.ncmay;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.gui2.BasicWindow;
import com.googlecode.lanterna.gui2.Label;
import com.googlecode.lanterna.gui2.LinearLayout;
import com.googlecode.lanterna.gui2.MultiWindowTextGUI;
import com.googlecode.lanterna.gui2.Panel;
import com.googlecode.lanterna.gui2.WindowBasedTextGUI;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

public class Main {
    public static void main(String[] args)  {
        try {
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            Screen screen = new TerminalScreen(terminal);
            screen.startScreen();
            WindowBasedTextGUI gui = new MultiWindowTextGUI(screen);

            terminal.setBackgroundColor(TextColor.ANSI.BLACK);
            terminal.setForegroundColor(TextColor.ANSI.GREEN);
            terminal.clearScreen();

            BasicWindow w = new BasicWindow("Timer App");
            Panel mp = new Panel();
            mp.setLayoutManager(new LinearLayout());


            mp.addComponent(new Label("Hello from Lanterna!"));
            Label label = new Label("00:00:00");
            mp.addComponent(label);
            // mp.addComponent(new TimerPanel("Focus Timer")); // Your custom class
            ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

            TimerList tl = new TimerList(gui);
            Timer t = new Timer();
            tl.setTimer(t, label);
            t.start();

            scheduler.scheduleAtFixedRate(
                () -> {
                    try {
                        tl.tickUpdate();
                    } catch(Exception e) {
                        System.out.println(e.getMessage());
                    }
                }, 
                0, 
                1, 
                TimeUnit.SECONDS
                );

            w.setComponent(mp);
            gui.addWindowAndWait(w);

            // TimerPanel t = new TimerPanel("Work");
            // terminal.addComponent(t);

            KeyStroke key = terminal.readInput();

            terminal.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        System.out.println("Hello world!");
    }

    public static void addMenuOption(String text, int row, Terminal terminal) throws IOException {
        terminal.setCursorPosition(0, row);
        terminal.putString(text);
    }

}

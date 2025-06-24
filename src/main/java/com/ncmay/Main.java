package com.ncmay;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import java.io.IOException;

public class Main {
    public static void main(String[] args)  {
        try {
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            terminal.setBackgroundColor(TextColor.ANSI.BLACK);
            terminal.setForegroundColor(TextColor.ANSI.GREEN);
            terminal.clearScreen();
            addMenuOption("Option 1", 0, terminal);
            addMenuOption("Option 2", 1, terminal);
            addMenuOption("Option 3", 2, terminal);
            // terminal.setCursorPosition(5, 5);
            // terminal.putString("Hello from Lanterna! Press any key to exit. ");

            KeyStroke key = terminal.readInput();

            terminal.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        System.out.println("Hello world!");
    }

    public static void addMenuOption(String text, int row, Terminal terminal ) throws IOException {
        terminal.setCursorPosition(0, row);
        terminal.putString(text);
    }

}

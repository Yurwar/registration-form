package com.yurwar.controller;

import java.util.Scanner;

public class ConsoleReader {
    private Scanner sc;

    public ConsoleReader() {
        sc = new Scanner(System.in);
    }

    public String readLine() {
        return sc.nextLine();
    }

    public char readChar() {
        return sc.next().charAt(0);
    }

    public int readInt() throws NumberFormatException {
        return Integer.parseInt(sc.nextLine());
    }
}

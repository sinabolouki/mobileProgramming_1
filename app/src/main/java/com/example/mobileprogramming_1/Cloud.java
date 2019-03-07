package com.example.mobileprogramming_1;

import java.util.ArrayList;

public class Cloud implements Runnable {
    public ArrayList<Integer> output;
    public ConnectionManager connectionManager;
    public int n;

    public Cloud(ArrayList<Integer> output, ConnectionManager connectionManager, int n) {
        this.output = output;
        this.connectionManager = connectionManager;
        this.n = n;
    }

    @Override
    public void run() {
        output = connectionManager.load(n);
        output.notify();
    }
}

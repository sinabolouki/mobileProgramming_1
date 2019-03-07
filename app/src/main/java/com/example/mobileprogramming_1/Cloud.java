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
        synchronized (output) {
            output = connectionManager.load(n);
            try {
                output.notify();
            } catch (Exception e) {

            }
        }
    }
}

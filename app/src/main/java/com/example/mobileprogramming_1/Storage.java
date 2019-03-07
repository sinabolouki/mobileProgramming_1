package com.example.mobileprogramming_1;


import java.util.ArrayList;

public class Storage implements Runnable {
    private ArrayList<Integer> output;
    private StorageManager storageManager;

    public Storage(ArrayList<Integer> output, StorageManager storageManager) {
        this.output = output;
        this.storageManager = storageManager;
    }

    @Override
    public void run() {
        output = storageManager.load();
        output.notify();
    }
}
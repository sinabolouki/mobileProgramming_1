package com.example.mobileprogramming_1;


import android.util.Log;

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
        synchronized (output) {
            output = storageManager.load();
            Log.i(output.toString(), "Salam");
            try {
                output.notifyAll();
            } catch (Exception e) {

            }
        }
    }

    public ArrayList<Integer> getOutput() {
        return output;
    }
}

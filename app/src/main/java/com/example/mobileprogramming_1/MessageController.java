package com.example.mobileprogramming_1;


import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MessageController {
    ArrayList <Integer> numbers = new ArrayList<>();
    StorageManager storageManager = new StorageManager();
    ConnectionManager connectionManager = new ConnectionManager();
    NotificationCenter notificationCenter = NotificationCenter.getInstance();
    ExecutorService executorService = Executors.newFixedThreadPool(5);

    public MessageController(OutputStreamWriter outputStreamWriter, BufferedReader bufferedReader) {
        storageManager.setBufferedReader(bufferedReader);
        storageManager.setOutputStreamWriter(outputStreamWriter);
    }

    public void fetch(boolean fromCache) {
        ArrayList<Integer> output = new ArrayList<>();
        Runnable storage = new Storage(output, storageManager);
        Runnable cloud = new Cloud(output, connectionManager, numbers.size() - 1);
        if(fromCache) {
            executorService.execute(storage);
        } else {
            executorService.execute(cloud);
        }

        synchronized (output) {
            try {
                output.wait(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        numbers.addAll(((Storage) storage).getOutput());
        Log.i(numbers.toString(), "Message");
        notificationCenter.setDataLoaded(true);
        if(!fromCache)
            storageManager.save(numbers.size() - 1);
    }
}

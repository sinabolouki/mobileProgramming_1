package com.example.mobileprogramming_1;


import android.util.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MessageController {
    ArrayList <Integer> numbers = new ArrayList<>();
    StorageManager storageManager = new StorageManager();
    ConnectionManager connectionManager = new ConnectionManager();
    NotificationCenter notificationCenter = NotificationCenter.getInstance();
    ExecutorService executorService = Executors.newFixedThreadPool(5);

    public MessageController(FileInputStream fileInputStream, FileOutputStream fileOutputStream) {
        storageManager.setFileInputStream(fileInputStream);
        storageManager.setFileOutputStream(fileOutputStream);
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

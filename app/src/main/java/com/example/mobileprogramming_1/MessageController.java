package com.example.mobileprogramming_1;


import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MessageController {
    ArrayList <Integer> numbers = new ArrayList<>();
    StorageManager storageManager = new StorageManager();
    ConnectionManager connectionManager = new ConnectionManager();
    NotificationCenter notificationCenter = NotificationCenter.getInstance();
    ExecutorService executorService = Executors.newFixedThreadPool(5);


    public void fetch(boolean fromCache) {
        ArrayList<Integer> output = new ArrayList<>();
        if(fromCache) {
            Runnable storage = new Storage(output, storageManager);
            executorService.execute(storage);
        } else {
            Runnable cloud = new Cloud(output, connectionManager, numbers.get(numbers.size() - 1));
            executorService.execute(cloud);
        }

        synchronized (output) {
            try {
                output.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        numbers.addAll(output);
        notificationCenter.setDataLoaded(true);
        if(!fromCache)
            storageManager.save(numbers.get(numbers.size() - 1));
    }
}

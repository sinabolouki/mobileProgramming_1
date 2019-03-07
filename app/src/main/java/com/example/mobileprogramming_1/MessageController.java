package com.example.mobileprogramming_1;

import android.os.storage.StorageManager;

import java.util.ArrayList;

public class MessageController {
    ArrayList <Integer> numbers = new ArrayList<>();
    StorageManager storageManager = new StorageManager();
    ConnectionManager connectionManager = new ConnectionManager();
    NotificationCenter notificationCenter = NotificationCenter.getInstance();

    public void fetch(boolean fromCache) {
        ArrayList<Integer> output = new ArrayList<>();
        if(fromCache) {

        } else {
            
        }

        synchronized (this) {
            try {
                wait();
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

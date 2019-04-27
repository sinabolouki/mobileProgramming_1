package com.example.mobileprogramming_1;

import android.util.SparseArray;

import java.util.ArrayList;

public class NotificationCenter {
    private ArrayList<Observer> observers = new ArrayList<>();
    private boolean dataLoaded = false;
    private static NotificationCenter notificationCenterInstance = null;

    private NotificationCenter () {

    }

    public static NotificationCenter getInstance() {
        if (notificationCenterInstance == null)
            notificationCenterInstance = new NotificationCenter();
        return notificationCenterInstance;
    }
    public interface Observer {
        public void update(ArrayList<Integer> data);
    }

    public void setDataLoaded (boolean dataLoaded, ArrayList<Integer> data) {
        this.dataLoaded = dataLoaded;
        for (Observer observer : observers) {
            observer.update(data);
        }
    }

    public void registerObserver (Observer observer)  {
        observers.add(observer);
    }

    public void unregisterObserver (Observer observer) {
        observers.remove(observer);
    }

}

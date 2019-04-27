package com.example.mobileprogramming_1;

import java.util.ArrayList;

class ConnectionManager {

    public ArrayList<Integer> load (int n) {
        System.out.println("connection load called");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ArrayList<Integer> loadResult = new ArrayList<>();
        for (int i = n + 1 ; i <= n + 10 ; i ++) {
            loadResult.add(i);
        }
        return loadResult;
    }
}

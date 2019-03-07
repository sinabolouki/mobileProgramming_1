package com.example.mobileprogramming_1;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class StorageManager {

    private File storage = new File("storage.txt");

    public void save (int n) {
        try {
            FileWriter writer = new FileWriter(storage);
            writer.write(n);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Integer> load () {
        FileReader reader = null;
        try {
            reader = new FileReader(storage);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int n = 0;
        try {
            if (reader != null) {
                n = reader.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<Integer> loadResult = new ArrayList<>();
        for ( int i = n + 1 ; i <= n + 10 ; i++) {
            loadResult.add(i);
        }
        return loadResult;
    }

}

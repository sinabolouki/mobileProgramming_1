package com.example.mobileprogramming_1;


import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class StorageManager {

    FileOutputStream fileOutputStream;
    FileInputStream fileInputStream;


    public void save (int n) {
        try {
            fileOutputStream.write(n);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Integer> load () {
        int n = 1;
        try {
            n = fileInputStream.read();
            fileOutputStream.write(n + 10);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i("N is ", String.valueOf(n));
        ArrayList<Integer> loadResult = new ArrayList<>();
        for ( int i = n + 1 ; i <= n + 10 ; i++) {
            loadResult.add(i);
        }
        return loadResult;
    }

    public void setFileOutputStream(FileOutputStream fileOutputStream) {
        this.fileOutputStream = fileOutputStream;
    }

    public void setFileInputStream(FileInputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }
}

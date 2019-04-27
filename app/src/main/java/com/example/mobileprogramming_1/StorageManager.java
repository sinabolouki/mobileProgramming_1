package com.example.mobileprogramming_1;


import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class StorageManager {

    OutputStreamWriter outputStreamWriter;
    BufferedReader bufferedReader;


    public void save (int n) {
        try {
            outputStreamWriter.write(n);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Integer> load () {
        int n = 0;
        String receiveString;
        try {
            while ((receiveString = bufferedReader.readLine()) != null) {
                n = Integer.valueOf(receiveString);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<Integer> loadResult = new ArrayList<>();
        for ( int i = n + 1 ; i <= n + 10 ; i++) {
            loadResult.add(i);
        }
        try {
            outputStreamWriter.write(String.valueOf(n + 10));
            outputStreamWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loadResult;
    }

    public void setOutputStreamWriter(OutputStreamWriter outputStreamWriter) {
        this.outputStreamWriter = outputStreamWriter;
    }

    public void setBufferedReader(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }
}

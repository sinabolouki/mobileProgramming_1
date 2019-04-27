package com.example.mobileprogramming_1;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;


public class MainActivity extends Activity implements NotificationCenter.Observer {
    LinearLayout linearLayout;
    int shownNumber = 0;
    String fileName = "storage.txt";
    int maxNumber = 0;
    final NotificationCenter notificationCenter = NotificationCenter.getInstance();
    OutputStreamWriter outputStreamWriter;
    FileInputStream fileInputStream;
    BufferedReader bufferedReader;
    MessageController messageController;
    TextView dataTextView ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("print", "created");
        notificationCenter.registerObserver(this);
        linearLayout = findViewById(R.id.linearLayout);
        dataTextView = new TextView(this);
        File file = new File(this.getFilesDir(), fileName);
        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fileInputStream = openFileInput(fileName);
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            outputStreamWriter = new OutputStreamWriter(openFileOutput(fileName, Context.MODE_PRIVATE));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        messageController = new MessageController(outputStreamWriter, bufferedReader);
    }

    public void onDestroy () {
        notificationCenter.unregisterObserver(this);
        super.onDestroy();
        try {
            outputStreamWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void refreshAction (View v) {
        linearLayout.removeAllViews();
        String receiveString;
        try {
            while ((receiveString = bufferedReader.readLine()) != null) {
                maxNumber = Integer.valueOf(receiveString);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (shownNumber <= maxNumber) {
            Log.i(String.valueOf(shownNumber), String.valueOf(maxNumber));
           for (int i = shownNumber + 1 ; i <= maxNumber ; i++) {
               TextView textView = new TextView(this);
               textView.setText(String.valueOf(i));
               textView.setId(i);
               textView.setHeight(50);
               textView.setWidth(100);
               textView.setTextSize(10);
               textView.setGravity(Gravity.CENTER);
               linearLayout.addView(textView);
           }
            try {
                outputStreamWriter.write(String.valueOf(maxNumber));
                outputStreamWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
           shownNumber = maxNumber;
        }
    }
    public void clearMethod(View V) {
        shownNumber = 0;
        linearLayout.removeAllViews();
    }

    public void getAction(View v) {
        messageController.fetch(true);
    }


    @Override
    public void update(ArrayList<Integer> data) {
        if(shownNumber == 0)
            linearLayout.removeAllViews();
        Log.i("update", "called");
        for (int i = data.size() - 10 ; i < data.size() ; i++) {
            TextView dataTextView = new TextView(this);
            dataTextView.setText(String.valueOf(data.get(i)));
            dataTextView.setId(data.get(i));
            dataTextView.setHeight(50);
            dataTextView.setWidth(100);
            dataTextView.setTextSize(10);
            dataTextView.setGravity(Gravity.CENTER);
            linearLayout.addView(dataTextView);
        }
        shownNumber += 10;
    }
}

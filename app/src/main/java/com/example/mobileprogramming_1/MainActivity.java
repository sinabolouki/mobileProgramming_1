package com.example.mobileprogramming_1;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificationCenter.registerObserver(this);
        linearLayout = findViewById(R.id.linearLayout);
        File file = new File(fileName);
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
       if (shownNumber >= maxNumber) {

       } else {
           StringBuilder stringBuilder = new StringBuilder(shownNumber);
           for (int i = shownNumber + 1 ; i <= shownNumber + 10 ; i ++) {
               stringBuilder.append(i);
           }
           shownNumber += 10;
           LinearLayout linearLayout = findViewById(R.id.linearLayout);
           TextView textView = findViewById(R.id.text_view_id);
           textView.setText(stringBuilder.toString());
           linearLayout.addView(textView);
       }
    }
    public void clearMethod(View V) {
        final LinearLayout linearLayout = findViewById(R.id.linearLayout);
        final Button clearButton = (Button) findViewById(R.id.clear_button);
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.removeAllViews();
            }
        });
    }

    public void getAction(View v) {
        messageController.fetch(true);
    }

    @Override
    public void update() {

    }
}

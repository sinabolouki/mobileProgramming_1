package com.example.mobileprogramming_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NotificationCenter.Observer {
    LinearLayout linearLayout;

    int shownNumber = 0;
    File file = new File ("storage.txt");
    FileReader fileReader;

    {
        try {
            fileReader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    int maxNumber = 0;

    {
        try {
            maxNumber = fileReader != null ? fileReader.read() : 0;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    final MessageController messageController = new MessageController();
    final NotificationCenter notificationCenter = NotificationCenter.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notificationCenter.registerObserver(this);
        final LinearLayout linearLayout = findViewById(R.id.linearLayout);
    }

    public void onDestroy () {
        notificationCenter.unregisterObserver(this);
        super.onDestroy();
        linearLayout = findViewById(R.id.linearLayout);
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
        messageController.fetch(false);
    }

    @Override
    public void update() {

    }
}

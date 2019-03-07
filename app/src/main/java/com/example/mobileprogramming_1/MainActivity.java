package com.example.mobileprogramming_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements NotificationCenter.Observer {

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

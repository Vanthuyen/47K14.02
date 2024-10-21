package com.example.appstudy;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Profile extends Activity {
    TextView welcomeTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        welcomeTextView = findViewById(R.id.textViewWelcome);
        Button finishButton = findViewById(R.id.button_finish_app);

        String username = getIntent().getStringExtra("email");

        if (username != null) {
            welcomeTextView.setText("Welcome " + username);
        }

//        finishButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });

    }
    public void onClose(View view){
        finish();
    }
}

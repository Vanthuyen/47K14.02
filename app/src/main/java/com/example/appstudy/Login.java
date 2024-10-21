package com.example.appstudy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;


public class Login extends Activity {
    EditText email;
    EditText password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button button = (Button) findViewById(R.id.button_Login);
        email = (EditText) findViewById(R.id.edittext_email);
        password = (EditText) findViewById(R.id.edittext_password);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = email.getText().toString();
                String pw = password.getText().toString();
                if (Email.equals("Thuyen") && pw.equals("123456")){
//                    Toast.makeText(Login.this, "Ban da login thanh cong", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, Profile.class);
                    intent.putExtra("email", Email);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(Login.this, "Sai username hoac pass", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        EditText email = findViewById(R.id.edittext_email);
        EditText password = findViewById(R.id.edittext_password);
        email.setText("");
        password.setText("");
    }
}
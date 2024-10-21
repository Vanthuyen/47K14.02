//package com.example.myapplication;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.Firebase;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Objects;
//
//
//public class Login extends Activity {
//    EditText email;
//    EditText password;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.login);
//
////        FirebaseDatabase database = FirebaseDatabase.getInstance();
////        DatabaseReference myRef = database.getReference("messenge");
////        myRef.setValue("Hello, Thuyen");
////
////        // Read from the database
////        myRef.addValueEventListener(new ValueEventListener() {
////            @Override
////            public void onDataChange(DataSnapshot dataSnapshot) {
////                String data = dataSnapshot.getValue(String.class);
////                Toast.makeText(getBaseContext(),"Data is: " + data, Toast.LENGTH_SHORT).show();
////            }
////
////            @Override
////            public void onCancelled(DatabaseError databaseError) {
////                Toast.makeText(getBaseContext(),"Failed to read value.", Toast.LENGTH_SHORT).show();
////            }
////        });
//
//        Button button = (Button) findViewById(R.id.button_Login);
//        email = (EditText) findViewById(R.id.edittext_email);
//        password = (EditText) findViewById(R.id.edittext_password);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String Email = email.getText().toString();
//                String pw = password.getText().toString();
//                if (Email.equals("Thuyen") && pw.equals("123456")){
////                    Toast.makeText(Login.this, "Ban da login thanh cong", Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(Login.this, Profile.class);
//                    intent.putExtra("email", Email);
//                    startActivity(intent);
//                    finish();
//                }else{
//                    Toast.makeText(Login.this, "Sai username hoac pass", Toast.LENGTH_SHORT).show();
//                }
//                addDB(Email,pw);
//            }
//        });
//    }
//    public void addDB(String Email, String passw){
//        HashMap<String, String> loginhashMap = new HashMap<>();
//        loginhashMap.put("username", Email);
//        loginhashMap.put("password", passw);
//
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("Email");
//
//        String key = myRef.push().getKey();
//        loginhashMap.put("key",key);
//
//        myRef.child(key).setValue(loginhashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                Toast.makeText(Login.this, "Them thanh cong", Toast.LENGTH_SHORT).show();
//                email.getText().clear();
//                password.getText().clear();
//            }
//        });
//
//    }
//
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        EditText email = findViewById(R.id.edittext_email);
//        EditText password = findViewById(R.id.edittext_password);
//        email.setText("");
//        password.setText("");
//    }
//}

package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Login extends Activity {
    EditText email, password;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        // Khởi tạo DatabaseHelper cho SQLite
        dbHelper = new DatabaseHelper(this);

        // Liên kết EditText với layout
        email = findViewById(R.id.edittext_email);
        password = findViewById(R.id.edittext_password);
        Button button = findViewById(R.id.button_Login);

        // Bắt sự kiện nhấn nút đăng nhập
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = email.getText().toString();
                String pw = password.getText().toString();

                if (dbHelper.checkUser(Email, pw)) {
                    navigateToProfile(Email);
                } else {
                    Toast.makeText(Login.this, "Email hoặc Mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                }
//                else {
//                    // Thêm người dùng mới vào SQLite và Firebase
//                    if (dbHelper.addUser(Email, pw)) {
//                        addUserToFirebase(Email, pw);
//                    } else {
//                        Toast.makeText(Login.this, "Lỗi khi thêm người dùng vào SQLite", Toast.LENGTH_SHORT).show();
//                    }
//                }
            }
        });
    }

    // Thêm dữ liệu vào Firebase
    public void addUserToFirebase(String eml, String pass) {
        HashMap<String, String> userHashMap = new HashMap<>();
        userHashMap.put("username", eml);
        userHashMap.put("password", pass);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Users");

        String key = myRef.push().getKey();
        userHashMap.put("key", key);

        // Lưu dữ liệu lên Firebase
        myRef.child(key).setValue(userHashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Login.this, "Đã thêm người dùng vào Firebase", Toast.LENGTH_SHORT).show();
                    // Sau khi lưu Firebase thành công, chuyển sang trang Profile
                    navigateToProfile(eml);
                } else {
                    Toast.makeText(Login.this, "Lỗi khi thêm người dùng vào Firebase", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Phương thức chuyển qua trang Profile
    public void navigateToProfile(String email) {
        Intent intent = new Intent(Login.this, Profile.class);
        intent.putExtra("email", email);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        email.setText("");
        password.setText("");
    }
}

//package com.example.appstudy;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//import androidx.annotation.Nullable;
//
//
//public class Calculator  extends Activity {
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.calculator);
//
//        TextView edittext = (TextView) findViewById(R.id.display);
//        Button button0 = (Button) findViewById(R.id.button_0);
//        Button button1 = (Button) findViewById(R.id.button_1);
//        Button button2 = (Button) findViewById(R.id.button_2);
//        Button button3 = (Button) findViewById(R.id.button_3);
//        Button button4 = (Button) findViewById(R.id.button_4);
//        Button button5 = (Button) findViewById(R.id.button_5);
//        Button button6 = (Button) findViewById(R.id.button_6);
//        Button button7 = (Button) findViewById(R.id.button_7);
//        Button button8 = (Button) findViewById(R.id.button_8);
//        Button button9 = (Button) findViewById(R.id.button_9);
//        Button button_cong = (Button) findViewById(R.id.button_add);
//        Button button_tru = (Button) findViewById(R.id.button_subtract);
//        Button button_chia = (Button) findViewById(R.id.button_divide);
//        Button button_nhan = (Button) findViewById(R.id.button_multiply);
//        Button button_bang = (Button) findViewById(R.id.button_equals);
//        Button button_ac = (Button) findViewById(R.id.button_ac);
//
//        int a = 0;
//        int b = 0;
//
//    }
//}
//

package com.example.appstudy;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class Calculator extends Activity {

    TextView display;
    Button button0, button1, button2, button3, button4, button5, button6, button7, button8, button9;
    Button buttonAdd, buttonSubtract, buttonDivide, buttonMultiply, buttonEquals, buttonAC;

    String input = "";
    String operator = "";
    double num1 = 0;
    double num2 = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculator);

        display = findViewById(R.id.display);
        button0 = findViewById(R.id.button_0);
        button1 = findViewById(R.id.button_1);
        button2 = findViewById(R.id.button_2);
        button3 = findViewById(R.id.button_3);
        button4 = findViewById(R.id.button_4);
        button5 = findViewById(R.id.button_5);
        button6 = findViewById(R.id.button_6);
        button7 = findViewById(R.id.button_7);
        button8 = findViewById(R.id.button_8);
        button9 = findViewById(R.id.button_9);
        buttonAdd = findViewById(R.id.button_add);
        buttonSubtract = findViewById(R.id.button_subtract);
        buttonDivide = findViewById(R.id.button_divide);
        buttonMultiply = findViewById(R.id.button_multiply);
        buttonEquals = findViewById(R.id.button_equals);
        buttonAC = findViewById(R.id.button_ac);

        setNumberButtonListeners();
        setOperatorButtonListeners();
        setActionButtonListeners();
    }

    private void setNumberButtonListeners() {
        View.OnClickListener numberButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                input += button.getText().toString();
                display.setText(display.getText().toString() + button.getText().toString()); // Cập nhật display
            }
        };
        button0.setOnClickListener(numberButtonListener);
        button1.setOnClickListener(numberButtonListener);
        button2.setOnClickListener(numberButtonListener);
        button3.setOnClickListener(numberButtonListener);
        button4.setOnClickListener(numberButtonListener);
        button5.setOnClickListener(numberButtonListener);
        button6.setOnClickListener(numberButtonListener);
        button7.setOnClickListener(numberButtonListener);
        button8.setOnClickListener(numberButtonListener);
        button9.setOnClickListener(numberButtonListener);
    }

    private void setOperatorButtonListeners() {
        View.OnClickListener operatorButtonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) v;
                if (!input.isEmpty()) {
                    num1 = Double.parseDouble(input);
                    operator = button.getText().toString();
                    input = "";
                    display.setText(display.getText().toString() + " " + operator + " "); // Cập nhật display
                } else {
                    showToast("Please enter a number first");
                }
            }
        };


        buttonAdd.setOnClickListener(operatorButtonListener);
        buttonSubtract.setOnClickListener(operatorButtonListener);
        buttonMultiply.setOnClickListener(operatorButtonListener);
        buttonDivide.setOnClickListener(operatorButtonListener);
    }

    private void setActionButtonListeners() {
        buttonEquals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!input.isEmpty() && !operator.isEmpty()) {
                    num2 = Double.parseDouble(input);
                    double result = calculateResult();
                    display.setText(display.getText().toString() + " = " + result); // Cập nhật display
                    input = String.valueOf(result);
                    operator = "";
                } else {
                    showToast("Please enter complete expression");}
            }
        });

        buttonAC.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                input = "";
                operator = "";
                num1 = 0;
                num2 = 0;
                display.setText("");
            }
        });
    }

    private double calculateResult() {
        switch (operator) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                if (num2 != 0) {
                    return num1 / num2;
                } else {
                    showToast("Cannot divide by zero");
                    return 0;
                }
            default:
                return 0;
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

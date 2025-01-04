package com.example.calculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b0;

    EditText input;
    TextView result;
    ImageView img1,img2,img3,img4,img5,img6,img7,img8,img9,img0;
    private String currentInput = "";
    private String operator = "";
    private double firstNumber = 0;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        img1 = findViewById(R.id.buttonOne);
        img2 = findViewById(R.id.buttonTwo);
        img3 = findViewById(R.id.buttonThree);
        img4 = findViewById(R.id.buttonFour);
        img5 = findViewById(R.id.buttonFive);
        img6 = findViewById(R.id.buttonSix);
        img7 = findViewById(R.id.buttonSeven);
        img8 = findViewById(R.id.buttonEight);
        img9 = findViewById(R.id.buttonNine);
        img0 = findViewById(R.id.buttonZero);

        b1 = findViewById(R.id.buttonC);
        b2 = findViewById(R.id.buttonComma);
        b3 = findViewById(R.id.buttonComma1);
        b4 = findViewById(R.id.buttonSlash);
        b5 = findViewById(R.id.buttonMul);
        b6 = findViewById(R.id.buttonAdd);
        b7 = findViewById(R.id.buttonSub);
        b8 = findViewById(R.id.buttonIsEqualTo);
        b9 = findViewById(R.id.buttonPoint);
        b0 = findViewById(R.id.buttonAc);

        result = findViewById(R.id.textResult);
        input = findViewById(R.id.edit_text);
        img1.setOnClickListener(view -> appendNumber("1"));
        img2.setOnClickListener(view -> appendNumber("2"));
        img3.setOnClickListener(view -> appendNumber("3"));
        img4.setOnClickListener(view -> appendNumber("4"));
        img5.setOnClickListener(view -> appendNumber("5"));
        img6.setOnClickListener(view -> appendNumber("6"));
        img7.setOnClickListener(view -> appendNumber("7"));
        img8.setOnClickListener(view -> appendNumber("8"));
        img9.setOnClickListener(view -> appendNumber("9"));
        img0.setOnClickListener(view -> appendNumber("0"));

        b1.setOnClickListener(view -> clearInput());
        b2.setOnClickListener(view -> appendComma());

        // Operations button listeners
        b4.setOnClickListener(view -> setOperator("/"));
        b5.setOnClickListener(view -> setOperator("*"));
        b6.setOnClickListener(view -> setOperator("+"));
        b7.setOnClickListener(view -> setOperator("-"));

        b8.setOnClickListener(view -> calculateResult());
    }

    private void appendNumber(String number) {
        currentInput += number;
        input.setText(currentInput);
    }

    private void clearInput() {
        currentInput = "";
        operator = "";
        firstNumber = 0;
        input.setText(currentInput);
        result.setText("");
    }

    private void appendComma() {
        if (!currentInput.contains(".")) {
            currentInput += ".";
            input.setText(currentInput);
        }
    }

    private void setOperator(String op) {
        if (!currentInput.isEmpty()) {
            firstNumber = Double.parseDouble(currentInput);
            operator = op;
            currentInput = "";
        }
    }

    @SuppressLint("SetTextI18n")
    private void calculateResult() {
        if (!currentInput.isEmpty() && !operator.isEmpty()) {
            double secondNumber = Double.parseDouble(currentInput);
            double resultValue = 0;

            switch (operator) {
                case "+":
                    resultValue = firstNumber + secondNumber;
                    break;
                case "-":
                    resultValue = firstNumber - secondNumber;
                    break;
                case "*":
                    resultValue = firstNumber * secondNumber;
                    break;
                case "/":
                    if (secondNumber != 0) {
                        resultValue = firstNumber / secondNumber;
                    } else {
                        result.setText("Error");
                        return;
                    }
                    break;
            }

            result.setText(String.valueOf(resultValue));
            currentInput = String.valueOf(resultValue);
            operator = "";
        }
    }
}
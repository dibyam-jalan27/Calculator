package com.svnit.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btnAC,btnDel,btnPlus,btnMinus,btnDivide,btnMulti,btnEquals,btnDot;

    private TextView textViewResult,textViewHistory;

    private String number = null;

    double firstNumber = 0, secondNumber = 0;

    String status = null;

    boolean operator = false;

    DecimalFormat myformat = new DecimalFormat("#####.#####");

    String history,currentResult;

    boolean dot = true,btnACcontrol = true, btnEqualcontrol = false,avoidDouble = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);

        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        btnDivide = findViewById(R.id.btnDivide);
        btnMulti = findViewById(R.id.btnMulti);

        btnAC = findViewById(R.id.btnAC);
        btnDel = findViewById(R.id.btnDel);
        btnDot = findViewById(R.id.btnDot);
        btnEquals = findViewById(R.id.btnEquals);

        textViewResult = findViewById(R.id.textViewResult);
        textViewHistory = findViewById(R.id.textViewHistory);


        btn0.setOnClickListener(v -> numberClick("0"));

        btn1.setOnClickListener(v -> numberClick("1"));

        btn2.setOnClickListener(v -> numberClick("2"));

        btn3.setOnClickListener(v -> numberClick("3"));

        btn4.setOnClickListener(v -> numberClick("4"));

        btn5.setOnClickListener(v -> numberClick("5"));

        btn6.setOnClickListener(v -> numberClick("6"));

        btn7.setOnClickListener(v -> numberClick("7"));

        btn8.setOnClickListener(v -> numberClick("8"));

        btn9.setOnClickListener(v -> numberClick("9"));

        btnPlus.setOnClickListener(view -> {
            if(btnEqualcontrol){
                history = textViewHistory.getText().toString();
                textViewHistory.setText(history+"+");
                btnEqualcontrol = false;
            }
            else {
                history = textViewHistory.getText().toString();
                currentResult = textViewResult.getText().toString();
                textViewHistory.setText(history + currentResult + "+");
            }
            if (operator){
                if (Objects.equals(status, "multiplication")){
                    multiply();
                }
                else if(Objects.equals(status, "division")){
                    divide();
                }
                else if(Objects.equals(status, "subtraction")){
                    minus();
                }
                else{
                    add();
                }
                status="addition";
                operator=false;
                number = null;
            }
            if(btnEqualcontrol){
                operator = true;
            }
        });

        btnMinus.setOnClickListener(view -> {
            if(btnEqualcontrol){
                history = textViewHistory.getText().toString();
                textViewHistory.setText(history+"-");
                btnEqualcontrol = false;
            }
            else {
                history = textViewHistory.getText().toString();
                currentResult = textViewResult.getText().toString();
                textViewHistory.setText(history + currentResult + "-");
            }
            if (operator){
                if (Objects.equals(status, "multiplication")){
                    multiply();
                }
                else if(Objects.equals(status, "division")){
                    divide();
                }
                else if(Objects.equals(status, "addition")){
                    add();
                }
                else{
                    minus();
                }
                status="subtraction";
                operator=false;
                number = null;
            }
            if(btnEqualcontrol){
                operator = true;
            }
        });

        btnMulti.setOnClickListener(view -> {
            if(btnEqualcontrol){
                history = textViewHistory.getText().toString();
                textViewHistory.setText(history+"*");
                btnEqualcontrol = false;
            }
            else {
                history = textViewHistory.getText().toString();
                currentResult = textViewResult.getText().toString();
                textViewHistory.setText(history + currentResult + "*");
            }
            if (operator){
                if (Objects.equals(status, "addition")){
                    add();
                }
                else if(Objects.equals(status, "division")){
                    divide();
                }
                else if(Objects.equals(status, "subtraction")){
                    minus();
                }
                else{
                    multiply();
                }
                status="multiplication";
                operator=false;
                number = null;
            }
            if(btnEqualcontrol){
                operator = true;
            }
        });

        btnDivide.setOnClickListener(view -> {
            if(btnEqualcontrol){
                history = textViewHistory.getText().toString();
                textViewHistory.setText(history+"/");
                btnEqualcontrol = false;
            }
            else {
                history = textViewHistory.getText().toString();
                currentResult = textViewResult.getText().toString();
                textViewHistory.setText(history + currentResult + "/");
            }
            if (operator){
                if (Objects.equals(status, "multiplication")){
                    multiply();
                }
                else if(Objects.equals(status, "addition")){
                    add();
                }
                else if(Objects.equals(status, "subtraction")){
                    minus();
                }
                else{
                    divide();
                }
                status="division";
                operator=false;
                number = null;
            }
            if(btnEqualcontrol){
                operator = true;
            }
        });

        btnEquals.setOnClickListener(view -> {
            history = textViewHistory.getText().toString();
            currentResult = textViewResult.getText().toString();
            textViewHistory.setText(history+currentResult);
            if (operator) {
                if (Objects.equals(status, "addition")) {
                    add();
                } else if (Objects.equals(status, "division")) {
                    divide();
                } else if (Objects.equals(status, "subtraction")) {
                    minus();
                } else if (Objects.equals(status, "multiplication")) {
                    multiply();
                } else {
                    firstNumber = Double.parseDouble(textViewResult.getText().toString());
                }
                operator = false;

                number = null;
            }
            btnEqualcontrol = true;
        });

        btnDot.setOnClickListener(view -> {
            if (dot) {
                if (number == null) {
                    number = "0.";
                } else {
                    number = number + ".";
                }
            }
            textViewResult.setText(number);
            dot = false;
        });

        btnAC.setOnClickListener(view -> {
            number = null;
            status = null;
            textViewResult.setText("0");
            textViewHistory.setText("");
            firstNumber=0;
            secondNumber=0;
            dot = true;
            btnACcontrol = true;
        });

        btnDel.setOnClickListener(view -> {
            if (btnACcontrol){
                textViewResult.setText("0");
            }
            else{
                number = number.substring(0, number.length()-1);
                if(number.length()==0){
                    btnDel.setClickable(false);
                }
                dot = !number.contains(".");
                textViewResult.setText(number);
            }


        });

    }

    public void numberClick(String view)
    {
        if(btnEqualcontrol){
            firstNumber = 0;
            secondNumber = 0;
            number = view;
            textViewHistory.setText("");
            avoidDouble = false;
        }
        if (number == null && avoidDouble)
        {
            number = view;
        }

        else if (avoidDouble)
        {
            number = number + view;
        }

        textViewResult.setText(number);
        operator = true;
        btnACcontrol = false;
        btnDel.setClickable(true);
        btnEqualcontrol = false;
        avoidDouble = true;
    }

    public void add(){
        secondNumber = Double.parseDouble(textViewResult.getText().toString());
        firstNumber+= secondNumber;
        textViewResult.setText(myformat.format(firstNumber));
        dot = true;
    }

    public void minus(){

        if (firstNumber == 0){
            firstNumber = Double.parseDouble(textViewResult.getText().toString());
        }
        else{
            secondNumber = Double.parseDouble(textViewResult.getText().toString());
            firstNumber-= secondNumber;
        }
        textViewResult.setText(myformat.format(firstNumber));
        dot = true;
    }

    public void multiply(){

        if (firstNumber == 0){
            firstNumber = 1;
        }
        secondNumber = Double.parseDouble(textViewResult.getText().toString());
        firstNumber*= secondNumber;
        textViewResult.setText(myformat.format(firstNumber));
        dot = true;
    }

    public void divide(){
        secondNumber = Double.parseDouble(textViewResult.getText().toString());
        if (firstNumber == 0){
            firstNumber = secondNumber;
        }
        else{
            firstNumber = firstNumber/secondNumber;
        }
        textViewResult.setText(myformat.format(firstNumber));
        dot = true;
    }

}
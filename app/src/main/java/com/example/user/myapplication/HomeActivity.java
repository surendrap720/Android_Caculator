package com.example.user.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.regex.Pattern;

public class HomeActivity extends AppCompatActivity {
    private TextView screen;
    private String display = "";
    private String currentOperation = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        screen = (TextView)findViewById(R.id.tv1);
        screen.setText(display);
    }

    private void updateScreen(){

        screen.setText(display);
    }

    public void onClickNumber(View v){
        Button b = (Button)v;
        display += b.getText();
        updateScreen();
    }

    public void onClickOperator(View v){

        Button b = (Button)v;
        display += b.getText();
        currentOperation = b.getText().toString();
        updateScreen();

    }


    private void clear(){

        display = " ";
        currentOperation = " ";
        updateScreen();
    }

    public void onClickClear(View v){

        clear();
        updateScreen();
    }

    private double operate(String a,String b, String op){
        switch(op){
            case "+": return Double.valueOf(a)+ Double.valueOf(b);
            case "-": return Double.valueOf(a)- Double.valueOf(b);
            case "x": return Double.valueOf(a)* Double.valueOf(b);
            case "/": try{
                        return  Double.valueOf(a) / Double.valueOf(b);}
                      catch(Exception e){
                            Log.d("Calc",e.getMessage());

                      }

            default: return -1;
        }
    }

    public void onClickEqual(View v){
        String[] operation = display.split(Pattern.quote(currentOperation));
        if(operation.length < 2)return;

        Double result = operate(operation[0],operation[1],currentOperation);
        screen.setText(display + "\n" + String.valueOf(result));
    }
}

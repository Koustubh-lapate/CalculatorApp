package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.*;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display=findViewById(R.id.displayk);
        display.setShowSoftInputOnFocus(false);
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getString(R.string.display).equals(display.getText().toString())){
                    display.setText("");
                }
            }
        });
    }

    private void updateText(String strToAdd){
        String oldStr = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0,cursorPos);
        String rightStr = oldStr.substring(cursorPos);
        if(getString(R.string.display).equals(display.getText().toString())){
            display.setText(strToAdd);
            display.setSelection(cursorPos + 1);
        }
        else {
            display.setText(String.format("%s%s%s", leftStr, strToAdd, rightStr));
            display.setSelection(cursorPos + 1);
        }
     }

    public void zerok(View view){
        updateText("0");
    }
    public void onek(View view){
        updateText("1");
    }
    public void twok(View view){
        updateText("2");
    }
    public void threek(View view){
        updateText("3");
    }
    public void fourk(View view){
        updateText("4");
    }
    public void fivek(View view){
        updateText("5");
    }
    public void sixk(View view){
        updateText("6");
    }
    public void sevenk(View view){
        updateText("7");
    }
    public void eightk(View view){
        updateText("8");
    }
    public void ninek(View view){
        updateText("9");
    }
    public void addk(View view){
        updateText("+");
    }
    public void subtractk(View view){
        updateText("−");
    }
    public void multiplyk(View view){
        updateText("×");
    }
    public void dividek(View view){
        updateText("÷");
    }
    public void plusMinusk(View view){
        updateText("−");
    }
    public void pointk(View view){
        updateText(".");
    }
    public void equalsk(View view){
        String userExp = display.getText().toString();

        userExp = userExp.replaceAll("÷","/");
        userExp = userExp.replaceAll("×","*");
        userExp = userExp.replaceAll("−","-");
        Expression exp = new Expression(userExp);

        String result = String.valueOf(exp.calculate());

        display.setText(result);
        display.setSelection(result.length());
    }
    public void paranthesesk(View view){
        int cursorPos = display.getSelectionStart();
        int openPar=0;
        int closedPar=0;
        int textlen = display.getText().length();

        for(int i=0 ; i < cursorPos ; i++){
            if(display.getText().toString().substring(i , i+1).equals("(")){
                openPar+=1;
            }
            if(display.getText().toString().substring(i , i+1).equals(")")){
                closedPar+=1;
            }
        }

        if(openPar==closedPar || display.getText().toString().substring(textlen-1, textlen).equals("(")){
            updateText("(");
            display.setSelection(cursorPos + 1);
        }
        else if(closedPar<openPar && !display.getText().toString().substring(textlen-1, textlen).equals("(")){
            updateText(")");
            display.setSelection(cursorPos + 1);
        }
    }
    public void exponentk(View view){
        updateText("^");
    }
    public void cleark(View view){
        display.setText("");
    }
    public void backspacek(View view){
        int cursorPos = display.getSelectionStart();
        int textlen = display.getText().length();

        if(cursorPos!=0 && textlen!=0){
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos-1, cursorPos, "");
            display.setText(selection);
            display.setSelection(cursorPos - 1);
        }
    }
}
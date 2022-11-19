package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button b1, b2, b3, bAdd, b5, b6, b7, bSub, b9, b10, b11, bMulti, bC, b0, bEqual, bDivide, bSin, bCos, bTan, bToggle;
    TextView t;
    CharSequence num = "0";
    Boolean equal = false;
    Boolean start = true;
    Boolean rad = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t = findViewById(R.id.textView);

        t.setText("0");

        b1 = findViewById(R.id.button);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        bAdd = findViewById(R.id.button4);
        b5 = findViewById(R.id.button5);
        b6 = findViewById(R.id.button6);
        b7 = findViewById(R.id.button7);
        bSub = findViewById(R.id.button8);
        b9 = findViewById(R.id.button9);
        b10 = findViewById(R.id.button10);
        b11 = findViewById(R.id.button11);
        bMulti = findViewById(R.id.button12);
        bC = findViewById(R.id.button13);
        b0 = findViewById(R.id.button14);
        bEqual = findViewById(R.id.button15);
        bDivide = findViewById(R.id.button16);
        bSin = findViewById(R.id.button17);
        bCos = findViewById(R.id.button18);
        bTan = findViewById(R.id.button19);
        bToggle = findViewById(R.id.button20);

        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        bAdd.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        bSub.setOnClickListener(this);
        b9.setOnClickListener(this);
        b10.setOnClickListener(this);
        b11.setOnClickListener(this);
        bMulti.setOnClickListener(this);
        bC.setOnClickListener(this);
        b0.setOnClickListener(this);
        bEqual.setOnClickListener(this);
        bDivide.setOnClickListener(this);
        bSin.setOnClickListener(this);
        bCos.setOnClickListener(this);
        bTan.setOnClickListener(this);
        bToggle.setOnClickListener(this);
    }

    public void onClick(View view)
    {
        int id = view.getId();
        if(t.getText().equals("0")&&id!=R.id.button20)
        {
            num="";
        }
        switch(id) {
            case R.id.button:
                if(equal)
                    num="";
                num += "1";
                break;
            case R.id.button2:
                if(equal)
                    num="";
                num += "2";
                break;
            case R.id.button3:
                if(equal)
                    num="";
                num += "3";
                break;
            case R.id.button4:
                if(start)
                    num+="0";
                num += "+";
                break;
            case R.id.button5:
                if(equal)
                    num="";
                num += "4";
                break;
            case R.id.button6:
                if(equal)
                    num="";
                num += "5";
                break;
            case R.id.button7:
                if(equal)
                    num="";
                num += "6";
                break;
            case R.id.button8:
                if(start)
                    num+="0";
                num += "-";
                break;
            case R.id.button9:
                if(equal)
                    num="";
                num += "7";
                break;
            case R.id.button10:
                if(equal)
                    num="";
                num += "8";
                break;
            case R.id.button11:
                if(equal)
                    num="";
                num += "9";
                break;
            case R.id.button12:
                if(start)
                    num+="0";
                num += "*";
                break;
            case R.id.button13:
                num = "0";
                break;
            case R.id.button14:
                if(equal)
                    num="";
                num += "0";
                break;
            case R.id.button15:
                if(start)
                    num="0";
                main();
                equal = true;
                break;
            case R.id.button16:
                if(start)
                    num+="0";
                num += "/";
                break;
            case R.id.button17:
                if(equal)
                    num="";
                num+="Sin(";
                break;
            case R.id.button18:
                if(equal)
                    num="";
                num+="Cos(";
                break;
            case R.id.button19:
                if(equal)
                    num="";
                num+="Tan(";
                break;
            case R.id.button20:
                rad = !rad;
                if(rad)
                    bToggle.setText("Rad");
                else
                    bToggle.setText("Deg");
                break;
        }
        if(id!=R.id.button15&&id!=R.id.button20)
        {
            equal = false;
        }
        start = false;
        t.setText(num);
    }

    public void main()
    {
        String equation = String.valueOf(num);
        StringTokenizer st = new StringTokenizer(equation, "+-/*(", true);
        ArrayList<String> eq = new ArrayList<String>();

        while(st.hasMoreTokens())
        {
            eq.add(st.nextToken());
        }

        if(eq.size()>1)
        {
            eq = solve(eq, "*/");

            eq = solve(eq, "+-");

            DecimalFormat format = new DecimalFormat("0.####");
            if(eq.contains("Error"))
            {
                num = "Error";
            }
            else
            {
                num = String.valueOf(format.format(Double.parseDouble(eq.get(0))));
                if(num.equals("-0"))
                {
                    num = "0";
                }
            }
        }
        else if(eq.size()==1)
        {
            num = eq.get(0);
        }
        else
        {
            num="0";
        }
    }

    public ArrayList<String> solve(ArrayList<String> eq, String op)
    {
        double answer = 0;
        double angle = 0;
        String trigFunc = "";
        ArrayList<String> error = new ArrayList<String>();
        ArrayList<String> trig = new ArrayList<String>();
        error.add("Error");
        double x, y = 0;
        for(int i=0; i<eq.size(); i++)
        {
            if(eq.get(i).equals(op.substring(0,1))||eq.get(i).equals(op.substring(1)))
            {
                try
                {
                    x = Double.parseDouble(eq.get(i-1));
                    y = Double.parseDouble(eq.get(i+1));
                }
                catch(NumberFormatException e)
                {
                    return error;
                }
                catch(IndexOutOfBoundsException e)
                {
                    return error;
                }
                switch(op)
                {
                    case "*/":
                        if(eq.get(i).equals(op.substring(0,1)))
                            answer = x*y;
                        else {
                            try
                            {
                                answer = (int)x/(int)y;
                            }
                            catch(ArithmeticException e)
                            {
                                return error;
                            }
                            answer = x/y;
                        }
                        break;
                    case "+-":
                        if(eq.get(i).equals(op.substring(0,1)))
                            answer = x+y;
                        else
                            answer = x-y;
                        break;
                }
                return solve(populate(eq, i, answer), op);
            }
            if(eq.get(i).equals("("))
            {
                trigFunc = eq.get(i-1);
                for(int j=i+1; j<eq.size(); j++)
                {
                    trig.add(eq.get(j));
                }

                trig = solve(trig, " ");
                trig = solve(trig, "*/");
                trig = solve(trig, "+-");
                try {
                    if(rad)
                        angle = Double.parseDouble(trig.get(0));
                    else
                        angle = Math.toRadians(Double.parseDouble(trig.get(0)));
                }
                catch(Exception e)
                {
                    return error;
                }
                switch(trigFunc)
                {
                    case "Sin":
                        trig.set(0, String.valueOf(Math.sin(angle)));
                        break;
                    case "Cos":
                        trig.set(0, String.valueOf(Math.cos(angle)));
                        break;
                    case "Tan":
                        trig.set(0, String.valueOf(Math.tan(angle)));
                        break;
                }
                eq = populateTrig(eq, trig.get(0), i-1);
            }
        }
        return eq;
    }

    public ArrayList<String> populateTrig(ArrayList<String> eq, String val, int index)
    {
        ArrayList<String> next = new ArrayList<String>();
        for(int i=0; i<index; i++)
        {
            next.add(eq.get(i));
        }
        next.add(val);

        return next;
    }

    public ArrayList<String> populate(ArrayList<String> eq, int index, double answer)
    {
        ArrayList<String> next = new ArrayList<String>();
        boolean end = false;
        for(int j=0; j<eq.size()-2; j++)
        {
            if(j==index-1)
            {
                next.add(String.valueOf(answer));
                end = true;
            }
            else if(!end)
            {
                next.add(eq.get(j));
            }
            else
            {
                next.add(eq.get(j+2));
            }
        }
        return next;
    }
}


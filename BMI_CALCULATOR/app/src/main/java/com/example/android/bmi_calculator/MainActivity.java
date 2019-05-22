package com.example.android.bmi_calculator;


import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private String status;
    private double bmi;
    private int cnt = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText inch = (EditText) findViewById(R.id.inch);
        inch.setVisibility(View.GONE);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    //To calculate the BMI value for both standard and metric units according to the input
    public void calculateBMI(View view) {
        EditText ft = (EditText) findViewById(R.id.cm);
        EditText inch = (EditText) findViewById(R.id.inch);
        EditText lbs = (EditText) findViewById(R.id.kg);
        if (cnt == 1) {
            String feet1 = ft.getText().toString();
            String pounds1 = lbs.getText().toString();
            String inches1 = inch.getText().toString();
            if(TextUtils.isEmpty(feet1)){
                ft.setError("Please Enter The Height");
                ft.requestFocus();
                return;
            }
            if(TextUtils.isEmpty(inches1)){
                inch.setError("Please Enter The No. of Inches");
                inch.requestFocus();
                return;
            }
            if(TextUtils.isEmpty(pounds1)){
                lbs.setError("Please Enter The Weight");
                lbs.requestFocus();
                return;
            }

            Double feet = Double.parseDouble(feet1);
            Double inches = Double.parseDouble(inches1);
            Double pounds = Double.parseDouble(pounds1);
            inches += feet * 12;
            bmi = (pounds * 703) / (inches * inches);
        } else {
            String height = ft.getText().toString();
            String weight = lbs.getText().toString();
            if(TextUtils.isEmpty(height)){
                ft.setError("Please Enter The Height");
                ft.requestFocus();
                return;
            }
            if(TextUtils.isEmpty(weight)){
                lbs.setError("Please Enter The Weight");
                lbs.requestFocus();
                return;
            }

            Double cms = Double.parseDouble(height);

            Double kgs = Double.parseDouble(weight);
            bmi = kgs / (cms * cms * 0.0001);
        }
        status = decidestatus(bmi);
        getspec(bmi);
        displaybmi(bmi, status);
    }
    //To display the value of BMI
    private void displaybmi(double bmi, String status) {
        TextView bmiTextView = (TextView) findViewById(R.id.bmi_text_view);
        bmiTextView.setText(String.format("%.2f", bmi) + "\n" + status);

    }
    //To check the status of the user's health
    private String decidestatus(double bmi) {
        if (bmi >= 18.5 && bmi <25)
            return ("You have a healthy weight.");
        else if (bmi >= 25 && bmi <30)
            return ("You may need to lose weight.");
        else if (bmi >= 30)
            return ("You definitely have to lose weight!");
        else return ("You may need to gain weight.");
    }
    //To set the colour of the BMI specifications accoring to the user's BMI
    private void getspec(double bmi) {

        if (bmi < 16.0) {
            TextView t1 = (TextView) findViewById(R.id.c1);
            t1.setTextColor(Color.RED);
            TextView t2 = (TextView) findViewById(R.id.s1);
            t2.setTextColor(Color.RED);
        }
        if (bmi >= 16.0 && bmi < 17) {
            TextView t1 = (TextView) findViewById(R.id.c2);
            t1.setTextColor(Color.parseColor("#ffa000"));
            TextView t2 = (TextView) findViewById(R.id.s2);
            t2.setTextColor(Color.parseColor("#ffa000"));

        }
        if (bmi >= 17 && bmi < 18.5) {
            TextView t1 = (TextView) findViewById(R.id.c3);
            t1.setTextColor(Color.parseColor("#ffa000"));
            TextView t2 = (TextView) findViewById(R.id.s3);
            t2.setTextColor(Color.parseColor("#ffa000"));
        }
        if (bmi >= 18.5 && bmi < 25) {
            TextView t1 = (TextView) findViewById(R.id.c4);
            t1.setTextColor(Color.parseColor("#4caf50"));
            TextView t2 = (TextView) findViewById(R.id.s4);
            t2.setTextColor(Color.parseColor("#4caf50"));
        }
        if (bmi >= 25 && bmi < 30) {
            TextView t1 = (TextView) findViewById(R.id.c5);
            t1.setTextColor(Color.parseColor("#ffa000"));
            TextView t2 = (TextView) findViewById(R.id.s5);
            t2.setTextColor(Color.parseColor("#ffa000"));
        }
        if (bmi >= 30 && bmi < 35) {
            TextView t1 = (TextView) findViewById(R.id.c6);
            t1.setTextColor(Color.RED);
            TextView t2 = (TextView) findViewById(R.id.s6);
            t2.setTextColor(Color.RED);
        }

        if (bmi >= 35 && bmi < 40) {
            TextView t1 = (TextView) findViewById(R.id.c7);
            t1.setTextColor(Color.RED);
            TextView t2 = (TextView) findViewById(R.id.s7);
            t2.setTextColor(Color.RED);
        }

        if (bmi >= 40) {
            TextView t1 = (TextView) findViewById(R.id.c8);
            t1.setTextColor(Color.RED);
            TextView t2 = (TextView) findViewById(R.id.s8);
            t2.setTextColor(Color.RED);
        }
    }
    //Code when reset button is clicked
    public void reset(View view) {
        setContentView(R.layout.activity_main);
        EditText inch = (EditText) findViewById(R.id.inch);
        inch.setVisibility(View.GONE);
    }
    //To convert between metric and standard units
    public void convert(View view) {
        if (cnt == 0) {
            reset(view);
            Button conversion = (Button) findViewById(R.id.convert);
            conversion.setText("METRIC");
            EditText ft = (EditText) findViewById(R.id.cm);
            ft.setHint("feet");
            EditText inch = (EditText) findViewById(R.id.inch);
            inch.setVisibility(View.VISIBLE);
            EditText weight = (EditText) findViewById(R.id.kg);
            weight.setHint("lbs");
            cnt = 1;
        } else {
            reset(view);
            cnt = 0;
        }

    }


}









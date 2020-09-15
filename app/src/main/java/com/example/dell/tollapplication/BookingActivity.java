package com.example.dell.tollapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class BookingActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
Spinner sp;
    TextView rs;
    int twoors,two,fwrs;
    EditText etDate,noplate;
    ArrayList<String> name = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        sp = (Spinner) findViewById(R.id.sp);
        rs = (TextView) findViewById(R.id.Rs);
        etDate = (EditText) findViewById(R.id.etDate);
        noplate = (EditText) findViewById(R.id.noplate);
       /* i.putExtra("twors",twoors);
        i.putExtra("twoors",twors);
        */
         name.add("Two Wheeler");
        name.add("Four Wheeler");
        name.add("Truck");

        Intent i = getIntent();/*
        i.putExtra("twoors",twoors);
        i.putExtra("twors",twors);
        i.putExtra("fwrs",fwrs);*/

      twoors =   i.getIntExtra("twors",0);
       two =  i.getIntExtra("twoors",0);
      fwrs =  i.getIntExtra("fwrs",0);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_dropdown_item_1line,name);
        sp.setAdapter(adapter);
        sp.setOnItemSelectedListener(this);
       // edate = (EditText) findViewById(R.id.eedate);
        etDate.setFocusableInTouchMode(false);
        etDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDatePicker(etDate);
            }
        });
    }
    boolean validation()
    {
        boolean flag = true;
        if(CheckValidations.checkIsEmpty(getApplicationContext(),noplate.getText().toString()))
        {
            flag = false;
            noplate.setError("plz enter number");
        }
        if(CheckValidations.checkIsEmpty(getApplicationContext(),etDate.getText().toString()))
        {
            flag = false;
            etDate.setError("plz enter date ");
        }
        return  flag;
    }
    private void setDatePicker(final EditText et) {
        Calendar calendar = Calendar.getInstance();
        final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        DatePickerDialog datePickerDialog = new DatePickerDialog(BookingActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                etDate.setText(dateFormatter.format(newDate.getTime()));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE));
        datePickerDialog.setTitle("Select Date");
        datePickerDialog.show();
    }


    public  void onpaypal(View view)
{
   if(validation()) {
       Intent i = new Intent(BookingActivity.this, Onlinepaymentscreen.class);
       startActivity(i);
   }

}
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String data = parent.getItemAtPosition(position).toString();

        if(data == "Two Wheeler")
        {
            rs.setText(String.valueOf(two));
        }
        else if (data == "Four Wheeler")
        {
            rs.setText(String.valueOf(fwrs));
        }
        else
        {
            rs.setText(String.valueOf(twoors));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

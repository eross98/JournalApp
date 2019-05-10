package com.csusm.cs481.journalapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class AddEntry extends AppCompatActivity  {

    //Reference for Edit Texts
    EditText etTitle;
    EditText etDescription;
    EditText etDate;
    EditText etCat;

    //For the date
    public int year;
    public int month;
    public int day;
    private int yearPass = 0;
    private int dayPass = 0;
    private int monthPass = 0;

    private DatePickerDialog.OnDateSetListener mDateSetListener;

    //Reference for buttons
    Button btnExitAdd;
    Button btnAddAdd;

    //Used to get the users input
    private String stringLocation = null;//Change this to the gps later on
    private String stringTitle = null;
    private String stringDescription = null;
    private String stringCat = null;

    //for SQLite
    DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);

        btnExitAdd=findViewById(R.id.btnExitFromHere);
        btnExitAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(AddEntry.this,
                        Journal .class);
                startActivity(intentHome);
            }
        });



        myDb = new DatabaseHelper(AddEntry.this);


        //-------
        //For the cateogry
        etCat = findViewById(R.id.etCatxml);

        //Function to get the add Button to create the object
        btnAddAdd = findViewById(R.id.btnSaveEntry);
        btnAddAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                stringTitle = etTitle.getText().toString();
                stringDescription = etDescription.getText().toString();
                stringCat = etCat.getText().toString();

                if (stringDescription == null || stringTitle == null || dayPass == 0) {
                    Toast.makeText(AddEntry.this,"Please make sure to complete it", Toast.LENGTH_LONG).show();
                } else {
                    int dateOrganize = dayPass + monthPass * 100 + yearPass * 10000;//YearMonthDay
                    boolean isInserted = myDb.insertData(stringTitle,stringDescription,dayPass, monthPass,
                            yearPass,stringCat,stringLocation,dateOrganize);
                    if(isInserted=true){
                        Log.d("Works","Works somehow");
                        Toast.makeText(AddEntry.this,"Journal Entery added", Toast.LENGTH_LONG).show();
                        Intent intentAddTask = new Intent(AddEntry.this,
                                Journal .class);
                        startActivity(intentAddTask);
                    }
                    else {
                        Toast.makeText(AddEntry.this,"Error adding Journal Entery", Toast.LENGTH_LONG).show();
                    }
                    //Used to organize the posts by day


                }
            }
        });//End of onClick
        //------------------------------------------------------------------------------------------

        etTitle = findViewById(R.id.etTitleEntryxml);
        etDate = findViewById(R.id.etDatexml);
        etDescription = findViewById(R.id.etDescriptionEntryxml);


        //Function to get the date from the user
        etDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {//This will auto set the date to be the current date
                Calendar cal = Calendar.getInstance();
                year = cal.get(Calendar.YEAR);
                month = cal.get(Calendar.MONTH);
                day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(//popup for the date selector
                        AddEntry.this,
                        android.R.style.Theme_DeviceDefault_Dialog,//not sure if this is the correct theme
                        mDateSetListener,
                        year, month, day);

                //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });//end of etDate.setOnClickListener
        //---------------------------------------------------------------------------------------------------------

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {//This sets the date to the one the user inputted
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //This is where it would call the server to get the data for the day here
                month = month + 1;
                String date = month + "/" + dayOfMonth + "/" + year;
                dayPass = dayOfMonth;
                monthPass = month;
                yearPass = year;
                etDate.setText(date);

            }
        };
        //-------------------------------------------------------------
    }//End of onCreate

}//end of the class



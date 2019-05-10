package com.csusm.cs481.journalapp;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class Journal extends AppCompatActivity implements AdapterView.OnItemClickListener {

    DatabaseHelper myDb;
    private ListView mylistView;
    ArrayList<Entry> newArray;

    ArrayList<String> listDataString;
    ArrayList<Entry> listData;

    Button addNewEntry;

    TextView titleClicker;
    ImageView randomEntry;


    //For custom adapter
    private myAdapter myAdapter;

    //For the shakey
    private float acelVal;
    private float acelLast;
    private float shake;
    private SensorManager sm;

    private int ranNum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journal);

        titleClicker=findViewById(R.id.textView2);
        randomEntry=findViewById(R.id.getRanThing);


        //Nofication
        Calendar calendar = Calendar.getInstance();

        //Sets the notifcaiton for 8:01pm
        calendar.set(Calendar.HOUR_OF_DAY,21);
        calendar.set(Calendar.MINUTE,42);
        calendar.set(Calendar.SECOND,1);

        Intent intent = new Intent(getApplicationContext(), notifcationHere.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 100, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY,pendingIntent);


        newArray = new ArrayList<>();

        listDataString = new ArrayList<>();
        listData = new ArrayList<>();
        mylistView = findViewById(R.id.ListView);
        myDb = new DatabaseHelper(this);

        addNewEntry = findViewById(R.id.button_addEntry);
        addNewEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAddTask = new Intent(Journal.this,
                        AddEntry.class);
                startActivity(intentAddTask);
            }
        });


        //Shakey
        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //sm.registerListener(SensorE)

        updateList();

        ranNum= new Random().nextInt(listData.size());


    }

    private void updateList() {
        Cursor data = myDb.getAllData();
        while (data.moveToNext()) {
            int id = (data.getInt(0));
            String title = (data.getString(1));
            String descr = (data.getString(2));
            int day = (data.getInt(3));
            int month = (data.getInt(4));
            int year = (data.getInt(5));
            String cate = (data.getString(6));
            // String location = (data.getString(7));


            String stringDay;
            String stringMonth;
            //Will change the format to look cleaner
            if (day < 10) stringDay = "0" + day;
            else stringDay = "" + day;
            if (month < 10) stringMonth = "0" + month;
            else stringMonth = "" + month;


            //listDataString.add(stringMonth + "/" + stringDay + "/" + year +"     "+ title);
            listDataString.add(title);
            Entry tempEntry = new Entry(id, title, descr, day, month, year, cate);
            listData.add(tempEntry);

        }
        Collections.reverse(listDataString);
        Collections.reverse(listData);

        //Test for the custom adapter

        myAdapter = new myAdapter(this, R.layout.mylistlayout, listDataString);
        mylistView.setAdapter(myAdapter);
        //

        mylistView.setOnItemClickListener(Journal.this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        showEntry.getTaskFromMain(listData.get(position));
        Intent intentGoToTask = new Intent(Journal.this,
                showEntry.class);
        startActivity(intentGoToTask);
    }


    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void gotoToDo(View view) {
        Intent goToDo = new Intent(this,todoActivity.class);
        startActivity(goToDo);
    }

    public void getRandomEntry(View view) {
        ranNum= new Random().nextInt(listData.size());
        showEntry.getTaskFromMain(listData.get(ranNum));
        Intent intentGoToTask = new Intent(Journal.this,
                showEntry.class);
        startActivity(intentGoToTask);
    }
}

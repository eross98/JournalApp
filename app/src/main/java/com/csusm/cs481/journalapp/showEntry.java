package com.csusm.cs481.journalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

public class showEntry extends AppCompatActivity {

    TextView title;
    TextView descr;
    TextView date;
    TextView category;

    Button journalHome;

    String dateString;

    ImageView imgDelete;


    private static Entry entry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_entry);

        populateTexts();

        journalHome = findViewById(R.id.btn_goToJournal);
        journalHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentJournal = new Intent(showEntry.this,
                        Journal.class);
                startActivity(intentJournal);
            }
        });

        imgDelete=findViewById(R.id.imageViewDelete);
        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Enter the delete here
                Toast.makeText(showEntry.this,"Entry was deleted", Toast.LENGTH_LONG).show();
                Intent intentJournal = new Intent(showEntry.this,
                        Journal.class);
                startActivity(intentJournal);
            }
        });
    }


    private void populateTexts() {
        title = findViewById(R.id.textView_title);
        title.setText(entry.getTitle());

        descr = findViewById(R.id.textView_descr);
        descr.setMovementMethod(new ScrollingMovementMethod());
        descr.setText(entry.getDesc());

        category = findViewById(R.id.textView_Cate);
        category.setText(entry.getCategory());

        date = findViewById(R.id.textView_date);
        int day = entry.getDay();
        int month = entry.getMonth();
        int year = entry.getYear();

        String stringDay;
        String stringMonth;

        //Will change the format to look cleaner
        if (day < 10) stringDay = "0" + day;
        else stringDay = "" + day;
        if (month < 10) stringMonth = "0" + month;
        else stringMonth = "" + month;

        dateString = stringMonth + "/" + stringDay + "/" + year;
        date.setText(dateString);


    }

    //Used to get the Entry object that the user clicked on
    public static void getTaskFromMain(Entry t) {
        entry = t;
    }

}

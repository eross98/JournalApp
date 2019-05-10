package com.csusm.cs481.journalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {




    //
    //
    //
    //
    //
    //
    //IGNORE THIS, WAS FOR PROTOTYPE
    //
    //
    //
    //
    //

    Button btnAdd;
    Button btnJournal;
    Button btnGallery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd=findViewById(R.id.btnAddEntry);
        btnGallery=findViewById(R.id.btnViewGallery);
        btnJournal=findViewById(R.id.btnViewJournal);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAddTask = new Intent(MainActivity.this,
                        AddEntry.class);
                startActivity(intentAddTask);
            }//end on click
        });

        /*btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentAddTask = new Intent(MainActivity.this,
                        ViewGallery.class);
                startActivity(intentAddTask);
            }//end on click
        }); */

        btnJournal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intentAddTask = new Intent(MainActivity.this,
                        Journal.class);
                startActivity(intentAddTask);
            }//end on click
        });


        }
    }


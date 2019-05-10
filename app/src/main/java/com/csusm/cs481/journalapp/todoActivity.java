package com.csusm.cs481.journalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class todoActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private EditText etItem;
    private Button btnToDoAdd;
    private ListView lvItem;

    private ArrayList<String> itemArray;
    private myAdapter adapterToDo;
//private ArrayAdapter adapterToDo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        etItem = findViewById(R.id.etxtToDoAdd);
        lvItem = findViewById(R.id.listToDo);
        btnToDoAdd = findViewById(R.id.btnToDoAddTask);

        Log.d("Error adding", "Up to here");

        itemArray = ToDoWrite.readData(todoActivity.this);
        adapterToDo = new myAdapter(todoActivity.this, R.layout.mylistlayout, itemArray);
        //adapterToDo = new ArrayAdapter(this,android.R.layout.simple_list_item_1,itemArray);
        lvItem.setAdapter(adapterToDo);

        Log.d("Error adding", "Gottem");


        lvItem.setOnItemClickListener(todoActivity.this);

        //This will be removed once the navigation bar is implemented
        TextView returnHome;
        returnHome = findViewById(R.id.btnToDoGoHome);
        returnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(todoActivity.this,Journal.class);
                startActivity(intentHome);
            }
        });


        btnToDoAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnToDoAddTask:
                        String itemNew = etItem.getText().toString();
                        if (itemNew.length()==0);
                        else{
                            adapterToDo.add(itemNew);
                            etItem.setText("");
                            ToDoWrite.writeList(itemArray, todoActivity.this);
                            Toast.makeText(todoActivity.this, "Item Added to the List", Toast.LENGTH_SHORT).show();
                            break;
                        }
                }
            }
        });
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        itemArray.remove(position);
        adapterToDo.notifyDataSetChanged();
        Toast.makeText(this, "Item was Completed!", Toast.LENGTH_SHORT).show();
    }
}
package com.csusm.cs481.journalapp;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ToDoWrite {
    public static final String NAMEOFFILE = "ThoughtsList.dat";

    public static void writeList(ArrayList<String> items, Context context) {

        try {
            FileOutputStream fos = context.openFileOutput(NAMEOFFILE, Context.MODE_PRIVATE);
            ObjectOutput oos = new ObjectOutputStream(fos);
            oos.writeObject(items);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }//end of writeList

    public static ArrayList<String> readData(Context context){
        ArrayList<String> itemArrayList = null;
        try {
            FileInputStream fis = context.openFileInput(NAMEOFFILE);
            ObjectInputStream ois = new ObjectInputStream(fis);
            itemArrayList = (ArrayList<String>) ois.readObject();
        } catch (FileNotFoundException e) {
            itemArrayList = new ArrayList<>();
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return itemArrayList;
    }
}
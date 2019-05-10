package com.csusm.cs481.journalapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class myAdapter  extends ArrayAdapter<String>{
    private int layoutResourceId;
    private List<String> data;

    public myAdapter(Context context, int layoutId, List<String> list) {
        super(context, layoutId, list);
        layoutResourceId = layoutId;

        data = list;
    }

    @Override
    public View getView(int index, View row, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        row = inflater.inflate(layoutResourceId, parent, false);
        TextView text = (TextView) row.findViewById(R.id.list_row_text);
        text.setText(data.get(index));
        return row;
    }
}

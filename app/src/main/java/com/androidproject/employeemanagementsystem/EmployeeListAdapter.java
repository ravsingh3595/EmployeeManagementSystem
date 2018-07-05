package com.androidproject.employeemanagementsystem;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.androidproject.employeemanagementsystem.model.employeelist.EmployeeListItem;

import java.util.ArrayList;

public class EmployeeListAdapter extends ArrayAdapter<EmployeeListItem> {

    private final Context context;
    private final ArrayList<EmployeeListItem> itemsArrayList;

    public EmployeeListAdapter(Context context, ArrayList<EmployeeListItem> itemsArrayList) {

        super(context, R.layout.employee_list_row, itemsArrayList);

        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater
        View rowView = inflater.inflate(R.layout.employee_list_row, parent, false);

        // 3. Get the two text view from the rowView
        TextView labelView = (TextView) rowView.findViewById(R.id.txtName);
        TextView valueView = (TextView) rowView.findViewById(R.id.txtType);

        // 4. Set the text for textView
        labelView.setText(itemsArrayList.get(position).getTitle());
        valueView.setText(itemsArrayList.get(position).getDescription());

        // 5. retrn rowView

        return rowView;
    }
}

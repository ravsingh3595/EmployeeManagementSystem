package com.androidproject.employeemanagementsystem.userInterface.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.androidproject.employeemanagementsystem.EmployeeListAdapter;
import com.androidproject.employeemanagementsystem.R;
import com.androidproject.employeemanagementsystem.model.employee.Employee;
import com.androidproject.employeemanagementsystem.model.employeelist.EmployeeListItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.Unbinder;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class EmployeeListFragment extends Fragment {
    @BindView(R.id.listEmplyee)
    ListView listEmplyee;
    Unbinder unbinder;
    @BindView(R.id.constraintLayout)
    RelativeLayout constraintLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_employee_list, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        // 1. pass context and data to the custom adapter
        EmployeeListAdapter adapter = new EmployeeListAdapter(getActivity(), generateData());


        // 3. setListAdapter
        listEmplyee.setAdapter(adapter);

        // 4. list click
        listEmplyee.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                Intent intent = new Intent(getActivity(), SendMessage.class);
//                String message = "abc";
//                intent.putExtra(EXTRA_MESSAGE, message);
//                startActivity(intent);

                Log.e("id", "Selected index " + position);
//                Employee entry = (Employee) adapterView.getItemAtPosition(position);
            }
        });

        return rootView;
    }


    /* List of employees*/
    private ArrayList<EmployeeListItem> generateData() {
        ArrayList<EmployeeListItem> items = new ArrayList<EmployeeListItem>();
        items.add(new EmployeeListItem("Item 1", "First Item on the list"));
        items.add(new EmployeeListItem("Item 2", "Second Item on the list"));
        items.add(new EmployeeListItem("Item 3", "Third Item on the list"));

        items.add(new EmployeeListItem("Item 1", "First Item on the list"));
        items.add(new EmployeeListItem("Item 2", "Second Item on the list"));
        items.add(new EmployeeListItem("Item 3", "Third Item on the list"));

        items.add(new EmployeeListItem("Item 1", "First Item on the list"));
        items.add(new EmployeeListItem("Item 2", "Second Item on the list"));
        items.add(new EmployeeListItem("Item 3", "Third Item on the list"));

        items.add(new EmployeeListItem("Item 1", "First Item on the list"));
        items.add(new EmployeeListItem("Item 2", "Second Item on the list"));
        items.add(new EmployeeListItem("Item 3", "Third Item on the list"));

        return items;
    }



}

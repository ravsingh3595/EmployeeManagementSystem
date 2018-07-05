package com.androidproject.employeemanagementsystem.userInterface.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.androidproject.employeemanagementsystem.EmployeeListAdapter;
import com.androidproject.employeemanagementsystem.R;
import com.androidproject.employeemanagementsystem.model.employeelist.EmployeeListItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class EmployeeListFragment extends Fragment {
    @BindView(R.id.listEmplyee)
    ListView listEmplyee;
    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_employee_list, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        // 1. pass context and data to the custom adapter
        EmployeeListAdapter adapter = new EmployeeListAdapter(getActivity(), generateData());


        // 3. setListAdapter
        listEmplyee.setAdapter(adapter);

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

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

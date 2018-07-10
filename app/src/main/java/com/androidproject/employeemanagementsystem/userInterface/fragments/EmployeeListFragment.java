package com.androidproject.employeemanagementsystem.userInterface.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
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
import com.androidproject.employeemanagementsystem.userInterface.activities.EmployeeDetailActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.Unbinder;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class EmployeeListFragment extends Fragment {

    ArrayList<Employee> employeeArrayList = new ArrayList<>();

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

        Employee employee = (Employee) getActivity().getIntent().getSerializableExtra("employee");
        employeeArrayList.add(employee);

        // 1. pass context and data to the custom adapter
        EmployeeListAdapter adapter = new EmployeeListAdapter(getActivity(), employeeArrayList);

        // 3. setListAdapter
        listEmplyee.setAdapter(adapter);

        // 4. list click
        listEmplyee.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // send data to employee detail activity


                Intent intent = new Intent(getActivity(), EmployeeDetailActivity.class);
                intent.putExtra("employee", employeeArrayList.get(position));
                startActivity(intent);
                Log.e("id", "Selected index " + position);

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

        /* NOTE: -------------- UNCOMMENT CODE WHEN DATA IS GET FROM DATABASE ---------------- */


        return items;
    }
}

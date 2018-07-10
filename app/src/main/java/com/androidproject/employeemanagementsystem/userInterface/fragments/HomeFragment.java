package com.androidproject.employeemanagementsystem.userInterface.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.androidproject.employeemanagementsystem.R;

import java.text.DateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class HomeFragment extends Fragment {

    @BindView(R.id.txtDateHeading)
    TextView txtDateHeading;
    @BindView(R.id.txtDate)
    TextView txtDate;
    Unbinder unbinder;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, rootView);

        //Get or Generate Date
        Date todayDate = new Date();

        //Get an instance of the formatter
        DateFormat dateFormat = DateFormat.getDateTimeInstance();

        //If you want to show only the date then you will use
        //DateFormat dateFormat = DateFormat.getDateInstance();

        //Format date
        String todayDateTimeString = dateFormat.format(todayDate);

        //display Date
        txtDate.setText(todayDateTimeString);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

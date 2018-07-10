package com.androidproject.employeemanagementsystem.userInterface.activities;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import com.androidproject.employeemanagementsystem.R;
import com.androidproject.employeemanagementsystem.userInterface.fragments.EmployeeDetailFragment;
import com.androidproject.employeemanagementsystem.userInterface.fragments.EmployeeListFragment;
import com.androidproject.employeemanagementsystem.userInterface.fragments.HelpFragment;
import com.androidproject.employeemanagementsystem.userInterface.fragments.HomeFragment;
import com.androidproject.employeemanagementsystem.userInterface.fragments.ProfileFragment;

public class MainTabActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tab);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainTabActivity.this, AddEmployeeActivity.class);
                startActivity(intent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main_tab, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_help:
                // download pdf code
                Toast.makeText(MainTabActivity.this, "download pdf", Toast.LENGTH_SHORT).show();
                showDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    /* open dialouge */
    public void showDialog()
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainTabActivity.this);
        alertDialogBuilder.setTitle("Alert Message");
        alertDialogBuilder.setMessage("Here we will show the details");
        alertDialogBuilder.setCancelable(false);

        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                showMessage("Cancel - no");
            }
        });

        alertDialogBuilder.setPositiveButton("Call", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                makeCall();
                dialogInterface.dismiss();
                // TODO
                // change the class to whereeve the final destination
//                Intent mIntent = new Intent(MainTabActivity.this, HomeActivity.class);
//                startActivity(mIntent);
            }
        });

        alertDialogBuilder.setNeutralButton("Email", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                sendEmail();
            }
        });

        AlertDialog mAlertDialog = alertDialogBuilder.create();
        mAlertDialog.show();
    }

    void makeCall(){
        String phoneNo = "6476852023";
        if(!TextUtils.isEmpty(phoneNo)) {
            String dial = "tel:" + phoneNo;
            Intent phoneItent = new Intent(Intent.ACTION_DIAL, Uri.parse(dial));
            if(phoneItent.resolveActivity(MainTabActivity.this.getPackageManager()) != null)
            {
                startActivity(phoneItent);
            }
            else
            {
                Toast.makeText(MainTabActivity.this,"No application to handle Phone call",Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(MainTabActivity.this, "Enter a phone number", Toast.LENGTH_SHORT).show();
        }

    }

    void sendEmail(){
        String to = "employee@gmail.com";
        String subject = "Subject";
        String body = "Body";
        Intent emailIntent = new Intent(Intent.ACTION_SEND); //Intent.ACTION_SENDTO
        emailIntent.setType("text/plain");
//        emailIntent.setData(Uri.parse("mailto:" + to));
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, body);
        emailIntent.setType("message/rfc822");

        if(emailIntent.resolveActivity(MainTabActivity.this.getPackageManager()) != null)
        {
            startActivity(Intent.createChooser(emailIntent, "Select Email Client"));
        }
        else
        {
            Toast.makeText(MainTabActivity.this,"No application to handle Email",Toast.LENGTH_SHORT).show();
        }
    }

    public void showMessage(String msg)
    {
        Toast toast = Toast.makeText(MainTabActivity.this, msg, Toast.LENGTH_SHORT);
        toast.show();
    }



    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
            //TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            //textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
//            return PlaceholderFragment.newInstance(position + 1);

            switch(position){
                case 0: HomeFragment homeFragment = new HomeFragment();
                    return  homeFragment;
                case 1: EmployeeListFragment employeeListTab = new EmployeeListFragment();
                    return  employeeListTab;
                case 2: ProfileFragment profileFragment = new ProfileFragment();
                    return  profileFragment;
                case 3: HelpFragment helpFragment = new HelpFragment();
                    return helpFragment;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 4;
        }
    }
}

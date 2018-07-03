package com.androidproject.employeemanagementsystem.userInterface.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.androidproject.employeemanagementsystem.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ProfileFragment extends Fragment{

    MapView mMapView;
    private GoogleMap googleMap;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_profile, container, false);

        /*
        mMapView = (MapView) rootView.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
                                 @Override
                                 public void onMapReady(GoogleMap googleMap) {


                                     // For showing a move to my location button


                                     if (ContextCompat.checkSelfPermission(container.getContext(), Manifest.permission.ACCESS_FINE_LOCATION)
                                             == PackageManager.PERMISSION_GRANTED) {
                                         googleMap.setMyLocationEnabled(true);
                                     } else {
                                         // Show rationale and request permission.
                                     }

                                     // For dropping a marker at a point on the Map
                                     LatLng sydney = new LatLng(-34, 151);
                                     googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker Title").snippet("Marker Description"));

                                     // For zooming automatically to the location of the marker
                                     CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(12).build();
                                     googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                                 }
                             }

        );*/
        return rootView;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();

    }
}

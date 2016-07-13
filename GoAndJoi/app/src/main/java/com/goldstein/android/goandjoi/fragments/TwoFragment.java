package com.goldstein.android.goandjoi.fragments;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.goldstein.android.goandjoi.GoAndJoyInfoContract;
import com.goldstein.android.goandjoi.PlaceAdapter;
import com.goldstein.android.goandjoi.R;
import com.goldstein.android.goandjoi.models.PlaceModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class TwoFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int CONTACT_LOADER = 0;
    private RecyclerView recyclerview;
    //private List<AtractionModel> atractionModelList;

    private List<PlaceModel> placeModelList;
    private PlaceAdapter placeAdapter;
    public TwoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(getActivity());
        String url = "https://project-7072121739369652.firebaseio.com/place";
        Firebase dataRef = new Firebase(url);

        /**
         * Initializes the CursorLoader. The CONTACT_LOADER value is eventually passed
         * to onCreateLoader().
         */
        getActivity().getSupportLoaderManager().initLoader(CONTACT_LOADER, null, this);


        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Toast.makeText(getActivity(), snapshot.getValue().toString(), Toast.LENGTH_LONG);
                System.out.println(snapshot.getValue());
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });
        dataRef.child("name").setValue("myNewName");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_two, container, false);

        recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerview.setLayoutManager(layoutManager);

        getActivity().getSupportLoaderManager().initLoader(CONTACT_LOADER, null, this);
        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        Uri CONTACT_URI = GoAndJoyInfoContract.CONTENT_PLACE_URI;
        return new CursorLoader(getContext(), CONTACT_URI, null, null, null, null);
    }
    @Override
    public void onLoadFinished(Loader loader, Cursor cursor) {
        int columnNameIdx = cursor.getColumnIndex(GoAndJoyInfoContract.PlacesEntry.COLUMN_ENGLISH_NAME);
        int columnDescriptionIdx = cursor.getColumnIndex(GoAndJoyInfoContract.PlacesEntry.COLUMN_ENGLISH_DESCRIPTION);
      //  atractionModelList = new ArrayList<>();
        placeModelList = new ArrayList<>();

        if (cursor.moveToFirst()) {

            do {
                placeModelList.add(
                        new PlaceModel(
                                cursor.getString(columnNameIdx),
                                cursor.getString(columnDescriptionIdx),"first",""));
            } while (cursor.moveToNext());

        }
        placeAdapter = new PlaceAdapter(placeModelList);
        recyclerview.setAdapter(placeAdapter);
        // adapter = new RVAdapter(atractionModelList);
       // recyclerview.setAdapter(adapter);
    }

    /**
     * Callback that's invoked when there is a change in the data source.
     */
    @Override
    public void onLoaderReset(Loader loader) {

    }

}

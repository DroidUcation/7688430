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
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.goldstein.android.goandjoi.Enums;
import com.goldstein.android.goandjoi.GoAndJoyInfoContract;
import com.goldstein.android.goandjoi.R;
import com.goldstein.android.goandjoi.RVAdapter;
import com.goldstein.android.goandjoi.models.AtractionModel;
import com.goldstein.android.goandjoi.models.ItemViewHolder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class HikingFragment extends Fragment implements ItemViewHolder.IMyViewHolderClicks, LoaderManager.LoaderCallbacks<Cursor>, SearchView.OnQueryTextListener {

    // Identifies a particular Loader being used in this component
    private static final int CONTACT_LOADER = 0;

    private RecyclerView recyclerview;
    private List<AtractionModel> atractionModelList;
    private RVAdapter adapter;

    private LocationManager locationManager;

    public HikingFragment() {
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
        View view = inflater.inflate(R.layout.fragment_one, container, false);

        recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerview.setLayoutManager(layoutManager);

        loadLocation();

        getActivity().getSupportLoaderManager().initLoader(CONTACT_LOADER, null, this);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.removeUpdates(mLocationListener);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    public void onDestroy() {
        super.onDestroy();
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.removeUpdates(mLocationListener);
    }
/*
    * Callback that's invoked when the system has initialized the Loader and
            * is ready to start the query. This usually happens when initLoader() is
    * called. The loaderID argument contains the ID value passed to the
    * initLoader() call.
            */

    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        String selection = GoAndJoyInfoContract.PlacesEntry.COLUMN_CATEGORY_TYPE + " = ?";
       String[] selectionArgs = new String[]{Integer.toString(Enums.CategoryTypes.ATTRACTIONS)};
        Uri CONTACT_URI = GoAndJoyInfoContract.CONTENT_URI;
        switch (id) {
            case CONTACT_LOADER: {
                CONTACT_URI = GoAndJoyInfoContract.CONTENT_URI;
                break;
            }
        }
        return new CursorLoader(getContext(), CONTACT_URI, 
                null,
                selection,
                selectionArgs,
                null);
    }


    @Override
    public void onLoadFinished(Loader loader, Cursor cursor) {

        int id = loader.getId();
        switch (id)
        {
            case CONTACT_LOADER:
            {
                getRVAdapterData(cursor);
                break;
            }
        }

    }

    private void getRVAdapterData(Cursor cursor)
    {
        HashMap<Integer,AtractionModel> atractionMap = new HashMap<Integer, AtractionModel>();

        int columnPlaceIdIdx = cursor.getColumnIndex(GoAndJoyInfoContract.ExtendedPlacesEntry.COLUMN_PLACE_ID);
        int columnNameIdx = cursor.getColumnIndex(GoAndJoyInfoContract.PlacesEntry.COLUMN_ENGLISH_NAME);
        int columnDescriptionIdx = cursor.getColumnIndex(GoAndJoyInfoContract.PlacesEntry.COLUMN_ENGLISH_DESCRIPTION);
        int columnDurationTimeTypeIdx = cursor.getColumnIndex(GoAndJoyInfoContract.ExtendedPlacesEntry.COLUMN_DURATION_TIME_TYPE);
        int columnAccessibilityTypeIdx = cursor.getColumnIndex(GoAndJoyInfoContract.ExtendedPlacesEntry.COLUMN_ACCESSIBILITY_TYPE);
        int columnTripTypeIdx = cursor.getColumnIndex(GoAndJoyInfoContract.ExtendedPlacesEntry.COLUMN_TRIP_TYPE);
        int columnTripLevelTypeIdx = cursor.getColumnIndex(GoAndJoyInfoContract.ExtendedPlacesEntry.COLUMN_TRIP_LEVEL_TYPE);
        int columnLocationIdx = cursor.getColumnIndex(GoAndJoyInfoContract.PlacesEntry.COLUMN_LOCATION);
        int columnImgUriIdx = cursor.getColumnIndex(GoAndJoyInfoContract.ImageEntry.COLUMN_IMAGE_URI);
        int columnCategoryIdx = cursor.getColumnIndex(GoAndJoyInfoContract.PlacesEntry.COLUMN_CATEGORY_TYPE);
        atractionModelList = new ArrayList<>();

        if (cursor.moveToFirst()) {

            do {
               /* AtractionModel atraction = new AtractionModel(
                        cursor.getString(columnNameIdx),
                        cursor.getString(columnDescriptionIdx),
                        cursor.getInt(columnDurationTimeTypeIdx),
                        cursor.getInt(columnAccessibilityTypeIdx),
                        cursor.getInt(columnTripTypeIdx),
                        cursor.getInt(columnTripLevelTypeIdx),
                        cursor.getString(columnLocationIdx),"");
                String imageURI = cursor.getString(columnImgUriIdx);
                atraction.getImageList().add(imageURI);
                atractionModelList.add(atraction);*/
                int placeId = cursor.getInt(columnPlaceIdIdx);
                String imageURI = cursor.getString(columnImgUriIdx);
                if (atractionMap.containsKey(placeId))
                {
                    AtractionModel atraction = atractionMap.get(placeId);
                    atraction.getImageList().add(imageURI);
                }
                else
                {
                    AtractionModel atraction = new AtractionModel(
                            cursor.getString(columnNameIdx),
                            cursor.getString(columnDescriptionIdx),
                            cursor.getInt(columnDurationTimeTypeIdx),
                            cursor.getInt(columnAccessibilityTypeIdx),
                            cursor.getInt(columnTripTypeIdx),
                            cursor.getInt(columnTripLevelTypeIdx),
                            cursor.getString(columnLocationIdx),"");
                    atraction.getImageList().add(imageURI);
                    atractionMap.put(placeId,atraction);
                }

            } while (cursor.moveToNext());
        }

        atractionModelList.addAll(atractionMap.values());

        adapter = new RVAdapter(atractionModelList, this);
        recyclerview.setAdapter(adapter);
    }

    /**
     * Callback that's invoked when there is a change in the data source.
     */
    @Override
    public void onLoaderReset(Loader loader) {

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);

        final MenuItem item = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);

        MenuItemCompat.setOnActionExpandListener(item,
                new MenuItemCompat.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        // Do something when collapsed
                        adapter.setFilter(atractionModelList);
                        return true; // Return true to collapse action view
                    }

                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        // Do something when expanded
                        return true; // Return true to expand action view
                    }
                });
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        final List<AtractionModel> filteredModelList = filter(atractionModelList, newText);
        adapter.setFilter(filteredModelList);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }


    private List<AtractionModel> filter(List<AtractionModel> models, String query) {
        query = query.toLowerCase();

        final List<AtractionModel> filteredModelList = new ArrayList<>();
        for (AtractionModel model : models) {
            final String textName = model.getPlaceModel().getName().toLowerCase();
            //final String textDescription = model.getPlaceModel().getDescription().toLowerCase();
            if (textName.contains(query) ){
                //|| textDescription.contains(query)) {
                filteredModelList.add(model);
            }
        }
        return filteredModelList;
    }



    private final LocationListener mLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(final Location location) {
            //your code here
            // Called when a new location is found by the network location provider.
            makeUseOfNewLocation(location);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    private void loadLocation() {
        // Acquire a reference to the system Location Manager
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        // Register the listener with the Location Manager to receive location updates
        if (ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(getActivity(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 10, mLocationListener);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 10, mLocationListener);

    }

    private void makeUseOfNewLocation(Location location)
    {
        adapter.currentLocation = location.getLatitude() + ", " + location.getLongitude();

        adapter.notifyDataSetChanged();
    }

    public void onBtnGoClicked(int position)
    {
        AtractionModel atractionModel =  adapter.mCountryModel.get(position);
        atractionModel.getPlaceModel();
        getActivity().getContentResolver().update(GoAndJoyInfoContract.CONTENT_PLACE_URI, null, GoAndJoyInfoContract.PlacesEntry.COLUMN_ENGLISH_NAME+"=?",new String[] {atractionModel.getPlaceModel().getName()});

    }
}

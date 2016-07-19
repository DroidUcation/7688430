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
import com.goldstein.android.goandjoi.PlaceAdapter;
import com.goldstein.android.goandjoi.R;
import com.goldstein.android.goandjoi.RVAdapter;
import com.goldstein.android.goandjoi.models.AtractionModel;
import com.goldstein.android.goandjoi.models.ItemViewHolder;
import com.goldstein.android.goandjoi.models.PlaceModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShoppingCentersFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>, ItemViewHolder.IMyViewHolderClicks {

    private static final int CONTACT_LOADER = 1;
    private RecyclerView recyclerview;
    //private List<AtractionModel> atractionModelList;
    int type;
    private List<PlaceModel> placeModelList;
    private PlaceAdapter placeAdapter;

    public ShoppingCentersFragment() {
        type = Enums.CategoryTypes.SHOPPING_CENTERS;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActivity().getSupportLoaderManager().initLoader(CONTACT_LOADER, null, this);
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
        String selection = GoAndJoyInfoContract.PlacesEntry.COLUMN_CATEGORY_TYPE + " = ?";
        String[] selectionArgs = new String[]{Integer.toString(type)};
        // String selection=null; String[] selectionArgs=null;
        return new CursorLoader(getContext(), CONTACT_URI, null, selection, selectionArgs, null);
    }

    @Override
    public void onLoadFinished(Loader loader, Cursor cursor) {
        int columnNameIdx = cursor.getColumnIndex(GoAndJoyInfoContract.PlacesEntry.COLUMN_ENGLISH_NAME);
        int columnDescriptionIdx = cursor.getColumnIndex(GoAndJoyInfoContract.PlacesEntry.COLUMN_ENGLISH_DESCRIPTION);
        int columnLocationIdx = cursor.getColumnIndex(GoAndJoyInfoContract.PlacesEntry.COLUMN_LOCATION);
        //  atractionModelList = new ArrayList<>();
        placeModelList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                placeModelList.add(
                        new PlaceModel(
                                cursor.getString(columnNameIdx),
                                cursor.getString(columnDescriptionIdx),
                                cursor.getString(columnLocationIdx), ""));
            } while (cursor.moveToNext());
        }
        if(recyclerview != null) {
            placeAdapter = new PlaceAdapter(placeModelList);
            recyclerview.setAdapter(placeAdapter);
        }
    }


    @Override
    public void onLoaderReset(Loader loader) {

    }
    public void onBtnGoClicked(int position)
    {
        PlaceModel placeModel =  placeAdapter.mPlaceModel.get(position);
        getActivity().getContentResolver().update(GoAndJoyInfoContract.CONTENT_PLACE_URI, null, GoAndJoyInfoContract.PlacesEntry.COLUMN_ENGLISH_NAME+"=?",new String[] {placeModel.getName()});

    }
}

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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class SixFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int CONTACT_LOADER = 5;
    private RecyclerView recyclerview;

    int type;
    private List<AtractionModel> placeModelList;
    private RVAdapter placeAdapter;

    public SixFragment() {
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
        View view = inflater.inflate(R.layout.fragment_six, container, false);

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
        Uri CONTACT_URI = GoAndJoyInfoContract.CONTENT_URI;
        String selection = GoAndJoyInfoContract.PlacesEntry.CULUMN_MY_PLACE + " = ?";
        String[] selectionArgs = new String[]{"1"};
        // String selection=null; String[] selectionArgs=null;
        return new CursorLoader(getContext(), CONTACT_URI, null, selection, selectionArgs, null);
    }

    @Override
    public void onLoadFinished(Loader loader, Cursor cursor) {

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
        int columnMyPlaceIdx = cursor.getColumnIndex(GoAndJoyInfoContract.PlacesEntry.CULUMN_MY_PLACE);
        int columnCategoryIdx = cursor.getColumnIndex(GoAndJoyInfoContract.PlacesEntry.COLUMN_CATEGORY_TYPE);
        int columnDateIdx = cursor.getColumnIndex(GoAndJoyInfoContract.PlacesEntry.COLUMN_DATE);

        placeModelList = new ArrayList<>();

        if (cursor.moveToFirst()) {

            do {
                int placeId = cursor.getInt(columnPlaceIdIdx);
                String imageURI = cursor.getString(columnImgUriIdx);
                if (atractionMap.containsKey(placeId))
                {
                    AtractionModel attraction = atractionMap.get(placeId);
                    attraction.getImageList().add(imageURI);
                }
                else
                {
                    AtractionModel attraction = new AtractionModel(
                            cursor.getString(columnNameIdx),
                            cursor.getString(columnDescriptionIdx),
                            cursor.getInt(columnDurationTimeTypeIdx),
                            cursor.getInt(columnAccessibilityTypeIdx),
                            cursor.getInt(columnTripTypeIdx),
                            cursor.getInt(columnTripLevelTypeIdx),
                            cursor.getString(columnLocationIdx),"");
                    attraction.getImageList().add(imageURI);
                    attraction.getPlaceModel().setMyPlace(true);

                    DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

                    long milliSeconds= Long.parseLong(cursor.getString(columnDateIdx));
                    System.out.println(milliSeconds);

                    Calendar calendar = Calendar.getInstance();
                    calendar.setTimeInMillis(milliSeconds);

                    attraction.getPlaceModel().setDate(formatter.format(calendar.getTime()));
                    atractionMap.put(placeId,attraction);
                }

            } while (cursor.moveToNext());
        }

        placeModelList.addAll(atractionMap.values());

        if (recyclerview != null) {
            placeAdapter = new RVAdapter(placeModelList, new ItemViewHolder.IMyViewHolderClicks() {
                @Override
                public void onBtnGoClicked(int position) {
                    //do nothing
                }
            });
            recyclerview.setAdapter(placeAdapter);
        }
    }


    @Override
    public void onLoaderReset(Loader loader) {

    }

}

package com.goldstein.android.goandjoi.fragments;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goldstein.android.goandjoi.GoAndJoyInfoContract;
import com.goldstein.android.goandjoi.PrivateContentProvider;
import com.goldstein.android.goandjoi.R;
import com.goldstein.android.goandjoi.RVAdapter;
import com.goldstein.android.goandjoi.models.AtractionModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class OneFragment extends Fragment {

    private RecyclerView recyclerview;
    private List<AtractionModel> atractionModelList;
    private RVAdapter adapter;
    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_one, container, false);

        recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerview.setLayoutManager(layoutManager);

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setHasOptionsMenu(true);

        String[] projection = new String[]{
                GoAndJoyInfoContract.PlacesEntry.COLUMN_ID,
                GoAndJoyInfoContract.PlacesEntry.COLUMN_ENGLISH_NAME,
                GoAndJoyInfoContract.PlacesEntry.COLUMN_ENGLISH_DESCRIPTION
                };

        Cursor cursor = getActivity().getContentResolver().query(GoAndJoyInfoContract.CONTENT_URI, projection, null, null, null);
        int columnNameIdx = cursor.getColumnIndex(GoAndJoyInfoContract.PlacesEntry.COLUMN_ENGLISH_NAME);
        int columnDescriptionIdx = cursor.getColumnIndex(GoAndJoyInfoContract.PlacesEntry.COLUMN_ENGLISH_NAME);

        atractionModelList = new ArrayList<>();

        if (cursor.moveToFirst()) {

            do{
                atractionModelList.add(
                        new AtractionModel(
                                cursor.getString(columnNameIdx),
                                cursor.getString(columnDescriptionIdx)));

            } while (cursor.moveToNext());
        }

        adapter = new RVAdapter(atractionModelList);
        recyclerview.setAdapter(adapter);
    }
}

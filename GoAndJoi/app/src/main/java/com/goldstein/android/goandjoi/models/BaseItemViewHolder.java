package com.goldstein.android.goandjoi.models;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.goldstein.android.goandjoi.LocationsManager;
import com.goldstein.android.goandjoi.R;
import com.goldstein.android.goandjoi.RVAdapter;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class BaseItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private Context context;
private String destLocation;
    public TextView name_TextView;
    public TextView description_TextView;
    public TextView location_TextView;
    public BaseItemViewHolder(View itemView) {
        super(itemView);
        context = itemView.getContext();

        itemView.setClickable(true);
/*
        View name_des_location = itemView.findViewById(R.id.name_des_location);
        View name_des = name_des_location.findViewById(R.id.name_description);*/
        name_TextView = (TextView) itemView.findViewById(R.id.country_name);
        description_TextView = (TextView) itemView.findViewById(R.id.country_iso);
        location_TextView = (TextView) itemView.findViewById(R.id.location);

        Button button = (Button) itemView.findViewById(R.id.btn_go);
        button.setOnClickListener(this);
    }
    public void bind(PlaceModel placeModel) {
        name_TextView.setText(placeModel.getName());
        description_TextView.setText(placeModel.getDescription());
        location_TextView.setText(String.valueOf(placeModel.getDuration()));

       if(RVAdapter.currentLocation != null && RVAdapter.currentLocation != "") {
            String[] srcLoc = RVAdapter.currentLocation.split(",");
            LatLng src = new LatLng(Double.parseDouble(srcLoc[0]), Double.parseDouble(srcLoc[1]));
            /*if (destLocation == null || destLocation.equals(""))
            {
                destLocation = atractionModel.getPlaceModel().getLocation();
            }*/
            destLocation = placeModel.getLocation();
            String[] dstLoc = destLocation.split(",");
            LatLng dest = new LatLng(Double.parseDouble(dstLoc[0]), Double.parseDouble(dstLoc[1]));
            //LatLng dest = new LatLng(31.7767234, 35.2366972);
            LatLng dest1 = new LatLng(31.8162935, 35.1934127);
            LocationsManager locationsManager = new LocationsManager();
            locationsManager.setDuration(src, dest, location_TextView);
        }
		placeModel.setDuration(location_TextView.getText().toString());


    }

    @Override
    public void onClick(View v) {
        String uri = "http://maps.google.com/maps?saddr="+ RVAdapter.currentLocation+"&daddr="+destLocation+" ( "+name_TextView.getText()+" )";
        //String uri = "http://maps.google.com/maps?saddr=" + location_TextView.getText() + "(%s)&daddr=31.7767234,35.2366972 (הכותל המערבי)";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        context.startActivity(intent);
    }
}

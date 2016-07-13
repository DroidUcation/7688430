package com.goldstein.android.goandjoi.models;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.goldstein.android.goandjoi.LocationsManager;
import com.goldstein.android.goandjoi.R;
import com.goldstein.android.goandjoi.RVAdapter;
import com.goldstein.android.goandjoi.fragments.HorizontalAdapter;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private Context context;
private String destLocation;

    private ArrayList<String> horizontalList;
    public TextView name_TextView;
    public TextView description_TextView;
    public TextView durationTime_TextView;

    public TextView accessibility_TextView;
    public TextView trip_TextView;
    public TextView tripLevel_TextView;
    public TextView location_TextView;
    public  View details_go;
    public RecyclerView pic_recyclerView;

    public Boolean isOpen;
    HorizontalAdapter horizontalAdapter;
    public RecyclerView horizontal_recycler_view;

    public ItemViewHolder(View itemView) {
        super(itemView);
        isOpen = false;

        context = itemView.getContext();

        itemView.setClickable(true);

        View name_des_location_pic = itemView.findViewById(R.id.name_des_location_pic);
        //View name_des = itemView.findViewById(R.id.name_description);
        name_TextView = (TextView) name_des_location_pic.findViewById(R.id.country_name);
        description_TextView = (TextView) name_des_location_pic.findViewById(R.id.country_iso);
        location_TextView = (TextView) name_des_location_pic.findViewById(R.id.location);
        pic_recyclerView = (RecyclerView) name_des_location_pic.findViewById(R.id.recyclerview);

        horizontalList = new ArrayList<>();
        horizontalList.add(String.valueOf(R.drawable.img_0780_1));
        //horizontalList.add(String.valueOf(R.drawable.img_0807_1));


//        pic_recyclerView.setLayoutManager(new LinearLayoutManager(context));
        horizontal_recycler_view = (RecyclerView) name_des_location_pic.findViewById(R.id.horizontal_recycler_view);
        /*horizontalAdapter = new HorizontalAdapter(horizontalList);
        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view.setLayoutManager(horizontalLayoutManagaer);

        horizontal_recycler_view.setAdapter(horizontalAdapter);*/

        details_go = itemView.findViewById(R.id.details_go);

        accessibility_TextView = (TextView) details_go.findViewById(R.id.attraction_accessibility);
        trip_TextView = (TextView) details_go.findViewById(R.id.attraction_trip_type);
        tripLevel_TextView = (TextView) details_go.findViewById(R.id.attraction_trip_level);
        durationTime_TextView = (TextView) details_go.findViewById(R.id.attraction_time);

        Button button = (Button) details_go.findViewById(R.id.btnGo);
        button.setOnClickListener(this);


        itemView.setOnClickListener(handlerItem);
    }

    public void bind(AtractionModel atractionModel) {
        name_TextView.setText(atractionModel.getPlaceModel().getName());
        description_TextView.setText(atractionModel.getPlaceModel().getDescription());
        durationTime_TextView.setText(String.valueOf(atractionModel.getDurationTimeType()));
        accessibility_TextView.setText(String.valueOf(atractionModel.getAccessibilityType()));
        trip_TextView.setText(String.valueOf(atractionModel.getTripType()));
        tripLevel_TextView.setText(String.valueOf(atractionModel.getTripLevelType()));
        location_TextView.setText(String.valueOf(atractionModel.getPlaceModel().getDuration()));


        if (true){//horizontalAdapter == null) {
            horizontalList = (ArrayList<String>) atractionModel.getImageList();
            horizontalAdapter = new HorizontalAdapter(horizontalList);
            LinearLayoutManager horizontalLayoutManagaer
                    = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
            horizontal_recycler_view.setLayoutManager(horizontalLayoutManagaer);

            horizontal_recycler_view.setAdapter(horizontalAdapter);
        }
        /*horizontalAdapter.
        horizontalAdapter.notifyDataSetChanged();*/


        if(RVAdapter.currentLocation != null && RVAdapter.currentLocation != "") {
            String[] srcLoc = RVAdapter.currentLocation.split(",");
            LatLng src = new LatLng(Double.parseDouble(srcLoc[0]), Double.parseDouble(srcLoc[1]));
            /*if (destLocation == null || destLocation.equals(""))
            {
                destLocation = atractionModel.getPlaceModel().getLocation();
            }*/
            destLocation = atractionModel.getPlaceModel().getLocation();
            String[] dstLoc = destLocation.split(",");
            LatLng dest = new LatLng(Double.parseDouble(dstLoc[0]), Double.parseDouble(dstLoc[1]));
            //LatLng dest = new LatLng(31.7767234, 35.2366972);
            LatLng dest1 = new LatLng(31.8162935, 35.1934127);
            LocationsManager locationsManager = new LocationsManager();
            locationsManager.setDuration(src, dest, location_TextView);
        }
		atractionModel.getPlaceModel().setDuration(location_TextView.getText().toString());
    }

    View.OnClickListener handlerItem = new View.OnClickListener() {
        public void onClick(View v) {
            if (!isOpen)
                details_go.setVisibility(View.VISIBLE);
            else
                details_go.setVisibility(View.GONE);
            isOpen = !isOpen;
        }
    };

            @Override
    public void onClick(View v) {
       // String uri = "http://maps.google.com/maps?saddr=" + location_TextView.getText() + "(%s)&daddr=31.7767234,35.2366972 (הכותל המערבי)";
                String uri = "http://maps.google.com/maps?saddr="+ RVAdapter.currentLocation+"&daddr="+destLocation+" ( "+name_TextView.getText()+" )";

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        context.startActivity(intent);
    }
}

package com.goldstein.android.goandjoi.models;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.goldstein.android.goandjoi.LocationsManager;
import com.goldstein.android.goandjoi.R;
import com.goldstein.android.goandjoi.RVAdapter;
import com.google.android.gms.maps.model.LatLng;

public class ItemViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {

    private Context context;

    private String destLocation;

    public TextView name_TextView;
    public TextView description_TextView;
    public TextView durationTime_TextView;
    public TextView accessibility_TextView;
    public TextView trip_TextView;
    public TextView tripLevel_TextView;
    public TextView location_TextView;

    public ItemViewHolder2(View itemView) {
        super(itemView);

        context = itemView.getContext();

        itemView.setClickable(true);
        name_TextView = (TextView) itemView.findViewById(R.id.country_name);
        description_TextView = (TextView) itemView.findViewById(R.id.country_iso);
        durationTime_TextView = (TextView) itemView.findViewById(R.id.attraction_time);
        accessibility_TextView = (TextView) itemView.findViewById(R.id.attraction_accessibility);
        trip_TextView = (TextView) itemView.findViewById(R.id.attraction_trip_type);
        tripLevel_TextView = (TextView) itemView.findViewById(R.id.attraction_trip_level);
        location_TextView = (TextView) itemView.findViewById(R.id.location);

        Button button = (Button)itemView.findViewById(R.id.btnGo);
        button.setOnClickListener(this);

    }

    public void bind(AtractionModel atractionModel) {
        name_TextView.setText(atractionModel.getPlaceModel().getName());
        description_TextView.setText(atractionModel.getPlaceModel().getDescription());
        durationTime_TextView.setText(String.valueOf( atractionModel.getDurationTimeType()));
        accessibility_TextView.setText(String.valueOf( atractionModel.getAccessibilityType()));
        trip_TextView.setText(String.valueOf( atractionModel.getTripType()));
        tripLevel_TextView.setText(String.valueOf( atractionModel.getTripLevelType()));
        //location_TextView.setText(String.valueOf( atractionModel.getDuration()));

        if(RVAdapter.currentLocation != null && RVAdapter.currentLocation != "") {
            String[] srcLoc = RVAdapter.currentLocation.split(",");
            LatLng src = new LatLng(Double.parseDouble(srcLoc[0]), Double.parseDouble(srcLoc[1]));
           /* if (destLocation == null || destLocation.equals(""))
            {
                destLocation = atractionModel.getLocation();
            }
            destLocation = atractionModel.getLocation();*/
            String[] dstLoc = destLocation.split(",");
            LatLng dest = new LatLng(Double.parseDouble(dstLoc[0]), Double.parseDouble(dstLoc[1]));
            //LatLng dest = new LatLng(31.7767234, 35.2366972);
            LatLng dest1 = new LatLng(31.8162935,35.1934127);
            LocationsManager locationsManager = new LocationsManager();
            locationsManager.setDuration(src, dest, location_TextView);

        }
       // atractionModel.setDuration(location_TextView.getText().toString());
    }

    @Override
    public void onClick(View v) {
        String uri = "http://maps.google.com/maps?saddr="+ RVAdapter.currentLocation+"&daddr="+destLocation+" ( "+name_TextView.getText()+" )";
        //String uri = "http://maps.google.com/maps?q=loc:31.7767234,35.2366972 (הכותל המערבי)";
        // + location_TextView.getText(); //String.format(Locale.ENGLISH, "geo:%f,%f", latitude, longitude);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
        context.startActivity(intent);
    }
}

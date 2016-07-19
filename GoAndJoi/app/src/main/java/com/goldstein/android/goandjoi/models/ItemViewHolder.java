package com.goldstein.android.goandjoi.models;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
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
    public TextView date_TextView;
    public TextView tree_dotes_TextView;
    public View details_go;
    public RecyclerView pic_recyclerView;
    private Button go_Button;

    HorizontalAdapter horizontalAdapter;
    public RecyclerView horizontal_recycler_view;

    public IMyViewHolderClicks mListener;

    public ItemViewHolder(View itemView, IMyViewHolderClicks listener) {
        super(itemView);

        mListener = listener;
        context = itemView.getContext();

        itemView.setClickable(true);

        View name_des_location_pic = itemView.findViewById(R.id.name_des_location_pic);
        //View name_des = itemView.findViewById(R.id.name_description);
        name_TextView = (TextView) name_des_location_pic.findViewById(R.id.country_name);
        description_TextView = (TextView) name_des_location_pic.findViewById(R.id.country_iso);
        location_TextView = (TextView) name_des_location_pic.findViewById(R.id.location);
        date_TextView = (TextView) name_des_location_pic.findViewById(R.id.date_txt);
        pic_recyclerView = (RecyclerView) name_des_location_pic.findViewById(R.id.recyclerview);

        horizontalList = new ArrayList<>();
        /*horizontalList.add(String.valueOf(R.drawable.img_0780_1));
        horizontalList.add(String.valueOf(R.drawable.img_0807_1));
*/

//        pic_recyclerView.setLayoutManager(new LinearLayoutManager(context));
        horizontal_recycler_view = (RecyclerView) name_des_location_pic.findViewById(R.id.horizontal_recycler_view);

        details_go = itemView.findViewById(R.id.details_go);

        accessibility_TextView = (TextView) details_go.findViewById(R.id.attraction_accessibility);
        trip_TextView = (TextView) details_go.findViewById(R.id.attraction_trip_type);
        tripLevel_TextView = (TextView) details_go.findViewById(R.id.attraction_trip_level);
        durationTime_TextView = (TextView) details_go.findViewById(R.id.attraction_time);
        //tree_dotes_TextView = (TextView) name_des_location_pic.findViewById(R.id.tree_dotes_txt);
        go_Button = (Button) details_go.findViewById(R.id.btnGo);
        go_Button.setOnClickListener(this);


        itemView.setOnClickListener(handlerItem);
    }

    public void bind(AtractionModel atractionModel) {
        name_TextView.setText(atractionModel.getPlaceModel().getName());
        description_TextView.setText(atractionModel.getPlaceModel().getDescription());
        String duration = "";
        switch(atractionModel.getDurationTimeType())
        {
            case 1:
            {
                duration = "1 - 2 Hours";
                break;
            }
            case 2:
            {
                duration = "3 - 5 Hours";
                break;
            }
            case 3:
            {
                duration = "5 Hours and On";
                break;
            }

        }
        durationTime_TextView.setText(duration);

        String accessibility = "";
        switch(atractionModel.getAccessibilityType())
        {
            case 1:
            {
                accessibility = "Family";
                break;
            }
            case 2:
            {
                accessibility = "Buggy";
                break;
            }
            case 3:
            {
                accessibility = "Handicapped";
                break;
            }

        }
        accessibility_TextView.setText(accessibility);

        String tripType = "";
        switch(atractionModel.getDurationTimeType())
        {
            case 1:
            {
                tripType = "Round trip";
                break;
            }
            case 2:
            {
                tripType = "One way";
                break;
            }
            case 3:
            {
                tripType = "Circle";
                break;
            }

        }
        trip_TextView.setText(tripType);

        String tripLevel = "";
        switch(atractionModel.getTripLevelType())
        {
            case 1:
            {
                tripLevel = "Easy";
                break;
            }
            case 2:
            {
                tripLevel = "Medium";
                break;
            }
            case 3:
            {
                tripLevel = "Hard";
                break;
            }

        }
        tripLevel_TextView.setText(tripLevel);
        location_TextView.setText(String.valueOf(atractionModel.getPlaceModel().getDuration()));

        horizontalList = (ArrayList<String>) atractionModel.getImageList();
        horizontalAdapter = new HorizontalAdapter(horizontalList);
        LinearLayoutManager horizontalLayoutManagaer
                = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        horizontal_recycler_view.setLayoutManager(horizontalLayoutManagaer);

        horizontal_recycler_view.setAdapter(horizontalAdapter);

        if (atractionModel.getPlaceModel().getMyPlace())
        {
            go_Button.setVisibility(View.INVISIBLE);
            date_TextView.setVisibility(View.VISIBLE);
            date_TextView.setText(atractionModel.getPlaceModel().getDate());
        }
        else {

            go_Button.setVisibility(View.VISIBLE);
            date_TextView.setVisibility(View.INVISIBLE);
            if (RVAdapter.currentLocation != null && RVAdapter.currentLocation != "") {
                String[] srcLoc = RVAdapter.currentLocation.split(",");
                LatLng src = new LatLng(Double.parseDouble(srcLoc[0]), Double.parseDouble(srcLoc[1]));

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
    }

    View.OnClickListener handlerItem = new View.OnClickListener() {
        public void onClick(View v) {
            if (details_go.getVisibility() == View.GONE) {
                /*RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                params.addRule(RelativeLayout.BELOW, R.id.horizontal_recycler_view);

                description_TextView.setLayoutParams(params);
*/
                description_TextView.setEllipsize(TextUtils.TruncateAt.START);
                description_TextView.setSingleLine(false);
                description_TextView.setMaxEms(3000);
                description_TextView.setMaxLines(10);
                //tree_dotes_TextView.setVisibility(View.INVISIBLE);

                details_go.setVisibility(View.VISIBLE);
            } else {
                /*RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(500, 90);
                params.addRule(RelativeLayout.BELOW, R.id.horizontal_recycler_view);
                description_TextView.setLayoutParams(params);*/
                //tree_dotes_TextView.setVisibility(View.VISIBLE);
                description_TextView.setEllipsize(TextUtils.TruncateAt.END);
                description_TextView.setMaxLines(2);
                description_TextView.setSingleLine(true);
                details_go.setVisibility(View.GONE);
            }
        }
    };

    @Override
    public void onClick(View v) {
        // String uri = "http://maps.google.com/maps?saddr=" + location_TextView.getText() + "(%s)&daddr=31.7767234,35.2366972 (הכותל המערבי)";

        final int position = getAdapterPosition();
        mListener.onBtnGoClicked(position);
        /*
        AlertDialog.Builder adb = new AlertDialog.Builder(context);
        adb.setMessage("Do you want to add this attraction to My Places?");
        adb.setIcon(android.R.drawable.ic_dialog_alert);
        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                mListener.onBtnGoClicked(position);
            } });
        adb.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            } });
        adb.show();
*/
        ///mListener.onBtnGoClicked(position);
        Toast.makeText(context,"The Attraction was added to My Places",Toast.LENGTH_SHORT);

        String uri = "http://maps.google.com/maps?saddr=" +
                RVAdapter.currentLocation + "&daddr=" + destLocation + " ( " + name_TextView.getText() + " )";

        if (destLocation != null) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            context.startActivity(intent);
        }
        else
        {
            buildAlertMessageNoGps();
        }
    }

    private void buildAlertMessageNoGps() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("Your GPS seems to be disabled, do you want to enable it?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(@SuppressWarnings("unused") final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        context.startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(final DialogInterface dialog, @SuppressWarnings("unused") final int id) {
                        dialog.cancel();
                    }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }
    public static interface IMyViewHolderClicks {
        public void onBtnGoClicked(int position);
    }
}

package com.goldstein.android.goandjoi;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.goldstein.android.goandjoi.models.AtractionModel;
import com.goldstein.android.goandjoi.models.BaseItemViewHolder;
import com.goldstein.android.goandjoi.models.ItemViewHolder;
import com.goldstein.android.goandjoi.models.PlaceModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PlaceAdapter extends RecyclerView.Adapter<BaseItemViewHolder> {
    public List<PlaceModel> mPlaceModel;
    public static String currentLocation;
    private Context context;

    public PlaceAdapter(List<PlaceModel> mPlaceModel) {
        this.mPlaceModel = mPlaceModel;
        //      this.mOriginalCountryModel = mCountryModel;
    }

    @Override
    public void onBindViewHolder(BaseItemViewHolder itemViewHolder, int i) {
        final PlaceModel model = mPlaceModel.get(i);
        itemViewHolder.bind(model);
    }

    @Override
    public BaseItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.vw_place, viewGroup, false);

        //loadLocation();

        return new BaseItemViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mPlaceModel.size();
    }

    public void setFilter(List<PlaceModel> placeModels){
        mPlaceModel = new ArrayList<>();
        mPlaceModel.addAll(placeModels);
        notifyDataSetChanged();
    }

    public void sortNotifyDataSetChanged()
    {
        if (mPlaceModel!= null ) {
            Collections.sort(mPlaceModel, new Comparator<PlaceModel>() {
                @Override
                public int compare(PlaceModel lhs, PlaceModel rhs) {
                    if(lhs.getDuration().trim() == ""|| rhs.getDuration().trim() == "")
                        return 0;
                    int lDuration = Integer.getInteger(lhs.getDuration().split(" ")[0]);
                    int rDuration = Integer.getInteger(rhs.getDuration().split(" ")[0]);
                    return lDuration - rDuration;
                }
            });
        }
        notifyDataSetChanged();
    }

/*    private final LocationListener mLocationListener = new LocationListener() {
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
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        // Register the listener with the Location Manager to receive location updates
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mLocationListener);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, mLocationListener);
    }
    private void makeUseOfNewLocation(Location location) {
       currentLocation = location.getLatitude() + ", " + location.getLongitude();
*//*
        Collections.sort(mPlaceModel, new Comparator<PlaceModel>() {
            @Override
            public int compare(PlaceModel lhs, PlaceModel rhs) {
                int lDuration = Integer.getInteger(lhs.getDuration().split(" ")[0]);
                int rDuration = Integer.getInteger(rhs.getDuration().split(" ")[0]);
                return lDuration-rDuration;
            }
        });*//*
        notifyDataSetChanged();
    }*/
}

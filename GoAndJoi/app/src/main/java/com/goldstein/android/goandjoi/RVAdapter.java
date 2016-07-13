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
import com.goldstein.android.goandjoi.models.ItemViewHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class RVAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    public static String currentLocation;

    private List<AtractionModel> mCountryModel;
    private Context context;

    public RVAdapter(List<AtractionModel> mCountryModel) {
        this.mCountryModel = mCountryModel;

       /* Collections.sort(mCountryModel, new Comparator<AtractionModel>() {
            @Override
            public int compare(AtractionModel lhs, AtractionModel rhs) {
                return lhs.getPlaceModel().getName().toLowerCase().compareTo(rhs.getPlaceModel().getName().toLowerCase());
            }
        });*/

    }

    @Override
    public void onBindViewHolder(ItemViewHolder itemViewHolder, int i) {
        final AtractionModel model = mCountryModel.get(i);
        itemViewHolder.bind(model);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.actration_view, viewGroup, false);

        loadLocation();

        return new ItemViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mCountryModel.size();
    }

    public void setFilter(List<AtractionModel> countryModels){
        mCountryModel = new ArrayList<>();
        mCountryModel.addAll(countryModels);
        notifyDataSetChanged();
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
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        // Register the listener with the Location Manager to receive location updates
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mLocationListener);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, mLocationListener);

    }

    private void makeUseOfNewLocation(Location location)
    {
        currentLocation = location.getLatitude() + ", " + location.getLongitude();

        Collections.sort(mCountryModel, new Comparator<AtractionModel>() {
            @Override
            public int compare(AtractionModel lhs, AtractionModel rhs) {
                int res;
                return lhs.getPlaceModel().getDuration().compareTo(rhs.getPlaceModel().getDuration().toLowerCase());
            }
        });
        notifyDataSetChanged();
    }
}

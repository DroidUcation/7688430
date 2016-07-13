package com.goldstein.android.goandjoi.fragments;

import android.content.Context;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.goldstein.android.goandjoi.R;

import java.util.List;

/**
 * Created by Riki on 10/07/2016.
 */

public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.MyViewHolder> {

    private List<String> horizontalList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        Context context;
        //public TextView txtView;
        public ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            context= view.getContext();
         //   txtView = (TextView) view.findViewById(R.id.txtView);
            imageView = (ImageView) view.findViewById(R.id.image);

        }
    }

    public HorizontalAdapter(List<String> horizontalList) {
        this.horizontalList = horizontalList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pic, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        //holder.txtView.setText(horizontalList.get(position));
        //holder.imageView.setImageDrawable(ContextCompat.getDrawable(holder.context,Integer.getInteger(horizontalList.get(position) )));
        //holder.imageView.setImageDrawable(ContextCompat.getDrawable(holder.context, R.drawable.img_0807_1));
       Uri imgPath = Uri.parse("android.resource://com.goldstein.android.goandjoi/"+horizontalList.get(position));
        Glide.with(holder.imageView.getContext()).load(imgPath)
                .dontAnimate()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return horizontalList.size();
    }
}


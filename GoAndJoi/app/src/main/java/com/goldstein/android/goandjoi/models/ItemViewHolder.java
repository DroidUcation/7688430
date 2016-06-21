package com.goldstein.android.goandjoi.models;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.goldstein.android.goandjoi.R;

public class ItemViewHolder extends RecyclerView.ViewHolder {

    public TextView name_TextView;
    public TextView description_TextView;



    public ItemViewHolder(View itemView) {
        super(itemView);
        itemView.setClickable(true);
        name_TextView = (TextView) itemView.findViewById(R.id.country_name);
        description_TextView = (TextView) itemView.findViewById(R.id.country_iso);

    }

    public void bind(AtractionModel atractionModel) {
        name_TextView.setText(atractionModel.getName());
        description_TextView.setText(atractionModel.getDescription());

    }


}

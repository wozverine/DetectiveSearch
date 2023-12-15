package com.glitch.detectivesearch.cases;

import android.view.*;
import android.widget.*;

import androidx.recyclerview.widget.RecyclerView;

import com.glitch.detectivesearch.R;

public class CasesViewHolder extends RecyclerView.ViewHolder{

    public TextView listItemView;
    public ImageView iconItemView;

    public CasesViewHolder(View itemView) {
        super(itemView);
        iconItemView = (ImageView) itemView.findViewById(R.id.icon);
    }
    void bind(CasesInfo s) {
        listItemView.setText(s.getCase_name());
        iconItemView.setImageResource(s.getIcon_case());
    }
}

package com.glitch.detectivesearch.cases;

import android.view.*;
import android.widget.*;

import androidx.recyclerview.widget.RecyclerView;

import com.glitch.detectivesearch.ListItemClickListener;
import com.glitch.detectivesearch.R;

public class CasesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    private ListItemClickListener mListener;
    public TextView listItemView;
    public ImageView iconItemView;

    public CasesViewHolder(View itemView, ListItemClickListener listener) {
        super(itemView);
        listItemView = (TextView) itemView.findViewById(R.id.item_name);
        iconItemView = (ImageView) itemView.findViewById(R.id.icon);
        mListener            = listener;
        itemView.setOnClickListener(this);
    }
    void bind(CasesInfo s) {
        listItemView.setText(s.getCase_name());
        iconItemView.setImageResource(s.getIcon_case());
    }
    @Override
    public void onClick(View view) {
        int clickedPosition = getAdapterPosition();
        mListener.onListItemClick(clickedPosition);
    }
}

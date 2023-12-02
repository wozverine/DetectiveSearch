package com.glitch.detectivesearch.cases;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.glitch.detectivesearch.ListItemClickListener;
import com.glitch.detectivesearch.R;

public class CasesAdapter extends RecyclerView.Adapter<CasesViewHolder> {
    private int mNumberItems;
    private CasesInfo[] items;
    private ListItemClickListener mOnClickListener;
    int ID = 0;

    public CasesAdapter(int numberOfItems, CasesInfo[] itemList, ListItemClickListener listener) {
        mNumberItems        = numberOfItems;
        items               = itemList;
        mOnClickListener    = listener;
    }

    @Override
    public CasesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        ID= R.layout.list_view;

        int            layoutIdForListItem             = ID;
        LayoutInflater inflater                        = LayoutInflater.from(context);
        boolean        shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        CasesViewHolder viewHolder = new CasesViewHolder(view, mOnClickListener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CasesViewHolder holder, int position) {
        holder.bind(items[position]);
    }

    @Override
    public int getItemCount() {
        return items.length;
    }

    public void setData(CasesInfo[] data) {
        items        = data;
        mNumberItems = data.length;
        notifyDataSetChanged();
    }
    public CasesInfo[] getdata() {
        return items;
    }
}

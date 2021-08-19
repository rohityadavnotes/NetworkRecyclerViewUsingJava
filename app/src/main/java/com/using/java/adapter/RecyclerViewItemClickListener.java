package com.using.java.adapter;

import android.view.View;

public interface RecyclerViewItemClickListener {
    public void onItemClick(View view, int position);
    public void onItemLongClick(View view, int position);
}
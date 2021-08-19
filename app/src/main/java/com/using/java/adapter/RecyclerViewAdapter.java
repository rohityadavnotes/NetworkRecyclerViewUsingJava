package com.using.java.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.using.java.data.remote.glide.GlideCacheUtil;
import com.using.java.data.remote.glide.GlideImageLoader;
import com.using.java.data.remote.glide.GlideImageLoadingListener;
import com.using.java.data.remote.picasso.PicassoImageLoader;
import com.using.java.data.remote.picasso.PicassoImageLoadingListener;
import com.using.java.model.Android;
import com.using.java.R;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder> {

    private Activity activityContext;
    private ArrayList<Android> arrayList;

    public RecyclerViewAdapter(Activity activityContext) {
        this.activityContext = activityContext;
        arrayList = new ArrayList<>();
    }

    public void setAdapterListData(ArrayList<Android> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    public ArrayList<Android> getAdapterListData() {
        return this.arrayList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item_row, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, final int position) {
        Android currentItem = arrayList.get(position);
        ((ItemViewHolder) itemViewHolder).bind(currentItem, position);
    }

    @Override
    public int getItemCount() {
        return arrayList == null ? 0 : arrayList.size();
    }

    public boolean isAdapterListEmpty() {
        return getItemCount() == 0;
    }

    public Android getItemAt(int position) {
        return arrayList != null ? arrayList.get(position) : null;
    }

    public void removeAt(int position) {
        arrayList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(0, arrayList.size());
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView logoImageView;
        TextView codeNameTextView, versionNumbersTextView, apiLevelTextView, releaseDateTextView;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            logoImageView           = (ImageView) itemView.findViewById(R.id.logoImageView);
            codeNameTextView        = (TextView) itemView.findViewById(R.id.codeNameTextView);
            versionNumbersTextView  = (TextView) itemView.findViewById(R.id.versionNumbersTextView);
            apiLevelTextView        = (TextView) itemView.findViewById(R.id.apiLevelTextView);
            releaseDateTextView     = (TextView) itemView.findViewById(R.id.releaseDateTextView);
        }

        public void bind(Android android, int position) {
            //GlideCacheUtil.getInstance().clearAllCache(activityContext);
            GlideImageLoader.load(
                    activityContext,
                    android.getLogo(),
                    R.drawable.user_placeholder,
                    R.drawable.error_placeholder,
                    logoImageView,
                    new GlideImageLoadingListener() {
                        @Override
                        public void imageLoadSuccess() {
                        }
                        @Override
                        public void imageLoadError() {
                        }
                    });


            /*PicassoImageLoader.load(
                    activityContext,
                    android.getLogo(),
                    R.drawable.user_placeholder,
                    R.drawable.error_placeholder,
                    logoImageView,
                    new PicassoImageLoadingListener() {
                        @Override
                        public void imageLoadSuccess() {
                        }

                        @Override
                        public void imageLoadError(Exception exception) {
                            Toast.makeText(activityContext, "An error occurred", Toast.LENGTH_SHORT).show();
                        }
                    });*/

            codeNameTextView.setText(android.getCodeName());
            versionNumbersTextView.setText(android.getVersionNumbers());
            apiLevelTextView.setText(android.getApiLevel());
            releaseDateTextView.setText(android.getReleaseDate());
        }
    }
}
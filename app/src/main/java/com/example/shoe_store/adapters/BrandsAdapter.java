package com.example.shoe_store.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shoe_store.R;
import com.example.shoe_store.data.ShoeData;

import java.util.List;

public class BrandsAdapter extends RecyclerView.Adapter<BrandsAdapter.ViewHolder> {
    private static final String TAG = "NoteActivity";
    private List<ShoeData> shoeDataList;
    GetBrandPosition mGetBrandPosition;
    Context mContext;

    public BrandsAdapter(List<ShoeData> shoeDataList, GetBrandPosition getBrandPosition, Context mContext) {
        this.shoeDataList = shoeDataList;
        mGetBrandPosition = getBrandPosition;
        this.mContext = mContext;
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView imageView;
        CardView mCardView;
        GetBrandPosition getBrandPosition;
        public ViewHolder(@NonNull View itemView, GetBrandPosition getBrandPosition) {
            super(itemView);
            imageView = itemView.findViewById(R.id.Brands_Icon);
            mCardView = itemView.findViewById(R.id.Product_brand_card);

            itemView.setOnClickListener(this);
            this.getBrandPosition = getBrandPosition;
        }

        @Override
        public void onClick(View v) {
            Log.d(TAG, "You click: " + getAdapterPosition());
            getBrandPosition.getBrandPosition(getAdapterPosition());
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View brandsView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.brands_product, parent, false);
        return new ViewHolder(brandsView, mGetBrandPosition);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ShoeData shoeData = shoeDataList.get(position);
        Glide.with(holder.itemView.getContext()).load(shoeData.getBrandImage()).into(holder.imageView);
    }
    @Override
    public int getItemCount() {
        return shoeDataList.size();
    }

    public interface GetBrandPosition{
        void getBrandPosition(int position);
    }
}

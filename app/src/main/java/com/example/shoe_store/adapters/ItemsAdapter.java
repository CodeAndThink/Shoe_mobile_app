package com.example.shoe_store.adapters;

import android.content.Context;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.MyViewHolder>{
    private static final String TAG = "NoteActivity";
    private GetPosition mGetPosition;
    private List<ShoeData> shoeDataList;
    public Context mContext;
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_product, parent, false);
        return new MyViewHolder(itemView, mGetPosition);
    }
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ShoeData shoeData = shoeDataList.get(position);
        holder.Name.setText(shoeData.getName());
        holder.Price.setText("$" + Integer.toString(shoeData.getPrice()));
        holder.Rate.setText(Float.toString(shoeData.getRate()));
        holder.mCardview.getTag();
        Glide.with(holder.itemView.getContext()).load(shoeData.getShoeImage()).into(holder.thumbnail);
    }

    @Override
    public int getItemCount() {
        return shoeDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView Name, Price, Rate;
        public ImageView thumbnail;
        FloatingActionButton SaveBtn;
        CardView mCardview;
        GetPosition getPosition;
        public MyViewHolder(View view, GetPosition getPosition) {
            super(view);
            Name = view.findViewById(R.id.shoe_name);
            Price = view.findViewById(R.id.shoe_price);
            Rate = view.findViewById(R.id.shoe_rate);
            SaveBtn = view.findViewById(R.id.save_shoe_btn);
            mCardview = view.findViewById(R.id.Product_card);
            thumbnail = view.findViewById(R.id.shoe_image);

            view.setOnClickListener(this);
            this.getPosition = getPosition;
        }

        @Override
        public void onClick(View v) {
            Log.d(TAG, "You press : " + getAdapterPosition());
            getPosition.getPosition(getAdapterPosition());
        }
    }
    public ItemsAdapter(List<ShoeData> shoeDataList, GetPosition getPosition, Context mContext){
        this.mGetPosition = getPosition;
        this.shoeDataList = shoeDataList;
        this.mContext = mContext;
    }
    public interface GetPosition{
        void getPosition(int position);
    }
}

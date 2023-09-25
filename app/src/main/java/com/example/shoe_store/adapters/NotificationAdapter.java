package com.example.shoe_store.adapters;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shoe_store.R;
import com.example.shoe_store.data.NotificationData;
import com.example.shoe_store.data.ShoeData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder>{
    private static final String TAG = "NoteActivity";
    private NotificationAdapter.GetPosition mGetPosition;
    private List<NotificationData> notificationDataList;
    public Context mContext;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View notificationView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_notification, parent, false);
        return new ViewHolder(notificationView, mGetPosition);
    }
    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.ViewHolder holder, int position) {
        NotificationData notificationData = notificationDataList.get(position);
        holder.mContent.setText(notificationData.getContent());
        holder.mTime.setText(notificationData.getTime());
        holder.mCardview.getTag();
    }

    @Override
    public int getItemCount() {
        return notificationDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView mTime, mContent;
        CardView mCardview;
        NotificationAdapter.GetPosition getPosition;
        public ViewHolder(View view, NotificationAdapter.GetPosition getPosition) {
            super(view);
            mTime = view.findViewById(R.id.Notification_time);
            mContent = view.findViewById(R.id.Notification_short_content);
            mCardview = view.findViewById(R.id.Notification_card);
            view.setOnClickListener(this);
            this.getPosition = getPosition;
        }

        @Override
        public void onClick(View v) {
            Log.d(TAG, "You press : " + getAdapterPosition());
            mCardview.setCardBackgroundColor(Color.WHITE);
            NotificationData notificationData = notificationDataList.get(getAdapterPosition());
            notificationData.setStatus("readed");
            getPosition.getPosition(getAdapterPosition());
        }
    }
    public NotificationAdapter(List<NotificationData> notificationDataList, NotificationAdapter.GetPosition getPosition, Context mContext){
        this.mGetPosition = getPosition;
        this.notificationDataList = notificationDataList;
        this.mContext = mContext;
    }
    public interface GetPosition{
        void getPosition(int position);
    }

}

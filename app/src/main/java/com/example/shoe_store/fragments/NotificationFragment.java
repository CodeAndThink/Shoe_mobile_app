package com.example.shoe_store.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.shoe_store.R;
import com.example.shoe_store.adapters.ItemsAdapter;
import com.example.shoe_store.adapters.NotificationAdapter;
import com.example.shoe_store.data.NotificationData;
import com.example.shoe_store.data.ShoeData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class NotificationFragment extends Fragment implements NotificationAdapter.GetPosition{
    Button mAllNotification, mUnreadNotification;
    FloatingActionButton mGoBack, mSearch, mClose;
    CardView mNotificationDetail;
    TextView mNotificationContent;
    Context mContext;
    RecyclerView mNotificationRecycleView;
    private String[] notification_content;
    private String[] notification_time;
    private List<Integer> check;
    private NotificationAdapter notificationAdapter;
    private List<NotificationData> notificationDataList;
    private List<NotificationData> notificationUnreadDataList;
    public NotificationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notification, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAllNotification = view.findViewById(R.id.All_of_notification);
        mGoBack = view.findViewById(R.id.go_back);
        mSearch = view.findViewById(R.id.search_button);
        mUnreadNotification = view.findViewById(R.id.Unread_notification);
        mClose = view.findViewById(R.id.Notification_close_detail);
        mNotificationDetail = view.findViewById(R.id.Notification_full_content);
        mNotificationRecycleView = view.findViewById(R.id.NotificationRecycleView);
        mNotificationContent = view.findViewById(R.id.Notification_content_detail);
        check = new ArrayList<>();

        dataInitialize();

        mNotificationRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        notificationAdapter = new NotificationAdapter(notificationDataList, this, mContext);
        mNotificationRecycleView.setAdapter(notificationAdapter);

        mAllNotification.setOnClickListener(v -> {
            mNotificationRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
            notificationAdapter = new NotificationAdapter(notificationDataList, this, mContext);
            mNotificationRecycleView.setAdapter(notificationAdapter);
        });
        mGoBack.setOnClickListener(v -> {
            replaceFragment(new HomeFragment());
        });
        mSearch.setOnClickListener(v -> {

        });
        mUnreadNotification.setOnClickListener(v -> {
            boolean test = true;
            for (int i = 0; i < notificationDataList.size(); i++){
                test = true;
                if(notificationDataList.get(i).getStatus().equals("readed")){
                    if(check.isEmpty()){
                        notificationUnreadDataList.add(notificationDataList.get(i));
                        check.add(i);
                    }else{
                        for (int j = 0; j < check.size(); j++){
                            if(check.get(j) == i){
                                test = false;
                            }
                        }
                        if(test){
                            notificationUnreadDataList.add(notificationDataList.get(i));
                            check.add(i);
                        }
                    }
                }
            }
            if (!notificationUnreadDataList.isEmpty()){
                mNotificationRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
                notificationAdapter = new NotificationAdapter(notificationUnreadDataList, this, mContext);
                mNotificationRecycleView.setAdapter(notificationAdapter);
            }
        });
        mClose.setOnClickListener(v -> {
            mNotificationDetail.setVisibility(View.GONE);
        });
    }
    private void replaceFragment(Fragment fragment)
    {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void getPosition(int position) {
        mNotificationDetail.setVisibility(View.VISIBLE);
        mNotificationContent.setText(notificationDataList.get(position).getContent());
    }
    private void dataInitialize() {
        notificationDataList = new ArrayList<>();
        notificationUnreadDataList = new ArrayList<>();
        notification_content = new String[]{
                "Nike Air",
                "Nike Air Force 1.",
                "Nike Free.",
                "Nike React.",
                "Nike ZoomX.",
                "Space Hippie.",
                "Adidas Ultraboost",
                "Adidas NMD",
        };
        notification_time = new String[]{
                "11-02-2023"
        };

        for (int i = 0; i < notification_content.length; i++){
            NotificationData notificationData = new NotificationData(notification_time[0] , notification_content[i], "unread");
            notificationDataList.add(notificationData);
        }
    }
}
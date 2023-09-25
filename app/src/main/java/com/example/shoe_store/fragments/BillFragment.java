package com.example.shoe_store.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.shoe_store.R;
import com.example.shoe_store.data.ShoeData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class BillFragment extends Fragment {
    //
    FloatingActionButton mGoBackBtn;
    Button mApplyBillBtn;
    TextView mBillName, mBillPrice;
    ImageView mShoeImage;
    private List<ShoeData> shoeDataList;
    //Vars
    private String shoe_name, shoe_brand;
    private float shoe_rate;
    private int shoe_price, quantity, brand_image, shoe_image;
    //
    public BillFragment(String shoe_name, int shoe_price, float shoe_rate, int quantity, String shoe_brand, int brand_image, int shoe_image){
        this.shoe_name = shoe_name;
        this.shoe_rate = shoe_rate;
        this.shoe_price = shoe_price;
        this.quantity = quantity;
        this.shoe_brand = shoe_brand;
        this.brand_image = brand_image;
        this.shoe_image = shoe_image;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bill, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mGoBackBtn = view.findViewById(R.id.go_back);
        mBillName = view.findViewById(R.id.Bill_shoe_name_label);
        mBillPrice = view.findViewById(R.id.Bill_shoe_price_label);
        mShoeImage = view.findViewById(R.id.Shoe_image_bill);
        mApplyBillBtn = view.findViewById(R.id.Apply_buy_shoe_bill_btn);

        Glide.with(this.getContext()).load(shoe_image).into(mShoeImage);
        mBillName.setText("Name of production: " + shoe_name);
        mBillPrice.setText("Total price: " +CalculatePrice(shoe_price, quantity));

        mGoBackBtn.setOnClickListener(v -> {
            replaceFragment(new ProductInformationFragment(shoe_name, shoe_price, shoe_rate, shoe_brand, brand_image, shoe_image));
        });
        mApplyBillBtn.setOnClickListener(v -> {
            Toast.makeText(this.getContext(), "Success", Toast.LENGTH_SHORT).show();
        });
    }
    public String CalculatePrice(int price, int quantity){
        int result = price*quantity;
        return Integer.toString(result);
    }
    private void replaceFragment(Fragment fragment)
    {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }
}
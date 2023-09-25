package com.example.shoe_store.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.gridlayout.widget.GridLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.shoe_store.R;
import com.example.shoe_store.data.ShoeData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ProductInformationFragment extends Fragment {
    //Elements
    private static final String TAG = "Whereiscardview?";
    TextView mName, mPrice, mRate;
    ImageButton mPlusbtn, mMinorbtn;
    EditText mQuantitytxt;
    Button mBuyBtn;
    GridLayout mBuyArea;
    FloatingActionButton mStartBuy, mGoBackBtn;
    ImageView mShoeImage;
    //
    //Vars
    int quantity = 0;
    private String shoe_name, shoe_brand;
    private float shoe_rate;
    private int shoe_price, brand_image, shoe_image;
    public ProductInformationFragment(String shoe_name, int shoe_price, float shoe_rate, String shoe_brand, int brand_image, int shoe_image) {
        this.shoe_name = shoe_name;
        this.shoe_price = shoe_price;
        this.shoe_rate = shoe_rate;
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
        return inflater.inflate(R.layout.fragment_product_information, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mName = view.findViewById(R.id.Shoe_name_label);
        mPrice = view.findViewById(R.id.Shoe_price_label);
        mRate = view.findViewById(R.id.Shoe_rate_label);
        mPlusbtn = view.findViewById(R.id.Quantity_add_btn);
        mMinorbtn = view.findViewById(R.id.Quantity_minor_btn);
        mQuantitytxt = view.findViewById(R.id.Shoe_quantity);
        mBuyBtn = view.findViewById(R.id.Apply_buy_shoe_btn);
        mBuyArea = view.findViewById(R.id.Buy_area);
        mStartBuy = view.findViewById(R.id.Start_buy_btn);
        mGoBackBtn = view.findViewById(R.id.go_back);
        mShoeImage = view.findViewById(R.id.Scale_shoe_image);

        Glide.with(this.getContext()).load(shoe_image).into(mShoeImage);
        mName.setText(shoe_name);
        mPrice.setText("$" + Integer.toString(shoe_price));
        mRate.setText(Float.toString(shoe_rate));

        mGoBackBtn.setOnClickListener(v -> {
            replaceFragment(new HomeFragment());
        });

        mStartBuy.setOnClickListener(v -> {
            mBuyArea.setVisibility(View.VISIBLE);
        });

        mPlusbtn.setOnClickListener(v -> {
            quantity++;
            mQuantitytxt.setText(Integer.toString(quantity));
        });
        mMinorbtn.setOnClickListener(v -> {
            if(quantity!=0){
                quantity--;
            }else{
                quantity = 0;
            }
            mQuantitytxt.setText(Integer.toString(quantity));
        });
        mBuyBtn.setOnClickListener(v -> {
            if(quantity != 0){
                replaceFragment(new BillFragment(shoe_name, shoe_price, shoe_rate, quantity, shoe_brand, brand_image, shoe_image));
            }else{
                Toast.makeText(this.getContext(), "You have not set quantity!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void replaceFragment(Fragment fragment)
    {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }
}
package com.example.shoe_store.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shoe_store.R;
import com.example.shoe_store.adapters.BrandsAdapter;
import com.example.shoe_store.adapters.ItemsAdapter;
import com.example.shoe_store.data.ShoeData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements ItemsAdapter.GetPosition, BrandsAdapter.GetBrandPosition {
    //
    private static final String TAG = "Whereiscardview?";
    FloatingActionButton mSearchBtn, mMenuBtn, mSaveBtn;
    EditText mSearchText;
    TextView name, price, rate;
    ImageView shoePic , brandPic;
    Context mContext;
    private RecyclerView recyclerView, brandsRecyclerView;
    private ItemsAdapter itemsAdapter;
    private BrandsAdapter brandsAdapter;
    private List<ShoeData> shoeDataList;
    //Vars
    private String[] shoe_name;
    private String[] shoe_price;
    private  String[] shoe_rate;
    private  String[] shoe_brand;
    private int[] brand_image;
    private int[] shoe_image;
    boolean isSearchVisible;

    public HomeFragment(){

    }
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataInitialize();

        recyclerView = view.findViewById(R.id.ItemsRecycleView);
        brandsRecyclerView = view.findViewById(R.id.Brands_Recycle);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        itemsAdapter = new ItemsAdapter(shoeDataList, this, mContext);
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
        recyclerView.setAdapter(itemsAdapter);

        brandsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, true));
        brandsAdapter = new BrandsAdapter(shoeDataList, this, mContext);
        brandsRecyclerView.setAdapter(brandsAdapter);

        mSearchBtn = view.findViewById(R.id.search_button);
        mMenuBtn = view.findViewById(R.id.expanded_menu);
        mSearchText = view.findViewById(R.id.Search_text);
        mSaveBtn = view.findViewById(R.id.save_shoe_btn);

        isSearchVisible = false;

        mSearchBtn.setOnClickListener(v -> {
            String TempName = String.valueOf(mSearchText.getText());
            FindShoe(TempName);
        });
        mMenuBtn.setOnClickListener(v -> {

        });
    }
    public void FindShoe(String TempName){
        List<ShoeData> temp = new ArrayList<>();
        for (int i = 0; i < shoe_name.length; i++) {
            if (TempName.equals(shoe_name[i]) || TempName.equals(shoe_brand[i])) {
                temp.add(shoeDataList.get(i));
            }
        }
        if (temp.size() != 0) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            itemsAdapter = new ItemsAdapter(temp, this, mContext);
            recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
            recyclerView.setAdapter(itemsAdapter);
            Log.d(TAG, "Exit " + TempName);
        }
        if (TempName == null || temp.size() == 0) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            itemsAdapter = new ItemsAdapter(shoeDataList, this, mContext);
            recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
            recyclerView.setAdapter(itemsAdapter);
            Log.d(TAG, "Empty " + TempName);
        }
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void dataInitialize() {
        shoeDataList = new ArrayList<>();
        shoe_name = new String[]{
                "Nike Air",
                "Nike Air Force 1.",
                "Nike Free.",
                "Nike React.",
                "Nike ZoomX.",
                "Space Hippie.",
                "Adidas Ultraboost",
                "Adidas NMD",
        };
        shoe_brand = new String[]{
                getString(R.string.Adidas),
                getString(R.string.Puma),
                getString(R.string.New_Balance),
                getString(R.string.Balenciaga),
                getString(R.string.Converse),
                getString(R.string.Hey_Dude),
                getString(R.string.Skechers),
                getString(R.string.Vans),
        };
        brand_image = new int[]{
                R.drawable.adidas,
                R.drawable.puma,
                R.drawable.newbalance,
                R.drawable.balenciaga,
                R.drawable.converse,
                R.drawable.heydude,
                R.drawable.skechers,
                R.drawable.vans,
        };
        shoe_image = new int[]{
                R.drawable.dummy_shoe,
                R.drawable.shoe1,
                R.drawable.shoe2,
                R.drawable.shoe3,
                R.drawable.shoe4,
                R.drawable.shoe5,
                R.drawable.shoe6,
                R.drawable.shoe7,
        };
        for (int i = 0; i < shoe_name.length; i++){
            ShoeData shoeData = new ShoeData(shoe_name[i] , 100, 5.0F, shoe_brand[i], brand_image[i], shoe_image[i]);
            shoeDataList.add(shoeData);
        }
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
        replaceFragment(new ProductInformationFragment(shoeDataList.get(position).getName(),
                shoeDataList.get(position).getPrice(),
                shoeDataList.get(position).getRate(),
                shoeDataList.get(position).getBrand(),
                shoeDataList.get(position).getBrandImage(),
                shoeDataList.get(position).getShoeImage()));
    }

    @Override
    public void getBrandPosition(int position) {
        FindShoe(shoeDataList.get(position).getBrand());
    }
}
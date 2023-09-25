package com.example.shoe_store;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.example.shoe_store.databinding.ActivityMainBinding;
import com.example.shoe_store.fragments.BagFragment;
import com.example.shoe_store.fragments.HomeFragment;
import com.example.shoe_store.fragments.NotificationFragment;
import com.example.shoe_store.fragments.ProfileFragment;
import com.example.shoe_store.fragments.SaveFragment;

public class MainActivity extends AppCompatActivity{
    ActivityMainBinding binding;
    //Vars
    private String username, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        password = intent.getStringExtra("password");

        replaceFragment(new HomeFragment());
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if(itemId == R.id.home){
                replaceFragment(new HomeFragment());
            }
            if(itemId == R.id.bag){
                replaceFragment(new BagFragment());
            }
            if (itemId == R.id.save){
                replaceFragment(new SaveFragment());
            }
            if (itemId == R.id.notifications){
                replaceFragment(new NotificationFragment());
            }
            if(itemId == R.id.profile){
                replaceFragment(new ProfileFragment(username, password));
            }
            return true;
        });
    }
    private void replaceFragment(Fragment fragment)
    {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }
}
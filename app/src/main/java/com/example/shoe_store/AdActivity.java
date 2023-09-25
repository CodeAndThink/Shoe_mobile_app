package com.example.shoe_store;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.example.shoe_store.databinding.ActivityAdBinding;
import com.example.shoe_store.databinding.ActivityBeginBinding;

public class AdActivity extends AppCompatActivity {
    ActivityAdBinding binding;
    private String loginUsername, loginPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);
        binding = ActivityAdBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        loginUsername = intent.getStringExtra("username");
        loginPassword = intent.getStringExtra("password");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(AdActivity.this, MainActivity.class);
                intent.putExtra("username", loginUsername);
                intent.putExtra("password", loginPassword);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}
package com.example.shoe_store;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.shoe_store.databinding.ActivityBeginBinding;
import com.example.shoe_store.databinding.ActivityLoginBinding;

public class BeginActivity extends AppCompatActivity {
    ActivityBeginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin);
        binding = ActivityBeginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.BeginLoginBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });
        binding.BeginSignupBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, SignUpActivity.class);
            startActivity(intent);
        });
    }
}
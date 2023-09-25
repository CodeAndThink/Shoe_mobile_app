package com.example.shoe_store;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.shoe_store.databinding.ActivityLoginBinding;
import com.example.shoe_store.databinding.ActivitySignUpBinding;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class SignUpActivity extends AppCompatActivity {
    ActivitySignUpBinding binding;
    private static final String TAG = "yeaah?";
    private String URL = "https://10.0.2.2/ShoeDataBase/register.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        handleSSLHandshake();

        binding.AgreeWithUserRuleLabel.setOnClickListener(v -> {
            Toast toast = Toast.makeText(getBaseContext(), binding.signupUsername.getText().toString().trim() + " " + binding.signupPassword.getText().toString().trim(), Toast.LENGTH_SHORT);
            toast.show();
        });
        binding.TextLoginAccount.setOnClickListener(v -> {
            Intent intent = new Intent(this.getBaseContext(), LoginActivity.class);
            startActivity(intent);
        });
        binding.SignupBtn.setOnClickListener(v -> {
            if(binding.AgreeWithUserRule.isChecked() && !binding.signupUsername.getText().toString().trim().isEmpty() && !binding.signupPassword.getText().toString().trim().isEmpty()
                    && binding.signupRepassword.getText().toString().trim().equals(binding.signupPassword.getText().toString().trim())){
                StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("Success")) {
                            new CountDownTimer(3000, 1000) {

                                public void onTick(long millisUntilFinished) {

                                    Toast toast = Toast.makeText(getBaseContext(), "Return to login screen in 3 seconds", Toast.LENGTH_SHORT);
                                    toast.show();
                                }

                                public void onFinish() {
                                    Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                                    startActivity(intent);
                                }
                            }.start();
                        } else if (response.equals("Failure")) {
                            Toast.makeText(getBaseContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getBaseContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                        Log.e(TAG, error.toString().trim());
                    }
                }) {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> data = new HashMap<>();
                        data.put("username", binding.signupUsername.getText().toString().trim());
                        data.put("password", binding.signupPassword.getText().toString().trim());
                        return data;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);
            }else{
                if(binding.signupUsername.getText().toString().equals("")){
                    Toast.makeText(this.getBaseContext(), "Please enter username", Toast.LENGTH_SHORT).show();
                }
                if(binding.signupPassword.getText().toString().equals("")){
                    Toast.makeText(this.getBaseContext(), "Please enter password", Toast.LENGTH_SHORT).show();
                }
                if(binding.signupRepassword.getText().toString().equals("")){
                    Toast.makeText(this.getBaseContext(), "Please re-enter password", Toast.LENGTH_SHORT).show();
                }
                if(!binding.AgreeWithUserRule.isChecked()){
                    Toast.makeText(this.getBaseContext(), "You have not agree with user rule", Toast.LENGTH_SHORT).show();
                }
                if(!binding.signupRepassword.getText().toString().equals(binding.signupPassword.getText().toString())){
                    Toast.makeText(this.getBaseContext(), "Password mismatch", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(this.getBaseContext(), "Unknown error!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @SuppressLint("TrulyRandom")
    public static void handleSSLHandshake() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
        } catch (Exception ignored) {
        }
    }
}
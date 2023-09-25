package com.example.shoe_store.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.shoe_store.LoginActivity;
import com.example.shoe_store.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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

public class ProfileFragment extends Fragment {
    //
    private static final String TAG = "yeaah?";
    private String URL = "https://10.0.2.2/ShoeDataBase/update.php";
    TextView mUsername, mPassword, mChangeUsername, mChangePassword;
    FloatingActionButton mChangeInformationBtn;
    CardView mChangeInformationCard;
    Button mChangeInformationCardApplyBtn;
    ImageView mProfileUserImage, mProfileChangeUserImage;
    // Vars
    private String username, password;

    public ProfileFragment() {
        // Required empty public constructor
    }
    public ProfileFragment(String username, String password){
        this.password = password;
        this.username = username;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUsername = view.findViewById(R.id.Profile_username);
        mPassword = view.findViewById(R.id.Profile_password);
        mChangeInformationBtn = view.findViewById(R.id.Profile_change_information_btn);
        mChangeInformationCard = view.findViewById(R.id.Profile_change_information);
        mChangeInformationCardApplyBtn = view.findViewById(R.id.Profile_change_apply_btn);
        mChangeUsername = view.findViewById(R.id.Profile_change_username);
        mChangePassword = view.findViewById(R.id.Profile_change_password);
        mProfileUserImage = view.findViewById(R.id.Profile_user_image);
        mProfileChangeUserImage = view.findViewById(R.id.Profile_change_user_image);

        //Glide.with(this.getContext()).load(shoe_image).into(mProfileUserImage);
        mUsername.setText(username);
        mPassword.setText(password);

        mChangeUsername.setText(username);
        mChangePassword.setText(password);

        handleSSLHandshake();

        mChangeInformationBtn.setOnClickListener(v -> {
            mChangeInformationCard.setVisibility(View.VISIBLE);
        });
        mChangeInformationCardApplyBtn.setOnClickListener(v -> {
            if(!mChangeUsername.getText().toString().trim().isEmpty() && !mChangePassword.getText().toString().trim().isEmpty()){
                StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("Success")) {
                            Toast.makeText(getContext(), "Changing information successfully!", Toast.LENGTH_SHORT).show();
                            mUsername.setText(mChangeUsername.getText().toString());
                            mPassword.setText(mChangePassword.getText().toString());
                            username = mChangeUsername.getText().toString();
                            password = mChangePassword.getText().toString();

                            mChangeInformationCard.setVisibility(View.GONE);
                        } else if (response.equals("Failure")) {
                            Toast.makeText(getContext(), "Something went wrong!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                        Log.e(TAG, error.toString().trim());
                    }
                }) {
                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> data = new HashMap<>();
                        data.put("username", mChangeUsername.getText().toString().trim());
                        data.put("password", mChangePassword.getText().toString().trim());
                        return data;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                requestQueue.add(stringRequest);
            }else{
                if(mChangeUsername.getText().toString().equals("")){
                    Toast.makeText(this.getContext(), "Please enter username", Toast.LENGTH_SHORT).show();
                }
                if(mChangePassword.getText().toString().equals("")){
                    Toast.makeText(this.getContext(), "Please enter password", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(this.getContext(), "Unknown error!", Toast.LENGTH_SHORT).show();
            }
            Toast.makeText(this.getContext(), "Successfully changing information !", Toast.LENGTH_SHORT);
        });
        mProfileChangeUserImage.setOnClickListener(v -> {
            imageChooser();
        });
    }
    void imageChooser() {

        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), 200);
    }

    // this function is triggered when user
    // selects the image from the imageChooser
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == 200) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    mProfileChangeUserImage.setImageURI(selectedImageUri);
                    mProfileUserImage.setImageURI(selectedImageUri);
                }
            }
        }
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
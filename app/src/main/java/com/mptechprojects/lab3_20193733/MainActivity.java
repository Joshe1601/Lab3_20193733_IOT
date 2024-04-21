package com.mptechprojects.lab3_20193733;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mptechprojects.lab3_20193733.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isConnected = networkInfo != null && networkInfo.isConnected();

        if (!isConnected) {
            Toast.makeText(this, "No internet connection, Error Toast", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "Internet connection available, Success Toast", Toast.LENGTH_LONG).show();
        }


        binding.btnStart.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PrimeNumbersActivity.class);
            startActivity(intent);
        });

        binding.btnSearch.setOnClickListener(v -> {
            String searchText = binding.etSearch.getText().toString();

            binding.etSearch.getText().clear();
            Intent intent = new Intent(MainActivity.this, FilmsActivity.class);
            intent.putExtra("search", searchText);
            startActivity(intent);
        });


    }
}
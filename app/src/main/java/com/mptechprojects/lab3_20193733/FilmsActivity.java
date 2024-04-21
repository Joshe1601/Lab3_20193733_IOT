package com.mptechprojects.lab3_20193733;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mptechprojects.lab3_20193733.databinding.ActivityFilmsBinding;
import com.mptechprojects.lab3_20193733.databinding.ActivityMainBinding;

public class FilmsActivity extends AppCompatActivity {

    ActivityFilmsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityFilmsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toast.makeText(this, "Films Activity", Toast.LENGTH_LONG).show();


    }
}
package com.mptechprojects.lab3_20193733;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mptechprojects.lab3_20193733.databinding.ActivityMainBinding;
import com.mptechprojects.lab3_20193733.databinding.ActivityPrimeNumbersBinding;

public class PrimeNumbersActivity extends AppCompatActivity {

    ActivityPrimeNumbersBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPrimeNumbersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toast.makeText(this, "Prime Numbers Activity", Toast.LENGTH_LONG).show();


    }
}
package com.mptechprojects.lab3_20193733;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.mptechprojects.lab3_20193733.databinding.ActivityMainBinding;
import com.mptechprojects.lab3_20193733.databinding.ActivityPrimeNumbersBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PrimeNumbersActivity extends AppCompatActivity {

    ActivityPrimeNumbersBinding binding;
    PrimeNumberApiService primeNumberApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPrimeNumbersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toast.makeText(this, "Prime Numbers Activity", Toast.LENGTH_LONG).show();

        CounterViewModel counterViewModel = new ViewModelProvider(PrimeNumbersActivity.this).get(CounterViewModel.class);

        primeNumberApiService = new Retrofit.Builder()
                .baseUrl("https://prime-number-api.onrender.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PrimeNumberApiService.class);
        primeNumberApiService.getPrimeNumbers(999, 1).enqueue(new Callback<List<PrimeNumber>>() {
            @Override
            public void onResponse(@NonNull Call<List<PrimeNumber>> call, @NonNull Response<List<PrimeNumber>> response) {
                if (response.isSuccessful()) {
                    List<PrimeNumber> primeNumbers = response.body();
                    for (PrimeNumber primeNumber : primeNumbers) {
                        Log.d("PrimeNumbersActivity", "Prime number: " + primeNumber.getNumber() + " Order: " + primeNumber.getOrder());
                    }
                } else {
                    Toast.makeText(PrimeNumbersActivity.this, "Failed to fetch prime numbers", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<PrimeNumber>> call, @NonNull Throwable t) {
                Toast.makeText(PrimeNumbersActivity.this, "Failed to fetch prime numbers", Toast.LENGTH_LONG).show();
            }
        });


    }
}
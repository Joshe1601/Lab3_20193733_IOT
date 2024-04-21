package com.mptechprojects.lab3_20193733;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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
import java.util.ListIterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PrimeNumbersActivity extends AppCompatActivity {

    private ActivityPrimeNumbersBinding binding;
    private PrimeNumberApiService primeNumberApiService;
    private CounterViewModel counterViewModel;
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private boolean isPaused = false;

    private static final String PREFS_NAME = "PrimeNumbersPrefs";
    private static final String COUNTER_KEY = "counter";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPrimeNumbersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        int savedCounter = prefs.getInt(COUNTER_KEY, 0);

        Toast.makeText(this, "Prime Numbers Activity", Toast.LENGTH_LONG).show();

        counterViewModel = new ViewModelProvider(this).get(CounterViewModel.class);
        counterViewModel.getCounter().observe(this, counter -> {
            binding.viewPrimeNumber.setText(String.valueOf(counter));
        });

        executorService = Executors.newSingleThreadExecutor();
        primeNumberApiService = new Retrofit.Builder()
                .baseUrl("https://prime-number-api.onrender.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PrimeNumberApiService.class);
        fetchPrimeNumbers(true);

        binding.btnPauseRestart.setOnClickListener(v -> {
            if (!isPaused) {
                pauseCounter();
            } else {
                restartCounter(false);
            }
        });

        binding.btnUpDown.setOnClickListener(v -> {
            boolean isAscending = binding.btnUpDown.getText().equals("Ascender");
            if (isAscending) {
                binding.btnUpDown.setText("Descender");
            } else {
                binding.btnUpDown.setText("Ascender");
            }
            restartCounter(isAscending);
        });
    }

    private void fetchPrimeNumbers(boolean isAscending) {
        primeNumberApiService.getPrimeNumbers(999, 1).enqueue(new Callback<List<PrimeNumber>>() {
            @Override
            public void onResponse(@NonNull Call<List<PrimeNumber>> call, @NonNull Response<List<PrimeNumber>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    startCounter(response.body(), isAscending);
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

    private void startCounter(List<PrimeNumber> primeNumbers, boolean isAscending) {
        executorService.execute(() -> {
            if (isAscending) {
                for (PrimeNumber primeNumber : primeNumbers) {
                    if (!isPaused) {
                        counterViewModel.getCounter().postValue(primeNumber.getNumber());
                        sleepThread();
                    } else {
                        return;
                    }
                }
            } else {
                ListIterator<PrimeNumber> iterator = primeNumbers.listIterator(primeNumbers.size());
                while (iterator.hasPrevious()) {
                    if (!isPaused) {
                        PrimeNumber primeNumber = iterator.previous();
                        counterViewModel.getCounter().postValue(primeNumber.getNumber());
                        sleepThread();
                    } else {
                        return;
                    }
                }
            }
        });
    }

    private void sleepThread() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
    }


    private void pauseCounter() {
        isPaused = true;
        binding.btnPauseRestart.setText("Restart");
        binding.btnUpDown.setVisibility(View.GONE);
    }

    private void restartCounter(boolean isAscending) {
        isPaused = false;
        binding.btnPauseRestart.setText("Pause");
        binding.btnUpDown.setVisibility(View.VISIBLE);

        executorService.shutdownNow();
        executorService = Executors.newSingleThreadExecutor();
        fetchPrimeNumbers(isAscending);
    }

    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        editor.putInt(COUNTER_KEY, Integer.parseInt(binding.viewPrimeNumber.getText().toString()));
        editor.apply();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdownNow();
    }

}

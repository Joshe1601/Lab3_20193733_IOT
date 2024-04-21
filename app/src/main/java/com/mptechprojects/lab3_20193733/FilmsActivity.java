package com.mptechprojects.lab3_20193733;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.mptechprojects.lab3_20193733.databinding.ActivityFilmsBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FilmsActivity extends AppCompatActivity {

    ActivityFilmsBinding binding;
    FilmApiService filmApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        binding = ActivityFilmsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.backButton.setEnabled(false);

        binding.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            binding.backButton.setEnabled(isChecked);
        });

        binding.backButton.setOnClickListener(v -> {
            finish();
        });

        Toast.makeText(this, "Films Activity", Toast.LENGTH_LONG).show();

        String search = getIntent().getStringExtra("search");

        filmApiService = new Retrofit.Builder()
                .baseUrl("https://www.omdbapi.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(FilmApiService.class);

        filmApiService.getFilms("bf81d461",search).enqueue(new Callback<Film>() {
            @Override
            public void onResponse(@NonNull Call<Film> call, @NonNull Response<Film> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Film film = response.body();
                    Log.d("FilmsActivity", "Film data: " + film.toString());
                    binding.titleFilm.setText(film.getTitle());
                    binding.director.setText(film.getDirector());
                    binding.actors.setText(film.getActors());
                    binding.date.setText(film.getReleased());
                    binding.genre.setText(film.getGenre());
                    binding.writers.setText(film.getWriter());
                    binding.plot.setText(film.getPlot());
                    binding.imdb.setText(film.getRatings().get(0).getValue());
                    binding.rt.setText(film.getRatings().get(1).getValue());
                    binding.metaCritic.setText(film.getRatings().get(2).getValue());
                } else {
                    Log.e("FilmsActivity", "Response not successful or body is null");
                }

            }

            @Override
            public void onFailure(@NonNull Call<Film> call, @NonNull Throwable t) {
                Toast.makeText(FilmsActivity.this, "Error fetching film data", Toast.LENGTH_LONG).show();
            }
        });

        binding.checkBox.setOnClickListener(v -> {

        });

    }

}
package com.example.lab3_20267541;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lab3_20267541.databinding.ActivityMainBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Redirección al Contador
        binding.btnContador.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Contador.class);
            startActivity(intent);
        });

        // Comprobar Conexión
        binding.button4.setOnClickListener(v -> {
            if (hayConexion()) {
                Toast.makeText(this, "Conexión a internet exitosa", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error: Sin conexión a internet", Toast.LENGTH_SHORT).show();
            }
        });

        // Búsqueda OMDB API
        binding.btnBuscar.setOnClickListener(v -> {
            String imdbId = binding.edtPelicula.getText().toString().trim();
            if (!imdbId.isEmpty()) {
                buscarPelicula(imdbId);
            } else {
                Toast.makeText(this, "Ingrese un ID de IMDb válido", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean hayConexion() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm != null ? cm.getActiveNetworkInfo() : null;
        return ni != null && ni.isConnected();
    }

    private void buscarPelicula(String imdbId) {
        RetrofitClient.getApiService().getPelicula("bf81d461", imdbId).enqueue(new Callback<Pelicula>() {
            @Override
            public void onResponse(Call<Pelicula> call, Response<Pelicula> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getTitle() != null) {
                    Intent intent = new Intent(MainActivity.this, DetallePelicula.class);
                    intent.putExtra("pelicula", response.body());
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Película no encontrada", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Pelicula> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error de comunicación con OMDb API", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
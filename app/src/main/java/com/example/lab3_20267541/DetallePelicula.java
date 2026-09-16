package com.example.lab3_20267541;

import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.example.lab3_20267541.databinding.ActivityDetallePeliculaBinding;

public class DetallePelicula extends AppCompatActivity {

    private ActivityDetallePeliculaBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetallePeliculaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Pelicula pelicula = (Pelicula) getIntent().getSerializableExtra("pelicula");
        if (pelicula != null) {
            binding.edtNombrePelicula.setText(pelicula.getTitle());
            binding.edtAnioPelicula.setText(pelicula.getYear());
        }

        binding.btnRegresar.setOnClickListener(v -> mostrarDialogoConfirmacion());
    }

    @Override
    public void onBackPressed() {
        mostrarDialogoConfirmacion();
    }

    private void mostrarDialogoConfirmacion() {
        new AlertDialog.Builder(this)
                .setMessage("¿Desea volver al menú principal?")
                .setPositiveButton("Sí", (dialog, which) -> finish())
                .setNegativeButton("No", (dialog, which) -> dialog.dismiss())
                .setCancelable(false)
                .show();
    }
}
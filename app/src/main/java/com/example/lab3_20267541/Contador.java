package com.example.lab3_20267541;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.lab3_20267541.databinding.ActivityContadorBinding;

public class Contador extends AppCompatActivity {

    private ActivityContadorBinding binding;
    private ContadorViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityContadorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(ContadorViewModel.class);

        // Actualización de la vista
        viewModel.getContadorLiveData().observe(this, valor -> {
            if (valor > 0) {
                binding.edtValorContado.setText(String.valueOf(valor));
            }
        });

        binding.button3.setOnClickListener(v -> viewModel.iniciarContador());
        binding.button2.setOnClickListener(v -> finish());
    }
}
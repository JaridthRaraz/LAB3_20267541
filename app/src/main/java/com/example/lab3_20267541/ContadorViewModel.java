package com.example.lab3_20267541;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ContadorViewModel extends ViewModel {

    private final MutableLiveData<Integer> contadorLiveData = new MutableLiveData<>(0);
    private boolean enEjecucion = false;

    public LiveData<Integer> getContadorLiveData() {
        return contadorLiveData;
    }

    public void iniciarContador() {
        if (enEjecucion) return;

        enEjecucion = true;
        new Thread(() -> {
            try {
                for (int i = 1; i <= 20; i++) {
                    contadorLiveData.postValue(i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                enEjecucion = false;
            }
        }).start();
    }
}
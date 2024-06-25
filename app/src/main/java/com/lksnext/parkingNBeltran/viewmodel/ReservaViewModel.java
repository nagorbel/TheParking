package com.lksnext.parkingNBeltran.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.lksnext.parkingNBeltran.data.DataRepository;
import com.lksnext.parkingNBeltran.domain.Reserva;

public class ReservaViewModel extends ViewModel {
    private DataRepository dataRepository;
    private MutableLiveData<Boolean> reservaCreada;

    public ReservaViewModel() {
        dataRepository = new DataRepository();
        reservaCreada = new MutableLiveData<>();
    }

    public LiveData<Boolean> getReservaCreada() {
        return reservaCreada;
    }

    public void crearReserva(Reserva reserva) {
        reservaCreada = (MutableLiveData<Boolean>) dataRepository.crearReserva(reserva);
    }


}

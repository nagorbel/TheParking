package com.lksnext.parkingNBeltran.view.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.lksnext.parkingNBeltran.R;
import com.lksnext.parkingNBeltran.domain.Hora;
import com.lksnext.parkingNBeltran.domain.Plaza;
import com.lksnext.parkingNBeltran.domain.Reserva;
import com.lksnext.parkingNBeltran.viewmodel.ReservaViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class ReservaActivity extends AppCompatActivity {
    private ReservaViewModel reservaViewModel;
    private Spinner spinnerTipoPlaza;
    private TimePicker timePickerInicio;
    private TimePicker timePickerFin;
    private Button buttonCrearReserva;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva);

        reservaViewModel = new ViewModelProvider(this).get(ReservaViewModel.class);

        spinnerTipoPlaza = findViewById(R.id.spinner_tipo_plaza);
        timePickerInicio = findViewById(R.id.timepicker_inicio);
        timePickerFin = findViewById(R.id.timepicker_fin);
        buttonCrearReserva = findViewById(R.id.button_crear_reserva);

        buttonCrearReserva.setOnClickListener(view -> {
            String tipoPlaza = spinnerTipoPlaza.getSelectedItem().toString();
            long inicio = timePickerInicio.getHour() * 3600 + timePickerInicio.getMinute() * 60;
            long fin = timePickerFin.getHour() * 3600 + timePickerFin.getMinute() * 60;

            Plaza plaza = new Plaza(UUID.randomUUID().toString(), tipoPlaza);
            Hora hora = new Hora(inicio, fin);
            String fecha = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
            String usuario = "usuario_ejemplo"; // Deberías obtener el usuario autenticado

            Reserva reserva = new Reserva(fecha, usuario, UUID.randomUUID().toString(), plaza, hora);
            reservaViewModel.crearReserva(reserva);
        });

        reservaViewModel.getReservaCreada().observe(this, success -> {
            if (Boolean.TRUE.equals(success)) {
                Toast.makeText(this, "Reserva creada exitosamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error al crear la reserva", Toast.LENGTH_SHORT).show();
            }
        });
    }

}

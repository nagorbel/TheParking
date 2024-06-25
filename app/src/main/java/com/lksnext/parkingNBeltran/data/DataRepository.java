package com.lksnext.parkingNBeltran.data;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.lksnext.parkingNBeltran.domain.Callback;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.lksnext.parkingNBeltran.domain.Reserva;

import java.util.HashMap;
import java.util.Map;


public class DataRepository {

    private static DataRepository instance;
    private final FirebaseAuth mAuth;
    private final FirebaseFirestore firestore;
    public DataRepository(){
        this.mAuth = FirebaseAuth.getInstance();
        this.firestore = FirebaseFirestore.getInstance();
    }

    //Creación de la instancia en caso de que no exista.
    public static synchronized DataRepository getInstance(){
        if (instance==null){
            instance = new DataRepository();
        }
        return instance;
    }

    //Petición del login.
    public void login(String email, String pass, Callback callback){
        try {
            //Realizar petición
            callback.onSuccess();
        } catch (Exception e){
            callback.onFailure();
        }
    }

    public Task<AuthResult> registerUser(String email, String password) {
        return mAuth.createUserWithEmailAndPassword(email, password);
    }

    public Task<Void> saveUserName(String userId, String name) {
        Map<String, Object> user = new HashMap<>();
        user.put("name", name);
        return firestore.collection("users").document(userId).set(user);
    }

    public FirebaseUser getCurrentUser() {
        return mAuth.getCurrentUser();
    }

    public LiveData<Boolean> crearReserva(Reserva reserva) {
        MutableLiveData<Boolean> successLiveData = new MutableLiveData<>();

        if ((reserva.getHoraFin().getHoraFin()) - (reserva.getHoraInicio().getHoraInicio()) > 9 * 60 * 60 * 1000) {
            successLiveData.setValue(false); // Más de 9 horas
            return successLiveData;
        }

        if (reserva.getHoraInicio().getHoraInicio() > System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000) {
            successLiveData.setValue(false); // Más de 7 días
            return successLiveData;
        }

        firestore.collection("reservas")
                .add(reserva)
                .addOnSuccessListener(documentReference -> successLiveData.setValue(true))
                .addOnFailureListener(e -> successLiveData.setValue(false));

        return successLiveData;
    }

}


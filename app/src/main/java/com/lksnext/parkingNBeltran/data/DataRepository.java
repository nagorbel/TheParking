package com.lksnext.parkingNBeltran.data;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.lksnext.parkingNBeltran.domain.Callback;

import androidx.annotation.NonNull;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

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

}


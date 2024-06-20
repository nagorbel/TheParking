package com.lksnext.parkingNBeltran.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class LoginViewModel extends ViewModel {

    // Aquí puedes declarar los LiveData y métodos necesarios para la vista de inicio de sesión
    MutableLiveData<Boolean> logged = new MutableLiveData<>(null);
    private Executor executor = Executors.newSingleThreadExecutor();

    public LiveData<Boolean> isLogged(){
        return logged;
    }

    // Initialize Firebase Auth
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    public void loginUser(String email, String password) {

        // Sign in an existing user
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(executor, task -> {
                    if (task.isSuccessful()) {
                        // Sign in success, update UI with the signed-in user's information
                        FirebaseUser user = mAuth.getCurrentUser();
                        logged.postValue(Boolean.TRUE);
                        Log.i("signIn","OK");

                    } else {
                        // If sign in fails, display a message to the user.
                        logged.postValue(Boolean.FALSE);
                        Log.e("signIn","NOK");
                    }
                });
    }
}


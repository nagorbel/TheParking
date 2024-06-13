package com.lksnext.parkingplantilla.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseUser;
import com.lksnext.parkingplantilla.domain.Callback;
import com.google.firebase.auth.FirebaseAuth;

import java.util.concurrent.Executor;

// Initialize Firebase Auth

public class RegisterViewModel extends ViewModel {
    // Sign up a new user
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    public void signUp(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
           .addOnCompleteListener((Executor) this, task -> {
        if (task.isSuccessful()) {
            // Sign up success, update UI with the signed-in user's information
            FirebaseUser user = mAuth.getCurrentUser();
        } else {
            // If sign up fails, display a message to the user.
            Log.i("NO FUNCIONA","signUp");
        }
    });


    }


}

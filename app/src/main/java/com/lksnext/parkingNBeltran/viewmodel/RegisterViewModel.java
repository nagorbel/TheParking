package com.lksnext.parkingNBeltran.viewmodel;
import android.util.Log;
import androidx.lifecycle.ViewModel;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;
import java.util.concurrent.Executor;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.google.firebase.auth.FirebaseAuthException;
import com.lksnext.parkingNBeltran.data.DataRepository;


// Initialize Firebase Auth

public class RegisterViewModel extends ViewModel {

    private final DataRepository firebaseRepository;
    private final MutableLiveData<FirebaseUser> userLiveData;
    private final MutableLiveData<String> errorLiveData;

    public RegisterViewModel() {
        this.firebaseRepository = new DataRepository();
        this.userLiveData = new MutableLiveData<>();
        this.errorLiveData = new MutableLiveData<>();
    }

    public LiveData<FirebaseUser> getUserLiveData() {
        return userLiveData;
    }

    public LiveData<String> getErrorLiveData() {
        return errorLiveData;
    }

    public void signUp(String email, String password, String name) {
        firebaseRepository.registerUser(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = firebaseRepository.getCurrentUser();
                        if (user != null) {
                            firebaseRepository.saveUserName(user.getUid(), name)
                                    .addOnCompleteListener(saveTask -> {
                                        if (saveTask.isSuccessful()) {
                                            userLiveData.setValue(user);
                                        } else {
                                            errorLiveData.setValue("Failed to save user name: " + Objects.requireNonNull(saveTask.getException()).getMessage());
                                        }
                                    });
                        }
        } else {
            // If sign up fails, display a message to the user.
            Log.i("NO FUNCIONA","signUp");
            String errorMessage = task.getException() instanceof FirebaseAuthException
                    ? ((FirebaseAuthException) task.getException()).getErrorCode()
                    : Objects.requireNonNull(task.getException()).getMessage();
            errorLiveData.setValue("Registration failed: " + errorMessage);
        }
    });


    }


}

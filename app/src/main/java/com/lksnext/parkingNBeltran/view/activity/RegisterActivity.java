package com.lksnext.parkingNBeltran.view.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseAuth;
import com.lksnext.parkingNBeltran.databinding.ActivityRegisterBinding;
import com.lksnext.parkingNBeltran.viewmodel.RegisterViewModel;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private RegisterViewModel registerViewModel;

    private FirebaseAuth mAuth;
    private EditText emailEditText, passwordEditText;
    private Button registroButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Asignamos la vista/interfaz de registro
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Asignamos el viewModel de register
        registerViewModel = new ViewModelProvider(this).get(RegisterViewModel.class);


        String email = binding.emailText.getText().toString();
        String password = binding.passwordText.getText().toString();
        registerViewModel.signUp(email, password);




    }
}
package com.lksnext.parkingNBeltran.view.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.auth.FirebaseAuth;
import com.lksnext.parkingNBeltran.databinding.ActivityRegisterBinding;
import com.lksnext.parkingNBeltran.viewmodel.RegisterViewModel;

import java.util.Objects;

public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private RegisterViewModel registerViewModel;
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

        // Observamos los cambios en el LiveData del usuario
        registerViewModel.getUserLiveData().observe(this, user -> {
            if (user != null) {
                Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show();
                registerViewModel.getUserLiveData().observe(this, userLiveData -> {
                    Toast.makeText(this, "Bienvenido " + user.getDisplayName() + "!", Toast.LENGTH_SHORT).show();
                    // Navegar a la siguiente actividad o actualizar la UI
                });
            }
        });

        // Observamos los cambios en el LiveData de errores
        registerViewModel.getErrorLiveData().observe(this, errorMessage -> {
            if (errorMessage != null) {
                Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
            }
        });

        // Agregamos un listener al botón de registro
        binding.btnRegister.setOnClickListener(v -> {
            String name = Objects.requireNonNull(binding.nametext.getText()).toString().trim();
            String email = Objects.requireNonNull(binding.emailText.getText()).toString().trim();
            String password = Objects.requireNonNull(binding.passwordText.getText()).toString().trim();

            if (isValidName(name) && isValidEmail(email) && isValidPassword(password)) {
                registerViewModel.signUp(email, password, name);
            } else {
                showValidationError();
            }
        });

    }
    private boolean isValidName(String name) {
        return name != null && !name.isEmpty();
    }

    private boolean isValidEmail(String email) {
        return email != null && !email.isEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    private boolean isValidPassword(String password) {
        return password != null && password.length() >= 6; // Ejemplo de longitud mínima
    }

    private void showValidationError() {
        Toast.makeText(this, "Email o contraseña inválidos", Toast.LENGTH_SHORT).show();
    }
}
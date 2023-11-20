package com.example.ecorecicla;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

// MainActivity.java
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editTextUsername = findViewById(R.id.editTextUsername);
        final EditText editTextPassword = findViewById(R.id.editTextPassword);
        Button buttonLogin = findViewById(R.id.buttonLogin);
        TextView textViewRegister = findViewById(R.id.textViewRegister);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                // Validaciones
                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                    Toast.makeText(MainActivity.this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                } else {
                    // Verificar credenciales con el archivo "usuarios.txt"
                    if (validateCredentials(username, password)) {
                        // Credenciales válidas, ir a la página principal
                        startActivity(new Intent(MainActivity.this, HomeActivity.class));
                    } else {
                        // Credenciales no válidas
                        Toast.makeText(MainActivity.this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        // Agregado para ir a la página de registro
        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
            }
        });
    }

    // Método para validar credenciales
    private boolean validateCredentials(String enteredUsername, String enteredPassword) {
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(getFileStreamPath("usuarios.txt")));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(":");
                String username = parts[0].trim();
                String password = parts[1].trim();

                // Verificar si las credenciales coinciden con alguna línea en el archivo
                if (enteredUsername.equals(username) && enteredPassword.equals(password)) {
                    bufferedReader.close();
                    return true;
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}

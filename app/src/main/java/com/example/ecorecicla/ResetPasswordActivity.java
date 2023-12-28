package com.example.ecorecicla;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ResetPasswordActivity extends AppCompatActivity {

    private EditText editTextUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Recordar Contraseña");
        }

         editTextUsername =findViewById(R.id.editTextUsername);
         Button buttonResetPassword = findViewById(R.id.buttonResetPassword);

        buttonResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetPassword();
            }
        });
    }

    private void resetPassword() {
        String username = editTextUsername.getText().toString().trim();

        if (username.isEmpty()) {
            editTextUsername.setError("Ingrese su nombre de usuario");
            editTextUsername.requestFocus();
            return;
        }

        boolean usuarioEncontrado = buscarUsuarioPorUsername(username);
        if (usuarioEncontrado) {
            // Lógica para restablecer la contraseña para el usuario en el archivo de texto y notificar al usuario.


        } else {
            // Usuario no encontrado, mostrar mensaje de error
            Toast.makeText(ResetPasswordActivity.this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean buscarUsuarioPorUsername(String username) {
        try {
            File file = new File(getFilesDir(), "usuarios.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String linea;
            while ((linea = br.readLine()) != null) {

                String[] partes = linea.split("\\|");
                if (partes.length == 2 && partes[0].equals(username)) {
                    // Usuario encontrado
                    br.close();
                    return true;
                }
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}

package com.example.mylogin5b

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mylogin5b.databinding.ActivityMainBinding
import com.example.mylogin5b.MainActivityRegistrar


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Iniciar MainActivityRegistrar al hacer clic en el texto de registrarse
        binding.tvRegistrarse.setOnClickListener {
            val intentRegistrar = Intent(this, MainActivityRegistrar::class.java)
            startActivity(intentRegistrar)
        }

        // Verificar credenciales al hacer clic en el botón de login
        binding.btnLogin.setOnClickListener {
            val loginInput = binding.txtUsuario.text.toString()
            val passInput = binding.txtPassword.text.toString()

            val dbHelper = DBHelperUsuario(this)
            val db = dbHelper.readableDatabase

            val selectionArgs = arrayOf(loginInput, passInput)
            val cursor = db.rawQuery("SELECT * FROM usuarios WHERE userLogin = ? AND userPass = ?", selectionArgs)

            // Comprobar si se encontró un usuario con esas credenciales
            if (cursor.moveToFirst()) {
                Toast.makeText(this, "El usuario es correcto", Toast.LENGTH_SHORT).show()
                // Aquí puedes iniciar otra actividad si el inicio de sesión es correcto.
            } else {
                Toast.makeText(this, "Credenciales inválidas", Toast.LENGTH_SHORT).show()
            }
            cursor.close()
            db.close()
        }
    }
}


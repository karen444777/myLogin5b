package com.example.mylogin5b

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mylogin5b.databinding.ActivityMainRegistrarBinding

class MainActivityRegistrar : AppCompatActivity() {

    private lateinit var bindingRegis: ActivityMainRegistrarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingRegis = ActivityMainRegistrarBinding.inflate(layoutInflater)
        setContentView(bindingRegis.root)

        val dbServicios = DBHelperUsuario(this)
        val db = dbServicios.writableDatabase

        bindingRegis.btnCancelar.setOnClickListener {
            val intentPrinci = Intent(this, MainActivity::class.java)
            startActivity(intentPrinci)
        }

        bindingRegis.btnRegistrar.setOnClickListener {
            val user = bindingRegis.txtUsuario.text.toString()
            val pass = bindingRegis.txtPassword.text.toString()
            val corr = bindingRegis.txtCorreo.text.toString()
            val nom = bindingRegis.txtNombre.text.toString()

            val newReg = ContentValues().apply {
                put("userLogin", user)
                put("userPass", pass)
                put("userEmail", corr)
                put("userNombre", nom) // Asegúrate que este campo coincide con la definición en la base de datos
            }

            val res = db.insert("usuarios", null, newReg)
            db.close()

            if (res > 0) {
                Toast.makeText(this, "Usuario registrado con éxito", Toast.LENGTH_LONG).show()
                // Limpiar las cajas de texto
                bindingRegis.txtUsuario.text.clear()
                bindingRegis.txtPassword.text.clear()
                bindingRegis.txtCorreo.text.clear()
                bindingRegis.txtNombre.text.clear()

                val intentPrinci = Intent(this, MainActivity::class.java)
                startActivity(intentPrinci)
            } else {
                Toast.makeText(this, "No se pudo registrar el usuario", Toast.LENGTH_LONG).show()
            }
        }
    }
}





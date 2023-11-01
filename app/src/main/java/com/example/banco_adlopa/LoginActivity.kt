package com.example.banco_adlopa

import android.content.Intent
import android.os.Bundle
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import com.example.banco_adlopa.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private var usuario: String? = null
    private var pass: String? = null

    private  lateinit var  binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnEntrar.setOnClickListener {
            usuario = binding.editUser.text.toString()
            pass = binding.editPass.text.toString()

            if(comprobarDni(usuario) && comprobarPass(pass)) {
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("usuario", usuario)
                intent.putExtra("password",pass)
                startActivity(intent)
            }
            else {
                val mensaje: String = "Usuario o contraseña introducidos incorrectos"
                Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()

            }
        }

        binding.btnSalir.setOnClickListener {
            finish()
        }
    }

    //Hay que mejorar las funciones para comprobar el DNI y el Password
    fun comprobarDni(usuario:String?): Boolean {
        if(usuario != null && usuario.length  != 9)
            return false

        return true
    }

    fun comprobarPass(pass:String?): Boolean{
        if(pass != null && pass.length == 0)
            return false

        return true
    }


}
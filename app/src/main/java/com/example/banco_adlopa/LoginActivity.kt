package com.example.banco_adlopa

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.banco_adlopa.bd.MiBancoOperacional
import com.example.banco_adlopa.databinding.ActivityLoginBinding
import com.example.banco_adlopa.pojo.Cliente

class LoginActivity : AppCompatActivity() {

    private  lateinit var  binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnEntrar.setOnClickListener {
            val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(this)

            // Introducimos los datos como si fuera la pantalla inicial
            var cliente = Cliente()
            cliente.setNif(binding.editUser.text.toString())
            cliente.setClaveSeguridad(binding.editPass.text.toString())


            // Logueamos al cliente
            val clienteLogeado = mbo?.login(cliente) ?: -1
            if(clienteLogeado== -1) {
                Toast.makeText(this,"El cliente no existe en la base de datos", Toast.LENGTH_LONG).show()
            }else{
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("Cliente", clienteLogeado)
                startActivity(intent)
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
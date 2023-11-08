package com.example.banco_adlopa

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.banco_adlopa.databinding.ActivityMainBinding
import com.example.banco_adlopa.pojo.Cliente
import java.io.Serializable

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Recuperar el valor del cliente
        val cliente = intent.getSerializableExtra("Cliente") as Cliente
        binding.dniUser.text = cliente.getNombre()

        binding.btnTransfer.setOnClickListener{
            val intent = Intent(this,TransferActivity::class.java)
            intent.putExtra("Cliente",cliente)
            startActivity(intent)
        }

        binding.btnGlobal.setOnClickListener{
            val intent = Intent(this,GlobalPositionActivity::class.java)
            intent.putExtra("Cliente",cliente)
            startActivity(intent)
        }

        binding.btnMoves.setOnClickListener{
            val intent = Intent(this,MovementsActivity::class.java)
            intent.putExtra("Cliente",cliente)
            startActivity(intent)
        }

        binding.btnExit.setOnClickListener {
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
        }
    }
}
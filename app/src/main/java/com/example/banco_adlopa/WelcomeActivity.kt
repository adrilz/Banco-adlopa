package com.example.banco_adlopa

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.banco_adlopa.databinding.WelcomeActivityBinding

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: WelcomeActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = WelcomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnEntrar.setOnClickListener {

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)


        }
    }
}

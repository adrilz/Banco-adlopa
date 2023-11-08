package com.example.banco_adlopa

import android.R
import android.os.Bundle
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.banco_adlopa.databinding.ActivityTransferBinding


class TransferActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTransferBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTransferBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Cuentas para el spinner
        val cuentas = arrayOf("ES60-2100-0418-40-1234567891",
                            "ES60-2100-0418-40-9876543210",
                            "ES60-2100-0418-40-2468135791",
                            "ES60-2100-0418-40-1357902468")

        val divisas = arrayOf("€","$")

        //Crear adapters
        val adapterCuentasOri = ArrayAdapter(this, R.layout.simple_spinner_item, cuentas)
        val adapterDivisas= ArrayAdapter(this, R.layout.simple_spinner_item, divisas)
        val adapterCuentasDes = ArrayAdapter(this, R.layout.simple_spinner_item, cuentas)

        val spinnerCuentasOri = binding.cuentaOrigen
        val spinnerDivisas = binding.divisas
        val spinnerCuentasDes = binding.cuentaDestino

        // Especificar el diseño del menú desplegable
        adapterCuentasOri.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        adapterDivisas.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        adapterCuentasDes.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Asignar el ArrayAdapter al Spinner
        spinnerCuentasOri.adapter = adapterCuentasOri
        spinnerDivisas.adapter = adapterDivisas
        spinnerCuentasDes.adapter = adapterCuentasDes

        binding.radioPropia.setOnClickListener() {
            if(binding.radioPropia.isChecked) {
                binding.cuentaDestino.visibility = VISIBLE
                binding.introCuentaAjena.visibility = INVISIBLE
            }
        }

        binding.radioAjena.setOnClickListener() {
            if(binding.radioAjena.isChecked) {
                binding.cuentaDestino.visibility = INVISIBLE
                binding.introCuentaAjena.visibility = VISIBLE
            }
        }

        binding.btnSend.setOnClickListener(){

            var cuentaDestino: String? = null
            if(binding.radioPropia.isChecked) cuentaDestino = spinnerCuentasDes.selectedItem.toString()

            else cuentaDestino = binding.introCuentaAjena.toString()

            val mensaje:String = ("Cuenta origen: \n"+
                                spinnerCuentasOri.selectedItem.toString() +"\n " +
                                "Cuenta Destino: \n"+
                                cuentaDestino)

            var toast = Toast.makeText(this,mensaje,Toast.LENGTH_LONG)
            toast.show()
        }

    }
}


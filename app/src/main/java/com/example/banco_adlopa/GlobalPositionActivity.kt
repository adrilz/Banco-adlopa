package com.example.banco_adlopa

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.banco_adlopa.databinding.ActivityGlobalPositionBinding
import com.example.banco_adlopa.pojo.Cuenta
import com.example.banco_adlopa.adapters.CuentaAdapter
import com.example.banco_adlopa.bd.MiBancoOperacional
import com.example.banco_adlopa.pojo.Cliente

class GlobalPositionActivity : AppCompatActivity() {

    private lateinit var cuentaAdapter: CuentaAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var itemDecoration: DividerItemDecoration

    private lateinit var binding: ActivityGlobalPositionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGlobalPositionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Conecta con la BD
        val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(this)
        //Recuperar el valor del cliente
        var clienteLog = intent.getSerializableExtra("Cliente") as Cliente

        //Obtiene el metodo getCuentas del cliente logueado que esta en CuentaDAO
        var listaCuentas: ArrayList<Cuenta> = mbo?.getCuentas(clienteLog) as ArrayList<Cuenta>

        cuentaAdapter = CuentaAdapter(listaCuentas)
        linearLayoutManager = LinearLayoutManager(this)
        itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)


        binding.recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = cuentaAdapter
            addItemDecoration(itemDecoration)
        }
    }

}
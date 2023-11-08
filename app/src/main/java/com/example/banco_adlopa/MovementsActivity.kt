package com.example.banco_adlopa

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.banco_adlopa.adapters.MovementsAdapter
import com.example.banco_adlopa.bd.MiBancoOperacional
import com.example.banco_adlopa.databinding.ActivityMovementsBinding
import com.example.banco_adlopa.pojo.Cliente
import com.example.banco_adlopa.pojo.Cuenta
import com.example.banco_adlopa.pojo.Movimiento

class MovementsActivity : AppCompatActivity() {

    private lateinit var movementsAdapter: MovementsAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var itemDecoration: DividerItemDecoration

    private lateinit var binding: ActivityMovementsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMovementsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Conecta con la BD
        val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(this)
        //Recuperar el valor del cliente
        val clienteLog = intent.getSerializableExtra("Cliente") as Cliente

        //Obtiene el metodo getCuentas del cliente logueado que esta en CuentaDAO
        val listaCuentas: ArrayList<Cuenta> = mbo?.getCuentas(clienteLog) as ArrayList<Cuenta>

        val listaCuentasSpin =  ArrayList<String>()

        //Bucle forEach para recorrer las cuentas
        listaCuentas.forEach()
        {
            //Recoge en un string la informacion de la cuenta que queremos
            val cuentaCompleta :String = it.getBanco().toString()+"-"+
                    it.getSucursal().toString()+"-"+it.getDc().toString()+"-"+
                    it.getNumeroCuenta().toString()

            //Se añade al nuevo array de strings para el spiner la informacion de las cuentas
            listaCuentasSpin.add(cuentaCompleta)
        }

            //Pone todos los valores que se usan en el numero de cuenta en una misma frase

            //añade la informacion a un nuevo array de strings


        //Se usa el array de strings creado en el bucle anterior para el spinner
        val adapterSpin = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,listaCuentasSpin)

        val spinner = binding.selecCuenta

        // Especificar el diseño del menú desplegable
        adapterSpin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = adapterSpin

        linearLayoutManager = LinearLayoutManager(this)
        itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)

        // Manejar la selección de elementos del Spinner
        spinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val listaMovimientos: ArrayList<Movimiento> = mbo.getMovimientos(listaCuentas.get(position)) as ArrayList<Movimiento>

                movementsAdapter = MovementsAdapter(listaMovimientos)

                binding.recyclerView.apply {
                    layoutManager = linearLayoutManager
                    adapter = movementsAdapter
                    addItemDecoration(itemDecoration)
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Acciones a realizar si no se selecciona nada
                binding.selecCuenta.toString()
            }
        })
    }


}
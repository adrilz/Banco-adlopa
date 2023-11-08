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
        var clienteLog = intent.getSerializableExtra("Cliente") as Cliente

        //Obtiene el metodo getCuentas del cliente logueado que esta en CuentaDAO
        var listaCuentas: ArrayList<Cuenta> = mbo?.getCuentas(clienteLog) as ArrayList<Cuenta>

        var listaNumerosCuenta =  ArrayList<String>()
        var index = 0

        while(index < listaCuentas.size){
            //Pone todos los valores que se usan en el numero de cuenta en una misma frase
            var cuentaCompleta :String = listaCuentas.get(index).getBanco().toString()+"-"+
                    listaCuentas.get(index).getSucursal().toString()+"-"+listaCuentas.get(index).getDc().toString()+"-"+
                    listaCuentas.get(index).getNumeroCuenta().toString()

            //añade la informacion a un nuevo array de strings
            listaNumerosCuenta.add(cuentaCompleta)
            index++
        }


        //Se usa el array de strings creado en el bucle anterior para el spinner
        val adapterSpin = ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,listaNumerosCuenta)

        val spinner = binding.selecCuenta

        // Especificar el diseño del menú desplegable
        adapterSpin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = adapterSpin

        linearLayoutManager = LinearLayoutManager(this)
        itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)

        // Manejar la selección de elementos del Spinner
        spinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                var listaMovimientos: ArrayList<Movimiento> = mbo?.getMovimientos(listaCuentas?.get(position)) as ArrayList<Movimiento>

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
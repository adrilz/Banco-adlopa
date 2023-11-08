package com.example.banco_adlopa.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.banco_adlopa.R
import com.example.banco_adlopa.databinding.ItemCuentasBinding
import com.example.banco_adlopa.pojo.Cuenta

class CuentaAdapter(private val cuentas: ArrayList<Cuenta>): RecyclerView.Adapter<CuentaAdapter.ViewHolder>(){
        inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
            val binding = ItemCuentasBinding.bind(view) //Vinculamos la vista a nuestro adapter
        }

        private lateinit var context: Context

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {//Inflar la vista en el Recycler))
            context = parent.context
            val view = LayoutInflater.from(context).inflate(R.layout.item_cuentas,parent,false)
            return ViewHolder(view)
        }

        override fun getItemCount(): Int = cuentas.size


        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val cuenta = cuentas.get(position)
            with(holder){
                binding.numCuenta.text = cuenta.getBanco().toString()+"-"+cuenta.getSucursal().toString()+"-"+cuenta.getDc().toString()+"-"+cuenta.getNumeroCuenta().toString()
                binding.cantSaldo.text = cuenta.getSaldoActual().toString()
            }
        }

}
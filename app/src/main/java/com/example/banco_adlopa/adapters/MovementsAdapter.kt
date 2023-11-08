package com.example.banco_adlopa.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.banco_adlopa.R
import com.example.banco_adlopa.databinding.ItemCuentasBinding
import com.example.banco_adlopa.databinding.ItemMovementsBinding
import com.example.banco_adlopa.pojo.Cuenta
import com.example.banco_adlopa.pojo.Movimiento

class MovementsAdapter(private val movimientos: ArrayList<Movimiento>): RecyclerView.Adapter<MovementsAdapter.ViewHolder>(){
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding = ItemMovementsBinding.bind(view) //Vinculamos la vista a nuestro adapter
    }

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {//Inflar la vista en el Recycler))
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_movements,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = movimientos.size


    override fun onBindViewHolder(holder: MovementsAdapter.ViewHolder, position: Int) {
        val movimiento = movimientos.get(position)
        with(holder){
            binding.nameMov.text = movimiento.getDescripcion().toString()
            binding.dataMov.text = movimiento.getFechaOperacion().toString()+" Importe: "+movimiento.getImporte().toString()
        }
    }

}
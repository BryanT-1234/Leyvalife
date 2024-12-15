package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HistorialAdapter(private val historialList: List<HistorialItem>) :
    RecyclerView.Adapter<HistorialAdapter.HistorialViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistorialViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_historial, parent, false)
        return HistorialViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistorialViewHolder, position: Int) {
        val item = historialList[position]
        holder.tvFecha.text = "Fecha: ${item.fecha}"
        holder.tvPeso.text = "Peso: ${item.peso} kg"
        holder.tvIMC.text = "IMC: ${String.format("%.2f", item.imc)}"
    }

    override fun getItemCount(): Int = historialList.size

    class HistorialViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvFecha: TextView = itemView.findViewById(R.id.tvFecha)
        val tvPeso: TextView = itemView.findViewById(R.id.tvPeso)
        val tvIMC: TextView = itemView.findViewById(R.id.tvIMC)
    }
}

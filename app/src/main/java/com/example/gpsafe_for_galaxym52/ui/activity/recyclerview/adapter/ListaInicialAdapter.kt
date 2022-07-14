package com.example.gpsafe_for_galaxym52.ui.activity.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gpsafe_for_galaxym52.R
import com.example.gpsafe_for_galaxym52.model.clientes

class ListaInicialAdapter(
    private val context: Context,
    clientes: List<clientes> = emptyList(),
) : RecyclerView.Adapter<ListaInicialAdapter.ViewHolder>() {

    private val clientes = clientes.toMutableList()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun vincula(cliente: clientes) {
            val nome = itemView.findViewById<TextView>(R.id.busca_item_nome)
            nome.text = cliente.nome
            val data = itemView.findViewById<TextView>(R.id.busca_item_data)
            data.text = cliente.data
            val valor = itemView.findViewById<TextView>(R.id.busca_item_valor)
            valor.text = cliente.valor
            val cpf = itemView.findViewById<TextView>(R.id.busca_item_cpf)
            cpf.text = cliente.cpf

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.busca_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cliente = clientes[position]
        holder.vincula(cliente)
    }

    override fun getItemCount(): Int = clientes.size
    fun atualiza(clientes: List<clientes>) {
        this.clientes.clear()
        this.clientes.addAll(clientes)
        notifyDataSetChanged()
    }

}

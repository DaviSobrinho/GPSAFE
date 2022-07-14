package com.example.gpsafe_for_galaxym52.ui.activity.recyclerview.adapter

import android.content.Context
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.example.gpsafe_for_galaxym52.R
import com.example.gpsafe_for_galaxym52.model.clientes

class TelaClienteAdapter(

    private val context: Context,
    cliestespesquisa: List<clientes> =emptyList(),
    clientes: List<clientes> = emptyList(),

) : RecyclerView.Adapter<TelaClienteAdapter.ViewHolder>() {

    private val clientes = clientes.toMutableList()
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun vincula(cliente: clientes) {
            val nome = itemView.findViewById<EditText>(R.id.edittextnome)
            nome.text = Editable.Factory.getInstance().newEditable(cliente.nome)
            val cpf = itemView.findViewById<EditText>(R.id.edittextcpf)
            cpf.text = Editable.Factory.getInstance().newEditable(cliente.cpf)
            val valor = itemView.findViewById<EditText>(R.id.edittextvalor)
            valor.text = Editable.Factory.getInstance().newEditable(cliente.valor)
            val data = itemView.findViewById<EditText>(R.id.edittextdata)
            data.text = Editable.Factory.getInstance().newEditable(cliente.data)
            val telefone = itemView.findViewById<EditText>(R.id.edittexttelefone)
            telefone.text = Editable.Factory.getInstance().newEditable(cliente.telefone)
            val contrato = itemView.findViewById<EditText>(R.id.edittextcontrato)
            contrato.text = Editable.Factory.getInstance().newEditable(cliente.contrato)
        }
    }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val inflater = LayoutInflater.from(context)
            val view = inflater.inflate(R.layout.editexttelacliente, parent, false)
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
package com.example.gpsafe_for_galaxym52.ui.activity

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gpsafe_for_galaxym52.R
import com.example.gpsafe_for_galaxym52.database.AppDatabase
import com.example.gpsafe_for_galaxym52.model.NameViewModel
import com.example.gpsafe_for_galaxym52.ui.activity.recyclerview.adapter.ListaInicialAdapter
import com.example.gpsafe_for_galaxym52.ui.activity.recyclerview.adapter.TelaClienteAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    val model: NameViewModel by viewModels()
    val adapter = ListaInicialAdapter(context = this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_main)
        configurafabpesquisar()
        configuraRecyclerView()
        configurafab()
        val nameObserver = Observer<String> { newName ->
            // Update the UI, in this case, a TextView.
            val pesquisar = findViewById<EditText>(R.id.edittext_pesquisar)
            pesquisar.text = Editable.Factory.getInstance().newEditable(newName)

        }
        model.currentName.observe(this, nameObserver)



    }

    override fun onResume() {
        super.onResume()
        val db = AppDatabase.instancia(this)
        val ClienteDao =db.clienteDao()
        adapter.atualiza(ClienteDao.buscaTodos())

    }
    private fun configurafabpesquisar() {

        val fabpesquisar = findViewById<FloatingActionButton>(R.id.botaopesquisar)
        fabpesquisar.setOnClickListener {
            vaiParaTelaCliente()
        }
    }

    private fun vaiParaTelaCliente() {
        val nameObserver2 = findViewById<EditText>(R.id.edittext_pesquisar)
        val nameObserver3 = nameObserver2.text.toString()
        val intent = Intent(this,telaClienteActivity::class.java)
        intent.putExtra("Chave", nameObserver3)
        val intent1 = Intent(this,TelaClienteAdapter::class.java)
        intent1.putExtra("Chave", nameObserver3)
        startActivity(intent)
    }
    private fun configurafab() {
        val fab = findViewById<FloatingActionButton>(R.id.botaoadicionarcliente)
        fab.setOnClickListener {
            vaiParaFormularioCliente()
        }
    }

    private fun vaiParaFormularioCliente() {
        val intent = Intent(this, FormularioClienteActivity::class.java)
        startActivity(intent)
    }

    private fun configuraRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
}
package com.example.gpsafe_for_galaxym52.ui.activity

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gpsafe_for_galaxym52.R
import com.example.gpsafe_for_galaxym52.database.AppDatabase
import com.example.gpsafe_for_galaxym52.model.clientes
import com.example.gpsafe_for_galaxym52.ui.activity.recyclerview.adapter.TelaClienteAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

private const val TAG = "Detalhes Cliente"

class telaClienteActivity : AppCompatActivity(R.layout.activity_tela_cliente) {
    private val adapter = TelaClienteAdapter(context = this)

    override fun onCreate(savedInstanceState: Bundle?) {
        setContentView(R.layout.activity_tela_cliente)
        super.onCreate(savedInstanceState)
        criaBotaoHome()
        configurafab()
        configuraRecyclerView()
        configurafabpesquisar()
        val pesquisatelacliente = intent.getStringExtra("Chave")
        val pesquisatelacliente2 = findViewById<EditText>(R.id.edittext_pesquisar)
        val textView = findViewById<EditText>(R.id.edittextcpf)

        val db = AppDatabase.instancia(this)
        val ClienteDao =db.clienteDao()
        adapter.atualiza(ClienteDao.findClientePorCPF(cpf = pesquisatelacliente))
        handleIntent(intent)


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_opcoes_cliente, menu)
        /*val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        if (menu != null) {
            (menu.findItem(R.id.activity_main_pesquisar).actionView as SearchView).apply {
                setSearchableInfo(searchManager.getSearchableInfo(componentName))
            }
        }*/

        return true
        // Associate searchable configuration with the SearchView

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
        val intent1 = Intent(this, TelaClienteAdapter::class.java)
        intent1.putExtra("Chave", nameObserver3)
        startActivity(intent)
    }
    private fun criaBotaoHome() {
        val fab = findViewById<FloatingActionButton>(R.id.botaohome)
        fab.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
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

    override fun onResume() {
        super.onResume()
        configuraRecyclerView()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val addnome = findViewById<EditText>(R.id.edittextnome)
        val nome = addnome.text.toString()
        val addcpf = findViewById<EditText>(R.id.edittextcpf)
        val cpfemtetexto = addcpf.text.toString()
        val cpf = cpfemtetexto
        val addtelefone = findViewById<EditText>(R.id.edittexttelefone)
        val telefoneemtexto = addtelefone.text.toString()
        val telefone = telefoneemtexto
        val adddata = findViewById<EditText>(R.id.edittextdata)
        val dataemtexto = adddata.text.toString()
        val data = dataemtexto
        val addvalor = findViewById<EditText>(R.id.edittextvalor)
        val valoremtexto = addvalor.text.toString()
        val valor = valoremtexto
        val addcontrato = findViewById<EditText>(R.id.edittextcontrato)
        val contratoemtexto = addcontrato.text.toString()
        val contrato = contratoemtexto
        val db = AppDatabase.instancia(this)
        val clienteDao = db.clienteDao()
        when(item.itemId){
            R.id.remover -> {
                clienteDao.remove(
                    clientes(cpf = cpf,
                    nome = nome,
                    telefone = telefone,
                    contrato = contrato,
                    data = data,
                    valor = valor,
                )
                )
                Log.i(TAG, "onOptionsItensSelected: remover")
                val text = "O cliente foi removido!"
                val duration = Toast.LENGTH_SHORT

                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }R.id.editar -> {
            clienteDao.atualiza(
                clientes(cpf = cpf,
                nome = nome,
                telefone = telefone,
                contrato = contrato,
                data = data,
                valor = valor,)


            )
            Log.i(TAG, "onOptionsItensSelected: editar")
            val text = "Os dados do cliente foram atualizados!"
            val duration = Toast.LENGTH_SHORT

            val toast = Toast.makeText(applicationContext, text, duration)
            toast.show()
        }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun handleIntent(intent: Intent) {

        if (Intent.ACTION_SEARCH == intent.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            //use the query to search your data somehow
        }
    }
    private fun configuraRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewTelaCliente)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter= adapter
    }



















}

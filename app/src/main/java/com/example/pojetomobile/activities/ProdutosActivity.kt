package com.example.parcialapp.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parcialapp.databinding.ActivityProdutosBinding
import com.example.parcialapp.entities.ListaDeCompras
import com.example.parcialapp.entities.Produto

class ProdutosActivity : AppCompatActivity(){

    private lateinit var binding: ActivityProdutosBinding
    private lateinit var adapter: ProdutoAdapterActivity
    private lateinit var listaDeCompras: ListaDeCompras
    private var produtos: MutableList<Produto>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        listaDeCompras = intent.getSerializableExtra("listaDeCompras") as ListaDeCompras
        produtos = listaDeCompras.getListaProdutos() as? MutableList<Produto> ?: mutableListOf()

        binding = ActivityProdutosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nomeLista.text = listaDeCompras.getNome()

        adapter = ProdutoAdapterActivity(produtos ?: mutableListOf(), ::onProdutoItemClicked)
        val layoutManager = LinearLayoutManager(this)

        binding.recyclerViewProdutos.adapter = adapter
        binding.recyclerViewProdutos.layoutManager = layoutManager

        binding.floatingActionButton4.setOnClickListener {
            val intent = Intent(this, AdProdutoActivity::class.java)
            intent.putExtra("listaDeCompras", listaDeCompras) // Passa a mesma instância
            startActivityForResult(intent, 1) // Usa startActivityForResult para receber o resultado
        }
    }

    private fun onProdutoItemClicked(produto: Produto) {}

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            listaDeCompras = data?.getSerializableExtra("listaDeCompras") as ListaDeCompras
            atualizaTela() // Atualiza a tela com a lista modificada
        }
    }

    override fun onResume() {
        super.onResume()
        atualizaTela()
    }

    private fun atualizaTela() {
        // Atualiza a lista de produtos a partir da lista de compras
        produtos = listaDeCompras.getListaProdutos() as? MutableList<Produto> ?: mutableListOf()
        adapter.atualizaListaDeProdutos(produtos ?: mutableListOf())
    }
}

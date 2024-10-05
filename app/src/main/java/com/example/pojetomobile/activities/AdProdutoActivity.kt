package com.example.parcialapp.activities

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.parcialapp.databinding.ActivityAdprodutoBinding
import com.example.parcialapp.db.ListasBD
import com.example.parcialapp.entities.ListaDeCompras
import com.example.parcialapp.entities.Produto
import com.example.parcialapp.enuns.EnumCategoria
import com.example.parcialapp.enuns.EnumUnidade

class AdProdutoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdprodutoBinding
    private lateinit var listaDeCompras: ListaDeCompras

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        listaDeCompras = intent.getSerializableExtra("listaDeCompras") as ListaDeCompras

        binding = ActivityAdprodutoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val spinnerUnidade: Spinner = binding.spinner1
        val unidades = EnumUnidade.entries.toTypedArray()

        val adapter1 = ArrayAdapter(this, android.R.layout.simple_spinner_item, unidades.map { it.name })
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerUnidade.adapter = adapter1

        val spinnerCategoria: Spinner = binding.spinner2
        val categorias = EnumCategoria.entries.toTypedArray()

        val adapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_item, categorias.map { it.name })
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerCategoria.adapter = adapter2

        val quantidadePattern = "^[0-9]+$"

        binding.button4.setOnClickListener {
            val nome = binding.nomeItem.text.toString()
            val quantidade = binding.editText2.text.toString()
            val unidade = binding.spinner1.selectedItem.toString()
            val categoria = binding.spinner2.selectedItem.toString()

            if (nome.isEmpty() || nome.isBlank() || quantidade.isEmpty() || quantidade.isBlank() || unidade.isEmpty() || categoria.isEmpty()) {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Aviso")
                builder.setMessage("Preencha todos os campos!")
                builder.setPositiveButton("OK") { dialog: DialogInterface, _: Int ->
                    dialog.dismiss()
                }

                val dialog = builder.create()
                dialog.show()
            }

            else if (!quantidade.matches(quantidadePattern.toRegex())) {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Aviso")
                builder.setMessage("A quantidade deve ser um número válido e sem espaços.")
                builder.setPositiveButton("OK") { dialog: DialogInterface, _: Int ->
                    dialog.dismiss()
                }

                val dialog = builder.create()
                dialog.show()
            }

            else {
                val produto = Produto(nome, Integer.valueOf(quantidade), unidade, categoria, false)
                listaDeCompras.adProduto(produto)
                ListasBD.instance.atualizaLista(listaDeCompras)

                Toast.makeText(this, "Item adicionado: $nome", Toast.LENGTH_SHORT).show()

                val resultIntent = Intent().apply {
                    putExtra("listaDeCompras", listaDeCompras)
                }
                setResult(RESULT_OK, resultIntent)
                finish()
            }
        }
    }
}

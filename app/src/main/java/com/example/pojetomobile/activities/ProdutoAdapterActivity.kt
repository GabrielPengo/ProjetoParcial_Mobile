package com.example.parcialapp.activities

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parcialapp.databinding.ActivityProdutoadapterBinding
import com.example.parcialapp.entities.Produto

class ProdutoAdapterActivity(
    private var listaDeProdutos: List<Produto>,
    private val onClick: (Produto) -> Unit) : RecyclerView.Adapter<ProdutoAdapterActivity.ViewHolder>() {

    inner class ViewHolder(
        private val binding: ActivityProdutoadapterBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private var currentItem: Produto? = null

        init {
            itemView.setOnClickListener {
                currentItem?.let {
                    onClick(it)
                }
            }
        }

        fun bind(produto: Produto) {
            currentItem = produto
            binding.nomeProduto.text = produto.getNome()
            binding.qtdeProduto.text = produto.getQtdeUnidade();
            binding.checkBox.isChecked = produto.getComprado();
            binding.checkBox.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    produto.setComprado(true)
                } else {
                    produto.setComprado(false)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ActivityProdutoadapterBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listaDeProdutos[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = listaDeProdutos.size

    fun atualizaListaDeProdutos(produtos: List<Produto>) {
        listaDeProdutos = produtos
        notifyDataSetChanged()
    }
}
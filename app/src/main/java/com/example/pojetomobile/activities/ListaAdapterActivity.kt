package com.example.parcialapp.activities

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parcialapp.databinding.ActivityListaadapterBinding
import com.example.parcialapp.entities.ListaDeCompras

class ListaAdapterActivity(
    private var listasDeCompras: List<ListaDeCompras>,
    private val onClick: (ListaDeCompras) -> Unit) : RecyclerView.Adapter<ListaAdapterActivity.ViewHolder>() {

    inner class ViewHolder(
        private val binding: ActivityListaadapterBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private var currentItem: ListaDeCompras? = null

        init {
            itemView.setOnClickListener {
                currentItem?.let {
                    onClick(it)
                }
            }
        }

        fun bind(listaDeCompras: ListaDeCompras) {
            currentItem = listaDeCompras
            binding.nomeLista.text = listaDeCompras.getNome()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ActivityListaadapterBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listasDeCompras[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = listasDeCompras.size

    fun updateList(novaLista: List<ListaDeCompras>) {
        listasDeCompras = novaLista
        notifyDataSetChanged()
    }
}
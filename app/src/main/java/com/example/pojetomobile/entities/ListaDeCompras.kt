package com.example.parcialapp.entities

import java.io.Serializable

class ListaDeCompras (private var nome: String, private val usuario: Usuario) : Serializable {

    private var produtos: MutableList<Produto> = mutableListOf()

    fun getNome(): String {
        return nome
    }

    fun getListaProdutos(): MutableList<Produto> {
        return produtos;
    }

    fun adProduto(produto: Produto) {
        produtos.add(produto)
    }

    fun getUsuarioDaLista(): Usuario {
        return usuario
    }
}
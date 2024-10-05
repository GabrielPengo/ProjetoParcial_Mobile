package com.example.parcialapp.entities

import java.io.Serializable

class Produto(private var nome: String, private var quantidade: Int, private var unidade: String, private var categoria: String,
              private var comprado: Boolean) : Serializable {

    fun getNome(): String {
        return nome;
    }

    fun getQtdeUnidade(): String {
        return "$quantidade $unidade"
    }

    fun getCategoria(): String {
        return categoria
    }

    fun getComprado(): Boolean {
        return comprado
    }

    fun setComprado(comprado: Boolean) {
        this.comprado = comprado
    }

    fun setCategoria(categoria: String) {
        this.categoria = categoria
    }
}

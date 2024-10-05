package com.example.parcialapp.db

import com.example.parcialapp.entities.ListaDeCompras
import com.example.parcialapp.entities.Usuario

class ListasBD {

    companion object {
        var instance: ListasBD = ListasBD()
    }

    private var listaListasDeCompras: MutableList<ListaDeCompras> = mutableListOf()

    fun getListas(usuario: Usuario): List<ListaDeCompras> {
        var listaListasDeComprasAux: MutableList<ListaDeCompras> = mutableListOf()

        listaListasDeCompras.forEach { listaDeCompras ->
            if(listaDeCompras.getUsuarioDaLista().getNome() == usuario.getNome()) {
                listaListasDeComprasAux.add(listaDeCompras)
            }
        }
        return listaListasDeComprasAux
    }

    fun getLista(nome: String): ListaDeCompras? {
        listaListasDeCompras.forEach { listaDeCompras ->
            if(listaDeCompras.getNome() == nome) {
                return listaDeCompras
            }
        }
        return null
    }

    fun adLista(listaDeCompras: ListaDeCompras) {
        listaListasDeCompras.add(listaDeCompras)
    }

    fun atualizaLista(listaDeCompras: ListaDeCompras) {
        listaListasDeCompras.forEachIndexed { index, lista ->
            if (lista.getNome() == listaDeCompras.getNome()) {
                listaListasDeCompras[index] = listaDeCompras
                return
            }
        }
    }
}
package com.example.parcialapp.db

import com.example.parcialapp.entities.Usuario

class UsuariosBD {

    companion object {
        var instance: UsuariosBD = UsuariosBD()
    }

    private var listaUsuarios: MutableList<Usuario> = mutableListOf()

    fun getUsuario(email: String, senha: String): Usuario? {
        try {
            for(usuario in listaUsuarios) {
                if(usuario.getEmail() == email && usuario.getSenha() == senha) {
                    return usuario
                }
            }
            return null
        } catch (e: Exception) {
            println("Ocorreu uma exceção: ${e.message}")
        }
        return null
    }

    fun adUsuario(usuario: Usuario) {
        listaUsuarios.add(usuario)
    }
}
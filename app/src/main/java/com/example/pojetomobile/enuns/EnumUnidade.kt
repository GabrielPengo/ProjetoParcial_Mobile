package com.example.parcialapp.enuns

enum class EnumUnidade(private var descricao: String) {
    UN("unidade(s)"),
    PCT("pacote(s)"),
    KG("kg"),
    CX("caixa(s)");

    fun getDescricao(): String {
        return descricao
    }
}

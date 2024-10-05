package com.example.parcialapp.enuns

enum class EnumCategoria(private var descricao: String) {
    FRIOS("Frios"),
    DOCES("Doces"),
    PAES("Pães"),
    CARNES("Carnes"),
    COSMETICOS("Cosméticos"),
    LIMPEZA("Limpeza"),
    FRUTAS("Frutas"),
    LEGUMES("Legumes"),
    MANTIMENTOS("Mantimentos"),
    OUTROS("Outros");

    fun getDescricao(): String {
        return descricao
    }
}

package com.example.parcialapp.activities

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.parcialapp.databinding.ActivityAdlistaBinding
import com.example.parcialapp.db.ListasBD
import com.example.parcialapp.entities.ListaDeCompras
import com.example.parcialapp.entities.Usuario

class AdListaActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAdlistaBinding
    private val listaBD = ListasBD.instance
    private lateinit var usuario: Usuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        usuario = intent.getSerializableExtra("usuarioLogado") as Usuario

        binding = ActivityAdlistaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button5.setOnClickListener {
            val nome = binding.editText6.text.toString()
            if(nome.isEmpty() || nome.isBlank()) {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Aviso")
                builder.setMessage("Digite o nome da lista.")
                builder.setPositiveButton("OK") { dialog: DialogInterface, _: Int ->
                    dialog.dismiss()
                }

                val dialog = builder.create()
                dialog.show()
            }
            else {
                val listaDeCompras = ListaDeCompras(nome, usuario)
                listaBD.adLista(listaDeCompras)
                finish()
            }
        }
        binding.floatingActionButton2.setOnClickListener {
            selectImage()
        }
    }
    private fun selectImage() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivity(intent)
    }
}
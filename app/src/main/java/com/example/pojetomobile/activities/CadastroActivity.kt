package com.example.parcialapp.activities

import android.content.DialogInterface
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.parcialapp.databinding.ActivityCadastroBinding
import com.example.parcialapp.entities.Usuario
import com.example.parcialapp.db.UsuariosBD
import com.example.parcialapp.entities.ListaDeCompras

class CadastroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCadastroBinding
    val usuariosBD = UsuariosBD.instance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityCadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.regButton.setOnClickListener {
            val nome = binding.editTextNome.text.toString()
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextSenha.text.toString()
            val confPassword = binding.editTextConfSenha.text.toString()

            if(nome.isEmpty() || nome.isBlank() || email.isEmpty() || email.isBlank() || password.isEmpty() || password.isBlank() || confPassword.isEmpty() || confPassword.isBlank()) {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Aviso")
                builder.setMessage("Preencha todos os campos!")
                builder.setPositiveButton("OK") { dialog: DialogInterface, _: Int ->
                    dialog.dismiss()
                }

                val dialog = builder.create()
                dialog.show()
            }

            else if(validarEmail(email) == false) {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Aviso")
                builder.setMessage("Preencha o e-mail corretamente")
                builder.setPositiveButton("OK") { dialog: DialogInterface, _: Int ->
                    dialog.dismiss()
                }

                val dialog = builder.create()
                dialog.show()
            }

            else if(password == confPassword) {
                val listaDeCompras: MutableList<ListaDeCompras> = mutableListOf()
                val novoUsuario = Usuario(nome, email, password, listaDeCompras)
                usuariosBD.adUsuario(novoUsuario)
                Toast.makeText(this, "Cadastro feito com sucesso!: $nome", Toast.LENGTH_SHORT).show()
                finish()
            }
            else {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Aviso")
                builder.setMessage("Confirme a senha corretamente.")
                builder.setPositiveButton("OK") { dialog: DialogInterface, _: Int ->
                    dialog.dismiss()
                }

                val dialog = builder.create()
                dialog.show()
            }
        }
    }
    private fun validarEmail(email: String): Boolean {
        val emailPattern = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
        return email.matches(emailPattern.toRegex())
    }
}

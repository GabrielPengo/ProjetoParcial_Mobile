package com.example.parcialapp.activities

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.parcialapp.R
import com.example.parcialapp.databinding.ActivityLoginBinding
import com.example.parcialapp.db.UsuariosBD
import com.bumptech.glide.Glide
import com.example.parcialapp.entities.Usuario

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val usuariosBD = UsuariosBD.instance
    private var usuario: Usuario? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Glide.with(this)


        binding.loginButton.setOnClickListener {
            val email = binding.editTextEmail.text.toString()
            val password = binding.editTextPassword.text.toString()

            if(email.isEmpty() || email.isBlank() || password.isEmpty() || password.isBlank()) {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Aviso")
                builder.setMessage("Por favor, preencha todos os campos.")
                builder.setPositiveButton("OK") { dialog: DialogInterface, _: Int ->
                    dialog.dismiss()
                }

                val dialog = builder.create()
                dialog.show()
            }
            else {
                usuario = usuariosBD.getUsuario(email, password)
                if(usuario != null) {
                    val intent = Intent(this, ListasActivity::class.java)
                    intent.putExtra("usuarioLogado", usuario)
                    startActivity(intent)
                }
                else {
                    val builder = AlertDialog.Builder(this)
                    builder.setTitle("Aviso")
                    builder.setMessage("Por favor, verifique os dados inseridos ou cadastre-se.")
                    builder.setPositiveButton("OK") { dialog: DialogInterface, _: Int ->
                        dialog.dismiss()
                    }

                    val dialog = builder.create()
                    dialog.show()
                }
            }
        }

        binding.regButton.setOnClickListener {
            val intent = Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }
    }
}

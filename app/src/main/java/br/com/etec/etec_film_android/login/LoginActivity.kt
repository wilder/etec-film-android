package br.com.etec.etec_film_android.login

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import br.com.etec.etec_film_android.MainActivity
import br.com.etec.etec_film_android.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginActivity : AppCompatActivity() {

    private lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mAuth = FirebaseAuth.getInstance()

        val etLoginEmail = findViewById<EditText>(R.id.et_login_email)
        val etLoginSenha = findViewById<EditText>(R.id.et_login_senha)

        val etLoginAcessar = findViewById<Button>(R.id.et_login_acessar)
        etLoginAcessar.setOnClickListener {view ->
            signIn(view, etLoginEmail.text.toString(), etLoginSenha.text.toString())
        }

        val etLoginCriar = findViewById<Button>(R.id.et_login_criar)
        etLoginCriar.setOnClickListener {view ->
            createUser(view, etLoginEmail.text.toString(), etLoginSenha.text.toString())
        }
    }

    override fun onStart() {
        super.onStart()
        verificarUsuario(mAuth.currentUser!!)
    }

    fun verificarUsuario(currentUser: FirebaseUser) {
        if(currentUser.isEmailVerified) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    fun signIn(view: View, email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener<AuthResult> { task ->
            if(task.isSuccessful){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }else{
                showMessage(view,"Error: ${task.exception?.message}")
            }
        })
    }

    fun createUser(view: View, email: String, password: String) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, OnCompleteListener<AuthResult> { task ->
            if(task.isSuccessful){
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }else{
                showMessage(view,"Error: ${task.exception?.message}")
            }
        })
    }

    fun showMessage(view:View, message: String){

        //FirebaseAuth.getInstance().signOut()

        Snackbar.make(view, message, Snackbar.LENGTH_INDEFINITE).setAction("Action", null).show()
    }

}
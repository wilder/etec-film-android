package br.com.etec.etec_film_android.login

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import br.com.etec.etec_film_android.main.MainActivity
import br.com.etec.etec_film_android.R
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var mAuth : FirebaseAuth
    private lateinit var mAuthListener: FirebaseAuth.AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mAuth = FirebaseAuth.getInstance()

        val etLoginEmail = findViewById<EditText>(R.id.et_login_email)
        val etLoginSenha = findViewById<EditText>(R.id.et_login_senha)
        val etLoginAcessar = findViewById<Button>(R.id.et_login_acessar)
        val etLoginCriar = findViewById<Button>(R.id.et_login_criar)

        etLoginAcessar.setOnClickListener {view ->
            signIn(view, etLoginEmail.text.toString(), etLoginSenha.text.toString())
        }

        etLoginCriar.setOnClickListener {view ->
            createUser(view, etLoginEmail.text.toString(), etLoginSenha.text.toString())
        }

        mAuthListener = FirebaseAuth.AuthStateListener {
            val user = it.currentUser
            if(user != null) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }

    }

    override fun onStart() {
        super.onStart()
        mAuth.addAuthStateListener(mAuthListener)
    }

    override fun onStop() {
        super.onStop()
        if(mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener)
        }
    }

    fun signIn(view: View, email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, { task ->
            if(!task.isSuccessful){
                showMessage(view,"Error: ${task.exception?.message}")
            }
        })
    }

    fun createUser(view: View, email: String, password: String) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, { task ->
            if(!task.isSuccessful){
                showMessage(view,"Error: ${task.exception?.message}")
            }
        })
    }

    fun showMessage(view:View, message: String){
        //FirebaseAuth.getInstance().signOut()
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).setAction("Action", null).show()
    }

}
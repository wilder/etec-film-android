package br.com.etec.etec_film_android.login

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import br.com.etec.etec_film_android.main.MainActivity
import br.com.etec.etec_film_android.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var mAuth : FirebaseAuth
    private lateinit var mAuthListener: FirebaseAuth.AuthStateListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mAuth = FirebaseAuth.getInstance()


        et_login_acessar.setOnClickListener {view ->
            signIn(view, et_login_email.text.toString(), et_login_senha.text.toString())
        }

        et_login_criar.setOnClickListener {view ->
            createUser(view, et_login_email.text.toString(), et_login_senha.text.toString())
        }

        mAuthListener = FirebaseAuth.AuthStateListener {
            val user = it.currentUser
            if(user != null) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
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
package br.com.etec.etec_film_android.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.widget.EditText
import br.com.etec.etec_film_android.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvMainFilm = findViewById<RecyclerView>(R.id.rv_main_film)
//        rvMainFilm.adapter = MainAdapter(null, this)
    }
}

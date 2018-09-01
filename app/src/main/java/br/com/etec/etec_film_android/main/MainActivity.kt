package br.com.etec.etec_film_android.main

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.EditText
import br.com.etec.etec_film_android.R
import br.com.etec.etec_film_android.retrofit.RetrofitConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val context: Context = this
        val call: Call<ArrayList<Film>> = RetrofitConfig().filmApiService().getAll()
        val rvMainFilm = findViewById<RecyclerView>(R.id.rv_main_film)

        call.enqueue(object : Callback<ArrayList<Film>> {
            override fun onResponse(call: Call<ArrayList<Film>>?, response: Response<ArrayList<Film>>?) {
                response?.body()?.let {
                    val films = it
                    rvMainFilm.adapter = MainAdapter(films, context)
                }
            }

            override fun onFailure(call: Call<ArrayList<Film>>?, t: Throwable?) {
                showMessage(rvMainFilm, "Erro ao obter lista")
            }
        })
    }

    fun showMessage(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).setAction("Action", null).show()
    }

}


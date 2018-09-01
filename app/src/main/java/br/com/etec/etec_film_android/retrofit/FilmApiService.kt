package br.com.etec.etec_film_android.retrofit

import br.com.etec.etec_film_android.main.Film
import retrofit2.Call
import retrofit2.http.GET

interface FilmApiService {

    @GET("/film/v1/get-all")
    fun getAll() : Call<ArrayList<Film>>
}
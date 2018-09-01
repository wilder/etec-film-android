package br.com.etec.etec_film_android.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConfig {
    private final lateinit var retrofit : Retrofit
    private val retrofitBuilder = Retrofit.Builder()

    constructor() {
        retrofit = retrofitBuilder.baseUrl("https://etec-film.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    fun filmApiService(): FilmApiService {
        return this.retrofit.create(FilmApiService::class.java)
    }

}
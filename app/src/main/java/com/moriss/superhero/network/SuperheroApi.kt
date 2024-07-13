package com.moriss.superhero.network

import com.moriss.superhero.model.Superhero
import retrofit2.Call
import retrofit2.http.GET

interface SuperheroApi {
    @GET("78")
    fun getSuperhero(): Call<Superhero>
}
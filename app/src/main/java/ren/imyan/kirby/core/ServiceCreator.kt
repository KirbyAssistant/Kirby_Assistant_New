package ren.imyan.kirby.core

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * @author EndureBlaze/炎忍 https://github.com.EndureBlaze
 * @data 2021-01-24 17:57
 * @website https://imyan.ren
 */
object ServiceCreator {
    private const val BASE_URL = "https://api.imyan.ren/ka/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(serviceClass: Class<T>): T = retrofit.create(serviceClass)

    inline fun <reified T> create(): T = create(T::class.java)
}
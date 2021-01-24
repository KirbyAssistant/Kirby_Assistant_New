package ren.imyan.kirby.data.retrofit

import ren.imyan.kirby.data.model.Console
import retrofit2.Call
import retrofit2.http.GET

/**
 * @author EndureBlaze/炎忍 https://github.com.EndureBlaze
 * @data 2021-01-24 17:53
 * @website https://imyan.ren
 */
interface ConsoleService {
    @GET("data/console.json")
    fun getConsoleData(): Call<List<Console>>
}
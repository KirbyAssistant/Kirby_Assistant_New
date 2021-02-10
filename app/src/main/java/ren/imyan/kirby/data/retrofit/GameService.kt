package ren.imyan.kirby.data.retrofit

import ren.imyan.kirby.data.model.Game
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author EndureBlaze/炎忍 https://github.com.EndureBlaze
 * @data 2021-01-28 13:23
 * @website https://imyan.ren
 */
interface GameService {
    @GET("{console}.json")
    fun getGameData(@Path("console") console: String): Call<List<Game>>
}
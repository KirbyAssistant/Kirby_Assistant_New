package ren.imyan.kirby.data.retrofit

import ren.imyan.kirby.data.model.CheatCode
import ren.imyan.kirby.data.model.Game
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author EndureBlaze/炎忍 https://github.com.EndureBlaze
 * @data 2021-02-16 11:43
 * @website https://imyan.ren
 */
interface CheatCodeService {
    @GET("cheatcode/{game}.json")
    fun getCheatCodeData(@Path("game") game: String): Call<List<CheatCode>>
}
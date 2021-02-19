 package ren.imyan.kirby.data.retrofit

import ren.imyan.kirby.data.model.CheatCodeGame
import ren.imyan.kirby.data.model.Console
import ren.imyan.kirby.data.model.Emulator
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author EndureBlaze/炎忍 https://github.com.EndureBlaze
 * @data 2021-01-24 17:53
 * @website https://imyan.ren
 */
interface ResService {
    @GET("console.json")
    fun getConsoleData(): Call<List<Console>>

    @GET("emulator.json")
    fun getEmulatorData(): Call<List<Emulator>>

    @GET("cheatcode.json")
    fun getCheatCodeGameData(): Call<List<CheatCodeGame>>
}
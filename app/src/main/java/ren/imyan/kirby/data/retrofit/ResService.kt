package ren.imyan.kirby.data.retrofit

import ren.imyan.kirby.data.model.moshi.CheatCodeGame
import ren.imyan.kirby.data.model.moshi.Console
import ren.imyan.kirby.data.model.moshi.Emulator
import ren.imyan.kirby.data.model.moshi.Video
import retrofit2.Call
import retrofit2.http.GET

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

    @GET("video.json")
    fun getVideoData(): Call<List<Video>>
}
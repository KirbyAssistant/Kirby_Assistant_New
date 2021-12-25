package ren.imyan.kirby.net.api

import ren.imyan.kirby.net.response.CheatCodeGameResponse
import ren.imyan.kirby.net.response.ConsoleResponse
import ren.imyan.kirby.net.response.EmulatorResponse
import ren.imyan.kirby.net.response.VideoResponse
import retrofit2.http.GET

interface HomeApi {
    @GET("console.json")
    suspend fun getConsoleData(): List<ConsoleResponse>

    @GET("emulator.json")
    suspend fun getEmulatorData(): List<EmulatorResponse>

    @GET("cheatcode.json")
    suspend fun getCheatCodeGameData(): List<CheatCodeGameResponse>

    @GET("video.json")
    suspend fun getVideoData(): List<VideoResponse>
}
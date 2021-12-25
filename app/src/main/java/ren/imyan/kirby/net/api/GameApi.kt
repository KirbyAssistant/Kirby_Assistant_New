package ren.imyan.kirby.net.api

import ren.imyan.kirby.net.response.GameResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface GameApi {
    @GET("console/{name}.json")
    suspend fun getGameData(@Path("name") name: String): List<GameResponse>
}
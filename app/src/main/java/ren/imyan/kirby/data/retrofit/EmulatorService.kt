package ren.imyan.kirby.data.retrofit

import ren.imyan.kirby.data.model.Emulator
import retrofit2.Call
import retrofit2.http.GET

/**
 * @author EndureBlaze/炎忍 https://github.com.EndureBlaze
 * @data 2021-01-25 19:31
 * @website https://imyan.ren
 */
interface EmulatorService {
    @GET("emulator.json")
    fun getEmulatorData(): Call<List<Emulator>>
}
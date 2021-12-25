package ren.imyan.kirby.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ren.imyan.kirby.common.ktx.get
import ren.imyan.kirby.net.api.GameApi
import retrofit2.Retrofit

class GameRepository {
    private val gameApi by lazy { get<Retrofit>().create(GameApi::class.java) }

    suspend fun getGameListData(consoleName: String) =
        flow {
            emit(gameApi.getGameData(consoleName))
        }.flowOn(Dispatchers.IO)
}
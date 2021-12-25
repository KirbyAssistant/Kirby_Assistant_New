package ren.imyan.kirby.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ren.imyan.kirby.common.ktx.get
import ren.imyan.kirby.net.api.HomeApi
import retrofit2.Retrofit

class HomeRepository {
    private val homeApi by lazy { get<Retrofit>().create(HomeApi::class.java) }

    suspend fun getConsoleList() =
        flow {
            emit(homeApi.getConsoleData())
        }.flowOn(Dispatchers.IO)

    suspend fun getEmulatorList() =
        flow {
            emit(homeApi.getEmulatorData())
        }.flowOn(Dispatchers.IO)
}
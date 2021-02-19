package ren.imyan.kirby.ui.game

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ren.imyan.kirby.core.ServiceCreator
import ren.imyan.kirby.data.model.Game
import ren.imyan.kirby.data.model.ResItem
import ren.imyan.kirby.data.retrofit.GameService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @author EndureBlaze/炎忍 https://github.com.EndureBlaze
 * @data 2021-01-27 17:21
 * @website https://imyan.ren
 */
class GameListViewModel(val res: ResItem) : ViewModel() {
    val gameData: LiveData<List<Game>>
        get() = _gameData
    val gameDataState: LiveData<String>
        get() = _gameDataState

    private val _gameData = MutableLiveData<List<Game>>()
    private val _gameDataState = MutableLiveData<String>()

    init {
        getGameData(res.tag)
    }

    fun getAgainData(type: String) {
        getGameData(type)
    }

    private fun getGameData(type: String) {
        val gameService = ServiceCreator.create<GameService>()
        gameService.getGameData(type).enqueue(object : Callback<List<Game>> {

            override fun onResponse(call: Call<List<Game>>, response: Response<List<Game>>) {
                _gameData.value = response.body()
            }

            override fun onFailure(call: Call<List<Game>>, t: Throwable) {
                _gameDataState.value = t.message
            }

        })
    }
}
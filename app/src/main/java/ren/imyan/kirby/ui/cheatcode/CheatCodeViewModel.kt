package ren.imyan.kirby.ui.cheatcode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ren.imyan.kirby.core.ServiceCreator
import ren.imyan.kirby.data.model.CheatCode
import ren.imyan.kirby.data.model.ResItem
import ren.imyan.kirby.data.retrofit.CheatCodeService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @author EndureBlaze/炎忍 https://github.com.EndureBlaze
 * @data 2021-02-19 16:40
 * @website https://imyan.ren
 */
class CheatCodeViewModel(val game: ResItem) : ViewModel() {

    val cheatCodeData: LiveData<List<CheatCode>>
        get() = _cheatCodeData
    val cheatCodeDataState: LiveData<String>
        get() = _cheatCodeDataState

    private val _cheatCodeData = MutableLiveData<List<CheatCode>>()
    private val _cheatCodeDataState = MutableLiveData<String>()

    init {
        getCheatCodeData(game.tag)
    }

    fun getAgainData(type: String) {
        getCheatCodeData(type)
    }

    private fun getCheatCodeData(type: String) {
        val cheatCodeService = ServiceCreator.create<CheatCodeService>()
        cheatCodeService.getCheatCodeData(type).enqueue(object : Callback<List<CheatCode>> {
            override fun onResponse(
                call: Call<List<CheatCode>>,
                response: Response<List<CheatCode>>
            ) {
                response.body()?.let {
                    _cheatCodeData.value = it
                }
            }

            override fun onFailure(call: Call<List<CheatCode>>, t: Throwable) {
                _cheatCodeDataState.value = t.message
            }
        })
    }
}
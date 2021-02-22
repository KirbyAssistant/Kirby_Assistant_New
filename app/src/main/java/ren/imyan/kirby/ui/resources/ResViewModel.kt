package ren.imyan.kirby.ui.resources

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ren.imyan.base.ActivityCollector
import ren.imyan.kirby.R
import ren.imyan.kirby.core.ServiceCreator
import ren.imyan.kirby.data.model.moshi.CheatCodeGame
import ren.imyan.kirby.data.model.moshi.Console
import ren.imyan.kirby.data.model.moshi.Emulator
import ren.imyan.kirby.data.model.ResItem
import ren.imyan.kirby.data.retrofit.ResService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @author EndureBlaze/炎忍 https://github.com.EndureBlaze
 * @data 2021-01-24 16:44
 * @website https://imyan.ren
 */
class ResViewModel : ViewModel() {

    private val emulatorText: String
        get() = ActivityCollector.currActivity().resources.getString(R.string.tab_emulator)

    val tabTitles: List<String>
        get() = _tabTitles

    val consoleListData: LiveData<List<ResItem>>
        get() = _consoleListData

    val emulatorListData: LiveData<List<ResItem>>
        get() = _emulatorListData

    val cheatCodeGameListData: LiveData<List<ResItem>>
        get() = _cheatCodeGameListData

    val consoleDataState: LiveData<String>
        get() = _consoleDataState

    val emulatorDataState: LiveData<String>
        get() = _emulatorDataState

    val cheatCodeGameDataState: LiveData<String>
        get() = _cheatCodeGameDataState

    private val _tabTitles: MutableList<String> by lazy { ArrayList() }

    private val _consoleListData = MutableLiveData<List<ResItem>>()
    private val _emulatorListData = MutableLiveData<List<ResItem>>()
    private val _cheatCodeGameListData = MutableLiveData<List<ResItem>>()

    private val _consoleDataState = MutableLiveData<String>()
    private val _emulatorDataState = MutableLiveData<String>()
    private val _cheatCodeGameDataState = MutableLiveData<String>()

    init {
        _tabTitles.addAll(loadTabTitles())
        getConsoleListData()
        getEmulatorListData()
        getCheatCodeGameListData()
    }

    fun getAgainData(type: String) {
        when (type) {
            "console" -> getConsoleListData()
            "emulator" -> getEmulatorListData()
            "cheatcode" -> getCheatCodeGameListData()
        }
    }

    private fun loadTabTitles(): List<String> =
        listOf(
            "游戏", "模拟器", "金手指"
        )

    private fun getConsoleListData() {
        val consoleService = ServiceCreator.create<ResService>()
        consoleService.getConsoleData().enqueue(object : Callback<List<Console>> {
            override fun onResponse(call: Call<List<Console>>, response: Response<List<Console>>) {
                response.body()?.let {
                    val list = ArrayList<ResItem>()
                    for (ele in it) {
                        list.add(ResItem(ele.title, ele.image, ele.tag, "console"))
                    }
                    _consoleListData.value = list
                }
            }

            override fun onFailure(call: Call<List<Console>>, t: Throwable) {
                _consoleDataState.value = t.message
            }
        })
    }

    private fun getEmulatorListData() {
        val emulatorService = ServiceCreator.create<ResService>()
        emulatorService.getEmulatorData().enqueue(object : Callback<List<Emulator>> {
            override fun onResponse(
                call: Call<List<Emulator>>,
                response: Response<List<Emulator>>
            ) {
                response.body()?.let {
                    val list = ArrayList<ResItem>()
                    for (ele in it) {
                        list.add(
                            ResItem(
                                "${ele.type} $emulatorText ${ele.title}",
                                ele.image,
                                ele.tag,
                                "emulator"
                            )
                        )
                    }
                    _emulatorListData.value = list
                }
            }

            override fun onFailure(call: Call<List<Emulator>>, t: Throwable) {
                _emulatorDataState.value = t.message
            }
        })
    }

    private fun getCheatCodeGameListData() {
        val cheatCodeGameService = ServiceCreator.create<ResService>()
        cheatCodeGameService.getCheatCodeGameData().enqueue(object : Callback<List<CheatCodeGame>> {
            override fun onResponse(
                call: Call<List<CheatCodeGame>>,
                response: Response<List<CheatCodeGame>>
            ) {
                response.body()?.let {
                    val list = ArrayList<ResItem>()
                    for (ele in it) {
                        list.add(ResItem(ele.title, ele.image, ele.tag, "cheatcode"))
                    }
                    _cheatCodeGameListData.value = list
                }
            }

            override fun onFailure(call: Call<List<CheatCodeGame>>, t: Throwable) {
                _cheatCodeGameDataState.value = t.message
            }
        })
    }
}

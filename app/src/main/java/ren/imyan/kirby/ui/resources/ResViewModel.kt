package ren.imyan.kirby.ui.resources

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ren.imyan.kirby.core.ServiceCreator
import ren.imyan.kirby.data.model.Console
import ren.imyan.kirby.data.retrofit.ConsoleService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @author EndureBlaze/炎忍 https://github.com.EndureBlaze
 * @data 2021-01-24 16:44
 * @website https://imyan.ren
 */
class ResViewModel : ViewModel() {

    val tabTitles: List<String>
        get() = _tabTitles

    val consoleListData: LiveData<List<Console>>
        get() = _consoleListData

    private val _tabTitles: MutableList<String> by lazy { ArrayList() }
    private val _consoleListData = MutableLiveData<List<Console>>()

    init {
        _tabTitles.addAll(loadTabTitles())
        _consoleListData.value = getConsoleListData()
    }

    private fun loadTabTitles(): List<String> =
        listOf(
            "游戏", "模拟器", "金手指"
        )

    private fun getConsoleListData(): List<Console> {
        val list = ArrayList<Console>()
        val consoleService = ServiceCreator.create<ConsoleService>()
        consoleService.getConsoleData().enqueue(object : Callback<List<Console>> {
            override fun onResponse(call: Call<List<Console>>, response: Response<List<Console>>) {
                response.body()?.let { list.addAll(it) }
            }

            override fun onFailure(call: Call<List<Console>>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return list
    }
}

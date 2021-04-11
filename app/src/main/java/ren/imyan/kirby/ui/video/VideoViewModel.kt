package ren.imyan.kirby.ui.video

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ren.imyan.kirby.core.ServiceCreator
import ren.imyan.kirby.data.model.ConvertData
import ren.imyan.kirby.data.model.MutableConvertData
import ren.imyan.kirby.data.model.moshi.Video
import ren.imyan.kirby.data.retrofit.ResService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * @author EndureBlaze/炎忍 https://github.com.EndureBlaze
 * @data 2021-02-19 19:27
 * @website https://imyan.ren
 */
class VideoViewModel : ViewModel() {
    val videoListData: ConvertData<List<Video>>
        get() = _videoListData.toImmutable()

    private val _videoListData = MutableConvertData<List<Video>>()

    init {
        getVideoListData()
    }

    fun getAgainData() {
        getVideoListData()
    }

    private fun getVideoListData() {
        val videoService = ServiceCreator.create<ResService>()
        videoService.getVideoData().enqueue(object : Callback<List<Video>> {
            override fun onResponse(call: Call<List<Video>>, response: Response<List<Video>>) {
                response.body()?.let {
                    _videoListData.data.value = it
                }
            }

            override fun onFailure(call: Call<List<Video>>, t: Throwable) {
                _videoListData.state.value = t.message
            }
        })
    }
}
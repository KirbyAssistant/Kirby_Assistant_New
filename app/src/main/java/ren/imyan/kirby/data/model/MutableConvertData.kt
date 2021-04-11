package ren.imyan.kirby.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * @author EndureBlaze/炎忍 https://github.com.EndureBlaze
 * @data 2021-02-22 14:22
 * @website https://imyan.ren
 */
class MutableConvertData<E> {
    val data = MutableLiveData<E>()
    val state = MutableLiveData<String>()

    fun toImmutable(): ConvertData<E> = ConvertData(data, state)
}
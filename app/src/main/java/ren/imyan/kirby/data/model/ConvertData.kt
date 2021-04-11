package ren.imyan.kirby.data.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * @author EndureBlaze/炎忍 https://github.com.EndureBlaze
 * @data 2021-02-22 16:51
 * @website https://imyan.ren
 */
data class ConvertData<E>(val data: LiveData<E>, val state: LiveData<String>)
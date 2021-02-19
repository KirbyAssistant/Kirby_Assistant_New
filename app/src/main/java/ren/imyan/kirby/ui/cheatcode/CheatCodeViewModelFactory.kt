package ren.imyan.kirby.ui.cheatcode

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ren.imyan.kirby.data.model.ResItem

/**
 * @author EndureBlaze/炎忍 https://github.com.EndureBlaze
 * @data 2021-02-19 16:35
 * @website https://imyan.ren
 */
@Suppress("UNCHECKED_CAST")
class CheatCodeViewModelFactory(private val res: ResItem?) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return res?.let { CheatCodeViewModel(it) } as T
    }
}
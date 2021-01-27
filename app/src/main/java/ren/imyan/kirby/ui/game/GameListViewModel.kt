package ren.imyan.kirby.ui.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ren.imyan.kirby.data.model.ResItem

/**
 * @author EndureBlaze/炎忍 https://github.com.EndureBlaze
 * @data 2021-01-27 17:21
 * @website https://imyan.ren
 */
class GameListViewModel(val res: ResItem) : ViewModel() {
}

class GameListViewModelFactory(private val res: ResItem?):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return res?.let { GameListViewModel(it) } as T
    }
}
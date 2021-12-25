package ren.imyan.kirby.ui.game

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ren.imyan.kirby.common.ktx.inject
import ren.imyan.kirby.common.ktx.next
import ren.imyan.kirby.core.BaseLoad
import ren.imyan.kirby.core.BaseViewModel
import ren.imyan.kirby.repository.GameRepository

class GameViewModel : BaseViewModel<GameData, GameEvent, GameAction>() {
    override fun createInitialState(): GameData = GameData()

    private val gameRepo: GameRepository by inject()

    override fun dispatch(action: GameAction) {
        when (action) {
            is GameAction.LoadGameList -> getGameListData(action.console)
        }
    }

    private fun getGameListData(console: String) {
        viewModelScope.launch {
            gameRepo.getGameListData(console).next(
                doStart = {
                    emitData {
                        copy(gameListLoad = BaseLoad.Loading)
                    }
                },
                doFail = {
                    emitData {
                        copy(gameListLoad = it?.let { it1 -> BaseLoad.Error(it1) })
                    }
                }
            ) {
                emitData {
                    copy(gameListLoad = BaseLoad.Success(this@next.toMutableList()))
                }
            }
        }
    }
}
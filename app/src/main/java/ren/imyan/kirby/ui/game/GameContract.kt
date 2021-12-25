package ren.imyan.kirby.ui.game

import ren.imyan.kirby.core.BaseLoad
import ren.imyan.kirby.core.UiAction
import ren.imyan.kirby.core.UiData
import ren.imyan.kirby.core.UiEvent
import ren.imyan.kirby.net.response.GameResponse


data class GameData(
    val gameListLoad: BaseLoad<List<GameResponse>>? = null,
) : UiData

sealed class GameEvent : UiEvent {
}

sealed class GameAction : UiAction {
    data class LoadGameList(val console: String) : GameAction()
}
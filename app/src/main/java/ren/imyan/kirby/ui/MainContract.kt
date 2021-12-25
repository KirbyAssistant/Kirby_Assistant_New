package ren.imyan.kirby.ui

import ren.imyan.kirby.core.BaseLoad
import ren.imyan.kirby.core.UiAction
import ren.imyan.kirby.core.UiData
import ren.imyan.kirby.core.UiEvent
import ren.imyan.kirby.net.response.ConsoleResponse
import ren.imyan.kirby.net.response.EmulatorResponse

data class MainData(
    val consoleListLoad: BaseLoad<List<ConsoleResponse>>? = null,
    val emulatorListLoad: BaseLoad<List<EmulatorResponse>>? = null
) : UiData

sealed class MainEvent : UiEvent {
}

sealed class MainAction : UiAction {
    object LoadConsoleList : MainAction()
    object LoadEmulatorList : MainAction()
}
package ren.imyan.kirby.ui

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import ren.imyan.kirby.common.ktx.inject
import ren.imyan.kirby.common.ktx.next
import ren.imyan.kirby.core.BaseLoad
import ren.imyan.kirby.core.BaseViewModel
import ren.imyan.kirby.repository.HomeRepository

class MainViewModel : BaseViewModel<MainData, MainEvent, MainAction>() {
    override fun createInitialState(): MainData = MainData()

    private val homeRepo: HomeRepository by inject()

    init {
        getConsoleList()
        getEmulatorList()
    }

    override fun dispatch(action: MainAction) {
        when (action) {
            MainAction.LoadConsoleList -> getConsoleList()
            MainAction.LoadEmulatorList -> getEmulatorList()
        }
    }

    private fun getConsoleList() {
        viewModelScope.launch {
            homeRepo.getConsoleList().next(
                doStart = {
                    emitData {
                        copy(consoleListLoad = BaseLoad.Loading)
                    }
                },
                doFail = {
                    emitData {
                        copy(consoleListLoad = it?.let { it1 -> BaseLoad.Error(it1) })
                    }
                }
            ) {
                emitData {
                    copy(consoleListLoad = BaseLoad.Success(this@next.toMutableList()))
                }
            }
        }
    }

    private fun getEmulatorList(){
        viewModelScope.launch {
            homeRepo.getEmulatorList().next(
                doStart = {
                    emitData {
                        copy(emulatorListLoad = BaseLoad.Loading)
                    }
                },
                doFail = {
                    emitData {
                        copy(emulatorListLoad = it?.let { it1 -> BaseLoad.Error(it1) })
                    }
                }
            ) {
                emitData {
                    copy(emulatorListLoad = BaseLoad.Success(this@next.toMutableList()))
                }
            }
        }
    }
}
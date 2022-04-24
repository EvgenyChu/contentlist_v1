package com.template

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MenuViewModel() : ViewModel() {
    private val _state: MutableStateFlow<MenuState> = MutableStateFlow(MenuState())

    val state: StateFlow<MenuState>
        get() = _state

    val currentState: MenuState
        get() = state.value

    fun updateCounterPlus(){
        _state.value = currentState.copy(counter = currentState.counter.inc())
    }

    fun updateCounterMinus(){
        _state.value = currentState.copy(counter = currentState.counter-1)
    }
}

data class MenuState(
    val counter: Int = 1,
)
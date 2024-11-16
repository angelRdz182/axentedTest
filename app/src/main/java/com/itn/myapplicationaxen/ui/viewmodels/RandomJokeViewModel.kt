package com.itn.myapplicationaxen.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itn.myapplicationaxen.core.utils.DefaultDispatchersProvider
import com.itn.myapplicationaxen.core.utils.Resource
import com.itn.myapplicationaxen.data.network.response.RandomJokeResponse
import com.itn.myapplicationaxen.domain.usecases.GetRandomJokeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RandomJokeViewModel @Inject constructor(
    private val dispatcher: DefaultDispatchersProvider,
    private val randomJokeUseCase: GetRandomJokeUseCase
) : ViewModel(){

    private val _state: MutableStateFlow<UIState> by lazy { MutableStateFlow(createInitialState()) }
    val state: StateFlow<UIState> = _state.asStateFlow()

    private fun createInitialState(): UIState {
        return UIState(null, State.Idle)
    }

    data class UIState(val randomJokeResponse: RandomJokeResponse? = null, val state: State)

    sealed class State {
        data object Loading: State()
        data class Error(val error: String): State()
        data object JokeLoaded : State()
        data object Idle : State()
    }

    fun getRandomJoke(category: String) {
        viewModelScope.launch(dispatcher.io) {
            randomJokeUseCase(category).takeIf {
                it is Resource.Success
            }?.data?.let { response ->
                _state.update {
                    it.copy(randomJokeResponse = response, state = State.JokeLoaded)
                }
            } ?: run {
                _state.update {
                    it.copy(state = State.Error("Error al consultar la categoria"))
                }
            }
        }
    }

    private fun handleAction(action: Action) {
        when(action) {
            is Action.RequestRandomJoke -> getRandomJoke(action.category)
        }
    }

    sealed class Action {
        data class RequestRandomJoke(val category: String): Action()
    }

    fun resetState(){
        _state.update {
            it.copy(state = State.Idle)
        }
    }
}
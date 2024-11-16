package com.itn.myapplicationaxen.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itn.myapplicationaxen.core.utils.DefaultDispatchersProvider
import com.itn.myapplicationaxen.core.utils.Resource
import com.itn.myapplicationaxen.domain.usecases.GetCategoriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JokeCategoriesViewModel @Inject constructor(
    private val dispatcher: DefaultDispatchersProvider,
    private val getCategoriesUseCase: GetCategoriesUseCase
) : ViewModel(){

    private val _state: MutableStateFlow<UIState> by lazy { MutableStateFlow(createInitialState()) }
    val state: StateFlow<UIState> = _state.asStateFlow()

    private fun createInitialState(): UIState {
        handleAction(Action.RequestCategories)
        return UIState(arrayListOf(), State.Loading)

    }

    data class UIState(val itemList: List<String>, val state: State)

    sealed class State {
        data object Loading: State()
        data class Error(val error: String): State()
        data object ListLoaded : State()
        data object Idle : State()
    }

    private fun getCategories() {
        viewModelScope.launch(dispatcher.io) {
            getCategoriesUseCase().takeIf {
                it is Resource.Success
            }?.data?.let { response ->
                _state.update {
                    it.copy(itemList = response, state = State.ListLoaded)
                }
            } ?: run {
                _state.update {
                    it.copy(state = State.Error("Error al consultar la lista"))
                }
            }
        }
    }

    private fun handleAction(action: Action) {
        when(action) {
            Action.RequestCategories -> getCategories()
        }
    }

    sealed class Action {
        data object RequestCategories: Action()
    }

    fun resetState(){
        _state.update {
            it.copy(state = State.Idle)
        }
    }
}
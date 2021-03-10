package tech.tiagoloureiro.bloc

import kotlinx.coroutines.flow.MutableStateFlow

abstract class Bloc<State, Event>(initialState: State) {
    private val _state = MutableStateFlow(initialState)
    val state = _state

    fun add(event: Event) {
        _state.tryEmit(
            mapEventToState(event, _state.value)
        )
        //TODO: OnError?
    }

    abstract fun mapEventToState(event: Event, state: State): State

    //TODO:
    //onEvent
    //onChange
    //onTransition
    //onError
}

package com.julianparrilla.dragonbooker.base

import android.os.Bundle
import androidx.lifecycle.*
import androidx.navigation.NavController
import com.babel.marvel.domain.datastate.DataState
import java.io.Serializable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.*

@FlowPreview
@ExperimentalCoroutinesApi
abstract class BaseViewModel<EventState, ViewState> : ViewModel() {

    private val dataChannel: ConflatedBroadcastChannel<DataState<ViewState>> =
        ConflatedBroadcastChannel()

    private val _viewState: MutableLiveData<ViewState> = MutableLiveData()

    private val viewState: LiveData<ViewState>
        get() = _viewState

    private val _dataState: MutableLiveData<DataState<ViewState>> = MutableLiveData()

    var currentNavigator: NavController? = null

    val dataState: LiveData<DataState<ViewState>> = _dataState

    init {
        dataChannel
            .asFlow()
            .onEach { dataState ->
                _dataState.value = dataState
            }
            .launchIn(viewModelScope)
    }

    fun setEventState(eventState: EventState) {
        dataChannel.let { channel ->
            handleEventState(eventState).onEach { data ->
                if (!channel.isClosedForSend) {
                    channel.trySend(data).isSuccess
                }
            }
        }.launchIn(viewModelScope)
    }

    fun initViewStateWithBundle(argumets: Bundle?) {
        argumets?.let {
            setViewState(it.getSerializable(KEY_BUNDLE) as ViewState)
        }
    }

    fun setViewState(viewState: ViewState) {
        _viewState.value = viewState!!
    }

    fun getCurrentViewStateOrNew(): ViewState {
        return viewState.value ?: initNewViewState()
    }

    fun navigate(idDestination: Int, extraData: Serializable? = null) {
        val bundle = Bundle()
        bundle.putSerializable(KEY_BUNDLE, extraData)
        currentNavigator?.navigate(idDestination, bundle)
    }

    abstract fun handleEventState(eventState: EventState): Flow<DataState<ViewState>>

    abstract fun initNewViewState(): ViewState

    companion object {
        const val KEY_BUNDLE = "VM"
    }
}

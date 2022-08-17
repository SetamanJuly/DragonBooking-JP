package com.julianparrilla.dragonbooker.common

import com.julianparrilla.domain.datastate.StateMessage

interface UICommunicationListener {
    fun onUIMessageReceived(stateMessage: StateMessage)
    fun hideSoftKeyboard()
    fun displayLoading(isLoading: Boolean)
}

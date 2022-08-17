package com.babel.marvel.common

import com.babel.marvel.domain.datastate.DataState

interface DataStateChangeListener {
    fun onDataStateChangeListener(dataState: DataState<*>?)
}

package com.julianparrilla.dragonbooker.common

import com.julianparrilla.domain.utils.WithScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface Action

interface State

interface ViewStore<B : State> {
    fun B.render() {

    }

    fun StateFlow<B>.state() {

    }
}

interface FormStore<A : State> {
    fun init(): StateFlow<A>
}

abstract class Store<A : Action, B : State>(initialState: B) :
    FormStore<B>,
    WithScope,
    Reducer<A, B>,
    ActionHandler<A>,
    SideEffects<A, B> {

    private val state: MutableStateFlow<B> = MutableStateFlow(initialState)
    private val actions: MutableList<A> = mutableListOf()

    override fun getState(): StateFlow<B> = state

    override fun getActions(): List<A> = actions

    override fun init(): StateFlow<B> {
        onInit()
        return state
    }

    override fun A.handle() {
        actions.add(this)
        val newState: B = reduce(state.value)
        newState.state()
        launchMain { sideEffects(newState) }
    }

    abstract fun onInit()

    open fun onCreate(view: ViewStore<B>) {
        val state: StateFlow<B> = init()
        view.run { state.state() }
        launchIO {
            state.collect {
                Main { view.run { it.render() } }
            }
        }
    }

    private fun B.state() {
        state.value = this
    }
}

interface Reducer<A : Action, B : State> {
    fun A.reduce(currentState: B): B
}

interface SideEffects<A : Action, B : State> {
    fun A.sideEffects(currentState: B)
    fun getActions(): List<A>
    fun getState(): StateFlow<B>
}

interface ActionHandler<A : Action> {
    fun A.handle()
}

interface EmptyState<A : State> {
    fun empty(): A
}
package com.julianparrilla.dragonbooker.base

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.babel.marvel.common.DataStateChangeListener
import com.julianparrilla.dragonbooker.common.UICommunicationListener
import com.babel.marvel.domain.datastate.DataState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
abstract class BaseFragment<ViewState>() : Fragment() {

    private var dataStateChangeListener: DataStateChangeListener? = null
    private var uiCommunicationListener: UICommunicationListener? = null

    abstract val viewModel: BaseViewModel<*, *>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        kotlin.runCatching {
            dataStateChangeListener = context as DataStateChangeListener
            uiCommunicationListener = context as UICommunicationListener
            viewModel.currentNavigator = findNavController()
            viewModel.initViewStateWithBundle(arguments)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onSubscribeListeners()
    }

    abstract fun onUpdateView(data: ViewState)

    abstract fun onErrorView(data: DataState.ERROR<ViewState>)

    private fun onSubscribeListeners() {
        viewModel.dataState.observe(
            viewLifecycleOwner
        ) {
            dataStateChangeListener?.onDataStateChangeListener(it)
            when (it) {
                is DataState.SUCCESS -> {
                    (it.data as? ViewState)?.let { data ->
                        onUpdateView(data)
                    }
                }
                is DataState.ERROR -> {
                    (it as? DataState.ERROR<ViewState>)?.let { errorState ->
                        onErrorView(errorState)
                    }
                }
                is DataState.LOADING -> {
                    (activity as? BaseActivity)?.displayLoading(it.loading)
                }
            }
        }
    }
}

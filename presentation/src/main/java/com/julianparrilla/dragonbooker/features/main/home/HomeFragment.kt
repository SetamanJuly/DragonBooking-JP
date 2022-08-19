package com.julianparrilla.dragonbooker.features.main.home

import com.julianparrilla.dragonbooker.R
import com.julianparrilla.dragonbooker.base.BaseFragment
import com.julianparrilla.dragonbooker.common.ViewStore
import com.julianparrilla.dragonbooker.databinding.FragmentHomeBinding
import com.julianparrilla.dragonbooker.utils.viewBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import org.koin.android.ext.android.inject

@FlowPreview
@OptIn(ExperimentalCoroutinesApi::class)
class HomeFragment : BaseFragment(), ViewStore<HomeState> {

    val binding: FragmentHomeBinding by viewBinding()

    private val store: HomeStore by inject()

    override fun getLayout(): Int = R.layout.fragment_home

    override fun onCreate() {
        store.onCreate(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        store.cancel()
    }

    override fun HomeState.render() {

    }
}
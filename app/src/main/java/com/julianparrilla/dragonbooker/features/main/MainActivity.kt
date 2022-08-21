package com.julianparrilla.dragonbooker.features.main

import android.os.Bundle
import com.julianparrilla.dragonbooker.base.BaseActivity
import com.julianparrilla.dragonbooker.common.ViewStore
import com.julianparrilla.dragonbooker.databinding.ActivityMainBinding
import com.julianparrilla.dragonbooker.utils.viewBinding
import com.julianparrilla.dragonbooker.utils.visibleOrGone
import org.koin.android.ext.android.inject

class MainActivity : BaseActivity(), ViewStore<MainState> {

    private val store: MainStore by inject()

    private val binding: ActivityMainBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        store.onCreate(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        store.cancel()
    }

    override fun MainState.render() {
        binding.progressBar.visibleOrGone(loading)
    }
}

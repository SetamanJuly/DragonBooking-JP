package com.julianparrilla.dragonbooker.features.splash

import android.os.Bundle
import android.os.Looper
import com.julianparrilla.dragonbooker.base.BaseActivity
import com.julianparrilla.dragonbooker.databinding.ActivitySplashBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
class SplashActivity : BaseActivity() {

    lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navMainActivity()
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun navMainActivity() {
        android.os.Handler(Looper.getMainLooper()).postDelayed(
            {
                //startActivity(Intent(this, MainActivity::class.java))
                finish()
            },
            TIME_OUT
        )
    }

    override fun displayLoading(isLoading: Boolean) {
        // Not necessary
    }

    companion object {
        const val TIME_OUT = 3000L
    }
}

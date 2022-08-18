package com.julianparrilla.dragonbooker.features.splash

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.julianparrilla.dragonbooker.base.BaseActivity
import com.julianparrilla.dragonbooker.databinding.ActivitySplashBinding
import com.julianparrilla.dragonbooker.utils.viewBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity() {

    private val binding: ActivitySplashBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navMainActivity()
        setContentView(binding.root)
    }

    private fun navMainActivity() {
        Handler(Looper.getMainLooper()).postDelayed(
            {
                //startActivity(Intent(this, MainActivity::class.java))
                //finish()
            },
            TIME_OUT
        )
    }

    companion object {
        const val TIME_OUT = 3000L
    }
}

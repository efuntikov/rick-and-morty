package com.efuntikov.rickandmorty.android

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.badoo.reaktive.disposable.Disposable
import com.badoo.reaktive.observable.ObservableObserver
import com.badoo.reaktive.observable.observeOn
import com.badoo.reaktive.scheduler.mainScheduler
import com.efuntikov.rickandmorty.ViewModelBinding
import com.efuntikov.rickandmorty.viewmodel.SplashViewModel
import com.efuntikov.rickandmorty.viewmodel.SplashViewModelNavigation
import com.efuntikov.rickandmorty.viewmodel.ViewModelRepository
import com.efuntikov.rickandmorty.viewmodel.ViewModelType

class SplashActivity : RMBaseActivity() {

    private lateinit var viewModel: SplashViewModel
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(buildView())

        viewModel = ViewModelRepository.getViewModel(ViewModelType.SPLASH) as SplashViewModel

        binding()

        viewModel.inputs.load()
    }

    private fun buildView(): View {
        val progressBar = ProgressBar(this).apply {
            layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT).apply {
                gravity = Gravity.CENTER
            }
            isIndeterminate = true
            visibility = View.GONE
            progressBar = this
        }
        return FrameLayout(this).apply {
            layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
            addView(progressBar)
            addView(TextView(this@SplashActivity).apply {
                layoutParams = FrameLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply {
                    gravity = Gravity.CENTER
                }
                text = "Welcome!"
            })
        }
    }

    override fun binding() {
        binding.subscribe(viewModel.outputs.loading.observeOn(mainScheduler), onNext = ::onLoading)
        binding.subscribe(viewModel.outputs.navigation.observeOn(mainScheduler), onNext = ::onNavigation)
    }

    private fun onLoading(isLoading: Boolean) {
        progressBar.visibleOrGone(isLoading)
    }

    private fun onNavigation(navigation: SplashViewModelNavigation) {
        when(navigation) {
            SplashViewModelNavigation.TO_MAIN_SCREEN -> {
                startActivity(Intent(this, MainActivity::class.java))
            }
            else -> {}
        }
    }
}
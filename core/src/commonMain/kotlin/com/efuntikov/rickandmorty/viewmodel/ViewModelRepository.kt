package com.efuntikov.rickandmorty.viewmodel

enum class ViewModelType {
    SPLASH, MAIN
}

object ViewModelRepository {
    fun getViewModel(vmType: ViewModelType) = when (vmType) {
        ViewModelType.SPLASH -> SplashViewModelImpl()
        ViewModelType.MAIN -> MainViewModelImpl()
        else -> {
            throw IllegalArgumentException("Unknown view model type")
        }
    }
}
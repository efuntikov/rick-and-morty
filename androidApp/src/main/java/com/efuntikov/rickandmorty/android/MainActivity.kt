package com.efuntikov.rickandmorty.android

import android.os.Bundle
import androidx.fragment.app.FragmentContainerView
import com.efuntikov.rickandmorty.viewmodel.MainViewModel
import com.efuntikov.rickandmorty.viewmodel.MainViewModelNavigation

class MainActivity : RMBaseActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var characterListFragmentContainer: FragmentContainerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        characterListFragmentContainer = findViewById(R.id.characterListFragmentContainer)

//        supportFragmentManager.beginTransaction().replace(
//            characterListFragmentContainer.id,
//            RMCharacterListFragment.newInstance()
//        ).commitNow()

//        viewModel = ViewModelRepository.getViewModel(ViewModelType.MAIN) as MainViewModel
//
//        binding()
//
//        viewModel.inputs.load()
    }

//    override fun binding() {
//        binding.subscribe(viewModel.outputs.loading.observeOn(mainScheduler), onNext = ::onLoading)
//        binding.subscribe(viewModel.outputs.navigation.observeOn(mainScheduler), onNext = ::onNavigation)
//    }

    private fun onLoading(isLoading: Boolean) {
//        progressBar.visibleOrGone(isLoading)
    }

    private fun onNavigation(navigation: MainViewModelNavigation) {
        when (navigation) {
            MainViewModelNavigation.TO_MAIN_SCREEN -> {
//                startActivity(Intent(this, MainActivity::class.java))
            }
            else -> {
            }
        }
    }
}

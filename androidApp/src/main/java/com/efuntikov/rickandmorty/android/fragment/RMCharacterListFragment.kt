package com.efuntikov.rickandmorty.android.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.badoo.reaktive.observable.observeOn
import com.badoo.reaktive.scheduler.mainScheduler
import com.efuntikov.rickandmorty.android.CharacterCard
import com.efuntikov.rickandmorty.android.util.VerticalSpaceItemDecoration
import com.efuntikov.rickandmorty.data.entity.RMCharacter
import com.efuntikov.rickandmorty.viewmodel.RMCharacterListViewModelImpl
import java.lang.ref.WeakReference

class RMCharacterListFragment : RMBaseFragment() {
    private lateinit var characterRecyclerView: RecyclerView

    private val characterListViewModel by lazy {
        RMCharacterListViewModelImpl()
    }

    private lateinit var charactersAdapter: RMCharactersAdapter
    private lateinit var scrollListener: RMCharactersListScrollListener

    companion object {
        fun newInstance(): RMBaseFragment {
            return RMCharacterListFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = RecyclerView(requireContext()).apply {
        layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
        layoutManager = LinearLayoutManager(requireContext())
        charactersAdapter = RMCharactersAdapter()
        adapter = charactersAdapter
        addItemDecoration(VerticalSpaceItemDecoration())
        characterRecyclerView = this
        scrollListener = RMCharactersListScrollListener(WeakReference(characterListViewModel))
        addOnScrollListener(scrollListener)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding()

        characterListViewModel.inputs.load()
    }

    override fun binding() {
        binding.subscribe(characterListViewModel.outputs.loading.observeOn(mainScheduler), onNext = ::onLoading)
        binding.subscribe(characterListViewModel.outputs.characters.observeOn(mainScheduler), onNext = ::onCharacters)
    }

    private fun onLoading(isLoading: Boolean) {

    }

    private fun onCharacters(charactersCount: Int) {
        charactersAdapter.onCharactersUpdated(charactersCount)
    }

    private class RMCharacterViewHolder(private val view: CharacterCard) : RecyclerView.ViewHolder(view) {
        fun bind(character: RMCharacter) {
            view.setCharacter(character)
        }
    }

    private inner class RMCharactersAdapter : RecyclerView.Adapter<RMCharacterViewHolder>() {

        fun onCharactersUpdated(charactersCount: Int) {
            notifyItemRangeChanged(itemCount, charactersCount)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            RMCharacterViewHolder(CharacterCard(requireContext()))

        override fun onBindViewHolder(holder: RMCharacterViewHolder, position: Int) {
            holder.bind(characterListViewModel.getCharacterList()[position])
        }

        override fun getItemCount() = characterListViewModel.getCharacterList().size
    }

    private class RMCharactersListScrollListener(
        private val viewModel: WeakReference<RMCharacterListViewModelImpl>
    ) : RecyclerView.OnScrollListener() {
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)

            val manager = recyclerView.layoutManager as LinearLayoutManager

            if (manager.findLastVisibleItemPosition() == (manager.childCount - 1)) {
                // start loading of the next page
                viewModel.get()?.inputs?.load()
            }
        }
    }
}
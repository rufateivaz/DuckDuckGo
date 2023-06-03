package com.sample.viewer.characters

import CharacterAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import com.sample.domain.characters.model.Character
import com.sample.viewer.ViewerActivity
import com.sample.viewer.ViewerActivityViewModel
import com.sample.viewer.databinding.FragmentCharactersBinding
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class CharactersFragment : Fragment() {

    private lateinit var binding: FragmentCharactersBinding

    private val viewModel: ViewerActivityViewModel by activityViewModel()

    private val characterAdapter: CharacterAdapter by lazy {
        CharacterAdapter(::onItemClicked)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharactersBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListObserver()
        setSearchTextChangedListener()
    }

    private fun setSearchTextChangedListener() {
        binding.searchContent.doAfterTextChanged {
            viewModel.getCharacters(binding.searchContent.text.toString())
        }
    }

    private fun setListObserver() {
        binding.recyclerView.adapter = characterAdapter

        viewModel.characters.observe(viewLifecycleOwner) {
            characterAdapter.saveData(it)
        }
    }

    private fun onItemClicked(character: Character) {
        viewModel.setSelectedCharacter(character)
        val activity = (requireActivity() as ViewerActivity)
        activity.binding.slidingPaneLayout.open()
    }
}
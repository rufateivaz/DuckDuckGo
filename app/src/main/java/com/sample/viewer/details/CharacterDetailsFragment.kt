package com.sample.viewer.details

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sample.domain.characters.model.Character
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.sample.data.BuildConfig
import com.sample.viewer.ViewerActivityViewModel
import com.sample.viewer.databinding.FragmentCharacterDetailsBinding
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class CharacterDetailsFragment : Fragment() {

    private lateinit var binding: FragmentCharacterDetailsBinding
    private val viewModel: ViewerActivityViewModel by activityViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.selectedCharacter.observe(viewLifecycleOwner) { updateUI(it) }
    }

    private fun updateUI(character: Character) {
        binding.title.text = character.title
        binding.desc.text = Html.fromHtml(character.description) ?: ""
        context?.let {
            Glide
                .with(it)
                .load(BuildConfig.HOST + character.icon.url)
                .into(binding.image)
        }
    }
}
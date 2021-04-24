package com.example.android11developmentmasterclass.presenter.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.android11developmentmasterclass.databinding.FragmentPlaylistDetailsBinding
import com.example.android11developmentmasterclass.domain.model.PlaylistDetailsModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PlaylistDetailsFragment : Fragment() {

    @Inject
    lateinit var playlistDetailsViewModelFactory: PlaylistDetailsViewModelFactory

    private val playlistViewModel: PlaylistDetailsViewModel by viewModels(
        factoryProducer = {
            playlistDetailsViewModelFactory
        }
    )

    private var _binding: FragmentPlaylistDetailsBinding? = null
    private val binding: FragmentPlaylistDetailsBinding get() = _binding!!

    private val args: PlaylistDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlaylistDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val playlistId = args.playlistId
        playlistViewModel.getPlaylistDetails(playlistId)

        observeViewModel()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun observeViewModel() = with(playlistViewModel) {
        playlistDetails.observe(viewLifecycleOwner, { playlistDetailsModel ->
            setupUI(playlistDetailsModel)
        })

        loading.observe(viewLifecycleOwner, {
            when (it) {
                true -> binding.progressBar.visibility = View.VISIBLE
                false -> binding.progressBar.visibility = View.GONE
            }
        })
    }

    private fun setupUI(playlistDetailsModel: PlaylistDetailsModel) = with(binding) {
        playlistName.text = playlistDetailsModel.name
        playlistDetails.text = playlistDetailsModel.details
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            PlaylistDetailsFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}
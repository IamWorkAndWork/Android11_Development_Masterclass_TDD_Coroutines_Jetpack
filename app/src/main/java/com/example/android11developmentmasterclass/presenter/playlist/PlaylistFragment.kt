package com.example.android11developmentmasterclass.presenter.playlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android11developmentmasterclass.R
import com.example.android11developmentmasterclass.databinding.FragmentPlaylistBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PlaylistFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: PlaylistViewModelFactory

    private var _binding: FragmentPlaylistBinding? = null
    private val binding: FragmentPlaylistBinding get() = _binding!!

    val playlistAdapter: PlaylistAdapter by lazy {
        PlaylistAdapter(playlistListener)
    }

    private val viewModel: PlaylistViewModel by viewModels(
        factoryProducer = {
            viewModelFactory
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPlaylistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initWidget()
        observeViewModel()

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun initWidget() = with(binding) {

        playlistRecyclerView.apply {
            adapter = playlistAdapter
            layoutManager = LinearLayoutManager(context)
        }

    }


    private fun observeViewModel() = with(viewModel) {

        loader.observe(viewLifecycleOwner, { loading ->
            when (loading) {
                true -> binding.loader.visibility = View.VISIBLE
                false -> binding.loader.visibility = View.GONE
            }
        })

        playlists.observe(viewLifecycleOwner, { playlistModelList ->
            playlistAdapter.submitList(playlistModelList)
        })

    }

    val playlistListener: (String) -> Unit = { playlistId ->
//        Toast.makeText(context, "click at id = playlistId", Toast.LENGTH_SHORT).show()
        val direction =
            PlaylistFragmentDirections.actionPlaylistFragmentToPlaylistDetailsFragment(playlistId)
        findNavController().navigate(direction)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            PlaylistFragment().apply {
            }
    }
}
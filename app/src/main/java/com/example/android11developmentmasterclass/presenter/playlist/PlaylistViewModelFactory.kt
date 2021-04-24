package com.example.android11developmentmasterclass.presenter.playlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android11developmentmasterclass.domain.GetAllPlaylistUseCase
import javax.inject.Inject

class PlaylistViewModelFactory @Inject constructor(
    private val getAllPlaylistUseCase: GetAllPlaylistUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PlaylistViewModel(getAllPlaylistUseCase) as T
    }

}
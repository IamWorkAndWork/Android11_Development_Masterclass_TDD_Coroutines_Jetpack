package com.example.android11developmentmasterclass.presenter.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android11developmentmasterclass.domain.usecase.GetPlaylistDetailsUseCase
import javax.inject.Inject

class PlaylistDetailsViewModelFactory @Inject constructor(
    private val getPlaylistDetailsUseCase: GetPlaylistDetailsUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PlaylistDetailsViewModel(getPlaylistDetailsUseCase = getPlaylistDetailsUseCase) as T
    }
}
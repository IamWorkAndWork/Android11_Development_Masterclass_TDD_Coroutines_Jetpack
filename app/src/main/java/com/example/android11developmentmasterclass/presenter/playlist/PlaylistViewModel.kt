package com.example.android11developmentmasterclass.presenter.playlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.liveData
import com.example.android11developmentmasterclass.data.repository.PlaylistRepository
import com.example.android11developmentmasterclass.domain.GetAllPlaylistUseCase
import com.example.android11developmentmasterclass.domain.GetAllPlaylistUseCaseImpl
import com.example.android11developmentmasterclass.domain.model.PlaylistModel
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class PlaylistViewModel(
    private val getAllPlaylistUseCase: GetAllPlaylistUseCase
) : ViewModel() {

    val loader = MutableLiveData<Boolean>()

    val playlists = liveData<List<PlaylistModel>?> {

        loader.postValue(true)

        val playlistLiveData = getAllPlaylistUseCase.invoke().onEach {
            loader.postValue(false)
        }.asLiveData()

        emitSource(playlistLiveData)

    }

    val playlistsTDD = liveData<Result<List<PlaylistModel>>> {

        loader.postValue(true)

        val playlistLiveData = getAllPlaylistUseCase.invokeTDD().onEach {
            loader.postValue(false)
        }.asLiveData()

        emitSource(playlistLiveData)

    }

}
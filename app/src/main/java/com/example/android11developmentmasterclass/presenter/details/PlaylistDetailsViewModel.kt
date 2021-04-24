package com.example.android11developmentmasterclass.presenter.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android11developmentmasterclass.domain.model.PlaylistDetailsModel
import com.example.android11developmentmasterclass.domain.usecase.GetPlaylistDetailsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class PlaylistDetailsViewModel(private val getPlaylistDetailsUseCase: GetPlaylistDetailsUseCase) :
    ViewModel() {

    val playlistDetails = MutableLiveData<PlaylistDetailsModel>()

    val loading = MutableLiveData<Boolean>()

    fun getPlaylistDetails(playlistId: String) {
        viewModelScope.launch {

            loading.postValue(true)

            getPlaylistDetailsUseCase.invoke(playlistId)
                .flowOn(Dispatchers.IO)
                .onCompletion {
                    loading.postValue(false)
                }
                .collect { result ->
                    playlistDetails.postValue(result)
                }

        }
    }

}
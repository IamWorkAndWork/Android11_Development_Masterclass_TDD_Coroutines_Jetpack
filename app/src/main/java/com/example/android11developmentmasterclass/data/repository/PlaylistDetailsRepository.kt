package com.example.android11developmentmasterclass.data.repository

import com.example.android11developmentmasterclass.api.PlaylistDetailsAPI
import com.example.android11developmentmasterclass.data.model.PlaylistDetailsResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.lang.RuntimeException

interface PlaylistDetailsRepository {
    suspend operator fun invoke(playlistId: String): Flow<Result<PlaylistDetailsResponse>>
}

class PlaylistDetailsRepositoryImpl
    (private val playlistDetailsAPI: PlaylistDetailsAPI) : PlaylistDetailsRepository {

    override suspend fun invoke(playlistId: String): Flow<Result<PlaylistDetailsResponse>> {
        delay(1500L)
        return flow {
            emit(Result.success(playlistDetailsAPI.fetchPlaylistDetails(id = playlistId)))
        }.catch {
            emit(Result.failure(RuntimeException("error ${it.localizedMessage}")))
        }
    }

}
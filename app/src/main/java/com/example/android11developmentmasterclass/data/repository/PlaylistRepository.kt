package com.example.android11developmentmasterclass.data.repository

import com.example.android11developmentmasterclass.api.PlaylistAPI
import com.example.android11developmentmasterclass.data.model.PlaylistResponse
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import java.lang.RuntimeException
import javax.inject.Inject

interface PlaylistRepository {
    suspend operator fun invoke(): Flow<Result<List<PlaylistResponse>>>
}

class PlaylistRepositoryImpl @Inject constructor(
    private val playlistAPI: PlaylistAPI) : PlaylistRepository {

    override suspend fun invoke(): Flow<Result<List<PlaylistResponse>>> {
        delay(1500L)
        return flow {
            val response = playlistAPI.fetchAllPlaylists()
            val result = Result.success(response)
            emit(result)
        }.catch {
            val errorCause = RuntimeException("something went wrong : ${it.localizedMessage}")
            emit(Result.failure(errorCause))
        }
    }

}
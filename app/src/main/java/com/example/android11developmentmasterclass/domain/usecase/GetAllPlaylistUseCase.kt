package com.example.android11developmentmasterclass.domain

import com.example.android11developmentmasterclass.data.repository.PlaylistRepository
import com.example.android11developmentmasterclass.domain.mapper.PlaylistMapper
import com.example.android11developmentmasterclass.domain.model.PlaylistModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

interface GetAllPlaylistUseCase {
    suspend operator fun invoke(): Flow<List<PlaylistModel>?>
    suspend fun invokeTDD(): Flow<Result<List<PlaylistModel>>>
}

@Singleton
class GetAllPlaylistUseCaseImpl @Inject constructor(
    private val playlistRepository: PlaylistRepository,
    private val playlistMapper: PlaylistMapper
) : GetAllPlaylistUseCase {

    override suspend fun invoke(): Flow<List<PlaylistModel>?> {
        return playlistRepository.invoke()
            .map {
                it.getOrNull()?.let {
                    playlistMapper(it)
                }
            }
    }

    override suspend fun invokeTDD(): Flow<Result<List<PlaylistModel>>> {
        return playlistRepository.invoke()
            .map {
                val data = it.getOrNull()?.let {
                    playlistMapper.invoke(it)
                }.orEmpty()
                Result.success(data)
            }
    }

}
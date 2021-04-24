package com.example.android11developmentmasterclass.domain.usecase

import com.example.android11developmentmasterclass.api.PlaylistDetailsAPI
import com.example.android11developmentmasterclass.data.repository.PlaylistDetailsRepository
import com.example.android11developmentmasterclass.domain.model.PlaylistDetailsModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

interface GetPlaylistDetailsUseCase {
    suspend operator fun invoke(playlistId: String): Flow<PlaylistDetailsModel>
}

class GetPlaylistDetailsUseCaseImpl(
    private val playlistDetailsRepository: PlaylistDetailsRepository
) : GetPlaylistDetailsUseCase {

    override suspend fun invoke(playlistId: String): Flow<PlaylistDetailsModel> {
        return playlistDetailsRepository.invoke(playlistId)
            .map {
                PlaylistDetailsModel(
                    id = it.getOrNull()?.id.orEmpty(),
                    name = it.getOrNull()?.name.orEmpty(),
                    details = it.getOrNull()?.details.orEmpty()
                )
            }
    }

}
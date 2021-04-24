package com.example.android11developmentmasterclass.domain.mapper

import com.example.android11developmentmasterclass.R
import com.example.android11developmentmasterclass.data.model.PlaylistResponse
import com.example.android11developmentmasterclass.domain.model.PlaylistModel
import javax.inject.Inject
import javax.inject.Singleton

interface PlaylistMapper {
    suspend operator fun invoke(playlistResponse: List<PlaylistResponse>): List<PlaylistModel>
}

@Singleton
class PlaylistMapperImpl @Inject constructor() :
    PlaylistMapper //: Function1<List<PlaylistRaw>, List<PlaylistModel>> {
{
    override suspend fun invoke(playlistResponse: List<PlaylistResponse>): List<PlaylistModel> {
        return playlistResponse.map {
            val image = when (it.category) {
                "rock" -> R.mipmap.rock
                else -> R.mipmap.playlist
            }

            PlaylistModel(
                id = it.id,
                name = it.name,
                category = it.category,
                image = image
            )
        }
    }
}

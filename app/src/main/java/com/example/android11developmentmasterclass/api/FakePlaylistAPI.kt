package com.example.android11developmentmasterclass.api

import com.example.android11developmentmasterclass.data.model.PlaylistResponse
import javax.inject.Singleton
import kotlin.random.Random

@Singleton
class FakePlaylistAPI : PlaylistAPI {

    override suspend fun fetchAllPlaylists(): List<PlaylistResponse> {
        val PlaylistRawList = mutableListOf<PlaylistResponse>()
        for (i in 0..100) {
            val randomCategoryNumber = Random.nextInt(1, 3)
            val categoryRandom = when (randomCategoryNumber) {
                1 -> "rock"
                else -> "playlist"
            }
            val item = PlaylistResponse(
                i.toString(),
                "name $i",
                categoryRandom
            )
            PlaylistRawList.add(item)
        }
        return PlaylistRawList
    }

}
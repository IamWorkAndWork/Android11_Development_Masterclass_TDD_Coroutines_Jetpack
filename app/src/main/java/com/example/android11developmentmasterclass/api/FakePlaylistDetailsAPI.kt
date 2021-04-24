package com.example.android11developmentmasterclass.api

import com.example.android11developmentmasterclass.data.model.PlaylistDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Singleton

@Singleton
class FakePlaylistDetailsAPI : PlaylistDetailsAPI {

    override suspend fun fetchPlaylistDetails(id: String): PlaylistDetailsResponse {
        return PlaylistDetailsResponse(
            id = "$id",
            name = "John $id",
            details = "this is details"
        )
    }

}
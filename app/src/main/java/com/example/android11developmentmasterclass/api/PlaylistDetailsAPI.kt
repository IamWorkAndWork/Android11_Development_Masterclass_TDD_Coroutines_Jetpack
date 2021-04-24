package com.example.android11developmentmasterclass.api

import com.example.android11developmentmasterclass.data.model.PlaylistDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PlaylistDetailsAPI {

    @GET("playlist-details/{id}")
    suspend fun fetchPlaylistDetails(@Path("id") id: String): PlaylistDetailsResponse

}
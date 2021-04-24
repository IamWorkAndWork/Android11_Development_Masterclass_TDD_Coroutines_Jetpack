package com.example.android11developmentmasterclass.di

import com.example.android11developmentmasterclass.api.FakePlaylistAPI
import com.example.android11developmentmasterclass.api.FakePlaylistDetailsAPI
import com.example.android11developmentmasterclass.api.PlaylistAPI
import com.example.android11developmentmasterclass.api.PlaylistDetailsAPI
import com.example.android11developmentmasterclass.data.repository.PlaylistDetailsRepository
import com.example.android11developmentmasterclass.data.repository.PlaylistDetailsRepositoryImpl
import com.example.android11developmentmasterclass.data.repository.PlaylistRepository
import com.example.android11developmentmasterclass.data.repository.PlaylistRepositoryImpl
import com.example.android11developmentmasterclass.domain.GetAllPlaylistUseCase
import com.example.android11developmentmasterclass.domain.GetAllPlaylistUseCaseImpl
import com.example.android11developmentmasterclass.domain.mapper.PlaylistMapper
import com.example.android11developmentmasterclass.domain.mapper.PlaylistMapperImpl
import com.example.android11developmentmasterclass.domain.usecase.GetPlaylistDetailsUseCase
import com.example.android11developmentmasterclass.domain.usecase.GetPlaylistDetailsUseCaseImpl
import com.jakewharton.espresso.OkHttp3IdlingResource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

const val BASE_URL = "http://192.168.1.5:2999/"
const val API_TYPE = "debug"//prod //BuildConfig.DEBUG
val client = OkHttpClient()
val idlingResource = OkHttp3IdlingResource.create("okhttp", client)

@Module
@InstallIn(FragmentComponent::class)
class PlaylistModule {

    @Provides
    @Named("prod")
    fun playlistAPI(retrofit: Retrofit) = retrofit.create(PlaylistAPI::class.java)

    @Provides
    @Named("debug")
    fun fakePlaylistAPI(): PlaylistAPI {
        return FakePlaylistAPI()
    }

    @Provides
    @Named("prod")
    fun playlistDetailsAPI(retrofit: Retrofit): PlaylistDetailsAPI {
        return retrofit.create(PlaylistDetailsAPI::class.java)
    }

    @Provides
    @Named("debug")
    fun fakePlaylistDetailsAPI(): PlaylistDetailsAPI {
        return FakePlaylistDetailsAPI()
    }

    @Provides
    fun retrofit() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun providePlaylistRepository(@Named(API_TYPE) playlistAPI: PlaylistAPI): PlaylistRepository {
        return PlaylistRepositoryImpl(playlistAPI = playlistAPI)
    }

    @Provides
    fun providePlaylistDetailsRepository(@Named(API_TYPE) playlistDetailsAPI: PlaylistDetailsAPI): PlaylistDetailsRepository {
        return PlaylistDetailsRepositoryImpl(playlistDetailsAPI = playlistDetailsAPI)
    }

    @Provides
    fun providePlaylistMapper(): PlaylistMapper {
        return PlaylistMapperImpl()
    }

    @Provides
    fun provideGetAllPlaylistUseCase(
        playlistRepository: PlaylistRepository,
        playlistMapper: PlaylistMapper
    ): GetAllPlaylistUseCase {
        return GetAllPlaylistUseCaseImpl(
            playlistRepository = playlistRepository,
            playlistMapper = playlistMapper
        )
    }

    @Provides
    fun provideGetPlaylistDetailsUseCase(playlistDetailsRepository: PlaylistDetailsRepository): GetPlaylistDetailsUseCase {
        return GetPlaylistDetailsUseCaseImpl(playlistDetailsRepository = playlistDetailsRepository)
    }

}
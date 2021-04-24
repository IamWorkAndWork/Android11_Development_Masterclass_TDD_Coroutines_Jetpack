package com.example.android11developmentmasterclass.utils

import com.example.android11developmentmasterclass.domain.GetAllPlaylistUseCase
import com.example.android11developmentmasterclass.domain.model.PlaylistModel
import com.example.android11developmentmasterclass.presenter.playlist.PlaylistViewModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert
import org.hamcrest.core.Is.`is`
import org.junit.Test

class PlaylistViewModelShould : BaseUnitTest() {

    val getAllPlaylistUseCase: GetAllPlaylistUseCase = mock()

    private val playlists = mock<List<PlaylistModel>>()
    private val expected = playlists
    private val exception = RuntimeException("Something went wrong")

    init {
    }

    @ExperimentalCoroutinesApi
    @Test
    fun getPlaylistsFromUseCase() = runBlockingTest {

        val viewModel = mockSuccessFulCase()

        val response = viewModel.playlists.getValueForTest()

        verify(getAllPlaylistUseCase, times(1)).invoke()

        MatcherAssert.assertThat(expected, `is`(response))

    }

    private fun mockSuccessFulCase(): PlaylistViewModel {
        runBlocking {
            whenever(getAllPlaylistUseCase.invoke())
                .thenReturn(
                    flow {
                        emit(expected)
                    }
                )
        }
        return PlaylistViewModel(getAllPlaylistUseCase)
    }

}
package com.example.githubclient

import com.example.githubclient.mvp.model.entity.GithubRepository
import com.example.githubclient.mvp.presenter.RepositoryPresenter
import com.example.githubclient.mvp.view.RepositoryView
import com.github.terrakok.cicerone.Router
import com.nhaarman.mockito_kotlin.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class RepositoryPresenterTest {

    private lateinit var presenter: RepositoryPresenter

    @Mock
    private lateinit var view: RepositoryView

    @Mock
    private lateinit var router: Router

    @Mock
    private lateinit var githubRepository: GithubRepository

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)

        githubRepository = mock()
        presenter = RepositoryPresenter(githubRepository)
        presenter.router = router
        presenter.attachView(view)
    }

    @Test
    fun onFirstViewAttach_Test() {
        whenever(githubRepository.id)
            .thenReturn("1")
            .thenReturn("2")
        whenever(githubRepository.name)
            .thenReturn("Ololo")
            .thenReturn("Ururu")
        whenever(githubRepository.forksCount)
            .thenReturn(5)
            .thenReturn(10)

        presenter.onFirstViewAttach()

        verify(githubRepository, atMost(2)).id
        verify(githubRepository, atMost(2)).name
        verify(githubRepository, atMost(2)).forksCount
    }

    @Test
    fun backPressed_Test() {
        presenter.backPressed()

        verify(router, times(1)).exit()
    }
}
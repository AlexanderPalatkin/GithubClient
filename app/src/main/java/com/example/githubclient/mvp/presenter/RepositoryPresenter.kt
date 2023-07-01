package com.example.githubclient.mvp.presenter

import com.example.githubclient.mvp.model.entity.GithubRepository
import com.example.githubclient.mvp.view.RepositoryView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter
import javax.inject.Inject

class RepositoryPresenter(private val githubRepository: GithubRepository) :
    MvpPresenter<RepositoryView>() {

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        viewState.setId(githubRepository.id)
        viewState.setTitle(githubRepository.name ?: "")
        viewState.setForksCount((githubRepository.forksCount ?: 0).toString())
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        println("onDestroy presenter")
    }
}
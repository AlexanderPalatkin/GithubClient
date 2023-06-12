package com.example.githubclient.mvp.presenter

import com.example.githubclient.mvp.view.IndividualUserView
import com.github.terrakok.cicerone.Router
import moxy.MvpPresenter

class IndividualUserPresenter(private val router: Router) : MvpPresenter<IndividualUserView>() {
    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}
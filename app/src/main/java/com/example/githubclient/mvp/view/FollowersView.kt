package com.example.githubclient.mvp.view

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType

@StateStrategyType(AddToEndSingleStrategy::class)
interface FollowersView : MvpView {
    fun init()
    fun setLogin(text: String)
    fun updateList()
}
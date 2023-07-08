package com.example.githubclient.mvp.presenter.list

import com.example.githubclient.mvp.view.list.UserItemView

interface IUserListPresenter : IListPresenter<UserItemView> {
    fun onRepositoriesClicked(view: UserItemView)
    fun onFollowersClicked(view: UserItemView)
}
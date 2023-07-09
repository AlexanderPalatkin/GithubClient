package com.example.githubclient.mvp.view.list

interface FollowerItemView : IItemView {
    fun setLogin(text: String)
    fun loadAvatar(url: String)
}
package com.example.githubclient.di.module.follower

import com.example.githubclient.di.module.follower.module.FollowerModule
import com.example.githubclient.mvp.presenter.FollowersPresenter
import com.example.githubclient.ui.adapter.FollowersRVAdapter
import dagger.Subcomponent

@FollowerScope
@Subcomponent(
    modules = [FollowerModule::class]
)
interface FollowerSubcomponent {
    fun inject(followersPresenter: FollowersPresenter)
    fun inject(followersRVAdapter: FollowersRVAdapter)
}
package com.example.githubclient.di.module.follower.module

import com.example.githubclient.App
import com.example.githubclient.di.module.follower.FollowerScope
import com.example.githubclient.di.module.follower.IFollowerScopeContainer
import com.example.githubclient.mvp.model.api.IDataSource
import com.example.githubclient.mvp.model.cache.IGithubFollowersCache
import com.example.githubclient.mvp.model.cache.room.RoomGithubFollowersCache
import com.example.githubclient.mvp.model.entity.room.DataBase
import com.example.githubclient.mvp.model.repo.IGithubFollowersRepo
import com.example.githubclient.mvp.model.repo.retrofit.RetrofitGithubFollowersRepo
import com.example.githubclient.mvp.network.INetworkStatus
import dagger.Module
import dagger.Provides

@Module
class FollowerModule {

    @Provides
    fun followersCache(dataBase: DataBase): IGithubFollowersCache =
        RoomGithubFollowersCache(dataBase)

    @FollowerScope
    @Provides
    fun followersRepo(
        api: IDataSource, networkStatus: INetworkStatus, cache:
        IGithubFollowersCache
    ): IGithubFollowersRepo = RetrofitGithubFollowersRepo(
        api,
        networkStatus, cache
    )

    @FollowerScope
    @Provides
    fun scopeContainer(app: App): IFollowerScopeContainer = app
}
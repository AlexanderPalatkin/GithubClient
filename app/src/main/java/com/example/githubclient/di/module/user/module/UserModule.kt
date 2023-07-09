package com.example.githubclient.di.module.user.module

import com.example.githubclient.App
import com.example.githubclient.di.module.user.IUserScopeContainer
import com.example.githubclient.di.module.user.UserScope
import com.example.githubclient.mvp.model.api.IDataSource
import com.example.githubclient.mvp.model.cache.IGithubUsersCache
import com.example.githubclient.mvp.model.cache.room.RoomGithubUsersCache
import com.example.githubclient.mvp.model.entity.room.DataBase
import com.example.githubclient.mvp.model.repo.IGithubUsersRepo
import com.example.githubclient.mvp.model.repo.retrofit.RetrofitGithubUsersRepo
import com.example.githubclient.mvp.network.INetworkStatus
import dagger.Module
import dagger.Provides

@Module
class UserModule {
    @Provides
    fun userCache(dataBase: DataBase): IGithubUsersCache = RoomGithubUsersCache(dataBase)

    @UserScope
    @Provides
    fun usersRepo(
        api: IDataSource, networkStatus: INetworkStatus, cache:
        IGithubUsersCache
    ): IGithubUsersRepo = RetrofitGithubUsersRepo(
        api,
        networkStatus, cache
    )

    @UserScope
    @Provides
    fun scopeContainer(app: App): IUserScopeContainer = app
}
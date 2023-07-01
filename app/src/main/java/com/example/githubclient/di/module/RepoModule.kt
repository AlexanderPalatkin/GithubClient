package com.example.githubclient.di.module

import androidx.room.Room
import com.example.githubclient.App
import com.example.githubclient.mvp.model.api.IDataSource
import com.example.githubclient.mvp.model.cache.IGithubRepositoriesCache
import com.example.githubclient.mvp.model.cache.IGithubUsersCache
import com.example.githubclient.mvp.model.entity.room.DataBase
import com.example.githubclient.mvp.model.repo.IGithubRepositoriesRepo
import com.example.githubclient.mvp.model.repo.IGithubUsersRepo
import com.example.githubclient.mvp.model.repo.retrofit.RetrofitGithubRepositoriesRepo
import com.example.githubclient.mvp.model.repo.retrofit.RetrofitGithubUsersRepo
import com.example.githubclient.mvp.network.INetworkStatus
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepoModule {

    @Singleton
    @Provides
    fun database(app: App): DataBase =
        Room.databaseBuilder(app, DataBase::class.java, DataBase.DB_NAME).build()

    @Singleton
    @Provides
    fun usersRepo(
        api: IDataSource, networkStatus: INetworkStatus, cache:
        IGithubUsersCache
    ): IGithubUsersRepo = RetrofitGithubUsersRepo(
        api,
        networkStatus, cache
    )

    @Singleton
    @Provides
    fun repositoriesRepo(
        api: IDataSource, networkStatus: INetworkStatus, cache:
        IGithubRepositoriesCache
    ): IGithubRepositoriesRepo = RetrofitGithubRepositoriesRepo(
        api,
        networkStatus, cache
    )
}
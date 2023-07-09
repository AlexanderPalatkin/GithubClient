package com.example.githubclient.di.module.repository.module

import com.example.githubclient.App
import com.example.githubclient.di.module.repository.IRepositoryScopeContainer
import com.example.githubclient.di.module.repository.RepositoryScope
import com.example.githubclient.mvp.model.api.IDataSource
import com.example.githubclient.mvp.model.cache.IGithubRepositoriesCache
import com.example.githubclient.mvp.model.cache.room.RoomGithubRepositoriesCache
import com.example.githubclient.mvp.model.entity.room.DataBase
import com.example.githubclient.mvp.model.repo.IGithubRepositoriesRepo
import com.example.githubclient.mvp.model.repo.retrofit.RetrofitGithubRepositoriesRepo
import com.example.githubclient.mvp.network.INetworkStatus
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun repositoriesCache(dataBase: DataBase): IGithubRepositoriesCache =
        RoomGithubRepositoriesCache(dataBase)

    @RepositoryScope
    @Provides
    fun repositoriesRepo(
        api: IDataSource, networkStatus: INetworkStatus, cache:
        IGithubRepositoriesCache
    ): IGithubRepositoriesRepo = RetrofitGithubRepositoriesRepo(
        api,
        networkStatus, cache
    )

    @RepositoryScope
    @Provides
    fun scopeContainer(app: App): IRepositoryScopeContainer = app
}
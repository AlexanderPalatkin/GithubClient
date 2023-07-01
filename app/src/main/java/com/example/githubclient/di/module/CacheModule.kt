package com.example.githubclient.di.module

import com.example.githubclient.mvp.model.cache.IGithubRepositoriesCache
import com.example.githubclient.mvp.model.cache.IGithubUsersCache
import com.example.githubclient.mvp.model.cache.room.RoomGithubRepositoriesCache
import com.example.githubclient.mvp.model.cache.room.RoomGithubUsersCache
import com.example.githubclient.mvp.model.entity.room.DataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CacheModule {

    @Singleton
    @Provides
    fun userCache(dataBase: DataBase): IGithubUsersCache = RoomGithubUsersCache(dataBase)

    @Singleton
    @Provides
    fun repositoriesCache(dataBase: DataBase): IGithubRepositoriesCache =
        RoomGithubRepositoriesCache(dataBase)
}
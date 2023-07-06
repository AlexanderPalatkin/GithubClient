package com.example.githubclient.di.module

import androidx.room.Room
import com.example.githubclient.App
import com.example.githubclient.mvp.model.entity.room.DataBase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Singleton
    @Provides
    fun database(app: App): DataBase =
        Room.databaseBuilder(app, DataBase::class.java, DataBase.DB_NAME).build()
}
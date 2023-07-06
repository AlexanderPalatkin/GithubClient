package com.example.githubclient

import android.app.Application
import com.example.githubclient.di.AppComponent
import com.example.githubclient.di.DaggerAppComponent
import com.example.githubclient.di.module.AppModule
import com.example.githubclient.di.module.repository.IRepositoryScopeContainer
import com.example.githubclient.di.module.repository.RepositorySubcomponent
import com.example.githubclient.di.module.user.IUserScopeContainer
import com.example.githubclient.di.module.user.UserSubcomponent

class App : Application(), IUserScopeContainer, IRepositoryScopeContainer {
    companion object {
        lateinit var instance: App
    }

    lateinit var appComponent: AppComponent
        private set

    var userSubcomponent: UserSubcomponent? = null
        private set

    var repositorySubcomponent: RepositorySubcomponent? = null
        private set

    override fun onCreate() {
        super.onCreate()
        instance = this

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

    }

    fun initUserSubcomponent() = appComponent.userSubcomponent().also {
        userSubcomponent = it
    }

    fun initRepositorySubcomponent() = userSubcomponent?.repositorySubcomponent().also {
        repositorySubcomponent = it
    }

    override fun releaseUserScope() {
        userSubcomponent = null
    }

    override fun releaseRepositoryScope() {
        repositorySubcomponent = null
    }

}
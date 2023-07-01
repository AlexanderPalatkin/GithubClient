package com.example.githubclient.di

import com.example.githubclient.di.module.*
import com.example.githubclient.mvp.presenter.MainPresenter
import com.example.githubclient.mvp.presenter.UsersPresenter
import com.example.githubclient.ui.activity.MainActivity
import com.example.githubclient.ui.fragment.RepositoryFragment
import com.example.githubclient.ui.fragment.UserFragment
import com.example.githubclient.ui.fragment.UsersFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        CiceroneModule::class,
        CacheModule::class,
        ApiModule::class,
        RepoModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
    fun inject(usersPresenter: UsersPresenter)

    fun inject(userFragment: UserFragment)
    fun inject(usersFragment: UsersFragment)
    fun inject(repositoryFragment: RepositoryFragment)
}

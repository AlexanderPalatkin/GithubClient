package com.example.githubclient.di.module.user

import com.example.githubclient.di.module.repository.RepositorySubcomponent
import com.example.githubclient.di.module.user.module.UserModule
import com.example.githubclient.mvp.presenter.UsersPresenter
import com.example.githubclient.ui.adapter.UsersRVAdapter
import dagger.Subcomponent

@UserScope
@Subcomponent(
    modules = [UserModule::class]
)
interface UserSubcomponent {
    fun repositorySubcomponent(): RepositorySubcomponent
    fun inject(usersPresenter: UsersPresenter)
    fun inject(usersRVAdapter: UsersRVAdapter)
}
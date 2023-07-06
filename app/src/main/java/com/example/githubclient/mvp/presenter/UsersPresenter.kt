package com.example.githubclient.mvp.presenter

import com.example.githubclient.di.module.user.IUserScopeContainer
import com.example.githubclient.mvp.model.entity.GithubUser
import com.example.githubclient.mvp.model.repo.IGithubUsersRepo
import com.example.githubclient.mvp.presenter.list.IUserListPresenter
import com.example.githubclient.mvp.view.UsersView
import com.example.githubclient.mvp.view.list.UserItemView
import com.example.githubclient.navigation.IScreens
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.disposables.Disposable
import moxy.MvpPresenter
import javax.inject.Inject
import javax.inject.Named

class UsersPresenter :
    MvpPresenter<UsersView>() {

    @Inject
    lateinit var usersRepo: IGithubUsersRepo

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: IScreens

    @Inject
    @Named("mainThreadScheduler")
    lateinit var mainThreadScheduler: Scheduler

    @Inject
    lateinit var userScopeContainer: IUserScopeContainer

    class UsersListPresenter : IUserListPresenter {
        val users = mutableListOf<GithubUser>()

        override var itemClickListener: ((UserItemView) -> Unit)? = null
        override fun getCount() = users.size
        override fun bindView(view: UserItemView) {
            val user = users[view.pos]
            user.login.let {
                view.setLogin(it)
            }
            user.avatarUrl?.let {
                view.loadAvatar(it)
            }
        }
    }

    private var disposable: Disposable? = null

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.init()
        loadData()

        usersListPresenter.itemClickListener = {
            val user = usersListPresenter.users[it.pos]
            router.navigateTo(screens.user(user))
        }
    }

    private fun loadData() {
        usersRepo.getUsers().observeOn(mainThreadScheduler).subscribe { listGithubUsers ->
            usersListPresenter.users.clear()
            usersListPresenter.users.addAll(listGithubUsers)
            viewState.updateList()
        }
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        userScopeContainer.releaseUserScope()
        super.onDestroy()
        disposable?.dispose()
    }
}

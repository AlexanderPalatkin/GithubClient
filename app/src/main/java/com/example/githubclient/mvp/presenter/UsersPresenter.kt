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

        private val repositoriesClickObservers = mutableListOf<(UserItemView) -> Unit>()
        private val followersClickObservers = mutableListOf<(UserItemView) -> Unit>()

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

        override fun onRepositoriesClicked(view: UserItemView) {
            repositoriesClickObservers.forEach { observer ->
                observer.invoke(view)
            }
        }

        override fun onFollowersClicked(view: UserItemView) {
            followersClickObservers.forEach { observer ->
                observer.invoke(view)
            }
        }

        fun addRepositoriesObserver(observer: (UserItemView) -> Unit) {
            repositoriesClickObservers.add(observer)
        }

        fun addFollowersObserver(observer: (UserItemView) -> Unit) {
            followersClickObservers.add(observer)
        }

        fun removeRepositoriesObserver(observer: (UserItemView) -> Unit) {
            repositoriesClickObservers.remove(observer)
        }

        fun removeFollowersObserver(observer: (UserItemView) -> Unit) {
            followersClickObservers.remove(observer)
        }
    }

    private lateinit var repositoriesObserver: (UserItemView) -> Unit
    private lateinit var followersObserver: (UserItemView) -> Unit

    val usersListPresenter = UsersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.init()
        loadData()

        repositoriesObserver = { userItemView ->
            val user = usersListPresenter.users[userItemView.pos]
            router.navigateTo(screens.user(user))
        }

        followersObserver = { userItemView ->
            val user = usersListPresenter.users[userItemView.pos]
            router.navigateTo(screens.followers(user))
        }

        usersListPresenter.addRepositoriesObserver(repositoriesObserver)
        usersListPresenter.addFollowersObserver(followersObserver)
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
        usersListPresenter.removeRepositoriesObserver(repositoriesObserver)
        usersListPresenter.removeFollowersObserver(followersObserver)
        super.onDestroy()
    }
}

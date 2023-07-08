package com.example.githubclient.mvp.presenter

import com.example.githubclient.di.module.follower.IFollowerScopeContainer
import com.example.githubclient.mvp.model.entity.GithubFollower
import com.example.githubclient.mvp.model.entity.GithubUser
import com.example.githubclient.mvp.model.entity.room.DataBase
import com.example.githubclient.mvp.model.repo.IGithubFollowersRepo
import com.example.githubclient.mvp.presenter.list.IFollowersListPresenter
import com.example.githubclient.mvp.view.FollowersView
import com.example.githubclient.mvp.view.list.FollowerItemView
import com.example.githubclient.navigation.IScreens
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import javax.inject.Inject
import javax.inject.Named

class FollowersPresenter(private val user: GithubUser) : MvpPresenter<FollowersView>() {
    @Inject
    lateinit var dataBase: DataBase

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: IScreens

    @Inject
    lateinit var followersRepo: IGithubFollowersRepo

    @Inject
    @Named("mainThreadScheduler")
    lateinit var mainThreadScheduler: Scheduler

    @Inject
    lateinit var followersScopeContainer: IFollowerScopeContainer

    class FollowersListPresenter : IFollowersListPresenter {
        val followers = mutableListOf<GithubFollower>()

        override var itemClickListener: ((FollowerItemView) -> Unit)? = null
        override fun getCount(): Int = followers.size
        override fun bindView(view: FollowerItemView) {
            val follower = followers[view.pos]
            follower.login.let {
                view.setLogin(it)
            }
            follower.avatarUrl?.let {
                view.loadAvatar(it)
            }
        }
    }

    val followersListPresenter = FollowersListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()

        loadData()

        user.login.let { viewState.setLogin(it) }
    }

    private fun loadData() {
        followersRepo.getFollowers(user)
            .observeOn(mainThreadScheduler)
            .subscribe({ followers ->
                followersListPresenter.followers.clear()
                followersListPresenter.followers.addAll(followers)
                viewState.updateList()
            }, {
                println("Error: ${it.message}")
            })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    override fun onDestroy() {
        followersScopeContainer.releaseFollowerScope()
        super.onDestroy()
    }
}
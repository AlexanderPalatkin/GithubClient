package com.example.githubclient.mvp.presenter

import com.example.githubclient.di.module.repository.IRepositoryScopeContainer
import com.example.githubclient.mvp.model.entity.GithubRepository
import com.example.githubclient.mvp.model.entity.GithubUser
import com.example.githubclient.mvp.model.repo.IGithubRepositoriesRepo
import com.example.githubclient.mvp.presenter.list.IRepositoryListPresenter
import com.example.githubclient.mvp.view.UserView
import com.example.githubclient.mvp.view.list.RepositoryItemView
import com.example.githubclient.navigation.IScreens
import com.github.terrakok.cicerone.Router
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import javax.inject.Inject
import javax.inject.Named

class UserPresenter(private val user: GithubUser) : MvpPresenter<UserView>() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var screens: IScreens

    @Inject
    lateinit var repositoriesRepo: IGithubRepositoriesRepo

    @Inject
    @Named("mainThreadScheduler")
    lateinit var mainThreadScheduler: Scheduler

    @Inject
    lateinit var repositoryScopeContainer: IRepositoryScopeContainer

    class RepositoriesListPresenter : IRepositoryListPresenter {
        val repositories = mutableListOf<GithubRepository>()

        override var itemClickListener: ((RepositoryItemView) -> Unit)? = null
        override fun getCount(): Int = repositories.size
        override fun bindView(view: RepositoryItemView) {
            val repository = repositories[view.pos]
            repository.name?.let {
                view.setName(it)
            }
        }
    }

    val repositoriesListPresenter = RepositoriesListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()

        loadData()

        user.login.let { viewState.setLogin(it) }

        repositoriesListPresenter.itemClickListener = { itemView ->
            val repository = repositoriesListPresenter.repositories[itemView.pos]
            router.navigateTo(screens.repository(repository))
        }
    }

    private fun loadData() {
        repositoriesRepo.getRepositories(user)
            .observeOn(mainThreadScheduler)
            .subscribe({ repositories ->
                repositoriesListPresenter.repositories.clear()
                repositoriesListPresenter.repositories.addAll(repositories)
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
        repositoryScopeContainer.releaseRepositoryScope()
        super.onDestroy()
    }
}
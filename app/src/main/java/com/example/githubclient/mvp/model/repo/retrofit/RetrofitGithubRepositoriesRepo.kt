package com.example.githubclient.mvp.model.repo.retrofit

import com.example.githubclient.mvp.model.api.IDataSource
import com.example.githubclient.mvp.model.entity.GithubRepository
import com.example.githubclient.mvp.model.repo.IGithubRepositoriesRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RetrofitGithubRepositoriesRepo(private val api: IDataSource) : IGithubRepositoriesRepo {
    override fun getRepositories(url: String): Single<List<GithubRepository>> =
        api.getRepositories(url).subscribeOn(Schedulers.io())
}
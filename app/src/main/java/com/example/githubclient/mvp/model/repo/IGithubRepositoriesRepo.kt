package com.example.githubclient.mvp.model.repo

import com.example.githubclient.mvp.model.entity.GithubRepository
import io.reactivex.rxjava3.core.Single

interface IGithubRepositoriesRepo {
    fun getRepositories(url: String): Single<List<GithubRepository>>
}
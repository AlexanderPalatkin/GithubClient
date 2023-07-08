package com.example.githubclient.mvp.model.repo

import com.example.githubclient.mvp.model.entity.GithubFollower
import com.example.githubclient.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Single

interface IGithubFollowersRepo {
    fun getFollowers(user: GithubUser): Single<List<GithubFollower>>
}
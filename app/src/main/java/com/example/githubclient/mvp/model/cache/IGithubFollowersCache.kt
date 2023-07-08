package com.example.githubclient.mvp.model.cache

import com.example.githubclient.mvp.model.entity.GithubFollower
import com.example.githubclient.mvp.model.entity.GithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

interface IGithubFollowersCache {
    fun getUserFollowers(user: GithubUser): Single<List<GithubFollower>>
    fun putUserFollowers(user: GithubUser, followers: List<GithubFollower>): Completable
}
package com.example.githubclient.mvp.model.cache.room

import com.example.githubclient.mvp.model.cache.IGithubFollowersCache
import com.example.githubclient.mvp.model.entity.GithubFollower
import com.example.githubclient.mvp.model.entity.GithubUser
import com.example.githubclient.mvp.model.entity.room.DataBase
import com.example.githubclient.mvp.model.entity.room.RoomGithubFollower
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomGithubFollowersCache(private val db: DataBase) : IGithubFollowersCache {
    override fun getUserFollowers(user: GithubUser): Single<List<GithubFollower>> =
        Single.fromCallable {
            val roomUser =
                db.userDao.findByLogin(user.login)
                    ?: throw RuntimeException("No such user in cache")

            return@fromCallable db.followerDao.findForUser(roomUser.id).map {
                GithubFollower(it.id, it.login, it.avatarUrl)
            }
        }.subscribeOn(Schedulers.io())

    override fun putUserFollowers(user: GithubUser, followers: List<GithubFollower>): Completable =
        Completable.fromAction {
            val roomUser =
                db.userDao.findByLogin(user.login)
                    ?: throw RuntimeException("No such user in cache")

            val roomFollowers = followers.map {
                RoomGithubFollower(it.id, it.login, it.avatarUrl ?: "", roomUser.id)
            }

            db.followerDao.insert(roomFollowers)
        }.subscribeOn(Schedulers.io())
}
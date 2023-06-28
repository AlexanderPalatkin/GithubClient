package com.example.githubclient.mvp.model.cache.room

import com.example.githubclient.mvp.model.cache.IGithubUsersCache
import com.example.githubclient.mvp.model.entity.GithubUser
import com.example.githubclient.mvp.model.entity.room.DataBase
import com.example.githubclient.mvp.model.entity.room.RoomGithubUser
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomGithubUsersCache(private val db: DataBase) : IGithubUsersCache {
    override fun getUsers(): Single<List<GithubUser>> = Single.fromCallable {
        db.userDao.getAll().map { roomUser ->
            GithubUser(roomUser.id, roomUser.login, roomUser.avatarUrl, roomUser.reposUrl)
        }
    }.subscribeOn(Schedulers.io())

    override fun putUsers(users: List<GithubUser>): Completable = Completable.fromAction {
        val roomUsers = users.map { user ->
            RoomGithubUser(user.id, user.login, user.avatarUrl ?: "", user.reposUrl ?: "")

        }
        db.userDao.insert(roomUsers)
    }.subscribeOn(Schedulers.io())
}
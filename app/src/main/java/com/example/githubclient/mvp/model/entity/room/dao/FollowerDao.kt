package com.example.githubclient.mvp.model.entity.room.dao

import androidx.room.*
import com.example.githubclient.mvp.model.entity.room.RoomGithubFollower

@Dao
interface FollowerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(follower: RoomGithubFollower)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg followers: RoomGithubFollower)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(followers: List<RoomGithubFollower>)

    @Update
    fun update(follower: RoomGithubFollower)

    @Update
    fun update(vararg followers: RoomGithubFollower)

    @Update
    fun update(followers: List<RoomGithubFollower>)

    @Delete
    fun delete(follower: RoomGithubFollower)

    @Delete
    fun delete(vararg followers: RoomGithubFollower)

    @Delete
    fun delete(followers: List<RoomGithubFollower>)

    @Query("SELECT * FROM RoomGithubFollower")
    fun getAll(): List<RoomGithubFollower>

    @Query("SELECT * FROM RoomGithubFollower WHERE userId = :userId")
    fun findForUser(userId: String): List<RoomGithubFollower>
}

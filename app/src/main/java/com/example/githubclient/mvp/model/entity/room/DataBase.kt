package com.example.githubclient.mvp.model.entity.room

import android.content.Context

import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.githubclient.mvp.model.entity.room.dao.RepositoryDao
import com.example.githubclient.mvp.model.entity.room.dao.UserDao

@androidx.room.Database(entities = [RoomGithubUser::class, RoomGithubRepository::class], version = 1)
abstract class DataBase : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val repositoryDao: RepositoryDao

    companion object {
        const val DB_NAME = "database.db"
        private var instance: DataBase? = null
        fun getInstance() = instance ?: throw RuntimeException(
            "Database has not been created. Please call create(context)"
        )

        fun create(context: Context?) {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context!!, DataBase::class.java,
                    DB_NAME
                )
                    .build()
            }
        }
    }
}
package com.example.githubclient.navigation

import com.example.githubclient.mvp.model.entity.GithubRepository
import com.example.githubclient.mvp.model.entity.GithubUser
import com.github.terrakok.cicerone.Screen

interface IScreens {
    fun users(): Screen
    fun user(user: GithubUser): Screen
    fun repository(repository: GithubRepository): Screen
}
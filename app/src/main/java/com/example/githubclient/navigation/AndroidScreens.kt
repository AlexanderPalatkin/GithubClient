package com.example.githubclient.navigation

import com.example.githubclient.mvp.model.entity.GithubRepository
import com.example.githubclient.mvp.model.entity.GithubUser
import com.example.githubclient.ui.fragment.FollowersFragment
import com.example.githubclient.ui.fragment.RepositoryFragment
import com.example.githubclient.ui.fragment.UserFragment
import com.example.githubclient.ui.fragment.UsersFragment
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun users(): Screen = FragmentScreen { UsersFragment.newInstance() }

    override fun user(user: GithubUser): Screen {
        return FragmentScreen { UserFragment.newInstance(user) }
    }

    override fun repository(repository: GithubRepository): Screen {
        return FragmentScreen { RepositoryFragment.newInstance(repository) }
    }

    override fun followers(user: GithubUser): Screen {
        return FragmentScreen { FollowersFragment.newInstance(user) }
    }
}
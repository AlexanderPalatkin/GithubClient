package com.example.githubclient.navigation

import com.example.githubclient.ui.fragment.IndividualUserFragment
import com.example.githubclient.ui.fragment.UsersFragment
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class AndroidScreens : IScreens {
    override fun users(): Screen = FragmentScreen { UsersFragment.newInstance() }

    override fun individualUser(login: String): Screen {
        return FragmentScreen { IndividualUserFragment.newInstance(login) }
    }
}
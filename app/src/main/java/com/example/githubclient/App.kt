package com.example.githubclient

import android.app.Application
import com.example.githubclient.navigation.IScreens
import com.example.githubclient.ui.fragment.IndividualUserFragment
import com.example.githubclient.ui.fragment.UsersFragment
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.FragmentScreen

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    private val cicerone: Cicerone<Router> by lazy {
        Cicerone.create()
    }
    val navigatorHolder get() = cicerone.getNavigatorHolder()
    val router get() = cicerone.router

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    object Screens : IScreens {
        override fun users(): Screen = FragmentScreen { UsersFragment.newInstance() }

        override fun individualUser(login: String): Screen {
            return FragmentScreen { IndividualUserFragment.newInstance(login) }
        }
    }
}
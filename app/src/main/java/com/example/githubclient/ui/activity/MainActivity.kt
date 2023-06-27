package com.example.githubclient.ui.activity

import android.os.Bundle
import com.example.githubclient.App
import com.example.githubclient.R
import com.example.githubclient.databinding.ActivityMainBinding
import com.example.githubclient.mvp.presenter.MainPresenter
import com.example.githubclient.mvp.view.MainView
import com.github.terrakok.cicerone.androidx.AppNavigator
import moxy.MvpAppCompatActivity
import moxy.ktx.moxyPresenter

class MainActivity : MvpAppCompatActivity(), MainView {
    private lateinit var vb: ActivityMainBinding
    private val navigator = AppNavigator(this, R.id.container)
    private val presenter by moxyPresenter {
        MainPresenter(
            App.instance.router,
            App.instance.screens
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb.root)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()

        App.instance.navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()

        App.instance.navigatorHolder.removeNavigator()
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        supportFragmentManager.fragments.forEach {
            if (it is BackButtonListener && it.backPressed()) {
                return
            }
        }

        presenter.backClicked()
    }
}
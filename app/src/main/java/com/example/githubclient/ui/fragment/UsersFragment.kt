package com.example.githubclient.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubclient.App
import com.example.githubclient.databinding.FragmentUsersBinding
import com.example.githubclient.di.module.user.UserSubcomponent
import com.example.githubclient.mvp.presenter.UsersPresenter
import com.example.githubclient.mvp.view.UsersView
import com.example.githubclient.ui.activity.BackButtonListener
import com.example.githubclient.ui.adapter.UsersRVAdapter
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UsersFragment : MvpAppCompatFragment(), UsersView, BackButtonListener {

    private var _vb: FragmentUsersBinding? = null
    private val vb get() = _vb!!

    private var userSubcomponent: UserSubcomponent? = null
    private lateinit var adapter: UsersRVAdapter

    val presenter: UsersPresenter by moxyPresenter {
        UsersPresenter().apply {
            userSubcomponent = App.instance.initUserSubcomponent()
            userSubcomponent?.inject(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _vb = FragmentUsersBinding.inflate(inflater, container, false)
        return vb.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _vb = null
    }

    override fun init() {
        vb.rvUsers.layoutManager = LinearLayoutManager(context)
        adapter = UsersRVAdapter(presenter.usersListPresenter).apply {
            userSubcomponent?.inject(this)
        }
        vb.rvUsers.adapter = adapter
    }

    override fun updateList() {
        adapter.notifyItemRangeChanged(0, adapter.itemCount)
    }

    companion object {
        fun newInstance() = UsersFragment()
    }

    override fun backPressed() = presenter.backPressed()
}

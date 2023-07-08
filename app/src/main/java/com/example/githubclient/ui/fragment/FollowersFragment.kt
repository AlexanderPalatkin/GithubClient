package com.example.githubclient.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubclient.App
import com.example.githubclient.databinding.FragmentFollowersBinding
import com.example.githubclient.di.module.follower.FollowerSubcomponent
import com.example.githubclient.mvp.model.entity.GithubUser
import com.example.githubclient.mvp.presenter.FollowersPresenter
import com.example.githubclient.mvp.view.FollowersView
import com.example.githubclient.ui.activity.BackButtonListener
import com.example.githubclient.ui.adapter.FollowersRVAdapter
import com.example.githubclient.utils.KEY_FOLLOWERS_FRAGMENT
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class FollowersFragment : MvpAppCompatFragment(), FollowersView, BackButtonListener {

    private var _binding: FragmentFollowersBinding? = null
    private val binding get() = _binding!!

    private var followerSubcomponent: FollowerSubcomponent? = null
    private lateinit var adapter: FollowersRVAdapter

    val presenter: FollowersPresenter by moxyPresenter {
        val user = arguments?.getParcelable<GithubUser>(KEY_FOLLOWERS_FRAGMENT) as GithubUser

        FollowersPresenter(
            user
        ).apply {
            followerSubcomponent = App.instance.initFollowerSubcomponent()
            followerSubcomponent?.inject(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFollowersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance(user: GithubUser) = FollowersFragment().apply {
            arguments = Bundle().apply {
                putParcelable(KEY_FOLLOWERS_FRAGMENT, user)
            }
        }
    }

    override fun backPressed() = presenter.backPressed()
    override fun init() {
        binding.rvUserFollowers.layoutManager = LinearLayoutManager(context)
        adapter = FollowersRVAdapter(presenter.followersListPresenter).apply {
            followerSubcomponent?.inject(this)
        }
        binding.rvUserFollowers.adapter = adapter
    }

    override fun setLogin(text: String) {
        binding.tvUserLogin.text = text
    }

    override fun updateList() {
        adapter.notifyItemRangeChanged(0, adapter.itemCount)
    }
}
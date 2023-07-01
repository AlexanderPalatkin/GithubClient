package com.example.githubclient.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubclient.App
import com.example.githubclient.databinding.FragmentUserBinding
import com.example.githubclient.mvp.model.entity.GithubUser
import com.example.githubclient.mvp.presenter.UserPresenter
import com.example.githubclient.mvp.view.UserView
import com.example.githubclient.ui.activity.BackButtonListener
import com.example.githubclient.ui.adapter.RepositoriesRVAdapter
import com.example.githubclient.utils.KEY_USER_FRAGMENT
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class UserFragment : MvpAppCompatFragment(), UserView, BackButtonListener {

    private var _binding: FragmentUserBinding? = null
    private val binding get() = _binding!!

    val presenter: UserPresenter by moxyPresenter {
        val user = arguments?.getParcelable<GithubUser>(KEY_USER_FRAGMENT) as GithubUser

        UserPresenter(
            user
        ).apply {
            App.instance.appComponent.inject(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private lateinit var adapter: RepositoriesRVAdapter

    companion object {
        fun newInstance(user: GithubUser) = UserFragment().apply {
            arguments = Bundle().apply {
                putParcelable(KEY_USER_FRAGMENT, user)
            }
        }
    }

    override fun backPressed() = presenter.backPressed()
    override fun init() {
        binding.rvUserRepositories.layoutManager = LinearLayoutManager(context)
        adapter = RepositoriesRVAdapter(presenter.repositoriesListPresenter)
        binding.rvUserRepositories.adapter = adapter
    }

    override fun setLogin(text: String) {
        binding.tvLogin.text = text
    }

    override fun updateList() {
        adapter.notifyItemRangeChanged(0, adapter.itemCount)
    }
}
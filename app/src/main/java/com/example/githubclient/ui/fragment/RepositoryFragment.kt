package com.example.githubclient.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.githubclient.App
import com.example.githubclient.R
import com.example.githubclient.databinding.FragmentRepositoryBinding
import com.example.githubclient.mvp.model.entity.GithubRepository
import com.example.githubclient.mvp.presenter.RepositoryPresenter
import com.example.githubclient.mvp.view.RepositoryView
import com.example.githubclient.ui.activity.BackButtonListener
import com.example.githubclient.utils.KEY_REPOSITORY_FRAGMENT
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class RepositoryFragment : MvpAppCompatFragment(), RepositoryView, BackButtonListener {

    private var _binding: FragmentRepositoryBinding? = null
    private val binding
        get() = _binding!!

    companion object {
        fun newInstance(repository: GithubRepository) = RepositoryFragment().apply {
            arguments = Bundle().apply {
                putParcelable(KEY_REPOSITORY_FRAGMENT, repository)
            }
        }
    }

    val presenter: RepositoryPresenter by moxyPresenter {
        val repository =
            arguments?.getParcelable<GithubRepository>(KEY_REPOSITORY_FRAGMENT) as GithubRepository

        RepositoryPresenter(repository).apply {
            App.instance.repositorySubcomponent?.inject(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        FragmentRepositoryBinding.inflate(inflater, container, false).also {
            _binding = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun init() {}

    override fun setId(text: String) {
        val repositoryIdString = getString(R.string.repository_id)
        val repositoryIdText = "$repositoryIdString $text"
        binding.tvId.text = repositoryIdText
    }

    override fun setTitle(text: String) {
        binding.tvTitle.text = text
    }

    override fun setForksCount(text: String) {
        val repositoryForksCountString = getString(R.string.repository_forks_count)
        val repositoryForksCountText = "$repositoryForksCountString $text"
        binding.tvForksCount.text = repositoryForksCountText
    }

    override fun backPressed() = presenter.backPressed()
}
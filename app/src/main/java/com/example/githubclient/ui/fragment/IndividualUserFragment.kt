package com.example.githubclient.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.githubclient.App
import com.example.githubclient.databinding.FragmentIndividualUserBinding
import com.example.githubclient.mvp.presenter.IndividualUserPresenter
import com.example.githubclient.mvp.view.IndividualUserView
import com.example.githubclient.ui.activity.BackButtonListener
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter


class IndividualUserFragment : MvpAppCompatFragment(), IndividualUserView, BackButtonListener {

    private var _binding: FragmentIndividualUserBinding? = null
    private val binding get() = _binding!!

    val presenter: IndividualUserPresenter by moxyPresenter {
        IndividualUserPresenter(App.instance.router)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIndividualUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance() = IndividualUserFragment()
    }

    override fun backPressed() = presenter.backPressed()
}
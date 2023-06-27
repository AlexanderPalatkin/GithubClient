package com.example.githubclient.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.githubclient.databinding.ItemUserRepositoryBinding
import com.example.githubclient.mvp.presenter.list.IRepositoryListPresenter
import com.example.githubclient.mvp.view.list.RepositoryItemView
import com.example.githubclient.utils.INVALID_VALUE

class RepositoriesRVAdapter(val presenter: IRepositoryListPresenter) :
    RecyclerView.Adapter<RepositoriesRVAdapter.ViewHolder>() {

    inner class ViewHolder(private val vb: ItemUserRepositoryBinding) :
        RecyclerView.ViewHolder(vb.root), RepositoryItemView {
        override var pos = INVALID_VALUE

        override fun setName(name: String) {
            vb.tvRepository.text = name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            ItemUserRepositoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        ).apply {
            itemView.setOnClickListener {
                presenter.itemClickListener?.invoke(this)
            }
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        presenter.bindView(holder.apply {
            pos = position
        })

    override fun getItemCount() = presenter.getCount()
}
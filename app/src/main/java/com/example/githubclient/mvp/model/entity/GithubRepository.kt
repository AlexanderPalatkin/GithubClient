package com.example.githubclient.mvp.model.entity

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubRepository(
    @Expose val id: String,
    @Expose val name: String? = null,
    @Expose val forksCount: Int? = null
) : Parcelable


package com.example.githubclient.di.module

import android.widget.ImageView
import com.example.githubclient.mvp.view.IImageLoader
import com.example.githubclient.ui.image.GlideImageLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ImageModule {

    @Singleton
    @Provides
    fun imageLoader(): IImageLoader<ImageView> = GlideImageLoader()
}
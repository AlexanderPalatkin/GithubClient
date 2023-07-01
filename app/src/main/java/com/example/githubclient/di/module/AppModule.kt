package com.example.githubclient.di.module

import com.example.githubclient.App
import dagger.Module
import dagger.Provides
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import javax.inject.Named

@Module
class AppModule(val app: App) {

    @Provides
    fun app(): App {
        return app
    }

    @Named("mainThreadScheduler")
    @Provides
    fun mainThreadScheduler(): Scheduler = AndroidSchedulers.mainThread()
}
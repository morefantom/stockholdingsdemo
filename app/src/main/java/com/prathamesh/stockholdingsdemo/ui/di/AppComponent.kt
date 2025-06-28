package com.prathamesh.stockholdingsdemo.ui.di

import com.prathamesh.stockholdingsdemo.data.remote.di.RemoteDataModule
import com.prathamesh.stockholdingsdemo.domain.di.DomainModule
import com.prathamesh.stockholdingsdemo.ui.commons.di.UiCommonsModule
import com.prathamesh.stockholdingsdemo.ui.main.di.MainActivityComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RemoteDataModule::class,
        DomainModule::class,
        UiCommonsModule::class,
    ]
)
interface AppComponent {
    fun mainActivityComponent(): MainActivityComponent.Factory
}
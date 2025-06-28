package com.prathamesh.stockholdingsdemo.ui.main.di

import com.prathamesh.stockholdingsdemo.ui.main.view.MainActivity
import dagger.Subcomponent

@MainActivityScope
@Subcomponent
interface MainActivityComponent {
    fun inject(activity: MainActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainActivityComponent
    }
}
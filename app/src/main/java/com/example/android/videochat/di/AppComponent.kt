package com.example.android.videochat.di

import android.content.Context
import com.example.android.videochat.di.modules.*
import com.example.android.videochat.di.utils.holder.DiComponent
import com.example.android.videochat.presentation.callfeature.CallFragment
import com.example.android.videochat.presentation.startcallfeature.StartCallFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [IceServersModule::class, RtcClientModule::class, SignalServerModule::class,
        StartCallElmModule::class, CallElmModule::class]
)
interface AppComponent : DiComponent {
    fun injectStartCallFragment(fragment: StartCallFragment)
    fun injectCallFragment(fragment: CallFragment)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}
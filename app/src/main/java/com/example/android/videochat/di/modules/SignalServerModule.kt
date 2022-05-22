package com.example.android.videochat.di.modules

import android.content.Context
import com.example.android.videochat.data.CallRepositoryImpl
import com.example.android.videochat.data.signalserver.SignalServer
import com.example.android.videochat.data.signalserver.SignalServerImpl
import com.example.android.videochat.domain.CallRepository
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface SignalServerModule {
    @Binds
    @Singleton
    fun bindCallRepository(impl: CallRepositoryImpl): CallRepository

    @Binds
    @Singleton
    fun bindSignalServer(impl: SignalServerImpl): SignalServer

    companion object {
        @Provides
        @Singleton
        fun provideFirestore(context: Context): FirebaseFirestore {
            return FirebaseFirestore.getInstance()
        }
    }
}
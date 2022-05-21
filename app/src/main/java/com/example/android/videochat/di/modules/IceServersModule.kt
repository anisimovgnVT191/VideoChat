package com.example.android.videochat.di.modules

import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoSet
import org.webrtc.PeerConnection
import javax.inject.Singleton

@Module
class IceServersModule {
    @Provides
    @Singleton
    @IntoSet
    fun provideStunServerAtPort80(): PeerConnection.IceServer {
        return PeerConnection.IceServer.builder("stun:openrelay.metered.ca:80").createIceServer()
    }

    @Provides
    @Singleton
    @IntoSet
    fun provideTurnServerAtPort80(): PeerConnection.IceServer {
        return PeerConnection.IceServer.builder("turn:openrelay.metered.ca:80").apply {
            setUsername("openrelayproject")
            setPassword("openrelayproject")
        }.createIceServer()
    }

    @Provides
    @Singleton
    @IntoSet
    fun provideTurnServerAtPort443(): PeerConnection.IceServer {
        return PeerConnection.IceServer.builder("turn:openrelay.metered.ca:443").apply {
            setUsername("openrelayproject")
            setPassword("openrelayproject")
        }.createIceServer()
    }

    @Provides
    @Singleton
    @IntoSet
    fun provideTurnServerAtPort443Tcp(): PeerConnection.IceServer {
        return PeerConnection.IceServer.builder("turn:openrelay.metered.ca:443?transport=tcp").apply {
            setUsername("openrelayproject")
            setPassword("openrelayproject")
        }.createIceServer()
    }
}

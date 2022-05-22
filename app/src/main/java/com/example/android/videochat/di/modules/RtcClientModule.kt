package com.example.android.videochat.di.modules

import com.example.android.videochat.data.rtc.RtcClient
import com.example.android.videochat.data.rtc.RtcClientImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RtcClientModule {
    @Binds
    @Singleton
    fun bindRtcClient(impl: RtcClientImpl): RtcClient
}
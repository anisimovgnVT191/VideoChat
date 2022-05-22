package com.example.android.videochat.di.modules

import com.example.android.videochat.presentation.startcallfeature.elm.StartCallActor
import com.example.android.videochat.presentation.startcallfeature.elm.StartCallReducer
import com.example.android.videochat.presentation.startcallfeature.elm.models.StartCallCommand
import com.example.android.videochat.presentation.startcallfeature.elm.models.StartCallEffect
import com.example.android.videochat.presentation.startcallfeature.elm.models.StartCallEvent
import com.example.android.videochat.presentation.startcallfeature.elm.models.StartCallState
import dagger.Module
import dagger.Provides
import vivid.money.elmslie.core.ElmStoreCompat

@Module
class StartCallElmModule {
    @Provides
    fun provideStartCallStore(
        reducer: StartCallReducer,
        actor: StartCallActor
    ): ElmStoreCompat<StartCallEvent, StartCallState, StartCallEffect, StartCallCommand> {
        return ElmStoreCompat(
            initialState = StartCallState(),
            actor = actor,
            reducer = reducer
        )
    }
}
package com.example.android.videochat.di.modules

import com.example.android.videochat.presentation.callfeature.elm.CallActor
import com.example.android.videochat.presentation.callfeature.elm.CallReducer
import com.example.android.videochat.presentation.callfeature.elm.models.CallCommand
import com.example.android.videochat.presentation.callfeature.elm.models.CallEffect
import com.example.android.videochat.presentation.callfeature.elm.models.CallEvent
import com.example.android.videochat.presentation.callfeature.elm.models.CallState
import dagger.Module
import dagger.Provides
import vivid.money.elmslie.core.ElmStoreCompat

@Module
class CallElmModule {
    @Provides
    fun provideCallStore(
        actor: CallActor,
        reducer: CallReducer
    ): ElmStoreCompat<CallEvent, CallState, CallEffect, CallCommand> {
        return ElmStoreCompat(
            initialState = CallState(),
            reducer = reducer,
            actor = actor
        )
    }
}
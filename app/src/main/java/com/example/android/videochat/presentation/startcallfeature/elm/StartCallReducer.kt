package com.example.android.videochat.presentation.startcallfeature.elm

import com.example.android.videochat.presentation.startcallfeature.elm.models.StartCallCommand
import com.example.android.videochat.presentation.startcallfeature.elm.models.StartCallEffect
import com.example.android.videochat.presentation.startcallfeature.elm.models.StartCallEvent
import com.example.android.videochat.presentation.startcallfeature.elm.models.StartCallState
import vivid.money.elmslie.core.store.dsl_reducer.ScreenDslReducer
import javax.inject.Inject

class StartCallReducer @Inject constructor() :
    ScreenDslReducer<StartCallEvent, StartCallEvent.EventUI, StartCallEvent.EventInternal, StartCallState, StartCallEffect, StartCallCommand>(
        internalEventClass = StartCallEvent.EventInternal::class,
        uiEventClass = StartCallEvent.EventUI::class
    ) {
    override fun Result.internal(event: StartCallEvent.EventInternal): Any? {
        TODO("Not yet implemented")
    }

    override fun Result.ui(event: StartCallEvent.EventUI): Any? {
        TODO("Not yet implemented")
    }
}
package com.example.android.videochat.presentation.startcallfeature.elm

import com.example.android.videochat.presentation.models.UserType
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
    override fun Result.internal(event: StartCallEvent.EventInternal) = when (event) {
        is StartCallEvent.EventInternal.CallJoined -> {
            effects { +StartCallEffect.NavigateToCallScreen(event.callId, UserType.CALLEE) }
        }
        is StartCallEvent.EventInternal.CallStarted -> {
            effects { +StartCallEffect.NavigateToCallScreen(event.callId, UserType.CALLER) }
        }

        StartCallEvent.EventInternal.ErrorJoiningCall -> {

        }
        StartCallEvent.EventInternal.ErrorStartingCall -> {

        }
    }

    override fun Result.ui(event: StartCallEvent.EventUI) = when (event) {
        is StartCallEvent.EventUI.JoinCallButtonPressed -> {
            commands { +StartCallCommand.JoinCall(event.callId) }
        }
        is StartCallEvent.EventUI.StartCallButtonPressed -> {
            commands { +StartCallCommand.StartCall(event.callId) }
        }
    }
}
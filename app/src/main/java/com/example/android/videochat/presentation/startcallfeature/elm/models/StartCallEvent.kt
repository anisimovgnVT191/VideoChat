package com.example.android.videochat.presentation.startcallfeature.elm.models

sealed class StartCallEvent {
    sealed class EventUI : StartCallEvent() {
        class StartCallButtonPressed(val callId: String) : EventUI()
        class JoinCallButtonPressed(val callId: String) : EventUI()
    }

    sealed class EventInternal : StartCallEvent() {
        object ErrorStartingCall : EventInternal()
        object ErrorJoiningCall : EventInternal()

        class CallStarted(val callId: String): EventInternal()
        class CallJoined(val callId: String): EventInternal()

    }
}
